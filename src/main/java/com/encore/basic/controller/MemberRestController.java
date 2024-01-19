package com.encore.basic.controller;


import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
@Api(tags = "회원관리서비스")
@RestController
@RequestMapping("/rest")
public class MemberRestController {
    @Autowired
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("members/create")
    public String membersCreate(@RequestBody MemberRequestDto MemberRequestDto) {
        memberService.memberCreate(MemberRequestDto);
        return "ok";
    }

    @GetMapping("members")
    public List<MemberResponseDto> members() {
        return memberService.members();
    }

    @GetMapping("/member/find/{id}")
    public ResponseEntity<Map<String, Object>> memberFind(@PathVariable int id) {
        MemberResponseDto memberResponseDto = null;
        try {
            memberResponseDto = memberService.findById(id);
            return ResponseEntityController.responseMessage(HttpStatus.OK, memberResponseDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntityController.errResponseMessge(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/member/delete/{id}")
    public String memberDelete(@PathVariable int id) {
        memberService.delete(id);
        return "OK";
    }

    @PatchMapping("/members/update")
    public MemberResponseDto memberUpdate(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.memberUpdate(memberRequestDto);
        return  memberService.findById(memberRequestDto.getId());
    }
}