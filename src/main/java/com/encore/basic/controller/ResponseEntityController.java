package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {
//    @ResponseStatus 어노테이션 방식
    @GetMapping("responsestatus1")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus(){
        return  "ok";
    }

    @GetMapping("responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2(){
        Member member = new Member();
        return member;
    }
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member();
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
//    ResponseEntity<String>일 경우 text/html로 설정
    @GetMapping("custom2")
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 id입니다.</h1>";
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    map형태의 메시지 커스텀
    public static ResponseEntity<Map<String, Object>> errResponseMessge(HttpStatus httpStatus, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("error message", message);
        return new ResponseEntity<>(body, httpStatus);
    }
//    status 201, message : 객체, map_custom2
    public static ResponseEntity<Map<String, Object>> responseMessage(HttpStatus httpStatus, Object object){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("member", object);
        return new ResponseEntity<>(body, httpStatus);
    }

//    메서드 체이닝 : ResponseEntity의 클래스 메서드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member();
        return ResponseEntity.ok(member);
    }
    @GetMapping("chaining2")
    public ResponseEntity<String> chaining2(){
        return ResponseEntity.notFound().build();
    }
    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = new Member();
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
