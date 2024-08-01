package com.testSecurity.testSecurity.repository;


import com.testSecurity.testSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository를 사용하면  Spring Data JPA는 런타임에 자동으로 CRUD 작업 및 쿼리 메서드를 구현해줍니다.
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //유저네임이 있다 ->t 없다 ->f
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
