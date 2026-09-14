# Student Dashboard

A console application for registering students and managing a simple roster. Staff (or a student at the keyboard) can enter a name and section, review stored records, and search by name or section. Records are saved locally so they remain after the program exits.

---

## Overview

Student Dashboard is a small Java project built with separate classes for input, storage, and display. It is suitable as a lab or office prototype: it does not require a database or a graphical interface.

On startup the program loads `data/students.txt` (if it exists), then shows a numbered menu. New registrations are validated, given a sequential ID, written to that file, and listed on request.

---

## Features

- Register a student with **name** and **section**
- Automatic sequential **student ID**
- **View all** records in a table with a total count
- **Search** by partial name or section (case-insensitive)
- Input **validation** (required fields, allowed characters)
- **Persistent storage** in `data/students.txt` (UTF-8, pipe-delimited)
- Invalid menu choices are rejected without crashing
- Empty roster or empty search results show a clear message

---

## Technologies

| Area | Choice |
|------|--------|
| Language | Java (JDK 11 or later; `Path.of` is used) |
| Runtime | Command-line / standard I/O |
| Storage | Plain-text file (`data/students.txt`) |
| Build | `javac` / `java` (no Maven or Gradle) |

---

## Requirements

**To run**

- [JDK 11+](https://adoptium.net/) installed, with `javac` and `java` on your `PATH`
- Windows, macOS, or Linux

**Name rules (as implemented)**

- Required; letters, spaces, apostrophes, periods, and hyphens only (`A–Z`, `a–z`, space, `'`, `.`, `-`)

**Section rules (as implemented)**

- Required; stored in uppercase; letters, digits, and hyphens only (for example `A`, `B`, `10-A`)

---

## Installation

1. Install a JDK 11 or newer and confirm it is available:

   ```bash
   java -version
   javac -version
   ```

2. Copy or clone this project so the folder `Student dashboard` (or your clone path) contains the `src` directory.

3. Open a terminal in the **project root** (the folder that contains `src`).

---

## How to run

From the project root:

```bash
javac -d bin src/studentdashboard/*.java
java -cp bin studentdashboard.Main
```

On Windows PowerShell you can use the same commands, or:

```powershell
javac -d bin src\studentdashboard\*.java
java -cp bin studentdashboard.Main
```

The working directory must be the project root. Records are stored at `data/students.txt` **relative to where you launch** `java`. If you start the program from another folder, a different file will be used.

To quit, choose menu option `4`.

---

## Example usage

```text
========================================
         STUDENT DASHBOARD
========================================

1. Register student (name & section)
2. View all records
3. Search records
4. Exit
Choose an option: 1
Enter student name: Priya Sharma
Enter section (e.g. A, B, 10-A): 10-a

Saved: Priya Sharma | Section 10-A

1. Register student (name & section)
2. View all records
3. Search records
4. Exit
Choose an option: 2

ID     NAME                  SECTION
----------------------------------------
#1     Priya Sharma          Section 10-A
Total: 1
```

Search example:

```text
Choose an option: 3
Search by name or section: priya
```

Matching rows are printed in the same table format. After a successful register, `data/students.txt` looks like:

```text
# id|name|section
1|Priya Sharma|10-A
```

---

## Project structure

```text
Student dashboard/
├── README.md
├── src/
│   └── studentdashboard/
│       ├── Main.java              # Entry point and menu loop
│       ├── Student.java           # One record: id, name, section
│       ├── InputHandler.java      # Reads and validates keyboard input
│       ├── StudentRecords.java    # In-memory list, load/save, search
│       └── Dashboard.java         # Welcome, menu, and printed tables
├── bin/                           # Created by javac (compiled .class files)
└── data/
    └── students.txt               # Created on first successful save
```

| Class | Responsibility |
|-------|----------------|
| `Main` | Starts the app and connects the other classes |
| `InputHandler` | Name, section, menu, and search prompts |
| `Student` | Data model and file-line parse/format |
| `StudentRecords` | Add, list, search, persist |
| `Dashboard` | User-facing text |

---

## Future improvements

These are not in the current build; they are the next steps if this is used as a real office tool.

- Graphical or web dashboard instead of console-only
- Official unique identifier (roll number or email) and duplicate checks
- Edit and delete (or deactivate) a record
- Closed list of allowed sections
- Support for names in other scripts and with diacritics
- Login and roles (clerk vs administrator)
- Distinct messages for “no records yet” vs “no search matches”
- Reject empty search instead of matching every record
- Warn when file lines are skipped as corrupt
- Fixed storage path (not dependent on the working directory)
- Backup, file locking, and a proper database if multiple staff share the roster
- Automated tests for validation, search, and file load/save
