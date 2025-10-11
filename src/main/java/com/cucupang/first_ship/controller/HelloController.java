package com.cucupang.first_ship.controller;


import com.cucupang.first_ship.dto.KeywordDto;
import com.cucupang.first_ship.service.GeminiApiService;
import com.cucupang.first_ship.service.TistoryAutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HelloController {

    private final GeminiApiService geminiApiService;
    private final TistoryAutomationService tistoryAutomationService;

    @GetMapping("/hello")
    public String hello(Model model) {

       int saveCount =   geminiApiService.callGeminiApi();

        System.out.println("save==" +  saveCount);


        return "hello";
    }

    @GetMapping("/testTistory")
    public String testTistory(Model model) throws InterruptedException {


        //tistoryAutomationService.loginToTistory();
        tistoryAutomationService.postToTistory("1","2");


        return "";
    }



}
