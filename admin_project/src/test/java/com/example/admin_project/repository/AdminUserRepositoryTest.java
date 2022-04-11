package com.example.admin_project.repository;

import com.example.admin_project.AdminProjectApplication;
import com.example.admin_project.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminUserRepositoryTest extends AdminProjectApplication {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("eyjeyj1");
        adminUser.setPassword("eyj29880997");
        adminUser.setStatus("valiid");
        adminUser.setRole("Manager");
        //adminUser.setCreatedAt(LocalDateTime.now()); //Component로 로우가 생성될때마다 자동으로 넣어주도록 설정했기때문에 주석처리되도 자동으로 값이들어감
        //adminUser.setCreatedBy("AdminUser");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);

        //newAdminUser.setAccount("change");
        //adminUserRepository.save(newAdminUser);
    }
}
