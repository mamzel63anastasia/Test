package models;

public class Material implements Model{
    private int id;
    private String name;
    private String text;
    private int id_user;

    public Material(String name, String text, int id_user) {
        this.name = name;
        this.text = text;
        this.id_user = id_user;
    }

    public Material(int id, String name, String text, int id_user) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
