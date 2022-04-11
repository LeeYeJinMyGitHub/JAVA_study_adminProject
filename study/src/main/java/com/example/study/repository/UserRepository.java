package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Repository 인터페이스는 보통 앞의 클래스명(User) 뒤에 Repository를 붙여서 만듦
@Repository
//User는 엔티티, Long은 User의 기본키가 Id인데, Id의 자료형이 long이여서
public interface UserRepository extends JpaRepository<User, Long> {

    //select * from ueer where account = ? (String 받는 값)
    Optional<User> findByAccount(String account);
    Optional<User> findByEmail(String email);
    //select * from ueer where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}
