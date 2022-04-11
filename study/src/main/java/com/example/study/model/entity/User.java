package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//객체 생성, 기본 생성자, get/set 메서드 생성
@Data
//모든 매개변수를 가지는 생성자 생성
@AllArgsConstructor
//기본생성자를 만드는 어노테이션
@NoArgsConstructor
@Entity //==table
//이것은 엔티티이면서, Table의 이름은 user라고 명시해줌, table이름과 class 이름이 모두 user이면 명시가 굳이 필요없음
//@Table(name = "user")
public class User {

    //식별자에 대해서는 @Id 붙여줌
    @Id
    //어떤 전략으로 관리할 것인지 설정, Mysql 사용하기 때문에 IDENTITY 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    //자바에서는 카멜케이스, DB에서는 snake 케이스로 선언해서 다르지만, JPA에서는 자동으로 매칭해줌
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    //User는 자신은 1이지만 OrderDetail은 N임
    //user : 어떤 변수에 매핑시켜줄 것인지, orderDetail의 user
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;

}
