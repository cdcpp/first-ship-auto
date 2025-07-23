package com.cucupang.first_ship.controller;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GenerateTextFromTextInput {


        public static void main(String[] args) {
            // The client gets the API key from the environment variable `GEMINI_API_KEY`.
            Client client = new Client();

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.5-flash",
                            "영양제의 종류를 20개의  json array로 돌려줘. 예를 들면 비타민c, 비타민d 이렇게",
                            null);

            System.out.println("====="+response.text());
        }

}
