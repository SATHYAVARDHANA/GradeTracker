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

        // Display Summary in a box
        String line = "╔══════════════════════════════╗";
        String middleLine = "╠══════════════════════════════╣";
        String bottomLine = "╚══════════════════════════════╝";

        System.out.println(line);
        System.out.println("║        SUMMARY REPORT        ║");
        System.out.println(middleLine);

        for (Student s : students) {
            System.out.printf("║ %-15s : %5d ║\n", s.name, s.grade);
        }

        System.out.println(middleLine);
        System.out.printf("║ %-15s : %5.2f ║\n", "Average Score", average);
        System.out.printf("║ %-15s : %5d ║\n", "Highest (" + topStudent + ")", highest);
        System.out.printf("║ %-15s : %5d ║\n", "Lowest (" + lowStudent + ")", lowest);
        System.out.println(bottomLine);
    }
}
