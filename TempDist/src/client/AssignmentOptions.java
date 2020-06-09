package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import server.Assessment;
import server.InvalidOptionNumber;
import server.InvalidQuestionNumber;
import server.Question;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AssignmentOptions extends JFrame {

    private ArrayList<Integer> l= new ArrayList<Integer>();
    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<ButtonGroup> groups = new ArrayList<ButtonGroup>();
    public ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
    public JRadioButton radioAnswer1, radioAnswer2, radioAnswer3, radioAnswer4;
    public int twos = 0 ,threes =0,fours = 0;
    public Assessment assessment;
    public ButtonGroup group;
    public JButton submitAnswers;
    public String messaget;


    public AssignmentOptions(Assessment assignment, int studentID){


        assessment = assignment;

        questions = (ArrayList<Question>) assessment.getQuestions();
        System.out.println("Number of questions to answer: "+assessment.getQuestions().size());

        for(int k = 0; k<questions.size();k++){
            l.add(questions.get(k).getAnswerOptions().length);
        }

        for (int i = 0;i<l.size(); i++){

            if (l.get(i) == 2){
                twos ++;
            }
            else if (l.get(i)==3){
                threes ++;
            }
            else if (l.get(i) == 4){
                fours ++;
            }
        }

        int rows = twos+(threes*2)+(fours*2)+2;  //for 3 and 4 answer questions there need to be 2 lines to fit the answers.

        Container cp = getContentPane();
        cp.setLayout(new GridLayout(rows, 3, 5, 5));  // The content-pane sets its layout


        cp.add(new JLabel(" "));
        cp.add(new JLabel(" "));
        cp.add(new JLabel(" "));

        //loops through each question
        for (int j = 0;j<assessment.getQuestions().size(); j++){
            String[]options = questions.get(j).getAnswerOptions();
                cp.add(new JLabel(questions.get(j).getQuestionDetail()));
                radioAnswer1 = new JRadioButton(options[0]);
                radioAnswer1.setActionCommand("0"); //adds first answer action
                radioAnswer2 = new JRadioButton(options[1]);
                radioAnswer2.setActionCommand("1"); //adds second answer action
                radioAnswer3 = new JRadioButton(options[2]);
                radioAnswer3.setActionCommand("2"); //adds third answer
                cp.add(radioAnswer1);
                cp.add(radioAnswer2);
                cp.add(new JLabel(" "));
                cp.add(radioAnswer3);
                cp.add(new JLabel(" "));
                buttons.add(radioAnswer1); //attaches button to each action
                buttons.add(radioAnswer2);
                buttons.add(radioAnswer3);
                buttons.add(radioAnswer4);

                group = new ButtonGroup();
                group.add(radioAnswer1); //attches each answer to a group
                group.add(radioAnswer2);
                group.add(radioAnswer3);
                groups.add(group);
        }

        cp.add(new JLabel(" "));

        submitAnswers = new JButton("Submit");
        cp.add(submitAnswers);

        cp.add(new JLabel(" "));

        submitAnswers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String x;
                System.out.println("***** Summary of the answers ***** ");
                for (int i = 0; i<groups.size(); i++) { //loops through questions
                    x = groups.get(i).getSelection().getActionCommand();
                        int j = i + 1; //counter for question number
                        int selected = Integer.parseInt(x);
                        System.out.println("Question " + j + ":"); //prints question
                        questions.get(i).selectAnswer(selected);
                        System.out.println("Selected Answer: " + questions.get(i).getAnswer());
                        try {
                            assessment.selectAnswer(j, selected);
                        } catch (InvalidQuestionNumber | InvalidOptionNumber ex) {
                            Logger.getLogger(AssignmentOptions.class.getName()).log(Level.SEVERE, null, ex);
                        }

                }

                messaget = "You have submitted all the questions";
                JOptionPane.showMessageDialog(null, messaget, "Submission Summary", 1);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // exits gui when x is clicked
        setTitle("This is an Assessment for user "+ studentID +":"); //sets title of gui
        setSize(1000, 600);  // sets initial size gui
        setLocationRelativeTo(null);
        setVisible(true);
    }
    }

