package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase {

    @Test
    @DisplayName("Проверка полностью заполненной формы")

    void filledFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Tom");
        $("#lastName").setValue("Holland");
        $("#userEmail").setValue("tom@mail.ru");
        $("#userNumber").setValue("8800777900");
        $("#genterWrapper").$(byText("Male")).click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1989");
        $$("div.react-datepicker__day").findBy(text("22")).click();
        $("#subjectsInput").setValue("bi").sendKeys(Keys.RETURN);
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").setValue("Mira, 1");
        $("#react-select-3-input").setValue("u").sendKeys(Keys.RETURN);
        $("#react-select-4-input").setValue("m").sendKeys(Keys.RETURN);
        //Вариант 2 >
        //$("#state").click();
        //$("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        //$("#city").click();
        //$("#stateCity-wrapper").$(byText("Merrut")).click();

        $("#submit").click();

        // Проверки
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Tom Holland"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("tom@mail.ru"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("8800777900"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("22 May,1989"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("image.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Mira, 1"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Merrut"));

        // Закрытие формы
        Selenide.actions().sendKeys(Keys.PAGE_DOWN).perform();
        $("#closeLargeModal").shouldBe(interactable).click();
    }

    @Test
    @DisplayName("Форма заполнена минимальным количеством данных")

    void minValuesFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Tom");
        $("#lastName").setValue("Holland");
        $("#userEmail").setValue("tom@mail.ru");
        $("#userNumber").setValue("8800777900");
        $("#genterWrapper").$(byText("Male")).click();
        $("#submit").scrollTo().click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Tom Holland"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("8800777900"));
    }

    @Test
    @DisplayName("Не заполнено обязательное поле Mobile Number")

    void notFilledMobileNumber() {
        open("/automation-practice-form");
        $("#firstName").setValue("Tom");
        $("#lastName").setValue("Holland");
        $("#genterWrapper").$(byText("Male")).click();
        $("#submit").scrollTo().click();

        $(".table-responsive").shouldNot(exist);
    }
}
