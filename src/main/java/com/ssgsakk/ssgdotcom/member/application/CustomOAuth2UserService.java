package com.ssgsakk.ssgdotcom.member.application;

import com.ssgsakk.ssgdotcom.member.domain.Member;
import com.ssgsakk.ssgdotcom.member.dto.*;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
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
    public CustomOAuth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User >>> " + oAuth2User);

        // 제공 회사 판별
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 응답을 저장한 바구니 미리 생성
        OAuth2Response oAuth2Response = null;

        // 각 제공 회사별 처리
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }


        // 이메일을 통해 기존 유저 확인
        String userEmail = oAuth2Response.getEmail();
        System.out.println("OAuth2 Email >> " + userEmail);

        // Member가 DB 접근해서 있는 지 확인해야 한다.
        // 본 프로젝트에서는 email
        Member existData = memberRepository.findByUserEmail(userEmail);
        System.out.println("member >>> " + existData.toString());

        // 존재하지 않는 경우
        // 회원 테이블에 이름과 이메일, UUID, state(1 = 소셜로그인 유저)를 새롭게 추가하고 간편 로그인 테이블에 추가해줘야 한다.
        // 간편 로그인 테이블 처리는 추후에 진행

        if (existData == null) {
            Member member = new Member();
            member.builder()
                    .name(existData.getName())
                    .userEmail(existData.getUserEmail())
                    .uuid(UUID.randomUUID().toString())
                    .state(1)
                    .build();

            // 이름과 이메일 데이터를 가진 회원 엔티티를 저장
            memberRepository.save(member);

            // CustomOAuth2User로 전달할 DTO 생성
            OAuthDto oAuthDto = new OAuthDto();
            oAuthDto.setEmail(existData.getUserEmail());
            oAuthDto.setRole("OAUTHUSER");
            oAuthDto.setName(existData.getName());

            return new CustomOAuth2User(oAuthDto);
        }

        // 존재하는 경우
        // 간편 로그인 테이블에 연결을 진행한다.
        else {

            // 간편 로그인 테이블 연결 로직


            OAuthDto oAuthDto = new OAuthDto();
            oAuthDto.setEmail(existData.getUserEmail());
            oAuthDto.setRole("ALLUSER");
            oAuthDto.setName(existData.getName());

            return new CustomOAuth2User(oAuthDto);
        }
    }
}
