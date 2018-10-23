package com.saviosvm.showdomilhaomatemtico.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.controler.JogadorC;
import com.saviosvm.showdomilhaomatemtico.model.JogadorM;
import com.saviosvm.showdomilhaomatemtico.view.adapters.PlacarA;

import java.util.ArrayList;

public class Placar extends AppCompatActivity {

    static JogadorC bancoJogador;
    private ArrayList<JogadorM> jogadores = null;
    private ListView placar = null;
    private PlacarA adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);

        placar = findViewById(R.id.placar_lista);
        carregarJogadores();

        adapter = new PlacarA(this, jogadores);
        placar.setAdapter(adapter);
    }

    private void carregarJogadores() {
        jogadores = bancoJogador.listar();
        if (!jogadores.isEmpty()) {
            //vai organizar os jogadores por pontuiação
            jogadores = organizaPlacar();
        } else {
            //se não tiver nada mostra uma mensagem
            Toast.makeText(this, "Ainda não há jogadores registrados!", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<JogadorM> organizaPlacar() {
        ArrayList<JogadorM> aux = new ArrayList<>(jogadores);
        JogadorM p1, p2;
        int total = jogadores.size();
        int index;
        //verifica a partir do array, primeiro por pontuação
        for (int a = 0; a < total; a++) {
            index = a;
            p1 = jogadores.get(a);
            //faz as comparações
            for (int p = 0; p < total; p++) {
                p2 = jogadores.get(p);
                if (p1.getId() != p2.getId()) {
                    //1 - pontuação, maior ganha
                    if (p1.getPontos() > p2.getPontos()) {
                        index = p;
                    }
                    //2 - acertos, maior ganha
                    else if (p1.getAcertos() > p2.getAcertos()) {
                        index = p;
                    }
                    //3 - faltas, menor ganha
                    else if (p1.getFaltas() < p2.getFaltas()) {
                        index = p;
                    }
                    //4 - tempo, menor ganha
                    else if (p1.getTempo() < p2.getTempo()) {
                        index = p;
                    }
                    //5 - media de tempo, menor ganha
                    else {
                        if (p1.getMediaTempo() < p2.getMediaTempo())
                            index = p;
                        else
                            index = a;
                    }
                }
            }
            aux.set(index,p1);
        }
        return aux;
    }

    public static void validaBanco(JogadorC pJogador) {
        bancoJogador = pJogador;
    }
}
