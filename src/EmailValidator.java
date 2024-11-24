import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class EmailValidator {

    // Regular expression for validating email addresses
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void main(String[] args) {
        // The path to the input file containing email addresses
        String inputFilePath = "emails.txt";  // Input file with list of email addresses
        String outputFilePath = "valid_emails.txt";  // Output file for valid email addresses

        // Read and validate emails
        List<String> validEmails = readAndValidateEmails(inputFilePath);

        // Write valid emails to the output file
        writeValidEmailsToFile(validEmails, outputFilePath);

        System.out.println("Valid emails have been written to " + outputFilePath);
    }

    // Read email addresses from the file and validate them
    private static List<String> readAndValidateEmails(String filePath) {
        List<String> validEmails = new ArrayList<>();
        try {
            // Read all lines (email addresses) from the file
            List<String> emails = Files.readAllLines(Paths.get(filePath));

            // Validate each email address
            for (String email : emails) {
                if (isValidEmail(email)) {
                    validEmails.add(email);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the email file: " + e.getMessage());
        }
        return validEmails;
    }

    // Validate the email using regex
    private static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Write valid emails to the output file
    private static void writeValidEmailsToFile(List<String> validEmails, String filePath) {
        try {
            // Write valid emails to the output file
            Files.write(Paths.get(filePath), validEmails);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
