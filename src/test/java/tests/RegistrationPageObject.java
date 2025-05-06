package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Проверка полностью заполненной формы")
    void filledFormTest() {
        registrationPage.openPage()
                .setFirstName("Tom")
                .setLastName("Holland")
                .setEmail("tom@mail.ru")
                .setGender("Male")
                .setUserNumber("8800777900")
                .setDateOfBirth("22", "May", "1989")
                .setSubjects("bi")
                .setHobby("Music")
                .setPicture("image.jpg")
                .setCurrentAddress("Mira, 1")
                .setState("Uttar Pradesh")
                .setCity("Merrut")
                .submitClick()

                .checkResults("Student Name", "Tom Holland")
                .checkResults("Student Email", "tom@mail.ru")
                .checkResults("Gender", "Male")
                .checkResults("Mobile", "8800777900")
                .checkResults("Date of Birth", "22 May,1989")
                .checkResults("Subjects", "Biology")
                .checkResults("Hobbies", "Music")
                .checkResults("Picture", "image.jpg")
                .checkResults("Address", "Mira, 1")
                .checkResults("State and City", "Uttar Pradesh Merrut");

    }

    @Test
    @DisplayName("Форма заполнена минимальным количеством данных")
    void minValuesFormTest() {
        registrationPage.openPage()
                .setFirstName("Tom")
                .setLastName("Holland")
                .setGender("Male")
                .setUserNumber("8800777900")
                .submitScrollAndClick()

                .checkResults("Student Name", "Tom Holland")
                .checkResults("Mobile", "8800777900")
                .checkResults("Gender", "Male");
    }

    @Test
    @DisplayName("Не заполнено обязательное поле Mobile Number")

    void notFilledMobileNumber() {
        registrationPage.openPage()
                .setFirstName("Tom")
                .setLastName("Holland")
                .setGender("Male")
                .submitScrollAndClick()

                .checkTableNotOpen();
    }
}
