package demoqatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class AutotestDemoqa {
    int randomNumber = new Random().nextInt(100000000) + 900000000;
    int randomYear = new Random().nextInt(101) + 1900;
    int randomDay = new Random().nextInt(27) + 1;
    int randomRadio = new Random().nextInt(3) + 1;
    int randomCheckBox = new Random().nextInt(3) + 1;

    @BeforeAll
    static void beforeAllTests() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
    }

    @Test
    void successFullSearch() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#firstName").setValue("Test");
        $("#lastName").setValue("Testov");
        $("#userEmail").setValue("test_testov@testmail.com");
        $("#userEmail").setValue("test_testov@testmail.com");
        $(String.format("#gender-radio-%s", randomRadio)).sendKeys(" ");
//      $(By.id("gender-radio-2")).sendKeys(" "); // Работает. Нужно добавить import org.openqa.selenium.By;
        $("#userNumber").setValue(String.format("%s", randomNumber));
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").setValue("December");
        $(".react-datepicker__year-select").setValue(String.format("%s", randomYear));

        if (randomDay < 10)
            $(String.format(".react-datepicker__day--00%s", randomDay)).click();
        else
            $(String.format(".react-datepicker__day--0%s", randomDay)).click();

//        $("#subjectsContainer").click();
//        $("#subjectsContainer").setValue("Physics").pressEnter();

        $(String.format("#hobbies-checkbox-%s", randomCheckBox)).sendKeys(" ");
        $("#uploadPicture").uploadFile(new File("src/test/resources/Kavai.jpg"));

    }

    @AfterAll
    static void afterAllTest() {
        Configuration.holdBrowserOpen = true;
    }
}
