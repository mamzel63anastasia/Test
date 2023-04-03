package models.data;

import java.util.List;

public class TestData {
    private int id;

    private String name;

    private int ball;

    private String description;

    private List<QuestionData> questionData;

    public List<QuestionData> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(List<QuestionData> questionData) {
        this.questionData = questionData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
