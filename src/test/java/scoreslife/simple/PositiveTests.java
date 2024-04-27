package scoreslife.simple;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.impl.JavaScript;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;

public class PositiveTests extends TestBase {


    @BeforeAll
    static void setup() {


        Configuration.remote = "https://user1:1234@" + System.getProperty("remote", "selenoid.autotests.cloud") + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("rakeya")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Поиск раздела Конные скачки")
    public void testHorsesFind() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("На главной странице перейти в раздел скачки", () -> {
            $("#header-nav-sports").click();

        });

        // $$("#sc-hkjphn-10").findBy(text("Скачки")).click();


        step("Убедиться, что на сегодня скачек нет", () -> {
            $("[href='/ru/horse-racing']").click();
            var text = $(".sc-18h6mvu-0").getText();
            WebSteps steps = new WebSteps();
            if (!text.contains("нет матчей")) {
                steps.takeScreenshot();
            }


        });


        // $(".sc-18h6mvu-0").shouldHave(text("На сегодня нет матчей."));
        //  $(".sc-18h6mvu-0").shouldNotHave(text("545454"));


    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("rakeya")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Поиск заголовка Ставка дня Экспрессы")
    public void testTotalExpressFind() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("На главной странице перейти в раздел эксперссы", () -> {
            $("[href='/ru/accumulators']").click();

        });

        step("Прокурутить скролл к букмекеру", () -> {
            $(".sc-1gqm27j-6").scrollTo();

        });

        step("Прокрутить скролл к тройному экспрессу", () -> {
            $(".sc-redash-0").scrollTo();

        });

        step("Убедиться в наличии заголовка", () -> {
            $(".ilZOcA").shouldHave(text("Ставка дня"));

        });


    }

}
