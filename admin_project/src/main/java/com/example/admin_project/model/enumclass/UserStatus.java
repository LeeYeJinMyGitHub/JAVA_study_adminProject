package com.example.admin_project.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0, "등록", "사용자 등록 상태"),
    UNREGISTERED(1, "해지상태", "사용자 해지 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
