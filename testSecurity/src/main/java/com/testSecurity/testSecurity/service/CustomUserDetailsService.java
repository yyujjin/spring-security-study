package com.testSecurity.testSecurity.service;

import com.testSecurity.testSecurity.dto.CustomUserDetails;
import com.testSecurity.testSecurity.entity.UserEntity;
import com.testSecurity.testSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // 스프링이 제공하는 서비스 UserDetailsService
    //상속받아서 오버라이드 해주면 됨
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //해당 메서드가 호출될 때 username 인자를 받는다. (앞단에서 사용자가 로그인을 하면 스프링 시큐리티가 검증을 위해 username을 넣어줌)
    //username을 가지고 db에서 조회하면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData =  userRepository.findByUsername(username);

        //null일 경우 저장된 아이디가 없다.
        //username = > 아이디
        if(userData != null) {
            return new CustomUserDetails(userData);
        }


        return null;
    }
}
