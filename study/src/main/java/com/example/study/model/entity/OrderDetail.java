package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;
    //OrderDetail 입장에서는 자기 자신은 N : User는 1 (N:1),OrderDetail 클래스이기 때문에 OD 입장에서 생각해야 함
    @ManyToOne
    //user의 타입이 Long이었는데 @ManyToOne 어노테이션 붙이면서 User 타입으로 변경해줌,
    //또, 객체 이름으로 바꿔야함 user_id -> user로 변경함함    private User user;  //user를 Hibernate에서 알아서 user_id로 변환해줌
    private User user;
    @ManyToOne
    private Item item;
}
