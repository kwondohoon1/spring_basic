package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;

import java.util.List;

public interface MemberRepository {
    public List<Member> members();

    public void memberCreate(Member Member);

    public Member memberFind(String id);
}
