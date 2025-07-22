import java.util.Scanner;

public class DataHandler {
    public static boolean login(String username, String password) {
        if (username.equals("admin")) {
            return password.equals("1234");
        }
        return false;
    }

    public static void printArray(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            System.out.println(ary[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean access;

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

        Student s = new Student(name, rollNo,marks);

        

    }
}
