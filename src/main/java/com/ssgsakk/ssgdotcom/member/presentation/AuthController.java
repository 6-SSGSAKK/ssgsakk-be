package com.ssgsakk.ssgdotcom.member.presentation;

import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;
import com.ssgsakk.ssgdotcom.member.vo.SignInRequestVo;
import com.ssgsakk.ssgdotcom.member.vo.SignInResponseVo;
import com.ssgsakk.ssgdotcom.member.vo.SignUpRequestVo;
import com.ssgsakk.ssgdotcom.member.vo.SignUpResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "로그인", tags = {"User SignIn"})
    @PostMapping("signin")
    public ResponseEntity<SignInResponseVo> signIn(@RequestBody SignInRequestVo signInRequestVo) {

        SignInDto signInDto = SignInDto.builder()
                .userId(signInRequestVo.getUserId())
                .userPassword(signInRequestVo.getUserPassword())
                .build();

        System.out.println("signInDto >>> " + signInDto.toString());
        signInDto = authService.signIn(signInDto);

        System.out.println(signInDto.toString());

        // 로그인 계정이 있는 경우
        // uuid를 통해 계정 유무 확인
        if (!(signInDto.getUuid() == null || signInDto.getUuid().isEmpty())) {
            SignInResponseVo signInResponseVo = SignInResponseVo.builder()
                    .userName(signInDto.getUserName())
                    .uuid(signInDto.getUuid())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(signInResponseVo);
        }
        // 계정이 없는 경우
        System.out.println("account not found!!!!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(SignInResponseVo.builder()
                .build());
    }

    @Operation(summary = "회원가입", description = "회원가입", tags = {"User SignUp"})
    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestVo signUpRequestVo) {

        SignUpDto signUpDto = SignUpDto.builder()
                .userId(signUpRequestVo.getUserId())
                .userPassword(signUpRequestVo.getUserPassword())
                .userName(signUpRequestVo.getUserName())
                .userEmail(signUpRequestVo.getUserEmail())
                .userPhoneNum(signUpRequestVo.getUserPhoneNum())
                .userMobileNum(signUpRequestVo.getUserMobileNum())
                .build();

        signUpDto = authService.signUp(signUpDto);

        SignUpResponseVo signUpResponseVo = SignUpResponseVo.builder()
                .userName(signUpDto.getUserName())
                .uuid(signUpDto.getUuid())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(signUpResponseVo);

    }
}
