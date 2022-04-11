package com.example.admin_project.repository;

import com.example.admin_project.AdminProjectApplication;
import com.example.admin_project.model.entity.Category;
import com.example.admin_project.model.entity.User;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest extends AdminProjectApplication {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account="Test03";
        String password = "Test03";
        String status = "registered";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-3333-1234";
        LocalDateTime registeredAt = LocalDateTime.now();
        //LocalDateTime createdAt = LocalDateTime.now();
        //String createdBy = "adminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        //user.setCreatedAt(createdAt);
        //user.setCreatedBy(createdBy);

        //Builder 패턴 테스트
        //builder() 호출 후 제일 마지막에 build로 끝내야함
        //생성자를 직접 만들 필요없이 넣고싶은 컬럼에만 값을 지정할 수있음
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .phoneNumber("010-2134-4789")
                .build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

        User newu = userRepository.save(u);
        Assertions.assertNotNull(newu);

    }

    @Transactional
    @Test
    public void read(){

        String phoneNumber="010-2134-4789";
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);

        if (user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("---------주문 묶음------------");
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("총 수량 : " + orderGroup.getTotalQuantity());
                System.out.println("총 금액 : " + orderGroup.getTotalPrice());

                System.out.println("----------주문 상세-----------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " +orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " +orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자: " + orderDetail.getArrivalDate());
                });
                System.out.println();
            });
        }
        Assertions.assertNotNull(user);
    }

    @Test
    public void update(){

        String phoneNumber="010-2022-1234";
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);

        user
                .setEmail("eyjeyj@gmail.com")
                .setStatus("status")
                .setPhoneNumber("010-2988-0996");

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }
}
