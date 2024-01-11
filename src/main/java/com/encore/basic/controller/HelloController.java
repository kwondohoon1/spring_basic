package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//모든 요청에 ResponseBody 붙이고 싶다면, @RestController를 사용
@Controller
//클래스 차원에서 url 경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정.
@RequestMapping("hello")
public class HelloController {


//    responseBody가 없고, return타입이 String이면 templates밑에 html파일 리턴
//    data만을 return 할때는 @ResponseBody 를 붙인다.
    @GetMapping("string")
    @ResponseBody
    public String helloString(){
        return "hello_string";
    }

    @GetMapping("json")
    @ResponseBody
    public String helloJson(){
        return "hello_string";
    }

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }
    @GetMapping("screen-model-param")
//    ?name=hongildong의 방식으로 호출(파라미터 호출 방식)
    public String helloScreenModelParam(@RequestParam(value = "name")String inputName, Model model) {
//        화면에 data를 넘기고 싶을때는 model 객체 사용
//        model에 key:value형식으로 전달
        model.addAttribute("myData",inputName);
        return "screen";
    }

//    pathvariable방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어,
//    좀더 RestFul API 디자인에 적합
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("myData", id);
        return "screen";
    }
}
