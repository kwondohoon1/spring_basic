package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class MemoryMemberRepository implements MemberRepository{
    private List<Member> memberDB; // 현재는 메모리 리스트형태, 이부분이 진짜 DB로 바뀜
    static int total_id;
    public MemoryMemberRepository()
    {
        memberDB = new ArrayList<>();

    }
    public List<Member> findAll() {
        return memberDB;

    }

    public Member save(Member member) {
        total_id += 1;
        LocalDateTime now = LocalDateTime.now();
        member.setId(total_id);
        member.setCreate_time(now);
        memberDB.add(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id){
        for(Member member : memberDB){
            if(member.getId()==id){
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Member member) {

    }

}
