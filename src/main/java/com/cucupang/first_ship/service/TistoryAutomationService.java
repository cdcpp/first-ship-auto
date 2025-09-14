package com.cucupang.first_ship.service;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class TistoryAutomationService {

    public void loginToTistory(){

        //TODO... 티스토리 Login 테스트 중
        //크롬드라이버 설정
        WebDriverManager.chromedriver().setup();

        //크롬 실행
        WebDriver driver = new ChromeDriver();

        //티스토리 이동
        driver.get("http://www.tistory.com/auth/login");
        
        //로그인 버튼 클릭
        WebElement kakaoLoginButton = driver.findElement(By.cssSelector("a.btn_login.link_kakao_id"));
        kakaoLoginButton.click();

        //id , pwd 인풋
        WebElement idInput = driver.findElement(By.id("loginId--1"));
        idInput.sendKeys("id입력~");

        WebElement passwordInput = driver.findElement(By.id("password--2"));
        passwordInput.sendKeys("pwd입력~");

        WebElement loginSubmitButton = driver.findElement(By.cssSelector("button.btn_g.highlight.submit"));
        loginSubmitButton.click();

        //드라이버 종료
        //driver.quit();

    }

}
