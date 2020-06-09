package client;

import java.util.Scanner;
import server.Assessment;

public class Login {
    int studentID = 1;

    //function to allow user to login
    public void LoginNow() {

        System.out.println("Please enter your login details: ");
        Scanner in = new Scanner(System.in); //scanner to read in input from cmd line

        System.out.println("Enter username: "); //enter username/ID
        String usernameInputs = in.nextLine(); //waits for input from next line in command line
        System.out.println("You have entered the username: " + usernameInputs); //displays the entered userID
        int usernameInput = Integer.parseInt(usernameInputs); //converts string input to int

        System.out.println("Enter Password: "); //enter password
        String passInput = in.nextLine(); //waits for input from next line in command line
        System.out.println("You have entered the password: " + passInput); //displays the entered password

        verifyUser(usernameInput, passInput);
    }

    // function to verify the user
    public void verifyUser(int userID, String pass) {
        try {
            int verified = Client.server.login(userID, pass); // int verified equals to client server userid and password
            System.out.println("You have entered the correct details");
            Assessment assignment = Client.server.getAssessment(verified, userID, "CT414"); //gets assignment passed on id and password and course code
            System.out.println(assignment.getInformation()); //gets info on assigment
            new AssignmentOptions(assignment, studentID); //calls new assignment options class
        } catch (Exception e) {
            System.err.println("Exception getting the assesment:"); //exception getting the assignemnt
            e.printStackTrace();
        }

    }
}


