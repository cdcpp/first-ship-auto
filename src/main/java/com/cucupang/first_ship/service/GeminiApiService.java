package com.cucupang.first_ship.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiApiService {

    public String callGeminiApi(){
        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "영양제의 종류를 30개의  json array로 돌려줘. 예를 들면 비타민c, 비타민d 이렇게",
                        null);

        System.out.println("====="+response.text());
        return response.text();

    };




}
