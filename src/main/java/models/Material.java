package models;

public class Material {
    private String nameMaterial;
    private String textMaterial;
    private int id_user;

    public Material(String nameMaterial, String textMaterial, int id_user) {
        this.nameMaterial = nameMaterial;
        this.textMaterial = textMaterial;
        this.id_user = id_user;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    public String getTextMaterial() {
        return textMaterial;
    }

    public void setTextMaterial(String textMaterial) {
        this.textMaterial = textMaterial;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
