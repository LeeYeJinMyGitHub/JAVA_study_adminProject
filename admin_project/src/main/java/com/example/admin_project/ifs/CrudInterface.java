package com.example.admin_project.ifs;

import com.example.admin_project.model.network.Header;
import com.example.admin_project.model.network.Request.UserApiRequest;
import com.example.admin_project.model.network.Response.UserApiResponse;
import org.springframework.web.bind.annotation.RequestBody;

//반드시 정의해야할 메서드들을 체크하기위해 파일 만듦
public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);
    Header<Res> read(Long id);
    Header<Res> update(Header<Req> request);
    Header<Res> delete(Long id);
}
