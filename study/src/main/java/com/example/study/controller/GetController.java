package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

//에너테이션으로 이곳이 컨트롤러임을 명시함
@RestController
//이 곳으로 들어올 API 주소를 매핑하기 위해 사용
@RequestMapping("/api") // Localhost:8080/api

public class GetController {

    //메서드는 어떠한 타입으로 받을것인지 지정 (Get 타입), 경로는 어떤 주소로 받을건지 지정
    @RequestMapping(method = RequestMethod.GET, path = "getMethod") //Localhost:8080/api/getMethod
    //사용자의 요청에 의해 요청을 아래 메서드로 받게 됨
    public String getRequest(){
        return "Hi getMethod";
    }

    //request가 아닌 get에 대해 처리할 것이기 때문에 getMapping 에너테이션 사용,
    //request와 다르게 주소만 설정해 주면 됨
    @GetMapping("/getParameter") //Localhost:8080/api/getParameter?id=1234&password=abcd
    //GetMethod 뒤에 파라미터에 대해서(사용자 요청에 대해)어떤식으로 값을 받아들이는지 확인
    //스프링에서 이러한 요청을 받아주기 위해서는 requestParam이라는 에너테이션을 사용함
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id :" + id);
        System.out.println("password : " + password);

        return id + password;
    }

    //여러가지 파라미터를 받기위함 (계정, 이메일, 페이지 등)
    @GetMapping("/getMultiParameter")
    //Localhost:8080/api/multiParameter?account=yjlee&email=yjlee@gmail.com&page=10
    //파라미터를 객체로 받을수 있는 방법이 있음 - model 패키지 않에 SearchParam 객체 만들어줌
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //아래 같은 json 형식의 데이터가 내려오길 원함 -> String으로 리턴하지말고 받은 객체 (Searchparam)로 리턴함
        //{ "account" : "", "email" : "", "page" : 0 }
        return searchParam;
    }

}
