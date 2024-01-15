package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.JdbcMemberRepository;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
//    @Autowired //의존성주입(DI) 방법 1 IOC 컨테이너의 싱글톤으로 만든객체 주입
//    private MemoryMemberRepository memoryMemberRepository;

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository){
        this.memberRepository = memoryMemberRepository;
    }
    static int total_id;

    public MemberService(){
        memberRepository = new MemoryMemberRepository();
    }
    public List<MemberResponseDto> members(){
        List<Member> members = memberRepository.members();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.getId());
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDto.setPassword(member.getPassword());
            memberResponseDto.setCreate_time(member.getCreate_time());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    public void memberCreate(MemberRequestDto memberRequestDto){
        LocalDateTime now = LocalDateTime.now();
        total_id += 1;
        Member member = new Member(total_id, memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword(), now);
        memberRepository.memberCreate(member);
    }

    public MemberRequestDto memberFind(int id){
        Member member = memberRepository.memberFind(id);
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        MemberResponseDto.setId(member.getId());
        MemberResponseDto.setName(member.getName());
        MemberResponseDto.setEmail(member.getEmail());
        MemberResponseDto.setPassword(member.getPassword());
    }
}
