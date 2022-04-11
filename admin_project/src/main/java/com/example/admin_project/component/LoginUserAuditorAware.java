package com.example.admin_project.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

//각엔티티에서 createdby, updatedby 를 어노테이션으로 대체 가능
@Component
//로그인한 사용자를 감시하는 역할로 사용예정
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("AdminServer");
    }
}
