package models;

public class Answer {
    private String textAnswer;
    private boolean correctAnswer;

    public Answer(String textAnswer, boolean correctAnswer) {
        this.textAnswer = textAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
