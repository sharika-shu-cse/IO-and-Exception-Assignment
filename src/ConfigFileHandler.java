import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ConfigFileHandler {

    public static void main(String[] args) {
        String configFile = "config.properties"; // Configuration file name

        // Load the properties from the file
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFile)) {
            properties.load(input);
            System.out.println("Loaded properties:");
            properties.forEach((key, value) -> System.out.println(key + " = " + value));
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found. A new file will be created.");
        } catch (IOException e) {
            System.out.println("Error reading the configuration file: " + e.getMessage());
        }

        // User interaction for updating values
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nChoose an action:");
                System.out.println("1. View a property");
                System.out.println("2. Update a property");
                System.out.println("3. Add a new property");
                System.out.println("4. Save and exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter the key to view: ");
                        String viewKey = scanner.nextLine();
                        System.out.println(viewKey + " = " + properties.getProperty(viewKey, "Key not found"));
                        break;

                    case 2:
                        System.out.print("Enter the key to update: ");
                        String updateKey = scanner.nextLine();
                        if (properties.containsKey(updateKey)) {
                            System.out.print("Enter the new value: ");
                            String newValue = scanner.nextLine();
                            properties.setProperty(updateKey, newValue);
                            System.out.println("Property updated.");
                        } else {
                            System.out.println("Key not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter the new key: ");
                        String newKey = scanner.nextLine();
                        System.out.print("Enter the value for the new key: ");
                        String newValue = scanner.nextLine();
                        properties.setProperty(newKey, newValue);
                        System.out.println("New property added.");
                        break;

                    case 4:
                        try (FileOutputStream output = new FileOutputStream(configFile)) {
                            properties.store(output, "Updated configuration file");
                            System.out.println("Changes saved to file. Exiting...");
                        } catch (IOException e) {
                            System.out.println("Error saving the configuration file: " + e.getMessage());
                        }
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}
