package com.testSecurity.testSecurity.controller;

import com.testSecurity.testSecurity.dto.JoinDTO;
import com.testSecurity.testSecurity.service.JoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    private static final Logger log = LoggerFactory.getLogger(JoinController.class);

   private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }


    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    //원래는 회원가입이 완료되면 로그인 페이지로 이동
    //실패하면 조인 페이지로 이동하도록 세팅해야하지만
    //지금은 기본적인 설정이기에 로그인 페이지로 바로 리다이레트 시키도록 한다.
    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        log.info("넘어온 dto : {}",joinDTO);

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
