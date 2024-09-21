package com.forU.hackathon.service;

import com.forU.hackathon.dto.kakao.UserInfoResponse;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(UserInfoResponse userInfo) {
        // 중복된 ID
        if (memberRepository.findById(userInfo.getId()) != null) {
            throw new IllegalArgumentException("이미 등록된 회원입니다.");
        }

        // 새로운 회원 등록
        Member member = new Member(userInfo.getNickname(), userInfo.getId());
        return memberRepository.save(member);
    }

    // findById 메서드 추가
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
}
