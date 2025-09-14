package tests;

import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomAddress;
import static utils.RandomUtils.getRandomEmail;
import static utils.RandomUtils.getRandomPhone;

public class TestData {
    private static final String[] subjects = {"Computer Science", "English", "Arts", "Maths", "Social Studies", "Biology", "History"};
    private static final String[] hobbies = {"Reading", "Sports", "Music"};
    private static final String[] pictures = {"cat.jpg", "dog.jpg", "owl.jpg"};
    private static final String[] genders = {"Male", "Female", "Other"};

    // todo сделать генерацию
    public static String invalidPhoneNumber = "12345";
    public static String year = "1989";
    public static String month = "February";
    public static String day = "6";
    public static String state = "Rajasthan";
    public static String city = "Jaipur";

    public String firstName = getRandomFirstName();
    public String lastName = getRandomLastName();
    public String gender = getRandomItemFromArray(genders);
    public String phoneNumber = getRandomPhone();
    public String email = getRandomEmail();
    public String currentAddress = getRandomAddress();
    public String imageName = getRandomItemFromArray(pictures);
    public String hobby = getRandomItemFromArray(hobbies);
    public String subject = getRandomItemFromArray(subjects);

    public static Integer week = 1;
}
