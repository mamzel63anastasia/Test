package models;

import java.util.Date;

public class Statistic {
    private int id;
    private int idTest;
    private  int idUser;
    private int ball;
    private Date date;

    public Statistic(int id, int idTest, int idUser, int ball, Date date) {
        this.id = id;
        this.idTest = idTest;
        this.idUser = idUser;
        this.ball = ball;
        this.date = date;
    }

    public Statistic( int idTest, int idUser, int ball) {
        this.idTest = idTest;
        this.idUser = idUser;
        this.ball = ball;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
