package com.ssgsakk.ssgdotcom.member.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.application.MailSendService;
import com.ssgsakk.ssgdotcom.member.dto.IdDuplicateCheckDto;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;

import com.ssgsakk.ssgdotcom.member.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MailSendService mailSendService;

    @Operation(summary = "로그인", description = "로그인", tags = {"User SignIn"})
    @PostMapping("/signin")
    public BaseResponse<SignInResponseVo> signIn(@RequestBody SignInRequestVo signInRequestVo) {

        SignInDto signInDto = SignInDto.builder()
                .userId(signInRequestVo.getUserId())
                .userPassword(signInRequestVo.getUserPassword())
                .build();

        SignInDto servicedSignInDto = authService.signIn(signInDto);

        return new BaseResponse<>("SignIn Success", SignInResponseVo.builder()
                .userName(servicedSignInDto.getUserName())
                .uuid(servicedSignInDto.getUuid())
                .token(servicedSignInDto.getToken())
                .build());
    }

    @Operation(summary = "회원가입", description = "회원가입", tags = {"User SignUp"})
    @PostMapping("/signup")
    public BaseResponse<SignUpResponseVo> signUp(@RequestBody SignUpRequestVo signUpRequestVo) {

        SignUpDto signUpDto = SignUpDto.builder()
                .userId(signUpRequestVo.getUserId())
                .userPassword(signUpRequestVo.getUserPassword())
                .userName(signUpRequestVo.getUserName())
                .userEmail(signUpRequestVo.getUserEmail())
                .userMobileNum(signUpRequestVo.getUserMobileNum())
                .detailAddress(signUpRequestVo.getDetailAddress())
                .jibunAddress(signUpRequestVo.getJibunAddress())
                .roadAddress(signUpRequestVo.getRoadAddress())
                .zipCode(signUpRequestVo.getZipCode())
                .build();

        SignUpDto servicedSignUpDto = authService.signUp(signUpDto);

        return new BaseResponse<>("SignUp Success", SignUpResponseVo.builder()
                .userName(servicedSignUpDto.getUserName())
                .uuid(servicedSignUpDto.getUuid())
                .build());
    }

    @Operation(summary = "이메일 전송", description = "이메일 전송", tags = {"Email Send"})
    @PostMapping("/mail-send")
    public BaseResponse<Object> mailSend(@RequestBody @Valid EmailSendRequestVo emailSendRequestVo) {
        // 이메일 중복 확인
        if(authService.duplicateChecked(emailSendRequestVo.getEmail())){
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 이메일 인증 문자열 전송
        String authNum = mailSendService.joinEmail(emailSendRequestVo.getEmail());
        return new BaseResponse<>("이메일 발송", null);
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증", tags = {"Email Certification"})
    @PostMapping("/mail-check")
    public BaseResponse<Object> mailCheck(@RequestBody @Valid EmailCheckRequestVo emailCheckRequestVo) {
        boolean checked = mailSendService.checkAuthNum(emailCheckRequestVo.getEmail(), emailCheckRequestVo.getAuthNum());
        if(checked){
            return new BaseResponse<>("인증 성공", null);
        }
        else{
            throw new BusinessException(ErrorCode.MASSAGE_VALID_FAILED);
        }
    }

    @Operation(summary = "아이디 중복 확인", description = "아이디 중복 확인", tags = {"Id Duplicate Check"})
    @PostMapping("/id-duplicate-check")
    public BaseResponse<Object> idDuplicateCheck(@RequestBody IdDuplicateCheckRequestVo idDuplicateCheckRequestVo) {
        IdDuplicateCheckDto idDuplicateCheckDto = IdDuplicateCheckDto.builder()
                .inputId(idDuplicateCheckRequestVo.getInputId())
                .build();
        boolean checked = authService.idDuplicateCheck(idDuplicateCheckDto);
        if(!checked){
            return new BaseResponse<>("중복된 ID가 없습니다.", null);
        }
        else{
            throw new BusinessException(ErrorCode.DUPLICATE_ID);
        }
    }
}
