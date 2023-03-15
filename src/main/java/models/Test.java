package models;

public class Test {
    private String nameTest;
    private String textText;
    private int ball;


    public Test(String nameTest, String textText, int ball) {
        this.nameTest = nameTest;
        this.textText = textText;
        this.ball = ball;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getTextText() {
        return textText;
    }

    public void setTextText(String textText) {
        this.textText = textText;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }
}
