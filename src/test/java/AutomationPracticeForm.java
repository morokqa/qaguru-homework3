import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Tom");
        $("#lastName").setValue("Holland");
        $("#userEmail").setValue("tom@mail.ru");
        $("#userNumber").setValue("8800777900");

        // Пол (радиобаттон)
        $("#genterWrapper").$(byText("Male")).click();

        // Дата рождения (календарь)
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1989");
        $$("div.react-datepicker__day").findBy(text("22")).click();

        // Предмет (плейсхолдер)
        $("#subjectsInput").setValue("bi").sendKeys(Keys.RETURN);

        // Хобби (мультивыбор)
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();


        // Загрузка картинки
        $("#uploadPicture").uploadFromClasspath("image.jpg");

        $("#currentAddress").setValue("Mira, 1");
        $("#react-select-3-input").setValue("u").sendKeys(Keys.RETURN);
        $("#react-select-4-input").setValue("m").sendKeys(Keys.RETURN);

        // Отправка формы
        $("#submit").click();

        // Проверки
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Tom Holland"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("tom@mail.ru"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("8800777900"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("22 May,1989"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music, Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("image.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Mira, 1"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Merrut"));

        // Закрытие формы
        Selenide.actions().sendKeys(Keys.PAGE_DOWN).perform();
        $("#closeLargeModal").shouldBe(interactable).click();
    }
}
