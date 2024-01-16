package com.encore.basic.repository;
//jdbc는 레거시 기술 jpa는 가장많이 쓰는 최신기술

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//spring data jpa의 기본기능을 쓰기 위해서는 JpaRepository를 상속해야함
//상속시에 entity명과 해당 entity의 pk타입을 명시
//실질적인 구현 클래스와 스펙은 SimpleJpaRepository 클래스에 있고,
//실질적인 구동상황에서 hiberante 구현체에 동작위임.
public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {
}
