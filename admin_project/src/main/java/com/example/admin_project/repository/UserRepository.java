package com.example.admin_project.repository;

import com.example.admin_project.model.entity.User;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //한건에 대해 제일 최근데이터가 return
    //phonenumber where 절, ID로 Orderby DESC(역순)
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
