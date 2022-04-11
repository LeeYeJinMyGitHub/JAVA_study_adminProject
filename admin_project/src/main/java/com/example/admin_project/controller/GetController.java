package com.example.admin_project.controller;

import com.example.admin_project.model.network.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/getHeader")
    //헤더로 내리는 getHeader 메소드
    public Header getHeader(){
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
