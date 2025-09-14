package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    static Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String getRandomPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomItemFromArray(String[] array) {
        return faker.options().option(array);
    }
}
