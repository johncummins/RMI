package server;

import java.time.LocalDate;
import java.util.ArrayList;


public class Test {

	ArrayList<Student> students = new ArrayList<>();

	public ArrayList<Student> init (){
		//Geography questions
		//question 1 a
		String questionDetails1a = "What is the longest river in Ireland?";
		String[] answerOptions1a = {"River Barrow", "River Lee", "River Shannon"};
		Question question1a = new Mcq(1, questionDetails1a, answerOptions1a);
		//Question 1 b 
		String questionDetails1b = "Capital city of Ireland";
		String[] answerOptions1b = {"Dublin", "Cork", "Limerick"};
		Question question1b = new Mcq(2, questionDetails1b, answerOptions1b);
		//question 1 c 
		String questionDetails1c = "Whats the tallest mountain in Ireland?";
		String[] answerOptions1c = {"Carrauntoohil", "Croke Patrick", "Slieve Bloome mts"};
		Question question1c = new Mcq(3, questionDetails1c, answerOptions1c);      // Create set of questions 

		//creating set of questions
		ArrayList<Question> questionSet1 = new ArrayList<Question>();

		// adding questions to this set
		questionSet1.add(question1a);
		questionSet1.add(question1b);
		questionSet1.add(question1c);

		//Maths questions
		// Question 2 a 
		String questionDetails2a = "What is 5+5?";
		String[] answerOptions2a = {"5", "10", "25"};
		Question question2a = new Mcq(1, questionDetails2a, answerOptions2a);
		//Question 2 b 
		String questionDetails2b = "What is 7+2?";
		String[] answerOptions2b = {"7", "9", "11"};
		Question question2b = new Mcq(2, questionDetails2b, answerOptions2b);
		//question 2 c 
		String questionDetails2c = "What is 20 - 5?";
		String[] answerOptions2c = {"10", "5", "-5"};
		Question question2c = new Mcq(3, questionDetails2c, answerOptions2c);

		//Create set of questions 
		ArrayList<Question> questionSet2 = new ArrayList<Question>();

		//Add questions to these sets
		questionSet2.add(question2a);
		questionSet2.add(question2b);
		questionSet2.add(question2c);

		//array of course codes to see which assignemnt is for them
		ArrayList<String> courseCodes = new ArrayList<String>();
		courseCodes.add("CT414"); //adds ct414 as course code
		courseCodes.add("CT450"); //adds ct450 as course code

		//duedate for assisgnment is 21/2/2020
		LocalDate localDate1 = LocalDate.of(2020, 2, 21);

		// creating assessment 1 & 2
		Assessment assessment1 = new McqAssessment(questionSet1, courseCodes, localDate1);
		Assessment assessment2 = new McqAssessment(questionSet1, courseCodes, localDate1);

		// creating ArrayLists of Assessmentlist 1 & 2
		ArrayList<Assessment> assessmentList1 = new ArrayList<Assessment>();
		assessmentList1.add(assessment1);
		ArrayList<Assessment> assessmentList2 = new ArrayList<Assessment>();
		assessmentList2.add(assessment2);

		ArrayList<String> summary = new ArrayList<String>(); //array list to store the subject summaries
		summary.add("Geography"); // geography summary
		summary.add("Maths"); //maths summary

		//creating students, with userid and password
		Student s1 = new Student(1, "password");
		s1.setAssessments(assessmentList1);
		s1.setSummary(summary);
		students.add(s1);
		Student s2 = new Student(2, "password");
		s2.setAssessments(assessmentList2);
		students.add(s2);
		System.out.println("Test setup is complete!");

		return students;

	}


}