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

        //필수적으로 꼭 해야할 것!
        //db에 이미 동일한 username을 가진 회원이 존재하는지?



        //넘어온 dto를 entity로 변경시켜야함
        //아무 데이터도 들어있지 않은 빈 객체를 생성한다.
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        //비밀번호의 경우 필수적으로 암호화를 진행하여 db에 넣어줘야 함

        //비크립트패스워드엔코더가 제공하는 encode라는 메서드를 통해서 joinDTO의 비밀번호 데이터를 꺼낸 후 encode 시켜서
        //암호화 시켜 패스워드를 세팅한다.
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));


        //회원가입 진행 시 회원 자신이 특정한 롤값을 설정 할 수 없기에
        //서비스단에서 롤값을 강제로 명시적으로 넣어줘야 함
        //넣는 방법 : role이라는 접두사를 붙여주고 + 언더바 +  유저에 알맞는 role 적어주기
        data.setRole("ROLE_USER");

        //주입받은 걸 통해 최종적으로 넘어온 데이터를 insert할 수 있는 orm쿼리를 만든다.
        userRepository.save(data);

    }
}
