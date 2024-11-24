import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class WordReplacer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input file name
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        // Get the word to replace and the replacement word
        System.out.print("Enter the word to replace: ");
        String wordToReplace = scanner.nextLine();

        System.out.print("Enter the replacement word: ");
        String replacementWord = scanner.nextLine();

        try {
            // Read the content of the file
            String content = new String(Files.readAllBytes(Paths.get(fileName)));

            // Replace the specific word
            String modifiedContent = content.replaceAll("\\b" + wordToReplace + "\\b", replacementWord);

            // Write the modified content back to the file
            Files.write(Paths.get(fileName), modifiedContent.getBytes());

            System.out.println("The word has been replaced and the file has been updated successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
