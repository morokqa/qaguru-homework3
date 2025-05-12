package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

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
}