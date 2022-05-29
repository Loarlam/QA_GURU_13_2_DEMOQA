package demoqatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.Random;
import java.util.function.IntToLongFunction;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutotestDemoqa {
    String _userTestName = "Test",
            _userTestSurname = "Testov",
            _userTestEmail = "test_testov@testmail.com",
            _userTestBirthMonth = "December",
            _userTestPicture = "src/test/resources/Kavai.jpg",
            _userTestAddress = "Улица Пушкина, дом Ленина",
            _userTestState = "Haryana",
            _userTestCity = "Panipat";
    long randomNumber = new Random().nextInt(1000000000) + 9000000000L;
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
        $("#firstName").setValue(_userTestName);
        $("#lastName").setValue(_userTestSurname);
        $("#userEmail").setValue(_userTestEmail);
        $(String.format("#gender-radio-%s", randomRadio)).sendKeys(" ");
//      $(By.id("gender-radio-2")).sendKeys(" "); // Работает. Нужно добавить import org.openqa.selenium.By;
        $("#userNumber").setValue(String.format("%s", randomNumber));
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").setValue(_userTestBirthMonth);
        $(".react-datepicker__year-select").setValue(String.format("%s", randomYear));

        if (randomDay < 10)
            $(String.format(".react-datepicker__day--00%s", randomDay)).click();
        else
            $(String.format(".react-datepicker__day--0%s", randomDay)).click();

        $(String.format("#hobbies-checkbox-%s", randomCheckBox)).sendKeys(" ");
        $("#uploadPicture").uploadFile(new File(_userTestPicture));
        $("#currentAddress").setValue(_userTestAddress);
        $("#react-select-3-input").setValue(_userTestState).sendKeys(Keys.RETURN);
        $("#react-select-4-input").setValue(_userTestCity).sendKeys(Keys.RETURN);
        $("#submit").click();

    }

    @AfterAll
    static void afterAllTest() {
        Configuration.holdBrowserOpen = true;
    }
}
