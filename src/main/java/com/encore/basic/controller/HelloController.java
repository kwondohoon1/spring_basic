package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//모든 요청에 ResponseBody 붙이고 싶다면, @RestController를 사용
@Controller
//클래스 차원에서 url 경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정.
@RequestMapping("hello")
public class HelloController {

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }
//    responseBody가 없고, return타입이 String이면 templates밑에 html파일 리턴
//    data만을 return 할때는 @ResponseBody 를 붙인다.
    @GetMapping("string")
    @ResponseBody
    public String helloString(){
        return "hello_string";
    }
}
