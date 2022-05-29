package demoqatest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class AutotestDemoqa {

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
        $("label[for=gender-radio-3]").click();

/*      $("div[class='custom-control custom-radio custom-control-inline']").$("input[id='gender-radio-3']").click(); - не работает
        $(By.name("gender")).selectRadio("input[id=gender-radio-3").click(); - не работает
        $(By.name("gender")).setValue("Other").click(); - не работает
*/
        $("input[id=userNumber]").setValue("9876543210");
    }

    @AfterAll
    static void afterAllTest()
    {
        Configuration.holdBrowserOpen = true;
    }
}
