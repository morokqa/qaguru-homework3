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
    String dayOfBirth = randomDay();
    String monthOfBirth = randomMonth();
    String yearOfBirth = randomYear();
    String userSubject = randomSubject();
    String userHobby = randomHobby();
    String userPicture = "image.jpg";
    String userAddress = randomAddress();
    String userState = randomState();
    String userCity = randomCity(userState);

    @Test
    @DisplayName("Проверка полностью заполненной формы")
    void filledFormTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setUserNumber(userPhoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(userSubject)
                .setHobby(userHobby)
                .setPicture(userPicture)
                .setCurrentAddress(userAddress)
                .setState(userState)
                .setCity(userCity)
                .submitClick()

                .checkResults("Student Name", firstName + " " + lastName)
                .checkResults("Student Email", userEmail)
                .checkResults("Gender", userGender)
                .checkResults("Mobile", userPhoneNumber)
                .checkResults("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResults("Subjects", userSubject)
                .checkResults("Hobbies", userHobby)
                .checkResults("Picture", userPicture)
                .checkResults("Address", userAddress)
                .checkResults("State and City", userState + " " + userCity);

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

                .checkResults("Student Name", firstName + " " + lastName)
                .checkResults("Mobile", userPhoneNumber)
                .checkResults("Gender", userGender);
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
