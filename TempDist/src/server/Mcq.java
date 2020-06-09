package server;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Mcq implements Question{
    private String answer;
    private String[] answerOptions;
    private String questionDetail;
    private int id;

    private static final long serialVersionUID = 100000;


    public Mcq(int id, String details, String[] answerOptions){
        this.id = id;
        this.questionDetail = details;
        this.answerOptions = answerOptions;
    }

    public String getAnswer(){
        return answer;
    }

    @Override
    public void selectAnswer(int optionNumber){
        answer = answerOptions[optionNumber];
    }

    @Override
    public int getQuestionNumber() {
        return id;
    }

    @Override
    public String getQuestionDetail() {
        return questionDetail;
    }

    @Override
    public String[] getAnswerOptions() {
        return answerOptions;
    }

    @Override
    public void answerQuestions(ArrayList<String> answers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}