import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuestionAssigner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for number of students and questions per student
        System.out.print("Enter the number of students: ");
        int totalStudents = scanner.nextInt();

        System.out.print("Enter the number of questions each student should receive: ");
        int questionsPerStudent = scanner.nextInt();

        // Assume total questions are 30 as per the initial requirement
        int totalQuestions = 30;
        if (questionsPerStudent > totalQuestions) {
            System.out.println("Error: Number of questions per student cannot exceed the total number of available questions (30).");
            return;
        }

        // Create a FileWriter to write the output to a file
        try (FileWriter writer = new FileWriter("assigned_questions.txt")) {
            
            // Write header information to the file
            writer.write("Assigned Questions for Each Student\n");
            writer.write("===================================\n");

            // Assign questions for each student and write to file
            for (int student = 1; student <= totalStudents; student++) {
                // Get a unique set of questions for each student
                List<Integer> assignedQuestions = assignQuestions(totalQuestions, questionsPerStudent);
                
                // Write the assignment for the student to the file
                writer.write("Student " + student + ": " + assignedQuestions + "\n");
            }
            
            System.out.println("Assigned questions written to assigned_questions.txt successfully!");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }

    // Method to assign a unique set of questions
    private static List<Integer> assignQuestions(int totalQuestions, int questionsPerStudent) {
        List<Integer> questionPool = new ArrayList<>();

        // Add all question numbers (1 to 30) to the pool
        for (int i = 1; i <= totalQuestions; i++) {
            questionPool.add(i);
        }

        // Shuffle the question pool to randomize
        Collections.shuffle(questionPool);

        // Select the first `questionsPerStudent` questions
        return questionPool.subList(0, questionsPerStudent);
    }
}
