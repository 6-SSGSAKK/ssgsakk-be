package com.ssgsakk.ssgdotcom.member.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.application.MailSendService;
import com.ssgsakk.ssgdotcom.member.dto.*;

import com.ssgsakk.ssgdotcom.member.vo.*;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
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
    private final JWTUtil jwtUtil;

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
                .build());
    }

    @Operation(summary = "이메일 전송", description = "이메일 전송", tags = {"Email Send"})
    @PostMapping("/mail-send")
    public BaseResponse<Object> mailSend(@RequestBody @Valid EmailSendRequestVo emailSendRequestVo) {
        authService.duplicateChecked(emailSendRequestVo.getEmail());
        mailSendService.joinEmail(emailSendRequestVo.getEmail());
        return new BaseResponse<>("이메일 발송", null);
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증", tags = {"Email Certification"})
    @PostMapping("/mail-check")
    public BaseResponse<Object> mailCheck(@RequestBody @Valid EmailCheckRequestVo emailCheckRequestVo) {
        mailSendService.checkAuthNum(emailCheckRequestVo.getEmail(), emailCheckRequestVo.getAuthNum());
        return new BaseResponse<>("인증 성공", null);
    }

    @Operation(summary = "아이디 중복 확인", description = "아이디 중복 확인", tags = {"Id Duplicate Check"})
    @PostMapping("/id-duplicate-check")
    public BaseResponse<Object> idDuplicateCheck(@RequestBody IdDuplicateCheckRequestVo idDuplicateCheckRequestVo) {
        IdDuplicateCheckDto idDuplicateCheckDto = IdDuplicateCheckDto.builder()
                .inputId(idDuplicateCheckRequestVo.getInputId())
                .build();

        authService.idDuplicateCheck(idDuplicateCheckDto);
        return new BaseResponse<>("중복된 ID가 없습니다.", null);
    }

    @Operation(summary = "비밀번호 확인에 사용되는 이메일 전송", description = "비밀번호 확인에 사용되는 이메일 전송", tags = {"Email Send For Password Change"})
    @GetMapping("/mail-send-password-change")
    public BaseResponse<Object> mailSendForPasswordChange(@RequestHeader("Authorization") String accessToken) {
        String uuid = getUuid(accessToken);

        // 로그인이 되어 비밀번호 변경을 하는 경우, 이메일 전송 진행
        String email = authService.findByUuid(uuid);
//        log.info("email >>>>> {} ", email);
        mailSendService.joinEmail(email);

        return new BaseResponse<>("이메일 발송", null);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경", tags = {"Password Change"})
    @PostMapping("/password-change")
    public BaseResponse<Object> passwordChange(@RequestBody PasswordChangeRequestVo passwordChangeRequestVo
            , @RequestHeader("Authorization") String accessToken) {

        String uuid = getUuid(accessToken);

        PasswordChangeDto passwordChangeDto = PasswordChangeDto.builder()
                .password(passwordChangeRequestVo.getPassword())
                .uuid(uuid)
                .build();
        int checkd = authService.passwordChange(passwordChangeDto);

        // 서비스 로직에서 처리할 것!
        if (checkd != 0) {
            return new BaseResponse<>("비밀번호가 변경되었습니다.", null);
        } else {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "회원 정보 조회", description = "회원 정보 조회", tags = {"Look Up Member Information"})
    @GetMapping("/user-info")
    public BaseResponse<Object> userInfo(@RequestHeader("Authorization") String accessToken) {
        String uuid = getUuid(accessToken);
        UserInforDto userInforDto = authService.userInfor(uuid);

        return new BaseResponse<>("회원 정보 조회", UserInforResponseVo.builder()
                .userName(userInforDto.getUserName())
                .userId(userInforDto.getUserId())
                .userEmail(userInforDto.getUserEmail())
                .userMobileNum(userInforDto.getUserMobileNum())
                .build());
    }

    @Operation(summary = "전화번호 변경", description = "전화번호 변경", tags = {"Mobile Number Update"})
    @PatchMapping("/mobile-change")
    public BaseResponse<Object> mobileNumChange(@RequestBody MobileNumChangeRequestVo mobileNumChangeRequestVo, @RequestHeader("Authorization") String accessToken) {
        String uuid = getUuid(accessToken);

        try {
            authService.mobileNumChange(MobileNumChangeDto.builder()
                    .mobileNum(mobileNumChangeRequestVo.getMobileNum())
                    .uuid(uuid)
                    .build());

            return new BaseResponse<>("전화번호 변경", null);
        }
        // 서비스 쪽에 예외처리하기!
        catch (Exception e) {
            throw new BusinessException(ErrorCode.DUPLICATE_MOBILE_NUM);
        }
    }


    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        uuid = jwtUtil.getUuid(jwt.split(" ")[1]);
        checkUuid(uuid);
        return uuid;
    }

    // UUID 확인
    // 정상이면 true 반환
    public void checkUuid(String uuid) {
        if (uuid == null) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
}
