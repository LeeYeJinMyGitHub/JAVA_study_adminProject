package com.example.admin_project.service;

import com.example.admin_project.ifs.CrudInterface;
import com.example.admin_project.model.entity.User;
import com.example.admin_project.model.enumclass.UserStatus;
import com.example.admin_project.model.network.Header;
import com.example.admin_project.model.network.Request.UserApiRequest;
import com.example.admin_project.model.network.Response.UserApiResponse;
import com.example.admin_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

//해당 클래스는 서비스로 동작하도록 함
@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request 데이터 가져오기
        UserApiRequest userApiRequest = request.getData();  //데이터부분에 있는 request를 가져온다.

        //2. user를 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        //3. 생성된 데이터를 가지고 Response에 리턴해줌
        //3. 은 생성된 user를 가지고 리턴해주는 것이기 때문에 read 혹은 update등에서도 사용될 수
        //   있기 때문에 따로 메서드로 만들어준다. -> 하단에 response 메서드
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id를 가지고 repository를(getOne 또는 getByID) 통해서 데이터를 가지고 옴
        //Optional : 데이터가 있으면 가져오고 없으면 동작안함
        Optional<User> optional = userRepository.findById(id);

        //해당 id의 user 데이터가 오면 userApiResponse에 리턴해주면 됨
        return optional
                .map(user -> response(user))                  // 데이터 있을경우에 response에 user를 넘겨줌
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")       // 데이터가 없을경우는 에러처리
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. 데이터를 가져옴
        UserApiRequest userApiRequest = request.getData();

        //2. id를 가지고 user 데이터를 찾는다.
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            //3. update를 함 (체인걸어놓음)
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
            return user;
        })
                .map(user -> userRepository.save(user))
                //4. userApiResponse를 만들어줌
                .map(updateUser -> response(updateUser))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        //1. ID를 받아서 reposiotory를 통해서 user를 찾는다.
        Optional<User> optional = userRepository.findById(id);
        //2. repository를 통해서 delete를 해준다.
        return optional.map(user -> {
            userRepository.delete(user);
            //3. 응답을 내려준다. 지우기 때문에 별도의 데이터는 내리지 않는다
            return Header.OK();
         })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    //3. 생성된 데이터를 가지고 Response에 리턴해줌
    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //Header에 data부분을 합쳐서 return
        return Header.OK(userApiResponse);
    }
}
