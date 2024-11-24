import java.io.*;
import java.util.Scanner;

public class BinaryFileHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input file name
        System.out.print("Enter the source binary file name: ");
        String sourceFile = scanner.nextLine();

        // Output file name
        System.out.print("Enter the destination binary file name: ");
        String destinationFile = scanner.nextLine();

        // Read from source and write to destination
        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Contents copied successfully to " + destinationFile);

        } catch (FileNotFoundException e) {
            System.out.println("Source file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Error during file processing: " + e.getMessage());
            return;
        }

        // Append data to the destination file
        System.out.print("Do you want to append data to the destination file? (yes/no): ");
        String choice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(choice)) {
            System.out.print("Enter the data to append (text or binary in hex format): ");
            String dataToAppend = scanner.nextLine();

            try (FileOutputStream appendStream = new FileOutputStream(destinationFile, true)) {
                appendStream.write(dataToAppend.getBytes());
                System.out.println("Data appended successfully.");
            } catch (IOException e) {
                System.out.println("Error while appending data: " + e.getMessage());
            }
        }
    }
}

