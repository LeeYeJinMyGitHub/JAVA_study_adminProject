package com.example.admin_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//모든 변수를 가지는 생성자를 추가해주는 어노테이션
@AllArgsConstructor
public class SearchParam {

    private String account;
    private String email;
    private int page;

}
