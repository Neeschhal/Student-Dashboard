package studentdashboard;

import java.util.List;

/**
 * Prints the student dashboard menus and record tables.
 */
public class Dashboard {

    public void printWelcome() {
        System.out.println("========================================");
        System.out.println("         STUDENT DASHBOARD");
        System.out.println("========================================");
    }

    public void printMenu() {
        System.out.println();
        System.out.println("1. Register student (name & section)");
        System.out.println("2. View all records");
        System.out.println("3. Search records");
        System.out.println("4. Exit");
    }

    public void printStudentAdded(Student student) {
        System.out.println();
        System.out.println("Saved: " + student.getName() + " | Section " + student.getSection());
    }

    public void printRecords(List<Student> students) {
        System.out.println();
        if (students.isEmpty()) {
            System.out.println("No student records yet.");
            return;
        }
        System.out.println("ID     NAME                  SECTION");
        System.out.println("----------------------------------------");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Total: " + students.size());
    }

    public void printInvalidChoice() {
        System.out.println("Please enter 1, 2, 3, or 4.");
    }

    public void printGoodbye() {
        System.out.println("Goodbye.");
    }
}
