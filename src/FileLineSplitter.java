import java.io.*;

public class FileLineSplitter {

    public static void main(String[] args) {
        // Input and output file paths
        String inputFile = "input.txt";
        String evenLinesFile = "even_lines.txt";
        String oddLinesFile = "odd_lines.txt";

        // Process the file
        splitLinesToFiles(inputFile, evenLinesFile, oddLinesFile);
    }

    public static void splitLinesToFiles(String inputFile, String evenFile, String oddFile) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter evenWriter = new BufferedWriter(new FileWriter(evenFile));
                BufferedWriter oddWriter = new BufferedWriter(new FileWriter(oddFile))
        ) {
            String line;
            int lineNumber = 1;

            // Read file line by line
            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    evenWriter.write(line);
                    evenWriter.newLine();
                } else {
                    oddWriter.write(line);
                    oddWriter.newLine();
                }
                lineNumber++;
            }
            System.out.println("Lines have been split into even_lines.txt and odd_lines.txt successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: An issue occurred while processing the files - " + e.getMessage());
        }
    }
}

