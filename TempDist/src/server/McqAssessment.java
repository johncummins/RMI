package server;

import java.util.ArrayList;
import java.time.LocalDate;

public class McqAssessment implements Assessment {
	ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<String> courseCodes;
	LocalDate dueDate;

	private static final long serialVersionUID = 100000;

	public McqAssessment(ArrayList<Question> questions, ArrayList<String> courseCodes, LocalDate dueDate){
		this.questions = questions;
		this.courseCodes= courseCodes;
		this.dueDate = dueDate;

	}

	public McqAssessment(ArrayList<Question> questionSet1, LocalDate localDate1) {
	}


	public void setCourseCodes(ArrayList<String> courseCodes) {
		this.courseCodes = courseCodes;
	}

	public ArrayList<String> getCourseCodes() {
		return courseCodes;
	}



	@Override
	public String getInformation() {
		//System.out.println("Here's your assessment");
		return "Assessment";
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}


	@Override
	public LocalDate getClosingDate() {
		return dueDate;
	}

	@Override
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	@Override
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		Question q = findQuestion(questionNumber);
		return q;
	}

	@Override
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		Question q = findQuestion(questionNumber);
		q.selectAnswer(optionNumber);
	}

	@Override
	public int getSelectedAnswer(int questionNumber) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}



	@Override
	public void setCourseCodes() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}



	public Question findQuestion(int questionNumber) throws InvalidQuestionNumber{
		for (Question q : questions){
			System.out.println("Server Question No: " + questionNumber);
			if (q.getQuestionNumber() == questionNumber+1)
				System.out.println("Server found question no: " + questionNumber);
			return q;
		}

		throw new InvalidQuestionNumber();
	}
}