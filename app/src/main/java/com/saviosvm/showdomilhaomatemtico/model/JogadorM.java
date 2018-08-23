package com.saviosvm.showdomilhaomatemtico.model;

/**
 * Created by savio on 12/03/2018.
 */

public class JogadorM {

    private int id;
    private String nome;
    private String classe;
    private int ano;
    private int acertos;
    private Double pontos;
    private Double tempo;
    private Double mediaTempo;
    private int faltas;

    public JogadorM() {
        this.faltas = 0;
        this.pontos = 0.0;
        this.tempo = 0.0;
        this.mediaTempo = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public Double getMediaTempo() {
        return mediaTempo;
    }

    public void setMediaTempo(Double mediaTempo) {
        this.mediaTempo = mediaTempo;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
