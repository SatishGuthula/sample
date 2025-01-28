package sample;

public class Utils {
    /**
     * Reverses the given string.
     * @param input The string to reverse
     * @return The reversed string
     */
    public static String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Checks if the string is palindrome.
     * @param input The string to check
     * @return true if the string is palindrome, false otherwise
     */
    public static boolean isPalindrome(String input) {
        if (input == null) return false;
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
}
