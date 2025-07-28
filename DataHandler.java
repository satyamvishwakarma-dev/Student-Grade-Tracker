import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {
    public static boolean login(String username, String password) {
        if (username.equals("admin")) {
            return password.equals("1234");
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean access;
        int choice;

        //teacher login:
        do {
            System.out.print("Enter the username: ");
            String user = sc.nextLine();
            System.out.print("Enter the password: ");
            String code = sc.nextLine();
            access = login(user, code);

            if (!access) {
                System.out.println(" ");
                System.out.println("ACCESS DENIED..\nPLEASE TRY AGAIN!!");
                System.out.println(" ");
            }
        } while (!access);
        System.out.println("Login successful.\nWelcome!!");

        do {

            //choice
            System.out.print("""
                    What do you want to do today?
                        1 -> Enter marks
                        2 -> View Marks
                        3 -> Get Total Marks
                        4 -> Get Percentage
                        5 -> View Grades
                        6 -> Exit
                    """);
            choice = sc.nextInt();
            sc.nextLine();

            //Enter the data
            if (choice == 1) {
                //student details
                System.out.print("Enter the name of the student: ");
                String name = sc.nextLine();
                System.out.print("Enter the roll no. of the student: ");
                int rollNo = sc.nextInt();

                int[] marks = new int[5];

                //adding marks in array
                for (int i = 0; i < marks.length; i++) {
                    System.out.printf("Enter the marks for subject %d : ", i + 1);
                    marks[i] = sc.nextInt();
                }
                System.out.println("Your data has been saved successfully!!");

                Student s = new Student(name, rollNo, marks);

                //To CSV
                try (FileWriter fw = new FileWriter("Student.csv", true)) {
                    fw.write(s.toCSV() + "\n");
                } catch (IOException e) {
                    System.out.println("❌ Error saving student data: " + e.getMessage());
                }
            }//Enter marks closed here

            //View Marks
            if (choice == 2) {
                try (BufferedReader br = new BufferedReader(new FileReader("Student.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        for (String cell : data) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Get total marks
            if (choice == 3) {
                String fileName = "Student.csv";
                ArrayList<Student> student = new ArrayList<>();

                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length < 3) continue;

                        String name = parts[0].trim();
                        int roll = Integer.parseInt(parts[1].trim());

                        int[] marks = new int[parts.length - 2];

                        for (int i = 2; i < parts.length; i++) {
                            marks[i - 2] = Integer.parseInt(parts[i].trim());
                        }

                        Student s = new Student(name, roll, marks);
                        student.add(s);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (Student s : student) {
                    System.out.println(s.name + " (" + s.rollNo + "): " + s.getTotal());
                }
            }


            //Get percentage


            //View Grades


            //Exit
            if (choice == 6) {
                System.out.println("""
                        Thank you!!
                        have a great day!!""");
                return;
            }
        } while (true);

    }
}
