import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class GradeTrackerGUI extends JFrame {
    private JTextField nameField, gradeField;
    private JButton addButton, calcButton;
    private JTable table;
    private DefaultTableModel model;
    private JLabel resultLabel;

    private ArrayList<Student> students = new ArrayList<>();

    public GradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        // Buttons
        addButton = new JButton("Add Student");
        calcButton = new JButton("Calculate Summary");

        // Table
        model = new DefaultTableModel(new Object[]{"Name", "Grade"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Result label
        resultLabel = new JLabel("Summary will appear here");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.PAGE_END);

        // Action for Add Student
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String gradeText = gradeField.getText();
                try {
                    int grade = Integer.parseInt(gradeText);
                    students.add(new Student(name, grade));
                    model.addRow(new Object[]{name, grade});
                    nameField.setText("");
                    gradeField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for grade.");
                }
            }
        });

        // Action for Calculate
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (students.isEmpty()) {
                    resultLabel.setText("No students added yet.");
                    return;
                }

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

                double avg = (double) total / students.size();
                resultLabel.setText("<html>Average: " + avg +
                        "<br>Highest: " + highest + " (" + topStudent + ")" +
                        "<br>Lowest: " + lowest + " (" + lowStudent + ")</html>");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GradeTrackerGUI().setVisible(true);
        });
    }
}
