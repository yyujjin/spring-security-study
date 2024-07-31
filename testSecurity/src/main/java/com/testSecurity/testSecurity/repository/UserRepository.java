package com.testSecurity.testSecurity.repository;


import com.testSecurity.testSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {


}
