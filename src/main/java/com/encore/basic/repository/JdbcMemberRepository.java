package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;

public class JdbcMemberRepository implements MemberRepository{
    @Override
    public List<Member> members() {
        return null;
    }

    @Override
    public void memberCreate(Member Member) {

    }
    @Override
    public Member memberFind(String id) {
        return null;
    }
}

