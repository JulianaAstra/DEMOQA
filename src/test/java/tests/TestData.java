package tests;

import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomAddress;
import static utils.RandomUtils.getRandomEmail;
import static utils.RandomUtils.getRandomPhone;
import static utils.Utils.getDate;

public class TestData {
    private static final String[] subjects = {"Computer Science", "English", "Arts", "Maths", "Social Studies", "Biology", "History"};
    private static final String[] hobbies = {"Reading", "Sports", "Music"};
    private static final String[] pictures = {"cat.jpg", "dog.jpg", "owl.jpg"};
    private static final String[] genders = {"Male", "Female", "Other"};

    public static String invalidPhoneNumber = getRandomInvalidPhone();

    public Integer year = getRandomYear();
    public String month = getRandomMonth();
    public Integer day = getRandomDayByYearAndMonth(year, month);
    public String fullDate = getDate(month, day);

    // todo сделать генерацию
    public String state = "Rajasthan";
    public String city = "Jaipur";

    public String firstName = getRandomFirstName();
    public String lastName = getRandomLastName();
    public String gender = getRandomItemFromArray(genders);
    public String phoneNumber = getRandomPhone();
    public String email = getRandomEmail();
    public String currentAddress = getRandomAddress();
    public String imageName = getRandomItemFromArray(pictures);
    public String hobby = getRandomItemFromArray(hobbies);
    public String subject = getRandomItemFromArray(subjects);
}
