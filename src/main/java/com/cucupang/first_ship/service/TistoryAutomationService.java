package com.cucupang.first_ship.service;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TistoryAutomationService {

    public WebDriver loginToTistory() {

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
        idInput.sendKeys(System.getenv("TISTORY_ID"));

        WebElement passwordInput = driver.findElement(By.id("password--2"));
        passwordInput.sendKeys(System.getenv("TISTORY_PWD"));

        WebElement loginSubmitButton = driver.findElement(By.cssSelector("button.btn_g.highlight.submit"));
        loginSubmitButton.click();


        return driver;


        //드라이버 종료
        //driver.quit();
    }

    public void postToTistory(String title, String content)  {
        WebDriver driver = loginToTistory(); // 로그인 후 WebDriver 객체를 반환하는 메소드가 필요합니다.

        if (driver == null) {
            System.out.println("로그인 실패");
            return;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        try {

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#mArticle > div > div.marticle_right > div > div.my_tistory.border_box > div.wrap_link > a:nth-child(1)")));

            String blogName = ""; // 티스토리 주소
            driver.get("https://" + blogName + ".tistory.com/manage/newpost/");




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                // driver.quit();
            }
        }
    }

}
