package studentdashboard;

import java.util.Scanner;

/**
 * Collects and validates student input (name and section).
 */
public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readName() {
        while (true) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Try again.");
                continue;
            }
            if (!name.matches("[A-Za-z .'-]+")) {
                System.out.println("Use letters, spaces, apostrophes, or hyphens only.");
                continue;
            }
            return name;
        }
    }

    public String readSection() {
        while (true) {
            System.out.print("Enter section (e.g. A, B, 10-A): ");
            String section = scanner.nextLine().trim().toUpperCase();
            if (section.isEmpty()) {
                System.out.println("Section cannot be empty. Try again.");
                continue;
            }
            if (!section.matches("[A-Z0-9-]+")) {
                System.out.println("Section may contain letters, numbers, and hyphens only.");
                continue;
            }
            return section;
        }
    }

    public Student readNewStudent(int nextId) {
        String name = readName();
        String section = readSection();
        return new Student(nextId, name, section);
    }

    public int readMenuChoice() {
        System.out.print("Choose an option: ");
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String readSearchTerm() {
        System.out.print("Search by name or section: ");
        return scanner.nextLine().trim();
    }
}
