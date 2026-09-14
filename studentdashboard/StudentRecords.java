package studentdashboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Stores student records in memory and on disk (students.txt).
 */
public class StudentRecords {
    private final List<Student> students = new ArrayList<>();
    private final Path filePath;
    private int nextId = 1;

    public StudentRecords(Path filePath) {
        this.filePath = filePath;
        load();
    }

    public Student add(Student student) {
        students.add(student);
        nextId = Math.max(nextId, student.getId() + 1);
        save();
        return student;
    }

    public int nextId() {
        return nextId;
    }

    public List<Student> getAll() {
        return Collections.unmodifiableList(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public List<Student> search(String term) {
        String needle = term.toLowerCase(Locale.ROOT);
        List<Student> matches = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase(Locale.ROOT).contains(needle)
                    || student.getSection().toLowerCase(Locale.ROOT).contains(needle)) {
                matches.add(student);
            }
        }
        return matches;
    }

    private void load() {
        if (!Files.exists(filePath)) {
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }
                Student student = Student.fromFileLine(line);
                if (student != null) {
                    students.add(student);
                    nextId = Math.max(nextId, student.getId() + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load records: " + e.getMessage());
        }
    }

    private void save() {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                writer.write("# id|name|section");
                writer.newLine();
                for (Student student : students) {
                    writer.write(student.toFileLine());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Could not save records: " + e.getMessage());
        }
    }
}
