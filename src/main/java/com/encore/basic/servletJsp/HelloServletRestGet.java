package com.encore.basic.servletJsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-get")
public class HelloServletRestGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("kim");
        hello.setEmail("abc@naver.com");
        hello.setPassword("1234");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.print(objectMapper.writeValueAsString(hello));

        out.flush();
    }
}
