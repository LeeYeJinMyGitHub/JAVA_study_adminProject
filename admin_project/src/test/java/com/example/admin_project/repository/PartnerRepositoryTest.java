package com.example.admin_project.repository;

import com.example.admin_project.AdminProjectApplication;
import com.example.admin_project.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PartnerRepositoryTest extends AdminProjectApplication {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        String name = "Partner02";
        String status = "Register";
        String address = "고양시 원흥 1로 25 1103-611";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminUser";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        //partner.setCreatedAt(createdAt);
        //partner.setCreatedBy(createdBy);
       // partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);

        Assertions.assertNotNull(newPartner);

    }
}
