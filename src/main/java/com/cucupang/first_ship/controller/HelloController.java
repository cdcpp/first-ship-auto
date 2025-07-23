package com.cucupang.first_ship.controller;


import com.cucupang.first_ship.service.GeminiApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class HelloController {

    private final GeminiApiService geminiApiService;


    @GetMapping("/hello")
    public String hello(Model model) {

        geminiApiService.callGeminiApi();

        return "hello";
    }



}
