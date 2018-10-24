package com.saviosvm.showdomilhaomatemtico.controler;

import com.saviosvm.showdomilhaomatemtico.AndGraph.*;

public class Efeitos {

    static int mostraPerunta = AGSoundManager.vrSoundEffects.loadSoundEffect("efmostrapergunta.mp3");
    static int somAcerto = AGSoundManager.vrSoundEffects.loadSoundEffect("efacertou.mp3");
    static int somErro = AGSoundManager.vrSoundEffects.loadSoundEffect("eferrou.mp3");
    static int somEstacertodisso = AGSoundManager.vrSoundEffects.loadSoundEffect("eftemcerteza.mp3");
    static int somQualaresposta = AGSoundManager.vrSoundEffects.loadSoundEffect("efqualresposta.mp3");
    static int somNome = AGSoundManager.vrSoundEffects.loadSoundEffect("efnomeparticipante.mp3");
    static int somParar = AGSoundManager.vrSoundEffects.loadSoundEffect("efparar.mp3");
    static int somTempoacabou = AGSoundManager.vrSoundEffects.loadSoundEffect("eftempoacabou.mp3");
    static int somCartas = AGSoundManager.vrSoundEffects.loadSoundEffect("efcartas.mp3");
    static int somCerteza = AGSoundManager.vrSoundEffects.loadSoundEffect("efcerteza.mp3");
    static int somVaipular = AGSoundManager.vrSoundEffects.loadSoundEffect("efvaipular.mp3");

    static int somAjudaconv = AGSoundManager.vrSoundEffects.loadSoundEffect("efajudaconvid.mp3");
    static int somAjudaplacas = AGSoundManager.vrSoundEffects.loadSoundEffect("efajudaplacas.mp3");
    static int somParabens = AGSoundManager.vrSoundEffects.loadSoundEffect("efparabens.mp3");
    static int somSelecparou = AGSoundManager.vrSoundEffects.loadSoundEffect("efselecparou.mp3");
    static int somTchau = AGSoundManager.vrSoundEffects.loadSoundEffect("eftchau.mp3");

    static int nulo = AGSoundManager.vrSoundEffects.loadSoundEffect("null.mp3");

    public static int getMostraPerunta() {
        return mostraPerunta;
    }

    public static int getSomAcerto() {
        return somAcerto;
    }

    public static int getSomErro() {
        return somErro;
    }

    public static int getSomEstacertodisso() {
        return somEstacertodisso;
    }

    public static int getSomQualaresposta() {
        return somQualaresposta;
    }

    public static int getSomNome() {
        return somNome;
    }

    public static int getSomParar() {
        return somParar;
    }

    public static int getSomTempoacabou() {
        return somTempoacabou;
    }

    public static int getSomCartas() {
        return somCartas;
    }

    public static int getSomCerteza() {
        return somCerteza;
    }

    public static int getSomVaipular() {
        return somVaipular;
    }

    public static int getSomAjudaconv() {
        return somAjudaconv;
    }

    public static int getSomAjudaplacas() {
        return somAjudaplacas;
    }

    public static int getSomParabens() {
        return somParabens;
    }

    public static int getSomSelecparou() {
        return somSelecparou;
    }

    public static int getSomTchau() {
        return somTchau;
    }

    public static int getNulo() {
        return nulo;
    }
}
