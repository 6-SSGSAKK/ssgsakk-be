package com.ssgsakk.ssgdotcom.member.application;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.member.domain.OAuth;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.dto.*;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.member.infrastructure.OAuthRepository;
import com.ssgsakk.ssgdotcom.oauth2.ProviderName;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // DB 접근을 위해 MemberRepository 접근
    private final MemberRepository memberRepository;
    private final OAuthRepository oAuthRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository, OAuthRepository oAuthRepository) {
        this.memberRepository = memberRepository;
        this.oAuthRepository = oAuthRepository;
    }

    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 제공 회사 판별
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 응답을 저장한 바구니 미리 생성
        OAuth2Response oAuth2Response = null;


        // 각 제공 회사별 처리
        String providerName;
        if (registrationId.contains("naver")) {
            providerName = ProviderName.NAVER.getProviderName();
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.contains("google")) {
            providerName = ProviderName.GOOGLE.getProviderName();
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }


        // providerId 를 통해 OAuth 엔티티에 존재하는 계정인지 확인
        String providerId = oAuth2Response.getProviderId();
        String email = oAuth2Response.getEmail();
        String name = oAuth2Response.getName();

        OAuth existData = oAuthRepository.findByOauthIdAndOauthType(providerId, registrationId);

        OAuthDto oAuthDto = new OAuthDto();
        oAuthDto.setEmail(email);
        oAuthDto.setName(oAuth2Response.getName());
        oAuthDto.setUuid("uuid");
        oAuthDto.setRole("OAUTH2USER");
        oAuthDto.setOauthId(oAuth2Response.getProviderId());

        return new CustomOAuth2User(oAuthDto);

//        if (existData == null) {
//
//            User userExistData = memberRepository.findByUserEmail(email);
//
//            // 동일 이메일이 회원 테이블에 없으면 oAuth2Response에서 뽑은 이름과 이메일, UUID를 저장한다.
//            if(userExistData == null) {
//                String uuid = UUID.randomUUID().toString();
//                User user = User.builder()
//                        .userEmail(email)
//                        .uuid(uuid)
//                        .name(name)
//                        .state(1)
//                        .build();
//
//                // 저장
//                memberRepository.save(user);
//
//                // user 데이터의 user_seq, providerId, oauthType를 oauth 테이블에 저장
//                // user_seq 추출
//                long userSeq = memberRepository.findByEmail(email);
//
//                // OAuth 테이블에 저장
//                OAuth oAuth = OAuth.builder()
//                        .user(user)
//                        .oauthId(oAuth2Response.getProviderId())
//                        .oauthType(providerName)
//                        .build();
//
//                oAuthRepository.save(oAuth);
//
//                // 바구니 생성
//                OAuthDto oAuthDto = new OAuthDto();
//                oAuthDto.setEmail(email);
//                oAuthDto.setName(oAuth2Response.getName());
//                oAuthDto.setUuid(uuid);
//                oAuthDto.setRole("OAUTH2USER");
//                oAuthDto.setOauthId(oAuth2Response.getProviderId());
//
//                return new CustomOAuth2User(oAuthDto);
//            }
//
//            // 동일 이메일이 있으면 해당 이메일의 user_seq를 가지고 oauth 테이블에 추가
//            else {
//                // state 1로 수정
//                memberRepository.updateState(1, email);
//
//                OAuth oAuth = OAuth.builder()
//                        .oauthId(providerId)
//                        .oauthType(providerName)
//                        .user(userExistData)
//                        .build();
//
//                oAuthRepository.save(oAuth);
//
//                // 바구니 생성
//                OAuthDto oAuthDto = new OAuthDto();
//                oAuthDto.setEmail(email);
//                oAuthDto.setName(userExistData.getName());
//                oAuthDto.setUuid(userExistData.getUuid());
//                oAuthDto.setRole("OAUTH2USER");
//                oAuthDto.setOauthId(oAuth2Response.getProviderId());
//
//                return new CustomOAuth2User(oAuthDto);
//            }
//        }
//
//        // providerId에 관련된 OAuth 객체 존재하는 경우
//        // 바로 JWT 생성 쪽으로 넘어간다.
//        else {
//
//            // 회원 정보를 얻어오기 위해 User 객체를 추출
//            User user = existData.getUser();
//
//            // 바구니 생성
//            OAuthDto oAuthDto = new OAuthDto();
//            oAuthDto.setEmail(user.getUserEmail());
//            oAuthDto.setName(user.getName());
//            oAuthDto.setUuid(user.getUuid());
//            oAuthDto.setRole("OAUTH2USER");
//
//            return new CustomOAuth2User(oAuthDto);
//        }
    }
}
