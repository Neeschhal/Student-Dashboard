package studentdashboard;

/**
 * One student record: name and section collected from input.
 */
public class Student {
    private final int id;
    private final String name;
    private final String section;

    public Student(int id, String name, String section) {
        this.id = id;
        this.name = name;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public String toFileLine() {
        return id + "|" + name + "|" + section;
    }

    public static Student fromFileLine(String line) {
        String[] parts = line.split("\\|", 3);
        if (parts.length != 3) {
            return null;
        }
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String section = parts[2].trim();
            if (name.isEmpty() || section.isEmpty()) {
                return null;
            }
            return new Student(id, name, section);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("#%-4d  %-20s  Section %s", id, name, section);
    }
}
