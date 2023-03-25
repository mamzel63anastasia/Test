package models;

public class Question {
    private int id;
    private String txt;
    private int id_test;

    public Question(String txt, int id_test) {
        this.txt = txt;
        this.id_test = id_test;
    }

    public Question(int id, String txt, int id_test) {
        this.id = id;
        this.txt = txt;
        this.id_test = id_test;
    }


    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }
}
