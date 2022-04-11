package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
//GetController와 아래 /api 라는 주소가 동일하지만 실행 가능한 이유 :
//메서드에 대해 주소가 동일하다면 실행했을때 에러가 나지만, 클래스 단위에서 주소가 동일함은 실행에 상관 없음
@RequestMapping("/api")

public class PostController {

    //POST는 HTML의 <form> 태그를 사용하거나, ajax에서 검색을 할때 사용됨

    //아래 @RequestMapping과 PostMapping은 동일한 동작임
    //@RequestMapping(method = RequestMethod.POST , path "/postMethod")
    //@RequestBody : HTTP body에다가 data를 집어넣어서 보내겠다는 의미
    //@PostMapping(value = "/postMethod", produces = {"application-json"})
    //produces : 지원할 수 있는 형태(json, xml, multipart-form, text-plain)들을 작성할 수 있음, 단 해당 프로젝트에서는 기본 형태인 json 사용위해 produces 설정안함
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    //PUT 메서드를 쓸떄는 PutMapping 제공
    @PutMapping("/putMehod")
    public void put(){

    }

    //PATCH 메서드를 쓸떄는 PatchMapping 제공
    @PatchMapping("/patchMethod")
    public void patch(){

    }

}
