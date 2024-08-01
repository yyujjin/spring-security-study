package com.testSecurity.testSecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainP(Model model) {

        //원래는 서비스단을 생성해서 작업해야 함

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        //세션 현재 사용자 role
        //role에 대한 검증 로직을 만들 수 있음
        //예를 들어 글 삭제 로직이 있을 때 이 로직을 불러와서 role 값이 admin인지 확인하고 맞다면 삭제하는 로직 작성할 수 있음
        //t or f 로 보내주는 서비스단을 작성할 수 있음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();


        model.addAttribute("id",id);
        model.addAttribute("role",role);

        return "main";
    }
}
