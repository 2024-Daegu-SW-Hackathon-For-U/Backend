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

    public Member registerMember(UserInfoResponse userInfo, Long customId) {
        // 중복된 ID 확인
        if (memberRepository.findById(customId).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 회원입니다.");
        }

        // 새로운 회원 등록
        Member member = new Member(userInfo.getNickname(), customId);
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

}
