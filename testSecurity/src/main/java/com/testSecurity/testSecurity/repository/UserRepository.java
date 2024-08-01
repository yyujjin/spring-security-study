package com.testSecurity.testSecurity.repository;


import com.testSecurity.testSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //유저네임이 있다 ->t 없다 ->f
    boolean existsByUsername(String username);

}
