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
        return httpSecurity.build();
    }
}
