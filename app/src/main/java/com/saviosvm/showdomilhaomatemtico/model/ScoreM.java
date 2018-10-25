package com.saviosvm.showdomilhaomatemtico.model;

import java.util.ArrayList;

public class ScoreM {

    private ArrayList<String> card1Acertartxt = new ArrayList<>();
    private ArrayList<Integer> card1Acertarpts = new ArrayList<>();
    private ArrayList<String> card1Parartxt = new ArrayList<>();
    private ArrayList<Integer> card1Pararpts = new ArrayList<>();
    private ArrayList<String> card1Errartxt = new ArrayList<>();
    private ArrayList<Integer> card1Errarpts = new ArrayList<>();

    private ArrayList<String> card2Acertartxt = new ArrayList<>();
    private ArrayList<Integer> card2Acertarpts = new ArrayList<>();
    private ArrayList<String> card2Parartxt = new ArrayList<>();
    private ArrayList<Integer> card2Pararpts = new ArrayList<>();
    private ArrayList<String> card2Errartxt = new ArrayList<>();
    private ArrayList<Integer> card2Errarpts = new ArrayList<>();

    private ArrayList<String> card3Acertartxt = new ArrayList<>();
    private ArrayList<Integer> card3Acertarpts = new ArrayList<>();
    private ArrayList<String> card3Parartxt = new ArrayList<>();
    private ArrayList<Integer> card3Pararpts = new ArrayList<>();
    private ArrayList<String> card3Errartxt = new ArrayList<>();
    private ArrayList<Integer> card3Errarpts = new ArrayList<>();

    private String card4Acertartxt;
    private Integer card4Acertarpts;
    private String card4Parartxt;
    private Integer card4Pararpts;
    private String card4Errartxt;
    private Integer card4Errarpts;

    public ScoreM() {
        //card1 texto
        card1Acertartxt.add("1 Mil");card1Parartxt.add("500");card1Errartxt.add("Nada");
        card1Acertartxt.add("2 Mil");card1Parartxt.add("1 Mil");card1Errartxt.add("500");
        card1Acertartxt.add("3 Mil");card1Parartxt.add("1,5 Mil");card1Errartxt.add("750");
        card1Acertartxt.add("4 Mil");card1Parartxt.add("2 Mil");card1Errartxt.add("1 Mil");
        card1Acertartxt.add("5 Mil");card1Parartxt.add("2,5 Mil");card1Errartxt.add("1,25 Mil");

        card1Acertarpts.add(1000);card1Pararpts.add(500);card1Errarpts.add(0);
        card1Acertarpts.add(2000);card1Pararpts.add(1000);card1Errarpts.add(500);
        card1Acertarpts.add(3000);card1Pararpts.add(1500);card1Errarpts.add(750);
        card1Acertarpts.add(4000);card1Pararpts.add(2000);card1Errarpts.add(1250);
        card1Acertarpts.add(5000);card1Pararpts.add(2500);card1Errarpts.add(1250);
        //card2 texto
        card2Acertartxt.add("10 Mil");card2Parartxt.add("5 Mil");card2Errartxt.add("2,5 Mil");
        card2Acertartxt.add("20 Mil");card2Parartxt.add("10 Mil");card2Errartxt.add("5 Mil");
        card2Acertartxt.add("30 Mil");card2Parartxt.add("15 Mil");card2Errartxt.add("7,5 Mil");
        card2Acertartxt.add("40 Mil");card2Parartxt.add("20 Mil");card2Errartxt.add("10 Mil");
        card2Acertartxt.add("50 Mil");card2Parartxt.add("25 Mil");card2Errartxt.add("12,5 Mil");

        card2Acertarpts.add(10000);card2Pararpts.add(5000);card2Errarpts.add(2500);
        card2Acertarpts.add(20000);card2Pararpts.add(10000);card2Errarpts.add(5000);
        card2Acertarpts.add(30000);card2Pararpts.add(15000);card2Errarpts.add(7500);
        card2Acertarpts.add(40000);card2Pararpts.add(20000);card2Errarpts.add(10000);
        card2Acertarpts.add(50000);card2Pararpts.add(25000);card2Errarpts.add(12500);
        //card3 texto
        card3Acertartxt.add("100 Mil");card3Parartxt.add("50 Mil");card3Errartxt.add("25 Mil");
        card3Acertartxt.add("200 Mil");card3Parartxt.add("100 Mil");card3Errartxt.add("50 Mil");
        card3Acertartxt.add("300 Mil");card3Parartxt.add("150 Mil");card3Errartxt.add("75 Mil");
        card3Acertartxt.add("400 Mil");card3Parartxt.add("200 Mil");card3Errartxt.add("100 Mil");
        card3Acertartxt.add("500 Mil");card3Parartxt.add("250 Mil");card3Errartxt.add("125 Mil");

        card3Acertarpts.add(100000);card3Pararpts.add(50000);card3Errarpts.add(25000);
        card3Acertarpts.add(200000);card3Pararpts.add(100000);card3Errarpts.add(50000);
        card3Acertarpts.add(300000);card3Pararpts.add(150000);card3Errarpts.add(75000);
        card3Acertarpts.add(400000);card3Pararpts.add(200000);card3Errarpts.add(100000);
        card3Acertarpts.add(500000);card3Pararpts.add(250000);card3Errarpts.add(125000);
        //card4 texto
        card4Acertartxt = "1 Milhão";card4Parartxt = "500 Mil";card4Errartxt = "Perde tudo!";
        card4Acertarpts = 1000000;card4Pararpts = 500000;card4Errarpts = 0;
    }

