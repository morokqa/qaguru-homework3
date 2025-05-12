package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    public static Faker faker = new Faker();

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomUserEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String randomPhoneNumber() {
        return faker.numerify("##########");
    }

    public static String randomDay() {
        int day = faker.number().numberBetween(1, 32);
        return String.format("%02d", day);
    }

    public static String randomMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    }

    public static String randomYear() {
        return String.valueOf(faker.number().numberBetween(1915, 2015));
    }

    public static String randomSubject() {
        return faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology", "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History", "Civics");
    }

    public static String randomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String randomAddress() {
        return faker.address().streetAddress();
    }
    public static String randomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }
    public static String randomCity(String userState) {
        switch (userState) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                return null;
        }
    }
}
