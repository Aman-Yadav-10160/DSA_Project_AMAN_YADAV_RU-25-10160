# Student Attendance Management System

A simple **Java-based Student Attendance Management System** that allows users to manage student records, mark attendance, calculate attendance percentages, identify attendance defaulters, and rank students based on their attendance.

The project uses Java concepts such as classes, objects, ArrayList, loops, methods, file handling, and exception handling.

## Features

- **Add Student:** Add new students using their roll number and name.
- **Mark Attendance:** Mark students as Present (P) or Absent (A).
- **Search Student:** Find student records using their roll number.
- **List Defaulters:** Display students whose attendance is below 75%.
- **Sort Attendance:** Rank students according to their attendance percentage (highest to lowest).
- **File Handling:** Automatically load previously saved student records and save updated records to a text file.
- **Input Validation:** Handle invalid inputs and prevent duplicate roll numbers.

## Technologies Used

- Java
- Java Collections Framework (`ArrayList`)
- File Handling (`FileReader`, `FileWriter`, `BufferedReader`, `PrintWriter`)
- Exception Handling
- Object-Oriented Programming (OOP)

## Project Structure

```text
Student-Attendance-Management/
│
├── Main.java
├── students.txt
└── README.md
```

**Note:** `students.txt` is automatically created when the application saves student records for the first time.

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Student-Attendance-Management.git
```

### 2. Navigate to the Project Directory

```bash
cd Student-Attendance-Management
```

### 3. Compile the Java Program

```bash
javac Main.java
```

### 4. Run the Application

```bash
java Main
```

**Requirement:** Java JDK must be installed on your system.

## Application Menu

```text
===== Student Attendance Management =====
1. Add student
2. Mark attendance
3. Search student
4. List defaulters (below 75%)
5. Sort by attendance percentage (high to low)
6. Save and exit
```

## Attendance Calculation

The attendance percentage is calculated using the following formula:

```text
Attendance Percentage = (Classes Attended / Total Classes Held) × 100
```

**Example:**

If a student attends 45 out of 60 classes:

```text
Attendance = (45 / 60) × 100
           = 75%
```

Students with attendance below 75% are listed as defaulters.

## Data Storage

Student records are stored in `students.txt` using a comma-separated format.

Example:

```text
101,John,60,45
102,David,50,32
103,Alex,40,35
```

Each record contains:

- Roll Number
- Student Name
- Total Classes Held
- Classes Attended

The application loads existing records when it starts and saves the records when the user selects **Save and Exit**.

## Learning Outcomes

Through this project, the following Java concepts are demonstrated:

- Classes and Objects
- Constructors and Methods
- ArrayList and Collections
- Searching and Sorting Algorithms
- File Input/Output Operations
- Exception Handling
- Conditional Statements and Loops

## Author

**Your Name**

---

*This project was developed as a Java programming project to demonstrate the practical implementation of object-oriented programming and file handling.*
