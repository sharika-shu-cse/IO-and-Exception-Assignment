import java.io.*;
import java.util.*;

public class StudentCSVManager {

    // Data structure to store student information
    private static List<Student> students = new ArrayList<>();

    // Student class
    static class Student {
        String name;
        int rollNumber;
        int marks;

        public Student(String name, int rollNumber, int marks) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Roll No: " + rollNumber + ", Marks: " + marks;
        }
    }

    public static void main(String[] args) {
        String filePath = "students.csv";

        // Load students from the CSV file
        loadStudentsFromCSV(filePath);

        // Menu-driven program
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Display all students");
            System.out.println("2. Search for a student by roll number");
            System.out.println("3. Update student information");
            System.out.println("4. Delete a student record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayAllStudents();
                case 2 -> {
                    System.out.print("Enter roll number to search: ");
                    int rollNumber = scanner.nextInt();
                    searchStudent(rollNumber);
                }
                case 3 -> {
                    System.out.print("Enter roll number to update: ");
                    int rollNumber = scanner.nextInt();
                    updateStudent(rollNumber, scanner);
                }
                case 4 -> {
                    System.out.print("Enter roll number to delete: ");
                    int rollNumber = scanner.nextInt();
                    deleteStudent(rollNumber);
                }
                case 5 -> {
                    saveStudentsToCSV(filePath);
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Load student records from the CSV file
    private static void loadStudentsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    String name = fields[0].trim();
                    int rollNumber = Integer.parseInt(fields[1].trim());
                    int marks = Integer.parseInt(fields[2].trim());
                    students.add(new Student(name, rollNumber, marks));
                }
            }
            System.out.println("Student records loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    // Save student records back to the CSV file
    private static void saveStudentsToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                bw.write(student.name + "," + student.rollNumber + "," + student.marks);
                bw.newLine();
            }
            System.out.println("Student records saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    // Display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Search for a student by roll number
    private static void searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Update student information
    private static void updateStudent(int rollNumber, Scanner scanner) {
        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                System.out.print("Enter new name: ");
                student.name = scanner.next();
                System.out.print("Enter new marks: ");
                student.marks = scanner.nextInt();
                System.out.println("Student record updated successfully!");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Delete a student record
    private static void deleteStudent(int rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().rollNumber == rollNumber) {
                iterator.remove();
                System.out.println("Student record deleted successfully!");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }
}
