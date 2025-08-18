package com.cucupang.first_ship.service;

import com.cucupang.first_ship.dto.KeywordDto;
import com.cucupang.first_ship.entity.Keyword;
import com.cucupang.first_ship.enu.SearchYn;
import com.cucupang.first_ship.repository.KeywordRepository;
import com.google.common.reflect.TypeToken;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Type;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeminiApiService {

    private final KeywordRepository keywordRepository;

    public int callGeminiApi() {
        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "영양제의 종류를 30개의  json 형태로 돌려줘" +
                                "내가 응답받는 dto 형태는    " +
                                "    private String relKeyword;\n" +
                                "    private int monthlyPcQcCnt;\n" +
                                "    private int monthlyMobileQcCnt;" +
                                "이니까 , 이 변수명으로 줘야한단다",
                        null);


        List<KeywordDto> keywordDtos = parseKeywordsFromJson(response.text());
        for (KeywordDto keywordDto : keywordDtos) {
            Keyword keyword = Keyword.builder()
                    .keyword(keywordDto.getRelKeyword())
                    .searchYn(SearchYn.N)
                    .build();
            keywordRepository.save(keyword);
        }

        return keywordRepository.findBySearchYn(SearchYn.N).size();
}

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
