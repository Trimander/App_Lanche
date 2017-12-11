package com.app_lanche;

public class Lanche {
    private int imageLanche;
    private int imageAdd;
    private String nomeLanche;
    private String valor;
    private String description;
    private Class<?> cls;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageLanche() {
        return imageLanche;
    }

    public void setImageLanche(int imageLanche) {
        this.imageLanche = imageLanche;
    }

    public int getImageAdd() {
        return imageAdd;
    }

    public void setImageAdd(int imageAdd) {
        this.imageAdd = imageAdd;
    }

    public String getNomeLanche() {
        return nomeLanche;
    }

    public void setNomeLanche(String nomeLanche) {
        this.nomeLanche = nomeLanche;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public Lanche(int imageLanche, int imageAdd, String nomeLanche, String valor, String description, Class<?> cls) {
        this.imageLanche = imageLanche;
        this.imageAdd = imageAdd;
        this.nomeLanche = nomeLanche;
        this.valor = valor;
        this.description = description;
        this.cls = cls;
    }
}