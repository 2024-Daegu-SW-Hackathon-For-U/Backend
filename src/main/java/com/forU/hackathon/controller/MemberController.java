package com.forU.hackathon.controller;

import com.forU.hackathon.dto.kakao.UserInfoResponse;
import com.forU.hackathon.dto.login.LoginResponse;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.service.KakaoService;
import com.forU.hackathon.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.net.URI;
import java.util.UUID;

@Tag(name= "3. Member", description = "멤버 관련 API")
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
    public ResponseEntity<?> login(@RequestParam String code, HttpSession session) throws JSONException {
        String accessToken = kakaoService.getAccessToken(code);
        UserInfoResponse userInfo = kakaoService.getUserInfo(accessToken);

        // 세션에 액세스 토큰 저장
        session.setAttribute("accessToken", accessToken);

        // 회원 조회
        Member member = memberService.findById(userInfo.getId())
                .orElse(null); // 회원이 존재하지 않으면 null 반환

        if (member != null) {
            // 이미 회원인 경우, 액세스 토큰과 회원 정보를 반환
            return ResponseEntity.ok(new LoginResponse(member, accessToken));
        } else {
            // 회원이 존재하지 않는 경우 카카오 API에서 닉네임을 가져와서 회원가입 처리
            String nickname = userInfo.getNickname(); // 카카오 API에서 가져온 닉네임

            // 새로운 회원 객체 생성
            UserInfoResponse newUserInfo = new UserInfoResponse();
            newUserInfo.setId(userInfo.getId());
            newUserInfo.setNickname(nickname);

            // 회원가입 처리
            Member newMember = memberService.registerMember(newUserInfo, userInfo.getId());

            // 새로 등록된 회원 정보와 액세스 토큰 반환
            return ResponseEntity.ok(new LoginResponse(newMember, accessToken));
        }
    }


    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // 리다이렉트 URI 설정
        String logoutRedirectUri = "http://localhost:5500/index.html";
        String state = UUID.randomUUID().toString();

        // 카카오 로그아웃 요청
        try {
            kakaoService.logout(logoutRedirectUri, state);
        } catch (Exception e) {
            return ResponseEntity.ok("카카오 로그아웃 실패: " + e.getMessage());
        }

        // 세션에서 액세스 토큰 제거
        if (session != null) {
            session.removeAttribute("accessToken");
        }

        // 리다이렉트 응답 반환
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(logoutRedirectUri)).build();
    }
}