    public ArrayList<String> getCard1Acertartxt() {
        return card1Acertartxt;
    }

    public ArrayList<Integer> getCard1Acertarpts() {
        return card1Acertarpts;
    }

    public ArrayList<String> getCard1Parartxt() {
        return card1Parartxt;
    }

    public ArrayList<Integer> getCard1Pararpts() {
        return card1Pararpts;
    }

    public ArrayList<String> getCard1Errartxt() {
        return card1Errartxt;
    }

    public ArrayList<Integer> getCard1Errarpts() {
        return card1Errarpts;
    }

    public ArrayList<String> getCard2Acertartxt() {
        return card2Acertartxt;
    }

    public ArrayList<Integer> getCard2Acertarpts() {
        return card2Acertarpts;
    }

    public ArrayList<String> getCard2Parartxt() {
        return card2Parartxt;
    }

    public ArrayList<Integer> getCard2Pararpts() {
        return card2Pararpts;
    }

    public ArrayList<String> getCard2Errartxt() {
        return card2Errartxt;
    }

    public ArrayList<Integer> getCard2Errarpts() {
        return card2Errarpts;
    }

    public ArrayList<String> getCard3Acertartxt() {
        return card3Acertartxt;
    }

    public ArrayList<Integer> getCard3Acertarpts() {
        return card3Acertarpts;
    }

    public ArrayList<String> getCard3Parartxt() {
        return card3Parartxt;
    }

    public ArrayList<Integer> getCard3Pararpts() {
        return card3Pararpts;
    }

    public ArrayList<String> getCard3Errartxt() {
        return card3Errartxt;
    }

    public ArrayList<Integer> getCard3Errarpts() {
        return card3Errarpts;
    }

    public String getCard4Acertartxt() {
        return card4Acertartxt;
    }

    public Integer getCard4Acertarpts() {
        return card4Acertarpts;
    }

    public String getCard4Parartxt() {
        return card4Parartxt;
    }

    public Integer getCard4Pararpts() {
        return card4Pararpts;
    }

    public String getCard4Errartxt() {
        return card4Errartxt;
    }

    public Integer getCard4Errarpts() {
        return card4Errarpts;
    }
}
