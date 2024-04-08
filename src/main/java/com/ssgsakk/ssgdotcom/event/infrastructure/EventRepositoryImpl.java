package com.ssgsakk.ssgdotcom.event.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.event.domain.Event;
import com.ssgsakk.ssgdotcom.event.domain.QEvent;
import com.ssgsakk.ssgdotcom.event.domain.QEventProduct;
import com.ssgsakk.ssgdotcom.event.dto.EventDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepositoryImpl extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QEvent qEvent = QEvent.event;
    private final QEventProduct qEventProduct = QEventProduct.eventProduct;

    public EventRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(EventRepositoryImpl.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Integer findMinPriceByEvent(Event event) {

        return jpaQueryFactory.select(qEventProduct.product.productPrice.min())
                .from(qEventProduct)
                .where(qEventProduct.event.eq(event))
                .fetchOne();
    }

    public String findEventVendor(Event event) {

        return jpaQueryFactory.select(qEventProduct.product.vendor.vendorName)
                .from(qEventProduct)
                .where(qEventProduct.event.eq(event))
                .limit(1)
                .fetchOne();
    }

    public List<Event> getEvent(EventDto eventDto){
        return jpaQueryFactory
                .select(qEvent)
                .from(qEvent)
                .where(eqEventDeliveryType(eventDto.getDeliveryType())
                        ,eqEventCategory(eventDto.getCategorySeq()))
                .orderBy(qEvent.eventStartDate.asc())
                .fetch();
    }

    private BooleanExpression eqEventDeliveryType(DeliveryType deliveryType) {
        if(deliveryType == null) {
            return null;
        }
        return qEvent.deliveryType.eq(deliveryType);
    }

    private BooleanExpression eqEventCategory(Long categorySeq){
        if (categorySeq == null) {
            return null;
        }
        return  qEvent.category.categorySeq.eq(categorySeq);
    }
}
