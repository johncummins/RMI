package server;

import java.time.LocalDate;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public interface Assessment extends Serializable {


	// Return information about the assessment	
	public String getInformation();

	// Return the final date / time for submission of completed assessment
	public LocalDate getClosingDate();

	// Return a list of all questions and anser options
	public List<Question> getQuestions();

	// Return one question only with answer options
	public Question getQuestion(int questionNumber) throws 
		InvalidQuestionNumber;

	// Answer a particular question
	public void selectAnswer(int questionNumber, int optionNumber) throws
		InvalidQuestionNumber, InvalidOptionNumber;

	// Return selected answer or zero if none selected yet
	public int getSelectedAnswer(int questionNumber);

	//getter to get course codes
	public ArrayList<String> getCourseCodes();

	//setter for setting course codes
	public void setCourseCodes();

}



