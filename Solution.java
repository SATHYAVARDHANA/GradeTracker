import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===");
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input student details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            System.out.print("Enter " + name + "'s grade: ");
            int grade = sc.nextInt();
            sc.nextLine();
            students.add(new Student(name, grade));
        }

        // Calculations
        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                lowStudent = s.name;
            }
        }

        double average = (double) total / students.size();

        // Display Summary
        System.out.println("\n=== Summary Report ===");
        for (Student s : students) {
            System.out.println(s.name + " - " + s.grade);
        }
        System.out.println("---------------------------");
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest + " (" + topStudent + ")");
        System.out.println("Lowest Score: " + lowest + " (" + lowStudent + ")");
    }
}
