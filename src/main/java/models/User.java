package models;


import com.sun.istack.internal.NotNull;

public class User implements Model{
    private int id;
    private String login;
    private String password;
    private String fio;
    private String mail;
    private String tabNumber;
    private String department;
    private int role;

    public User(@NotNull String login, @NotNull String password, String fio, String mail, String tabNumber, String department, int role) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.mail = mail;
        this.tabNumber = tabNumber;
        this.department = department;
        this.role = role;
    }

    public User(int id, @NotNull String login, @NotNull String password, String fio, String mail, String tabNumber, String department, @NotNull int role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.mail = mail;
        this.tabNumber = tabNumber;
        this.department = department;
        this.role = role;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTabNumber() {
        return tabNumber;
    }

    public void setTabNumber(String tabNumber) {
        this.tabNumber = tabNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
