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
        //회원가입시 모든 유저에 대해 그냥 유저라는 권한을 주기 위해 일시적으로 ADMIN이라는 권한으로 변경해줌
        //회원가입을 진행하면 로그인이 되고 어드민으로 접근 가능한지 테스트해보기
        data.setRole("ROLE_ADMIN");
        //db에 넣는 코드
        userRepository.save(data);

    }
}
