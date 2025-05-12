package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static utils.RandomUtils.*;


public class RegistrationPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = randomFirstName();
    String lastName = randomLastName();
    String userEmail = randomUserEmail();
    String userGender = randomGender();
    String userPhoneNumber = randomPhoneNumber();
//    String dayOfBirth = randomDay();
//    String monthOfBirth = randomMonth();
//    String yearOfBirth = randomYear();
//    String userSubject = randomSubject();
//    String userHobby = randomHobby();
//    String userAddress = randomAddress();
//    String userState = randomState();
//    String userCity = randomCity();

    @Test
    @DisplayName("Проверка полностью заполненной формы")
    void filledFormTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setUserNumber(userPhoneNumber)
                .setDateOfBirth("22", "May", "1989")
                .setSubjects("bi")
                .setHobby("Music")
                .setPicture("image.jpg")
                .setCurrentAddress("Mira, 1")
                .setState("Uttar Pradesh")
                .setCity("Merrut")
                .submitClick()

                .checkResults("Student Name", firstName + " " + lastName)
                .checkResults("Student Email", userEmail)
                .checkResults("Gender", userGender)
                .checkResults("Mobile", userPhoneNumber)
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
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(userGender)
                .setUserNumber(userPhoneNumber)
                .submitScrollAndClick()

                .checkResults("Student Name", "Tom Holland")
                .checkResults("Mobile", "8800777900")
                .checkResults("Gender", "Male");
    }

    @Test
    @DisplayName("Не заполнено обязательное поле Mobile Number")

    void notFilledMobileNumber() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(userGender)
                .submitScrollAndClick()

                .checkTableNotOpen();
    }
}
