package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;


import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateList = $("#state"),
            stateInput = $("#stateCity-wrapper"),
            cityList = $("#city"),
            cityInput = $("#stateCity-wrapper"),
            submitButton = $("#submit"),
            filledTable = $(".table-responsive");


    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsTableComponent resultsTable = new ResultsTableComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectInput.setValue(value).sendKeys(Keys.RETURN);

        return this;
    }

    public RegistrationPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPicture(String value) {
        uploadPicture.uploadFromClasspath("image.jpg");

        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.setValue("Mira, 1");

        return this;
    }

    public RegistrationPage setState(String value) {
        stateList.click();
        stateInput.$(byText("Uttar Pradesh")).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        cityList.click();
        cityInput.$(byText("Merrut")).click();

        return this;
    }

    public RegistrationPage submitClick() {
        submitButton.click();

        return this;
    }
    public RegistrationPage submitScrollAndClick() {
        submitButton.scrollTo().click();

        return this;
    }

    public RegistrationPage checkResults(String key, String value) {
        resultsTable.checkResult(key, value);

        return this;
    }

    public RegistrationPage checkTableNotOpen() {
        filledTable.shouldNot(exist);

        return this;
    }
}

