package models;

import com.sun.istack.internal.Nullable;

public class GetTest {
    private int id;
    private  int id_user;
    private  int id_test;
    private  int id_material;
    private int id_admin;

    public GetTest() {

    }
    public GetTest(int id_user, int id_test, int id_material, int id_admin) {
        this.id_user = id_user;
        this.id_test = id_test;
        this.id_material = id_material;
        this.id_admin = id_admin;
    }
    public GetTest(int id, int id_user, int id_test, int id_material, int id_admin) {
        this.id = id;
        this.id_user = id_user;
        this.id_test = id_test;
        this.id_material = id_material;
        this.id_admin = id_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
}
