package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public List<Member> findAll();

    public Member save(Member Member);

    public Optional<Member> findById(int id);
}
