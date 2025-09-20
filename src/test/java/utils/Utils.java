package utils;

public class Utils {
    public static String getDate(String month, int day) {
        return month + " " + day;
    }

    public static String addZeroToNumber(int number) {
        if (number > 9) {
            return String.valueOf(number);
        }
        return "0" + number;
    }
}
