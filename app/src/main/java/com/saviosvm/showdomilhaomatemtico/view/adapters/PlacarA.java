package com.saviosvm.showdomilhaomatemtico.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.model.JogadorM;

import java.util.ArrayList;

public class PlacarA extends ArrayAdapter<JogadorM> {

    public PlacarA(Context contexto, ArrayList<JogadorM> vetJogadores){
        super(contexto,0,vetJogadores);
    }

    public View getView(int posicao, View celulaReciclado, ViewGroup pai){
        JogadorM jog = this.getItem(posicao);

        if(celulaReciclado == null){
            celulaReciclado = LayoutInflater.from(getContext()).inflate(R.layout.celula_placar, pai,false);
        }

        //pegar os dados do objeto e setar os elementos visuais da celula
        TextView pos = celulaReciclado.findViewById(R .id.cel_placar_pos);
        TextView jogador = celulaReciclado.findViewById(R.id.cel_placar_nome);
        TextView acertos = celulaReciclado.findViewById(R.id.cel_placar_acertos);
        TextView tempo = celulaReciclado.findViewById(R.id.cel_placar_tempo);
        TextView faltas = celulaReciclado.findViewById(R.id.cel_placar_faltas);
        TextView pontos = celulaReciclado.findViewById(R.id.cel_placar_pontos);

        //seta os dados
        pos.setText(String.valueOf(posicao+1));
        jogador.setText(jog.getNome()+" - "+jog.getClasse()+" ("+jog.getAno()+"º Ano)");
        acertos.setText(jog.getAcertos()+"/16");
        tempo.setText(jog.getTempoTxt()+"/"+jog.getMediatempoTxt());
        faltas.setText(jog.getFaltas()+"/16");
        pontos.setText(String.valueOf(jog.getPontos().intValue()));

        return celulaReciclado;

    }

}
