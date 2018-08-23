package com.saviosvm.showdomilhaomatemtico.model;

public class NivelM {

    private int id;
    private String descricao;

    public NivelM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString(){
        String ret = getDescricao();
        return ret;
    }
}
