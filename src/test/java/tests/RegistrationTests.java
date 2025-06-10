package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class RegistrationTests {
    @BeforeAll
    static void setupConfig() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
    }

    @Test
    @DisplayName("Проверка полностью заполненной формы")
    @Tag("demoqa")
    void filledFormTest() {
        step("Открываем страницу с формой", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form")); // проверка, что открыта нужная стр.
            executeJavaScript("$('#fixedban').remove()"); // закрытие баннера
            executeJavaScript("$('footer').remove()"); // удаление футера
        });
        step("Заполняем форму", () -> {
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
        });
        step("Нажимаем кнопку 'Отправить'", () -> {
            $("#submit").click();
        });
        step("Проверяем, что форма заполнена корректными данными", () -> {
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
        });
        step("Закрываем форму", () -> {
            Selenide.actions().sendKeys(Keys.PAGE_DOWN).perform();
            $("#closeLargeModal").shouldBe(interactable).click();
        });
    }
        @Test
        @DisplayName("Заполнение формы только обязательных полей")
        @Tag("demoqa")
        void minValuesFormTest () {
            step("Открываем форму на главной странице", () -> {
                open("/automation-practice-form");
            });
            step("Заполняем только обязательные поля", () -> {
                $("#firstName").setValue("Tom");
                $("#lastName").setValue("Holland");
                $("#userEmail").setValue("tom@mail.ru");
                $("#userNumber").setValue("8800777900");
                $("#genterWrapper").$(byText("Male")).click();
                $("#submit").scrollTo().click();
            });
            step("Проверяем, что поля заполнены корректными данными", () -> {
                $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Tom Holland"));
                $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
                $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("8800777900"));
            });
        }
        @Test
        @DisplayName("Не заполнено обязательное поле Mobile Number")
        @Tag("demoqa")

        void notFilledMobileNumber () {
            step("Открываем главную страницу с формой", () -> {
                open("/automation-practice-form");
            });
            step("Заполняем имя, фамилию и пол и кликаем Отправить", () -> {
            $("#firstName").setValue("Tom");
            $("#lastName").setValue("Holland");
            $("#genterWrapper").$(byText("Male")).click();
            $("#submit").scrollTo().click();
            });
            step("Проверяем, что форма не отправлена", () -> {
            $(".table-responsive").shouldNot(exist);
            });
        }
    }
