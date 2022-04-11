package com.example.admin_project.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header <T> {
    //항상 공통적으로 들어오는 값들
    //api 통신 시간
    //LocalDateTime 자료형으로도 할 수 있지만, 프론트와 소통할때는 보통 String으로 함
    private LocalDateTime transactionTime;
    //api 응답 코드
    private String resultCode;
    //api 부가 설명
    private String description;

    //헤더 클래스에서 제너릭을 통해 데이터를 지정하기 때문에 CrudInterface 및 로직 서비스에서도 Request를 모두 헤더<제네릭>로 감싸줘야함
    private T data;

    //OK 메서드
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }
    
    //데이터가 있는 OK 메서드, 매개변수를 제네릭으로 데이터를 받음
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    
    //에러 메서드
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
