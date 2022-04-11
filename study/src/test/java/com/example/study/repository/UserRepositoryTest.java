package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    //repository 이용해서 crud 테스트 할 것임
    //Autowired : DI(dependency Injection) 의존성 주입, 객체를 직접 만들지 않고 이 객체를 스프링이 관리하게 하겠다
    @Autowired
    //Autowired 사용으로 인해 객체 생성이 필요없음 (UserRepository userRepository = new UserRepository());
    private UserRepository userRepository;

    //테스트기 때문에 테스트 어노테이션
    @Test
    public void create(){
        //User를 Autowired로 관리하지 않는 이유 ; DI의 기본 핵심은 싱글톤이기 때문에
        User user = new User();
        user.setAccount("TestUser02");
        user.setEmail("eyj1013@naver.com");
        user.setPhoneNumber("010-1234-1234");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        //read 할 때는 userRepository에서 제공하는 메서드를 이용
        //2L 인 이유는 UserID의 타입이 long 타입이어서
        Optional<User> user = userRepository.findByAccount("TestUser02");

        //Optional은 있을수도 있고 없을수도 있음, selectUser가 Optional에 들어 있을때만 실행하겠다는 의미로 ifPresent
        //각각의 detail이 가지고 있는 ItemID를 출력함
        user.ifPresent(selectUser ->{
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(detail.getItem());
            });
        });
    }

    @Test
    public void update(){
        //업데이트를 하기 위해서는 특정 데이터를 먼저 SELECT 부터 해야되기 때문에
        Optional<User> user = userRepository.findById(2L);

        //User의 계정을 PPPP로 변경함, update 했기떄문에 Update 시간과 by에 데이터를 넣어줌
        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    //Transactional : 실행결과는 나오되, DB에서 실제적인 동작은 일어나지 않음
    @Transactional
    public void delete(){
        //delete를 하기 위해서는 특정 데이터를 먼저 SELECT 부터 해야되기 때문에
        Optional<User> user = userRepository.findById(4L);

        //3번 데이터를 SELECT 했을떄 반드시 존재해야 하기 때문에
        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(4L);

        Assertions.assertFalse(deleteUser.isPresent()); //false

    }
}