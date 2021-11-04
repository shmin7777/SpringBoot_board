package com.example.test.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImportPay {

    public String getToken() {

        RestTemplate restTemplate = new RestTemplate();

        //서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("imp_key", "7681399020268514");
        map.put("imp_secret", "fcff66b2ffa2c27be20812e9e502f68d88d23098829f2601a77253d946b8731a40171253b96b5714");

        Gson var = new Gson();
        String json = var.toJson(map);
        //서버로 요청할 Body

        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        return restTemplate.postForObject("https://api.iamport.kr/users/getToken", entity, String.class);

    }
}