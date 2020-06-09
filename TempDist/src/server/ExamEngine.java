package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ExamEngine implements ExamServer {

    private ArrayList<Student> loggedIn = new ArrayList<Student>(); // array to track who's logged in
    private ArrayList<Integer> tokenArray = new ArrayList<Integer>(); //array to keep track of tokens
    private ArrayList<Student> allStudents = new ArrayList<Student>(); // array that stores all students

    // Keep creating random tokens if token already exists
    public int createToken(){

        boolean isAdded = false;
        int randNum = (int) (Math.random() * 100);

        for (int i : tokenArray) {
            if (randNum == i) {
                isAdded = true;
            }
        }
        if (!isAdded)
            tokenArray.add(randNum);
        else
            createToken();
        return randNum;
    }

    //exam engine constructor
    public ExamEngine() {
        super();
    }

    //login method, returns a login token
    public int login(int studentid, String password) throws UnauthorizedAccess, RemoteException {

        setupTest(); //sets up the qustions and students
        Student stuSearch = new Student(studentid, password);

        for (Student s : allStudents) {
            if (s.getID() == stuSearch.getID() && s.getPassWord().equals(stuSearch.getPassWord())) { //if statement to equate the new search to the correct id and password
                int token = createToken();
                s.setToken(token); //sets the token
                long start = System.currentTimeMillis(); // tracks the creation of the token
                loggedIn.add(s); // tracks currently logged in students
                System.out.println("Student ID = " + s.getID()+ " is logged in with Token = " + s.getToken()); // prints out student ID and token
                return token;
            } else {
                throw new UnauthorizedAccess("Error: Incorrect Username or Password, please try again"); // error message
            }
        }
        return 0;
    }



    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String courseCode) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        //checkToken(token);
        Student student = findStudent(token, studentid);
        //Assessment stuAssessment = (Assessment) student.getAssessments();
        //return stuAssessment;

        for (Assessment a: student.getAssessments()){
            for (String s: a.getCourseCodes()){
                if (s.equals(courseCode))
                    return a;
            }

        }
        throw new NoMatchingAssessment("No match for the course code entered: " + courseCode);

    }

    // submits the answers to the assessment
    public String submitAssessment(int token, int studentid, Assessment completed) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        Student student = findStudent(token, studentid);
        student.complete(completed);
        System.out.println("Your assignment has been successfully submitted!");
        return null;
    }


    public Student findStudent(int token, int studentID) throws UnauthorizedAccess{
        for (Student s: loggedIn){
            if (s.getID() == studentID && s.getToken() == token)
                return s;
            else
                throw new UnauthorizedAccess("Unauthorized Access: Student with ID: " + studentID + " is not logged in.");
        }
        return null;

    }

    // this function populates, assignments and students
    public void setupTest(){
        Test driver = new Test();
        allStudents = driver.init(); //initialise driver
    }


    //main method
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ExamServer";
            ExamServer engine = new ExamEngine();
            ExamServer stub =
                    (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ExamEngine bound");
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }

    }
}
