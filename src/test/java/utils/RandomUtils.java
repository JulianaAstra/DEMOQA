package utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.devtools.v136.tethering.Tethering;
import tests.TestData;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import static tests.TestData.*;

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

    public static String getRandomInvalidPhone() {
        return faker.phoneNumber().subscriberNumber(9);
    }

    public static String getRandomItemFromArray(String[] array) {
        return faker.options().option(array);
    }

    public static Integer getRandomYear() {
        return faker.number().numberBetween(1900, 2100);
    }

    public static String getRandomMonth() {
        int monthNumber = faker.number().numberBetween(1, 12);
        return Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public static Integer getRandomDayByYearAndMonth(int year, String monthName) {
        Month month = Month.valueOf(monthName.trim().toUpperCase());
        int days = YearMonth.of(year, month.getValue()).lengthOfMonth();

        return faker.number().numberBetween(1, days);
    }

    public static String getRandomCityByState(String state) {
        return switch (state) {
            case "NCR" -> getRandomItemFromArray(citiesOfNCR);
            case "Uttar Pradesh" -> getRandomItemFromArray(citiesOfUttarPradesh);
            case "Haryana" -> getRandomItemFromArray(citiesOfHaryana);
            case "Rajasthan" -> getRandomItemFromArray(citiesOfRajasthan);
            default -> "";
        };
    }
}
