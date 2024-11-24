import java.io.*;
import java.nio.file.*;

public class FileMerger {

    public static void main(String[] args) {
        // File names for the input and output
        String file1 = "file1.txt";
        String file2 = "file2.txt";
        String outputFile = "merged_output.txt";

        try {
            // Read content from the first file
            String content1 = new String(Files.readAllBytes(Paths.get(file1)));

            // Read content from the second file
            String content2 = new String(Files.readAllBytes(Paths.get(file2)));

            // Merge the contents
            String mergedContent = content1 + "\n" + content2;

            // Write the merged content to the output file
            Files.write(Paths.get(outputFile), mergedContent.getBytes());

            System.out.println("Files merged successfully into " + outputFile);

        } catch (IOException e) {
            System.out.println("An error occurred during file processing: " + e.getMessage());
        }
    }
}
