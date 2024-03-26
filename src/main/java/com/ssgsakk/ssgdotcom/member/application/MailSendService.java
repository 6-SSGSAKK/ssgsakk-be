package com.ssgsakk.ssgdotcom.member.application;


import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class MailSendService {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    //임의의 6자리 양수를 반환합니다.
    public static String makeRandomNumber() {
        Random r = new Random();
        StringBuilder randomNumber = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            randomNumber.append(Integer.toString(r.nextInt(10)));
        }
        return randomNumber.toString();
    }


    //mail을 전송 관련 설정
    public String joinEmail(String email) {
        String authNum = makeRandomNumber();
        String setFrom = "gravitypie@naver.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "나의 APP을 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + authNum + "입니다." +
                        "<br>" +
                        "인증번호를 제대로 입력해주세요";
        mailSend(setFrom, toMail, title, content);

        // redis 저장
        try {
            redisUtil.setData(email, authNum);
        } catch (BusinessException e){
            throw new BusinessException(ErrorCode.REDIS_SERVER_ERROR);
        }
        return authNum;
    }

    //이메일 전송
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();//JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");//이메일 메시지와 관련된 설정을 수행합니다.
            // true를 전달하여 multipart 형식의 메시지를 지원하고, "utf-8"을 전달하여 문자 인코딩을 설정
            helper.setFrom(setFrom);//이메일의 발신자 주소 설정
            helper.setTo(toMail);//이메일의 수신자 주소 설정
            helper.setSubject(title);//이메일의 제목을 설정
            helper.setText(content,true);//이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
            mailSender.send(message);


        } catch (MessagingException e) {
            throw new BusinessException(ErrorCode.MASSAGE_SEND_FAILED);
        }
    }

    // 이메일 코드 인증
    public boolean checkAuthNum(String email, String authNum) {

        if(redisUtil.getData(email).equals(authNum)){
            return true;
        }
        else{
            return false;
        }
    }
}