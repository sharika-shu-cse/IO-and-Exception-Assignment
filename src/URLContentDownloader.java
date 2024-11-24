import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class URLContentDownloader {

    public static void main(String[] args) {
        // The path to the input file containing URLs
        String urlFilePath = "urls.txt"; // Input file with list of URLs
        List<String> urls = readURLsFromFile(urlFilePath);

        // Download content from each URL and save to a separate file
        for (String urlString : urls) {
            downloadAndSaveContent(urlString);
        }
    }

    // Read the URLs from the file
    private static List<String> readURLsFromFile(String filePath) {
        List<String> urls = new ArrayList<>();
        try {
            urls = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading URL file: " + e.getMessage());
        }
        return urls;
    }

    // Download content from the URL and save to a file
    private static void downloadAndSaveContent(String urlString) {
        try {
            // Create a URL object
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Set timeout to 5 seconds
            connection.setReadTimeout(5000);    // Set read timeout to 5 seconds

            // Check if the connection was successful
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read content from the URL
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    // Save content to a file named after the URL (sanitized)
                    String fileName = getSanitizedFileName(urlString);
                    Files.write(Paths.get(fileName), content.toString().getBytes());
                    System.out.println("Content from " + urlString + " saved to " + fileName);

                } catch (IOException e) {
                    System.out.println("Error reading content from " + urlString + ": " + e.getMessage());
                }
            } else {
                System.out.println("Failed to connect to " + urlString + ". Response code: " + responseCode);
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + urlString);
        } catch (IOException e) {
            System.out.println("Error opening connection to " + urlString + ": " + e.getMessage());
        }
    }

    // Sanitize the URL to create a valid file name
    private static String getSanitizedFileName(String urlString) {
        String sanitized = urlString.replaceAll("[^a-zA-Z0-9]", "_"); // Replace non-alphanumeric characters with underscores
        return sanitized + ".html"; // Save as an HTML file
    }
}

