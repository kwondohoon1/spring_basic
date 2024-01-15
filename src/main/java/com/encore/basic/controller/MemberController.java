package com.encore.basic.controller;


import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/* 1)회원목록조회
getmapping, 화면
url : members
화면 : member/memberList
=> 이름, email, password
=> 테이블구조 -> td는 적당히 3줄정도 채워주기
2)메모리 DB -> List에 담기
3)회원가입
3-1)get url : members/create
3-2)post url : members/create
3-3)form태그, input태그
4)회원목록조회
4-1)get-url : members
4-2)table
 */

//service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
// 스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control) -> IOC 컨테이너가 스프링 빈을 관리(빈을 생성, 의존성 주입)
@Controller
public class MemberController {
    @Autowired //의존성주입(DI) 방법 1 =>필드주입방식
    // private MemberService memberService; //다시선언못하도록 final

    // 의존성주입방법2 => 생성자 주입방식이고, 가장 많이 사용하는 방법
    // 장점 : final을 통해 상수로 사용가능, 다형성 구현 가능, 순환참조방지
    //    생성자가 1개 밖에 없을때에는 Autowired 생략가능
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    의존성 주입방법 3 @RequiredArgsConstructor를 이용한 방식
//    @RequiredArgsConstructor : @NonNull어노테이션이 붙어있는 필드 또는 초기화 되지 않은 final필드를
//    대상으로 생성자 생성.
//    private final MemberService memberService;


    @GetMapping("/")
    public String home() {
        return "member/header";
    }

    @GetMapping("members/create")
    public String membersCreateScreen() {
        return "member/member-create-screen";
    }

    @PostMapping("members/create")
    public String membersCreate(MemberRequestDto MemberRequestDto) {
        memberService.memberCreate(MemberRequestDto);
//        url 리다이렉트
        return "redirect:/members";
    }

    @GetMapping("members")
    public String members(Model model) {
        model.addAttribute("memberList", memberService.members());
        return "member/member-list";
    }

    @GetMapping("/member/find")
    public String meberFind(@RequestParam(value = "id") int id, Model model) {
        Member member = memberService.memberFind(id);
        model.addAttribute("findData", id);
        return "member/member-find";
    }
}