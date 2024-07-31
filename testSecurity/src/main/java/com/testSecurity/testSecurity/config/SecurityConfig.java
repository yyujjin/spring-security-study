package com.testSecurity.testSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //지정된 필터의 순서대로 필터적용
        //authorizeRequests
        //시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미합니다.
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","/login").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                // 그 외 나머지 리소스들은 무조건 인증을 완료해야 접근이 가능하다는 의미입니다.
                .anyRequest().authenticated()
        );

        //접근이 거부되면 로그인 페이지로 이동하는 설정
        //loginPage("/login")=> 괄호안에 내가 설정해둔 로그인 경로 입력하기
        //loginProcessingUrl
        //html form태그 action 경로를 적어주면 해당 경로를 스프링 시큐리티가 자동으로 특정한 시큐리티가 오픈되어서/
        //프론트단에서 데이터를 넘기면 시큐리티가 받아서 로그인처리를 진행
        httpSecurity.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc").permitAll()
        );

        //현재 csrf라는 사이트 미 변조 방지 설정이 스프링 시큐링티에는 자동으로 설정되어있음
        //이게 작동되면 포스트 요청 시 csrt토큰도 보내주어야 로그인이 진행됨
        //지금 현재 개발 환경에서는 토큰을 보내지 않으면 로그인이 진행되지 않기때문에 잠시 꺼둔다!
        httpSecurity.csrf((auth)->auth.disable());

        return httpSecurity.build();
    }



}
