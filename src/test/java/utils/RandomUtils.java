package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println(getRandomEmail());
    }

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

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return faker.options().option(genders);

    }

    public static String getRandomSubject() {
        String[] subjects = {"Computer Science", "English", "Arts", "Maths", "Social Studies", "Biology", "History"};
        return faker.options().option(subjects);
    }

    public static String getRandomHobby() {
        String[] subjects = {"Reading", "Sports", "Music"};
        return faker.options().option(subjects);

    }

    public static String getRandomImageName() {
        String[] pictures = {"cat.jpg", "dog.jpg", "owl.jpg"};
        return faker.options().option(pictures);
    }
}
