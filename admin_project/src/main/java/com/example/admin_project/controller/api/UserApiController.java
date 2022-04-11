package com.example.admin_project.controller.api;

import com.example.admin_project.ifs.CrudInterface;
import com.example.admin_project.model.network.Header;
import com.example.admin_project.model.network.Request.UserApiRequest;
import com.example.admin_project.model.network.Response.UserApiResponse;
import com.example.admin_project.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    //컨트롤러와 서비스를 연결해줌
    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")  // /api/user
    //응답은 UserApiResponse로 내려갈것이고, 요청에 대해서는 UserApiRequest로 받을것이다.
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        //INFO 레벨로 로그를 남기겠다는 의미 상단의 Slf4j 어노테이션 선언 후 사용가능
        log.info("{}",request);
        //요청을 받으면 userApiLogicService.create 로 요청 데이터를 넘겨줌
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")  // /api/user/{id}
    //@PathVariable : 매개변수와 페이지주소를 동일하게 매칭시켜줌
    public Header read(@PathVariable Long id) {
        //ID 어떤 값들어왔는지 확인하기 위한 로그
        log.info("read id : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")  // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        log.info("{}",request);
        //요청을 받으면 userApiLogicService.create 로 요청 데이터를 넘겨줌
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable Long id) {
        log.info("delete : {}",id);
        return userApiLogicService.delete(id);
    }
}
