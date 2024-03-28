package com.ssgsakk.ssgdotcom.member.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.application.MailSendService;
import com.ssgsakk.ssgdotcom.member.dto.IdDuplicateCheckDto;
import com.ssgsakk.ssgdotcom.member.dto.PasswordChangeDto;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;

import com.ssgsakk.ssgdotcom.member.vo.*;
import com.ssgsakk.ssgdotcom.security.JWTFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MailSendService mailSendService;
    private final JWTFilter jwtFilter;

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
                .build();

        SignUpDto servicedSignUpDto = authService.signUp(signUpDto);

        return new BaseResponse<>("SignUp Success", SignUpResponseVo.builder()
                .userName(servicedSignUpDto.getUserName())
                .uuid(servicedSignUpDto.getUuid())
                .build());
    }

    @Operation(summary = "이메일 전송", description = "이메일 전송", tags = {"Email Send"})
    @PostMapping("/mail-send")
    public BaseResponse<Object> mailSend(@RequestBody @Valid EmailSendRequestVo emailSendRequestVo, @RequestHeader("Authorization") String accessToken) {
        String uuid = getUuid(accessToken);

        // 로그인이 되어있지 않은 경우에는 이메일 중복 체크, 이메일 전송 진행
        if (uuid == null) {
            // 이메일 중복 확인
            if (authService.duplicateChecked(emailSendRequestVo.getEmail())) {
                throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
            }
            // 이메일 인증 문자열 전송
            mailSendService.joinEmail(emailSendRequestVo.getEmail());
        }
        // 로그인이 되어 비밀번호 변경을 하는 경우, 이메일 전송 진행
        else {
            mailSendService.joinEmail(emailSendRequestVo.getEmail());
        }

        return new BaseResponse<>("이메일 발송", null);
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증", tags = {"Email Certification"})
    @PostMapping("/mail-check")
    public BaseResponse<Object> mailCheck(@RequestBody @Valid EmailCheckRequestVo emailCheckRequestVo) {
        boolean checked = mailSendService.checkAuthNum(emailCheckRequestVo.getEmail(), emailCheckRequestVo.getAuthNum());
        if (checked) {
            return new BaseResponse<>("인증 성공", null);
        } else {
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
        if (!checked) {
            return new BaseResponse<>("중복된 ID가 없습니다.", null);
        } else {
            throw new BusinessException(ErrorCode.DUPLICATE_ID);
        }
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경", tags = {"Password Change"})
    @PostMapping("/password-change")
    public BaseResponse<Object> passwordChange(@RequestBody passwordChangeRequestVo passwordChangeRequestVo
            , @RequestHeader("Authorization") String accessToken) {

        String uuid = getUuid(accessToken);

        PasswordChangeDto passwordChangeDto = PasswordChangeDto.builder()
                .password(passwordChangeRequestVo.getPassword())
                .uuid(uuid)
                .build();
        int checkd = authService.passwordChange(passwordChangeDto);
        if (checkd != 0) {
            return new BaseResponse<>("비밀번호가 변경되었습니다.", null);
        } else {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        try {
            uuid = jwtFilter.getUuid();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
        return uuid;
    }
}
