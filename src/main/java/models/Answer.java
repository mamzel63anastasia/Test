package models;

public class Answer {
    private int id;
    private String txt;
    private int correct;
    private int id_question;

    public Answer(String txt, int correct, int id_question) {
        this.txt = txt;
        this.correct = correct;
        this.id_question = id_question;
    }

    public Answer(int id, String txt, int correct, int id_question) {
        this.id = id;
        this.txt = txt;
        this.correct = correct;
        this.id_question = id_question;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
