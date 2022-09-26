//package com.example.burger;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.concurrent.TimeUnit;
//
//@ExtendWith(SpringExtension.class) // <1>
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class HomePageBrowserTest {
//
//    @LocalServerPort
//    private int port;
//    private static HtmlUnitDriver browser;
//
//    @BeforeAll
//    public static void setup() {
//        browser = new HtmlUnitDriver();
//        browser.manage().timeouts()
//                .implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @AfterAll
//    public static void teardown() {
//        browser.quit();
//    }
//
//    @Test
//    public void testHomePage() {
//        String homePage = "http://localhost:" + port;
//        browser.get(homePage);
//
//        String titleText = browser.getTitle();
//        Assertions.assertThat(titleText)
//                .isEqualTo("Home");
//
//        String h1Text = browser
//                .findElementByTagName("h1")
//                .getText();
//        Assertions.assertThat(h1Text)
//                .isEqualTo("Hello dear friend");
//
//        String imgSrc = browser
//                .findElementByTagName("img")
//                .getAttribute("src");
//        Assertions.assertThat(imgSrc)
//                .isEqualTo(homePage + "/images/burger.jpg");
//    }
//}
