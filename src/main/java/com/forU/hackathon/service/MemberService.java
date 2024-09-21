package com.forU.hackathon.service;

import com.forU.hackathon.dto.kakao.UserInfoResponse;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(UserInfoResponse userInfo) {
        // 중복된 Kakao ID 체크
        if (memberRepository.findByKakaoId(userInfo.getId().toString()) != null) {
            throw new IllegalArgumentException("이미 등록된 회원입니다.");
        }

        // 새로운 회원 등록
        Member member = new Member();
        member.setKakaoId(userInfo.getId().toString());
        member.setNickname(userInfo.getNickname());

        return memberRepository.save(member);
    }

    public Member findMemberByKakaoId(String kakaoId) {
        return memberRepository.findByKakaoId(kakaoId);
    }
}
