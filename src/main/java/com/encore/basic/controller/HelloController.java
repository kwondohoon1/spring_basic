package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//HTTP 통신을 매우 편하게 할 수 있다
@Controller

//@RestController -> 모든 메서드들이 rest api 방식으로 리턴한다. -> csr 프론트가 따로 존재.

// 클래스 차원에서 url 경로를 지정하고 싶다면, @RequestMapping을 클래스 위에 선언하면서 경로지정.
@RequestMapping("hello")
public class HelloController {

    // @responseBody가 없고, return 타입이 String이면 templates밑에 return.html파일 리턴 없으면 에러
    // data만을 return 할때는 @ResponseBody를 붙인다.(CSR, Rest API 방식)
    // URL 지정 가능 (사용자의 요청을 분기처리 가능)
    @GetMapping("string")
    //url 패턴          // 메서드로 기능 명시
//    @RequestMapping(value = "string",method = RequestMethod.GET)
    @ResponseBody
    public String helloString() {
        return "hello_string";
    }


    // java에서 했던 것 처럼, ObjectMapper를 사용하여 직렬화 역직렬화를 수행해준다.
    @GetMapping("json")
    @ResponseBody
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setName("재영");
        hello.setEmail("naver");
        hello.setPassword("12343");
        System.out.println(hello);
        return hello;
    }

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }

    @GetMapping("screen-model-param")
    // ?name=재영 의 방식으로 호출 : parameter 호출 방식!
    public String helloScreenModelParam(@RequestParam(value = "name") String inputName, Model model) {
        // 화면에 데이터를 넘기고 싶을때는 model객체를 사용한다.
        // model의 키 value형식으로 전달한다.
        model.addAttribute("MyData", inputName); // 템플릿 엔진을 통해서 html에서 자바 문법 사용 가능
        return "screen";
    }

    // pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어 좀더 restful api 디자인에 적합하다
    // id를 표현하기 자연스러워서 그러듯..?
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("MyData", id);
        return "screen";
    }

    //Form tag x-www 데이터 처리

    // 1. 우선 입력 요청 화면을 사용자에게 전달
    @GetMapping("form-screen")
    public String formScreen() {
        return "formScreen";
    }

    @PostMapping("form-post-handle1")

    // form태그를 통한 body의 데이터 형태가 key1=value1&key2=value2.. -> parameter 방식과 똑같으니까
    @ResponseBody
    public String formPostHandle(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "passWd") String passWd) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(passWd);
        return "정상처리";
    }
    @PostMapping("form-post-handle2")
    @ResponseBody
//    Spring에서 Hello클래스의 인스턴스를 자동 매핑하여 생성4
//    form-data형식 즉, x-www-url인코딩 형식의 경우 사용
//    이를 데이터 바인딩 이라 부른다. (Hello 클래스에 setter 필수)
    public String formPostHandle2(Hello hello){
        System.out.println(hello);
        return "정상처리";
    }

    //    ----------------------------
    // jason 데이터 처리
    // 별도의 axoios와 같은 Json 데이터 처리 과정이 필요
    // Form tag x-www 데이터와 같은 화면에서 처리 할 수 없다.
    @GetMapping("json-screen")
    public String jsonScreen() {
        return "hello-json-screen";
    }

    @PostMapping("/json-post-handle1")
    @ResponseBody
    // @RequestBody Json으로 Post 요청이 들어왔을때 body에서 data를 꺼내기위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body) {
        System.out.println(body.get("name"));
        System.out.println(body.get("email"));
        System.out.println(body.get("passWd"));
        return "ok"; //JS에 변수에 담기는 값
    }
    @PostMapping("/json-post-handle2")
    @ResponseBody
    // @RequestBody Json으로 Post 요청이 들어왔을때 body에서 data를 꺼내기위해 사용
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());
        return "ok"; //JS에 변수에 담기는 값

        // 데이터 구조가 예측하기 힘들때는 jsNode로 받아주시
    }
    @PostMapping("/json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        return "ok"; //JS에 변수에 담기는 값
    }

    @PostMapping("httpservlet")
    @ResponseBody
    public String httpServletTest(HttpServletRequest req){
//        HttpServletRequest객체에서  header정보 추출
        System.out.println(req.getContentType());
        System.out.println(req.getMethod());
//        session : 로그인(auth) 정보에서 필요한 정보값을 추출할때 많이 사용
        System.out.println(req.getSession());
        System.out.println(req.getHeader("Accept"));

//        HttpServletRequest객체에서 body정보 추출
        System.out.println(req.getParameter("test1"));
        System.out.println(req.getParameter("test2"));
//        req.getReader()를 통해 BufferedReader로 받아 직접 파싱
        return "ok";
    }
    @GetMapping("/hello-servlet-jsp-get")
    public String helloServletJspGet(Model model) {
        model.addAttribute("myData", "jsp test data");
        return "hello-jsp";
    }

}