package com.saviosvm.showdomilhaomatemtico.model;

/**
 * Created by savio on 22/03/2018.
 */

public class PontuacaoM {

    private int ordem;
    private int card;
    private int valorAcerto;
    private int valorErro;
    private int valorDesistir;
    private String descricaoAcerto;
    private String descricaoErro;
    private String descricaoDesistir;

    public PontuacaoM() {

    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public int getValorAcerto() {
        return valorAcerto;
    }

    public void setValorAcerto(int valorAcerto) {
        this.valorAcerto = valorAcerto;
    }

    public int getValorErro() {
        return valorErro;
    }

    public void setValorErro(int valorErro) {
        this.valorErro = valorErro;
    }

    public int getValorDesistir() {
        return valorDesistir;
    }

    public void setValorDesistir(int valorDesistir) {
        this.valorDesistir = valorDesistir;
    }

    public String getDescricaoAcerto() {
        return descricaoAcerto;
    }

    public void setDescricaoAcerto(String descricaoAcerto) {
        this.descricaoAcerto = descricaoAcerto;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }

    public String getDescricaoDesistir() {
        return descricaoDesistir;
    }

    public void setDescricaoDesistir(String descricaoDesistir) {
        this.descricaoDesistir = descricaoDesistir;
    }
}
