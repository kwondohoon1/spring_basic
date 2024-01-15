package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class MemoryMemberRepository implements MemberRepository{
    private List<Member> memberDB; // 현재는 메모리 리스트형태, 이부분이 진짜 DB로 바뀜
    public MemoryMemberRepository(){
        memberDB = new ArrayList<>();
    }
    public List<Member> members(){
        return memberDB;
    }

    public void memberCreate(Member member){
        memberDB.add(member);
    }

    public Member memberFind(String id){
        return memberDB.get(Integer.parseInt(id));
    }

}
