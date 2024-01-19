package com.encore.basic.servletJsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-jsp-post")
public class HelloServletJspPost extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
//        콘솔에 출력
        System.out.println(name);
        System.out.println(email);
        System.out.println(password);
//        응답
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
//        응답body
        PrintWriter out = resp.getWriter();
        out.print("ok");

        out.flush();
    }
}
