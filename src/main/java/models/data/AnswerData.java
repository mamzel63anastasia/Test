package models.data;

public class AnswerData {
    private int id;
    private String answerText;
    private boolean answerCheck;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isAnswerCheck() {
        return answerCheck;
    }

    public void setAnswerCheck(boolean answerCheck) {
        this.answerCheck = answerCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
