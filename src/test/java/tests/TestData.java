package tests;

import com.github.javafaker.Faker;
import static utils.RandomUtils.*;

public class TestData {
    static Faker faker = new Faker();

    private static final String[] subjects = {"Computer Science", "English", "Arts", "Maths", "Social Studies", "Biology", "History"};
    private static final String[] hobbies = {"Reading", "Sports", "Music"};
    private static final String[] pictures = {"cat.jpg", "dog.jpg", "owl.jpg"};
    private static final String[] genders = {"Male", "Female", "Other"};

    private static final String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    public static final String[] citiesOfNCR = {"Delhi", "Gurgaon", "Noida"};
    public static final String[] citiesOfUttarPradesh = {"Agra", "Lucknow", "Merrut"};
    public static final String[] citiesOfHaryana = {"Karnal", "Panipat"};
    public static final String[] citiesOfRajasthan = {"Jaipur", "Jaiselmer"};

    public static String invalidPhoneNumber = faker.phoneNumber().subscriberNumber(9);

    public String state = faker.options().option(states);
    public String city = getRandomCityByState(state);

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String gender = faker.options().option(genders);
    public String phoneNumber = faker.phoneNumber().subscriberNumber(10);
    public String email = faker.internet().emailAddress();
    public String currentAddress = faker.address().fullAddress();
    public String permanentAddress = faker.address().fullAddress();
    public String imageName = faker.options().option(pictures);
    public String hobby = faker.options().option(hobbies);
    public String subject = faker.options().option(subjects);

    // date of birth
    public static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public Integer year = getRandomYear();
    public String month = faker.options().option(months);
    public String day = getRandomDayByYearAndMonth(year, month);
}
