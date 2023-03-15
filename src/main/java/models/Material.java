package models;

public class Material {
    private String nameMaterial;
    private String textMaterial;

    public Material(String nameMaterial, String textMaterial) {
        this.nameMaterial = nameMaterial;
        this.textMaterial = textMaterial;
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
}
