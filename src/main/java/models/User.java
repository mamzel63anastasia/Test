package models;


import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private int id_user;
    private String login;
    private String password;
    private String fio;
    private String mail;
    private String tabNumber;
    private String department;
    private int role;

    public User(String login, String password, String fio, String mail, String tabNumber, String department) {
        this.login = login;
        this.password = buildHash(password);
        this.fio = fio;
        this.mail = mail;
        this.tabNumber = tabNumber;
        this.department = department;
    }

    public User(int id_user, String login, String password, String fio, String mail, String tabNumber, String department, int role) {
        this.id_user = id_user;
        this.login = login;
        this.password = buildHash(password);
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
        this.password = buildHash(password);
    }

    public static String buildHash(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        md.update(str.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
