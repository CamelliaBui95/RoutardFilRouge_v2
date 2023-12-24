package fr.routardfilrouge.routard.utils;

public class DataValidator {
    public static boolean isValidFloat(String floatString) {
        try {
            Float.parseFloat(floatString);
            return true;
        } catch(NumberFormatException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidInteger(String integerString) {
        try {
            Integer.parseInt(integerString);
            return true;
        } catch(NumberFormatException e) {
            //e.printStackTrace();
            return false;
        }
    }
}
