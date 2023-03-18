package models;

public class Role {

    private int id;
    private String nameRole;

    public Role(int id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
