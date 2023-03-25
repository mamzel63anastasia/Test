package models;

public class Test {
    private int id;
    private String name;
    private String description;
    private int ball;
    private int id_user;

    public Test(String name, String description, int ball, int id_user) {
        this.name = name;
        this.description = description;
        this.ball = ball;
        this.id_user = id_user;
    }

    public Test(int id, String name, String description, int ball, int id_user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ball = ball;
        this.id_user = id_user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }
}
