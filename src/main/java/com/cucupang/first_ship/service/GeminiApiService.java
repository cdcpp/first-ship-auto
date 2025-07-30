package com.cucupang.first_ship.service;

import com.cucupang.first_ship.dto.KeywordDto;
import com.google.common.reflect.TypeToken;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Type;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GeminiApiService {

    public List<KeywordDto> callGeminiApi(){
        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "영양제의 종류를 30개의  json 형태로 돌려줘",
                        null);

        return parseKeywordsFromJson(response.text());

    };

    public List<KeywordDto> parseKeywordsFromJson(String jsonText){
        return Optional.ofNullable(jsonText)
                .filter(text -> text.contains("[") && text.contains("]"))
                .map(text -> text.substring(text.indexOf('['), text.lastIndexOf(']') + 1))
                .map(this::parseJsonArray)
                .orElseGet(()-> {
                    System.out.println("JSON ARRAY NOT FOUND");    // thorws Exception
                    return Collections.emptyList();
                });
    }


    private List<KeywordDto> parseJsonArray(String jsonArrayString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArrayString, new TypeToken<List<KeywordDto>>(){}.getType());
    }



   /* public List<String> parseKeywordsFromJson(String jsonText){
        int startIndex = jsonText.indexOf('[');
        int lastIndex = jsonText.lastIndexOf(']');
        String jsonArrayString = null;

        if (startIndex != -1 && lastIndex != -1) {
            jsonArrayString = jsonText.substring(startIndex, lastIndex + 1);
        } else {
            //  ,,,json  [] 형식이 아닌 경우
            System.out.println("오류: 응답에서 JSON 배열을 찾을 수 없습니다.");
            return Collections.emptyList();
        }

        Gson gson = new Gson();
        List<String> keywordList = gson.fromJson(jsonArrayString, List.class);

        return keywordList;
    }*/



}
