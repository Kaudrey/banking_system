package rca.bank;

import java.util.regex.*;
public class InputValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_NUMBER_PATTERN = "^(07\\d{8}|2507\\d{9})$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phoneNumberPattern = Pattern.compile(PHONE_NUMBER_PATTERN);
    public static boolean isValidPassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public static String getPasswordErrorMessage(String password) {
        if (!isValidPassword(password)) {
            return "Invalid password. Password must contain at least 8 characters including uppercase, lowercase, digit, and special characters.";
        }
        return null; // Return null if password is valid
    }
    public static String getEmailErrorMessage(String email) {
        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }
        return null; // Return null if email is valid
    }
    public static String getPhoneNumberErrorMessage(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            return "Invalid phone number format.";
        }
        return null; // Return null if phone number is valid
    }
}







