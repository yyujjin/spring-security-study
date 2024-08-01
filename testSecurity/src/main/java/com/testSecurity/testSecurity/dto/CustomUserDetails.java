package com.testSecurity.testSecurity.dto;

import com.testSecurity.testSecurity.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;

    }

    //사용자의 특정한 권한에 대해 리턴해줌
    //role 값에 대해 반환하는 메서드
    //위 3개는 중요한 메서드랑 많이 쓰고
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }




    //db에 해당 데이터를 저장하는게 없으니 그냥 강제로 만료되지 않았다라고 해주기 위해 true로 리턴값 바꿔줌
    //ture 로 설정해주면 계정이 잠기지 않았다고 판단하여 계속 사용할 수 있음
    //난 이번엔 사용 안할거임

    //사용자의 아이디가 만료 되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //잠겨있는지 체크
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //지금 사용가능한지 체크
    @Override
    public boolean isEnabled() {
        return true;
    }
}
