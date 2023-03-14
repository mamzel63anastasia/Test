package models;

public class User {

    private String fio;
    private String mail;
    private String tabNumber;
    private String department;
    private String login;
    private String password;


    public User(String fio, String mail, String tabNumber, String department, String login, String password) {
        this.fio = fio;
        this.mail = mail;
        this.tabNumber = tabNumber;
        this.department = department;
        this.login = login;
        this.password = password;
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
}
