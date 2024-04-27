package scoreslife.simple;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TestBase {
    @BeforeEach
    public void beforeEach() {
        Configuration.browserSize = "2560x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = System.getProperty("browser", "chrome");
        open("https://scores24.live/ru");

        var banner1 =$("._wrapper_mp148_33");
        executeJavaScript("arguments[0].remove()",banner1);
        var banner2 =$("._overlay_mp148_37");
        executeJavaScript("arguments[0].remove()",banner2);
        var banner3 =$("._root_mp148_1");
        executeJavaScript("arguments[0].remove()",banner3);
//       var banner3= $("div[class*=\"_bodyOpen_\"]");
//        executeJavaScript("arguments[0].remove()",banner3);




    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}

