package com.forU.hackathon.repository;

import com.forU.hackathon.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
