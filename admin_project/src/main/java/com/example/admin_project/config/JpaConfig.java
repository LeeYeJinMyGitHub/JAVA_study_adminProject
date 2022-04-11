package com.example.admin_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//해당 클래스는 컨피그임을 명시
@Configuration
//JPA에 대해서 감시를 설정하겠다 명시
@EnableJpaAuditing
public class JpaConfig {
}
