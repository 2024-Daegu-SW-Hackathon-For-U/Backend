package com.forU.hackathon.service;

import com.forU.hackathon.dto.kakao.UserInfoResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class KakaoService {

    @Value("${kakao.api_key}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    public String getAccessToken(String code) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 본문을 MultiValueMap으로 설정
        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
        requestData.add("grant_type", "authorization_code");
        requestData.add("client_id", kakaoApiKey);
        requestData.add("redirect_uri", kakaoRedirectUri);
        requestData.add("code", code);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestData, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://kauth.kakao.com/oauth/token",
                entity,
                String.class
        );

        JSONObject jsonObject = new JSONObject(response.getBody());
        return jsonObject.getString("access_token");
    }

    public UserInfoResponse getUserInfo(String accessToken) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON); // JSON 형식으로 설정

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                String.class
        );

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject kakaoAccount = jsonObject.getJSONObject("kakao_account");
        JSONObject profile = kakaoAccount.getJSONObject("profile");

        UserInfoResponse userInfo = new UserInfoResponse();
        userInfo.setId(jsonObject.getLong("id"));
        userInfo.setNickname(profile.getString("nickname"));

        return userInfo;
    }
}
