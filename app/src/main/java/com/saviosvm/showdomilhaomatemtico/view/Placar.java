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
        if (jogadores.isEmpty()) {
            //se não tiver nada mostra uma mensagem
            Toast.makeText(this, "Ainda não há jogadores registrados!", Toast.LENGTH_SHORT).show();
        } else {
            //transforma
            for(int i=0;i < jogadores.size(); i++){
                if(jogadores.get(i).getTempo() > 0 || jogadores.get(i).getMediaTempo() > 0 ){
                    transformaSeg(i);
                }
                else{
                    jogadores.get(i).setTempoTxt("0seg");
                    jogadores.get(i).setMediatempoTxt("0seg");
                }
            }
        }
    }

    private void transformaSeg(int index){
        int totalSeconds = jogadores.get(index).getTempo().intValue();
        if(totalSeconds < 60){
            jogadores.get(index).setTempoTxt(totalSeconds+"seg");
            jogadores.get(index).setMediatempoTxt(totalSeconds+"seg");
        } else {
            final int MINUTES_IN_AN_HOUR = 60;
            final int SECONDS_IN_A_MINUTE = 60;
            int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
            int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
            int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
            String result = String.format("%.2f", jogadores.get(index).getMediaTempo());
            jogadores.get(index).setTempoTxt(minutes+"min "+seconds+"seg");
            jogadores.get(index).setMediatempoTxt(result+"seg");
        }
    }

    public static void validaBanco(JogadorC pJogador) {
        bancoJogador = pJogador;
    }
}
