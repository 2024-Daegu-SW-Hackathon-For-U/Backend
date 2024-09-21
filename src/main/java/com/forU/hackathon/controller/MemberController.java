package com.forU.hackathon.controller;

import com.forU.hackathon.dto.kakao.UserInfoResponse;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.service.KakaoService;
import com.forU.hackathon.service.MemberService;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    public MemberController(KakaoService kakaoService, MemberService memberService) {
        this.kakaoService = kakaoService;
        this.memberService = memberService;
    }

    // 카카오 로그인
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String code) throws JSONException {
        String accessToken = kakaoService.getAccessToken(code);
        UserInfoResponse userInfo = kakaoService.getUserInfo(accessToken);

        Member member = memberService.findMemberByKakaoId(userInfo.getId().toString());

        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(201).body("회원가입을 위한 닉네임을 설정해 주세요.");
        }
    }

    // 닉네임으로 회원가입
    @PostMapping("/register")
    public ResponseEntity<Member> register(@RequestParam String kakaoId, @RequestParam String nickname) {
        UserInfoResponse userInfo = new UserInfoResponse();
        userInfo.setId(Long.parseLong(kakaoId));
        userInfo.setNickname(nickname);

        Member member = memberService.registerMember(userInfo);
        return ResponseEntity.ok(member);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // 여기서 필요한 로그아웃 처리 (예: 세션 무효화) 수행
        return ResponseEntity.ok("로그아웃 성공");
    }
}
