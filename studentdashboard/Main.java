package studentdashboard;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point: wires input, records, and dashboard display.
 */
public class Main {
    public static void main(String[] args) {
        Path recordsFile = Path.of("data", "students.txt");
        StudentRecords records = new StudentRecords(recordsFile);
        Dashboard dashboard = new Dashboard();

        try (Scanner scanner = new Scanner(System.in)) {
            InputHandler input = new InputHandler(scanner);
            dashboard.printWelcome();

            boolean running = true;
            while (running) {
                dashboard.printMenu();
                int choice = input.readMenuChoice();
                switch (choice) {
                    case 1:
                        Student student = input.readNewStudent(records.nextId());
                        records.add(student);
                        dashboard.printStudentAdded(student);
                        break;
                    case 2:
                        dashboard.printRecords(records.getAll());
                        break;
                    case 3:
                        String term = input.readSearchTerm();
                        List<Student> matches = records.search(term);
                        dashboard.printRecords(matches);
                        break;
                    case 4:
                        dashboard.printGoodbye();
                        running = false;
                        break;
                    default:
                        dashboard.printInvalidChoice();
                        break;
                }
            }
        }
    }
}
