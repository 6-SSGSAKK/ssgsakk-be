package com.ssgsakk.ssgdotcom.member.application;

import com.ssgsakk.ssgdotcom.member.domain.OAuth;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.dto.*;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.member.infrastructure.OAuthRepository;
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

        // providerId 를 통해 OAuth 엔티티에 존재하는 계정인지 확인
        String providerId = oAuth2Response.getProviderId();
        String email = oAuth2Response.getEmail();
        String name = oAuth2Response.getName();

        OAuth existData = null;
        existData = oAuthRepository.findByOauthId(providerId);

        // 존재하지 않는 경우
        // User 테이블에 이메일과 일치하는 데이터 확인(userExistData)

        if (existData == null) {

            User userExistData = null;
            userExistData = memberRepository.findByUserEmail(email);

            // 동일 이메일이 회원 테이블에 없으면 oAuth2Response에서 뽑은 이름과 이메일, UUID를 저장한다.
            if(userExistData == null) {
                String uuid = UUID.randomUUID().toString();
                User user = User.builder()
                        .userEmail(email)
                        .uuid(uuid)
                        .name(name)
                        .state(1)
                        .build();

                // 저장
                memberRepository.save(user);
                System.out.println("user save >>> " + user.toString());
                // user 데이터의 user_seq, providerId, oauthType를 oauth 테이블에 저장
                // user_seq 추출
                long userSeq = memberRepository.findByEmail(email);
                System.out.println("userseq >>>> " + userSeq);
                // OAuth 테이블에 저장
                OAuth oAuth = OAuth.builder()
                        .user(user)
                        .oauthId(providerId)
                        .oauthType(registrationId)
                        .build();

                oAuthRepository.save(oAuth);
                System.out.println("oauth save >>>> " + oAuth.toString());


                // 바구니 생성
                OAuthDto oAuthDto = new OAuthDto();
                oAuthDto.setEmail(email);
                oAuthDto.setName(oAuth2Response.getName());
                oAuthDto.setUuid(uuid);
                oAuthDto.setRole("OAUTH2USER");

                return new CustomOAuth2User(oAuthDto);
            }

            // 동일 이메일이 있으면 해당 이메일의 user_seq를 가지고 oauth 테이블에 추가
            else {
                // state 1로 수정
                memberRepository.updateState(1, email);
                OAuth oAuth = OAuth.builder()
                        .oauthId(providerId)
                        .oauthType(registrationId)
                        .user(userExistData)
                        .build();

                oAuthRepository.save(oAuth);

                // 바구니 생성
                OAuthDto oAuthDto = new OAuthDto();
                oAuthDto.setEmail(email);
                oAuthDto.setName(userExistData.getName());
                oAuthDto.setUuid(userExistData.getUuid());
                oAuthDto.setRole("OAUTH2USER");

                return new CustomOAuth2User(oAuthDto);
            }


            // userExistData 있으면 일반 회원가입 후, 소셜 로그인을 했다는 의미
            // userExistData의 state 1로 수정
            // userExistData의 user_seq, 이름 추출하고 providerId, oauthType를 oauth 엔티티로 만들어서 oauth 테이블에 저장






//            User member = new User();
//            String uuid = UUID.randomUUID().toString();
//
//            System.out.println("name >>> " + oAuth2Response.getName());
//            System.out.println("user_email >>> " + oAuth2Response.getEmail());
//            User member = User.builder()
//                    .name(oAuth2Response.getName())
//                    .userEmail(oAuth2Response.getEmail())
//                    .uuid(uuid)
//                    .state(1)
//                    .build();

            // 이름과 이메일 데이터를 가진 회원 엔티티를 저장
//            memberRepository.save(member);
//            System.out.println("member save!");
//
//            // OAuth 테이블에 데이터 저장
//            OAuth oAuth = OAuth.builder()
//                    // 이러면 user_seq는 자동으로 들어가나?
//                    .user(member)
//                    .oauthType(registrationId)
//                    .oauthId(providerId)
//                    .build();
//
//            oAuthRepository.save(oAuth);
//            System.out.println("oAuth save!");
//
//
//            // CustomOAuth2User로 전달할 DTO 생성
//            OAuthDto oAuthDto = new OAuthDto();
//            oAuthDto.setEmail(oAuth2Response.getEmail());
//            oAuthDto.setRole("OAUTH2USER");
//            oAuthDto.setUuid(uuid);
//            oAuthDto.setName(oAuth2Response.getName());

//            return new CustomOAuth2User(oAuthDto);
        }

        // 존재하는 경우
        // 간편 로그인 테이블에 연결을 진행한다.
        else {

            //TODO
            // 간편 로그인 테이블 연결 로직


            // state 갱싱
            User member = new User();
//            member.builder()
//                    .name(existData.getName())
//                    .userEmail(existData.getUserEmail())
//                    .uuid(existData.getUuid())
//                    .
//                    .state(1)
//                    .build();

            // uuid 가져오기
//            String uuid = existData.getUuid();
//
//            OAuthDto oAuthDto = new OAuthDto();
//            oAuthDto.setEmail(existData.getUserEmail());
//            oAuthDto.setRole("ALLUSER");
//            oAuthDto.setUuid(uuid);
//            oAuthDto.setName(existData.getName());
//
//            return new CustomOAuth2User(oAuthDto);
        }
        return null;
    }
}
