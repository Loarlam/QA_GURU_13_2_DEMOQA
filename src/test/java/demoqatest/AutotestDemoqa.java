package demoqatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class AutotestDemoqa {
    int randomNumber = new Random().nextInt(100000000) + 900000000;
    int randomYear = new Random().nextInt(27) + 1;
    int randomMonth = new Random().nextInt(12);
    int randomDay = new Random().nextInt(101) + 1900;
    int randomRadio = new Random().nextInt(3)+1;

    @BeforeAll
    static void beforeAllTests()
    {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition ="0x0";
    }

    @Test
    void successFullSearch()
    {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("input[id=firstName]").setValue("Test");
        $("input[id=lastName]").setValue("Testov");
        $("input[id=userEmail]").setValue("test_testov@testmail.com");
        $("input[id=userEmail]").setValue("test_testov@testmail.com");
        $(String.format("input[id=gender-radio-%s]",randomRadio)).sendKeys(" "); //Реализовал через нажатие пробелом на radio
//      $(By.id("gender-radio-2")).sendKeys(" "); // Работает. Нужно добавить import org.openqa.selenium.By;
        $("input[id=userNumber]").setValue(String.format("%s",randomNumber));
        $("input[id=dateOfBirthInput]").click();
        $("select[class=react-datepicker__month-select]").setValue(String.format("%s",randomMonth));
        $("select[class=react-datepicker__year-select]").setValue(String.format("%s",randomYear));
    }

    @AfterAll
    static void afterAllTest()
    {
        Configuration.holdBrowserOpen = true;
    }
}
