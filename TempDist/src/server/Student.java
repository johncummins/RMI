package server;

import java.util.ArrayList;

public class Student {
	private int token;
	private int userID;
	private String password;
	private ArrayList<String> assessmentSumry = new ArrayList<String>();
	private ArrayList<Assessment> assessments = new ArrayList<Assessment>();
	private ArrayList<Assessment> compAssessments = new ArrayList<Assessment>();

	Student(int user, String pass) {
		this.userID = user;
		this.password = pass;

	}

	public int getID() {
		return userID;
	}

	public String getPassWord() {
		return password;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int newToken) {
		this.token = newToken;
	}

	public void setAssessments(ArrayList<Assessment> newAssessments) {
		this.assessments = newAssessments;
	}

	public ArrayList<Assessment> getAssessments() {
		return assessments;
	}

	public ArrayList<String> getSummary() {
		return assessmentSumry;
	}

	public void setSummary(ArrayList<String> assessmentSumry) {
		this.assessmentSumry = assessmentSumry;
	}

	public void complete(Assessment assessment){
		compAssessments.add(assessment);

		for (int i=0; i< compAssessments.size(); i++){
			System.out.println("******** SUMMARY ********");
			System.out.println("Student: " + getID() + " has completed assignment " + getSummary().get(i));
			System.out.println("These are the answers to the questions: ");
			for (int j=0; j<compAssessments.get(i).getQuestions().size(); j++){
				System.out.println(compAssessments.get(i).getQuestions().get(j).getAnswer());
			}
		}

	}
}

