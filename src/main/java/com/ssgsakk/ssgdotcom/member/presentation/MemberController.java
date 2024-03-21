package com.ssgsakk.ssgdotcom.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {

    @GetMapping("/my")
    @ResponseBody
    public String test(){
        return "OAUTH YEAH";
    }
}
