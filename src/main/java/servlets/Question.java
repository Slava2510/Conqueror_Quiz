package servlets;

public class Question {

    private String question ;
    private int rightAnswer ;

    public Question(String q , String a) {
        this.question = q ;
        this.rightAnswer = Integer.parseInt(a) ;
    }

    //Getters + Setters

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

}
