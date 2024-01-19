package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service

//전형적인 트랜잭션 작동이 필요한 상황
//중간에 예외발생했을때 rollback처리되게하려면
public class MemberService {
//    @Autowired //의존성주입(DI) 방법 1 IOC 컨테이너의 싱글톤으로 만든객체 주입
//    private MemoryMemberRepository memoryMemberRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository){
        this.memberRepository = springDataJpaMemberRepository;
    }

    public List<MemberResponseDto> members(){
        List<Member> members = memberRepository.findAll();
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
//      Transactional 어노테이션 클래스 단위로 붙이면 모든 메서드에 각각 Transaction 적용
//      Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정
    @Transactional
    public void memberCreate(MemberRequestDto memberRequestDto) throws IllegalArgumentException{
            Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
            memberRepository.save(member);


////        transaction 테스트
//            Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
//            memberRepository.save(member);
//            if(member.getName().equals("kim")){
//                throw new IllegalArgumentException();
//        }

    }

    public MemberResponseDto findById(int id) throws EntityNotFoundException{
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setCreate_time(member.getCreate_time());
        return memberResponseDto;
    }
    public void delete(int id) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(member);
    }
    public void memberUpdate(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        System.out.println(memberRequestDto.getId());
        member.updateMember(memberRequestDto.getName(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }
}
