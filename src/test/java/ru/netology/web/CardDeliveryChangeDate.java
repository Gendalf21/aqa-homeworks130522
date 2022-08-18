package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.Configuration;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class CardDeliveryChangeDate {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Успешная перепланировка даты")
    void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");
        String meetingDate = DataGenerator.meetingDate(3);
        String otherMeetingDate = DataGenerator.meetingDate(7);

        $("[placeholder=Город]").setValue(info.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(meetingDate);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Успешно!"));
        $("[class=notification__content]")
                .shouldHave(text("Встреча успешно запланирована на " + meetingDate));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(otherMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=replan-notification]")
                .shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text("Необходимо подтверждение"));
        $(byText("Перепланировать")).click();
        $("[class=notification__content]")
                .shouldHave(text("Встреча успешно запланирована на " + otherMeetingDate));

    }

}



