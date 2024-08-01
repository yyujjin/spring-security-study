package com.testSecurity.testSecurity.service;

import com.testSecurity.testSecurity.dto.JoinDTO;
import com.testSecurity.testSecurity.entity.UserEntity;
import com.testSecurity.testSecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    //userentity 테이블에 접근하기 위해 UserRepository 연결해야함
    private final UserRepository userRepository;
    // //생성한 암호화 객체 불러옴
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void joinProcess(JoinDTO joinDTO) {

        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return;
        }


        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");
        userRepository.save(data);

    }
}
