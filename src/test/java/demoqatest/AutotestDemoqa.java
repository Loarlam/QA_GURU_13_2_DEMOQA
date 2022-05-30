package demoqatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AutotestDemoqa {
    String _userTestName = "Test",
            _userTestSurname = "Testov",
            _userTestEmail = "test_testov@testmail.com",
            _userTestBirthMonth = "December",
            _userTestSubject = "Chemistry",
            _userTestPicture = "src/test/resources/Kavai.jpg",
            _userTestPictureShortPath = "Kavai.jpg",
            _userTestAddress = "Улица Пушкина, дом Ленина",
            _userTestState = "Haryana",
            _userTestCity = "Panipat",
            _userTestModalTitleText = "Thanks for submitting the form";
    long _randomPhoneNumber = new Random().nextInt(1000000000) + 9000000000L;
    int _randomYear = new Random().nextInt(101) + 1900,
            _randomDay = new Random().nextInt(27) + 1,
            _randomRadio = new Random().nextInt(3) + 1,
            _randomCheckBox = new Random().nextInt(3) + 1;

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
        $(String.format("#gender-radio-%s", _randomRadio)).sendKeys(" ");
//      $(By.id("gender-radio-2")).sendKeys(" "); // Работает. Нужно добавить import org.openqa.selenium.By;
        $("#userNumber").setValue(String.format("%s", _randomPhoneNumber));
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").setValue(_userTestBirthMonth);
        $(".react-datepicker__year-select").setValue(String.format("%s", _randomYear));

        if (_randomDay < 10)
            $(String.format(".react-datepicker__day--00%s", _randomDay)).click();
        else
            $(String.format(".react-datepicker__day--0%s", _randomDay)).click();

        $("#subjectsInput").sendKeys("Chemistry");
        $("#subjectsInput").sendKeys(Keys.RETURN);
        $(String.format("#hobbies-checkbox-%s", _randomCheckBox)).sendKeys(" ");
        $("#uploadPicture").uploadFile(new File(_userTestPicture));
        $("#currentAddress").setValue(_userTestAddress);
        $("#react-select-3-input").setValue(_userTestState).sendKeys(Keys.RETURN);
        $("#react-select-4-input").setValue(_userTestCity).sendKeys(Keys.RETURN);
        $("#submit").click();
        $(".modal-title").shouldHave(text(_userTestModalTitleText));
        $(".table-responsive").shouldHave(
                text(_userTestName + " " + _userTestSurname),
                text(_userTestEmail),
                text(String.valueOf(_randomRadio)),
                text(String.valueOf(_randomPhoneNumber)),
                text(_randomDay + " " + _userTestBirthMonth + "," + _randomYear),
                text(_userTestSubject),
                text(String.valueOf(_randomCheckBox)),
                text(_userTestPictureShortPath),
                text(_userTestAddress),
                text(_userTestState + " " + _userTestCity));
    }

    @AfterAll
    static void afterAllTest() throws InterruptedException {
        Configuration.holdBrowserOpen = true;
        Thread.sleep(10000);
        Configuration.holdBrowserOpen = false;
    }
}
