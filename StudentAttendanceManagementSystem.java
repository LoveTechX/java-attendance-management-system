import java.util.Scanner;

public class StudentAttendanceManagementSystem {
    public static void main(String[] args) {

        // Creating Scanner object to take user input
        Scanner sc = new Scanner(System.in);

        // Variables to store number of students, working days and menu choice
        int n = 0, w = 0, choice = 0;

        // Displaying heading for registration
        System.out.println("=======================================");
        System.out.println("Student Registration");
        System.out.println("B.Tech IT 4th Sem - B");
        System.out.println("=======================================");

        // Taking total number of students with validation
        do {
            System.out.print("Enter total number of students: ");
            n = sc.nextInt();
            if (n <= 0) {
                System.out.println("Invalid Input! Please Try again.");
            }
        } while (n <= 0);

        // Taking total working days with validation
        do {
            System.out.print("Enter total working days: ");
            w = sc.nextInt();
            if (w <= 0) {
                System.out.println("Invalid Input! Please Try again.");
            }
            System.out.println();
        } while (w <= 0);

        // Creating 1D arrays for roll numbers and names
        int roll[] = new int[n];
        String name[] = new String[n];

        // Creating 2D array to store attendance (students x days)
        int attendance[][] = new int[n][w];

        // Loop to register each student
        for (int i = 0; i < n; i++) {

            System.out.println("Enter Student Details of student " + (i + 1) + ": ");

            // Taking roll number input
            System.out.print("Enter Roll Number: ");
            roll[i] = sc.nextInt();

            sc.nextLine(); // Clearing buffer before taking String input

            // Taking student name input
            System.out.print("Enter Student Name: ");
            name[i] = sc.nextLine();

            System.out.println("Student Registered Successfully!");
            System.out.println();
        }

        System.out.println("Success! Attendance System Initialized");
        System.out.println("=======================================");

        // Infinite loop for menu-driven system
        while (true) {

            // Displaying main menu
            System.out.println("\n=====================================");
            System.out.println("Student Attendance Management System");
            System.out.println("======================================");
            System.out.println("1. Mark Attendance (Day-Wise)");
            System.out.println("2. View Complete Record");
            System.out.println("3. Generate Attendance Report");
            System.out.println("4. Exit");
            System.out.println("======================================");

            // Validating menu choice
            do {
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 4) {
                    System.out.println("Error: Please Enter a valid choice!");
                }
            } while (choice < 1 || choice > 4);

            // OPTION 1: Mark attendance for a specific day
            if (choice == 1) {

                int day = 0;

                // Display total working days
                System.out.println("Total working days: " + w);

                // Validating entered day number
                do {
                    System.out.print("Enter the day number: ");
                    day = sc.nextInt();
                    if (day < 1 || day > w) {
                        System.out.println("Error: Please Enter a valid day number");
                    }
                } while (day < 1 || day > w);

                System.out.println("Mark the Attendance");

                // Loop through each student to mark attendance
                for (int i = 0; i < n; i++) {

                    int attendanceInput = 0;

                    // Accept only 0 (Absent) or 1 (Present)
                    do {
                        System.out.print("Attendance for " + name[i] + " (1=Present, 0=Absent): ");
                        attendanceInput = sc.nextInt();

                        if (attendanceInput != 0 && attendanceInput != 1) {
                            System.out.println("Error: Please Enter a valid attendance input");
                            continue;  // Skip storing and ask again
                        }

                        // Store attendance in 2D array
                        attendance[i][day - 1] = attendanceInput;

                    } while (attendanceInput != 0 && attendanceInput != 1);
                }
                System.out.println("Attendance Marked Successfully!");

            }

            // OPTION 2: Display complete attendance record
            else if (choice == 2) {

                System.out.println("\nAttendance Record:");
                System.out.print("Roll\tName\t");

                // Printing day headers
                for (int j = 0; j < w; j++) {
                    System.out.print("D" + (j + 1) + "\t");
                }

                System.out.println();

                // Printing attendance data using nested loops
                for (int i = 0; i < n; i++) {

                    System.out.print(roll[i] + "\t" + name[i] + "\t");

                    for (int j = 0; j < w; j++) {
                        System.out.print(attendance[i][j] + "\t");
                    }

                    System.out.println();
                }

            }

            // OPTION 3: Generate attendance percentage report
            else if (choice == 3) {

                System.out.println("\nAttendance Report:");
                System.out.println("-------------------------------------------------");
                System.out.println("Roll\tName\tPresent\t%\tStatus");
                System.out.println("-------------------------------------------------");

                // Loop through each student to calculate attendance
                for (int i = 0; i < n; i++) {

                    int presentCount = 0;

                    // Counting number of present days
                    for (int j = 0; j < w; j++) {
                        if (attendance[i][j] == 1) {
                            presentCount++;
                        }
                    }

                    // Calculating percentage
                    double percentage = (presentCount * 100.0) / w;

                    // Checking eligibility (minimum 75%)
                    String status;
                    if (percentage >= 75) {
                        status = "ELIGIBLE";
                    } else {
                        status = "SHORTAGE";
                    }

                    // Displaying final report for each student
                    System.out.println(roll[i] + "\t" + name[i] + "\t" +
                            presentCount + "\t" +
                            String.format("%.2f", percentage) + "\t" +
                            status);
                }
            }

            // OPTION 4: Exit program
            if (choice == 4) {
                System.out.println("Exiting, Thanks For Using The System...");
                System.out.println("======================================");
                break; // terminate loop
            }
        }

        // Closing scanner to avoid memory leak
        sc.close();
    }
}
