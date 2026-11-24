package Data.src;
import java.io.*;
import java.util.*;

class Student {
    int roll;
    String name;
    int held;
    int attended;

    Student(int roll, String name, int held, int attended) {
        this.roll = roll;
        this.name = name;
        this.held = held;
        this.attended = attended;
    }

    double percentage() {
        return held == 0 ? 0.0 : (attended * 100.0) / held;
    }

    String toFileLine() {
        return roll + "," + name + "," + held + "," + attended;
    }
}

public class Main {
    static final String FILE_NAME = "students.txt";
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: markAttendance(); break;
                case 3: searchStudent(); break;
                case 4: listDefaulters(); break;
                case 5: sortByPercentage(); break;
                case 6:
                    saveToFile();
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                    break;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n===== Student Attendance Management =====");
        System.out.println("1. Add student");
        System.out.println("2. Mark attendance");
        System.out.println("3. Search student");
        System.out.println("4. List defaulters (below 75%)");
        System.out.println("5. Sort by attendance percentage (high to low)");
        System.out.println("6. Save and exit");
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static Student findByRoll(int roll) {
        for (Student s : students) {
            if (s.roll == roll) return s;
        }
        return null;
    }

    static void addStudent() {
        int roll = readInt("Roll: ");
        if (findByRoll(roll) != null) {
            System.out.println("Roll " + roll + " already exists.");
            return;
        }
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty() || name.contains(",")) {
            System.out.println("Invalid name.");
            return;
        }
        students.add(new Student(roll, name, 0, 0));
        System.out.println("Student added. " + roll + " | " + name);
    }

    static void markAttendance() {
        int roll = readInt("Roll: ");
        Student s = findByRoll(roll);
        if (s == null) {
            System.out.println("Roll " + roll + " not found.");
            return;
        }
        String status;
        while (true) {
            System.out.print("Present or Absent (P/A): ");
            status = sc.nextLine().trim().toUpperCase();
            if (status.equals("P") || status.equals("A")) break;
            System.out.println("Enter only P or A.");
        }
        s.held++;
        if (status.equals("P")) s.attended++;
        System.out.println(s.roll + " | " + s.name + " | " + s.attended + "/" + s.held
                + " | " + String.format("%.1f", s.percentage()) + "%");
    }

    static void searchStudent() {
        int roll = readInt("Roll: ");
        Student s = findByRoll(roll);
        if (s == null) {
            System.out.println("Roll " + roll + " not found.");
            return;
        }
        System.out.println(s.roll + " | " + s.name + " | Attendance: " + s.attended + "/" + s.held
                + " | " + String.format("%.1f", s.percentage()) + "%");
    }

    static void listDefaulters() {
        boolean found = false;
        for (Student s : students) {
            if (s.percentage() < 75) {
                System.out.println(s.roll + " | " + s.name + " | " + String.format("%.1f", s.percentage()) + "%");
                found = true;
            }
        }
        if (!found) System.out.println("No defaulters.");
    }

    static void sortByPercentage() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        ArrayList<Student> sorted = new ArrayList<>(students);
        // Selection-style bubble sort, high to low
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).percentage() < sorted.get(j + 1).percentage()) {
                    Student temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        int rank = 1;
        for (Student s : sorted) {
            System.out.println(rank++ + ". " + s.roll + " | " + s.name + " | "
                    + String.format("%.1f", s.percentage()) + "%");
        }
    }

    static void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 4) continue;
                try {
                    students.add(new Student(Integer.parseInt(p[0].trim()), p[1].trim(),
                            Integer.parseInt(p[2].trim()), Integer.parseInt(p[3].trim())));
                } catch (NumberFormatException e) {
                    // skip corrupted line
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read " + FILE_NAME + ": " + e.getMessage());
        }
    }

    static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toFileLine());
            }
        } catch (IOException e) {
            System.out.println("Could not save " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
