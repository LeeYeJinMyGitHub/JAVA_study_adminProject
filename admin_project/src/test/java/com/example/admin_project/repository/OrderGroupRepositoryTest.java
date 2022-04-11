package com.example.admin_project.repository;

import com.example.admin_project.AdminProjectApplication;
import com.example.admin_project.model.entity.OrderGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderGroupRepositoryTest extends AdminProjectApplication {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType("ALL");
        orderGroup.setRevAddress("경기도 파주시 월롱산로 111");
        orderGroup.setPaymentType("CreditCard");
        orderGroup.setTotalPrice(BigDecimal.valueOf(209300));
        orderGroup.setTotalQuantity(3);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        //orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setArrivalDate(LocalDateTime.now().plusDays(2));
        //orderGroup.setCreatedBy("AdminUser");
        //orderGroup.User(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assertions.assertNotNull(newOrderGroup);
    }
}
