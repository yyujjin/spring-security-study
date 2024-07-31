package com.testSecurity.testSecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    //가장 필수적인 회원 검증을 위한 로직을 만들기 위해서는 3개의 값은 필수적으로 들어가야함.
    private String username;
    private String password;

    //로그인 진행한 사용자가 권한이 나뉘게 됨 (어드민, 일반유저)
    //권한을 저장할 role 값을 필수적으로 넣어야함. 
    private String role;

}
