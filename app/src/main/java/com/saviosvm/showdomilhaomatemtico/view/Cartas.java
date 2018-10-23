package com.saviosvm.showdomilhaomatemtico.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saviosvm.showdomilhaomatemtico.AndGraph.AGSoundManager;
import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.controler.Efeitos;

import java.util.ArrayList;
import java.util.Collections;

public class Cartas extends AppCompatActivity implements View.OnClickListener {

    //componentes visuais
    private Button carta0 = null;
    private Button carta1 = null;
    private Button carta2 = null;
    private Button carta3 = null;

    //componentes visuais do dialog
    private Button aviOk = null;
    private TextView aviNome = null;
    private TextView aviTexto = null;

    //lista de valores
    private int index;
    private ArrayList<Integer> cartasIndex = null;
    private int cartaSelecionada = -1;
    private String nomejog = null;

    //dialogo
    //builders visuais
    private AlertDialog.Builder confirmacao = null;
    private AlertDialog confirmacaod = null;
    private LayoutInflater inflador = null;
    private View dialogView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);

        nomejog = getIntent().getExtras().getString("nome");

        //referencia visual
        carta0 = findViewById(R.id.cartas_carta0);
        carta1 = findViewById(R.id.cartas_carta1);
        carta2 = findViewById(R.id.cartas_carta2);
        carta3 = findViewById(R.id.cartas_carta3);

        //sorteia as cartas
        sorteiaCartas();

        //seta o listener
        carta0.setOnClickListener(this);
        carta1.setOnClickListener(this);
        carta2.setOnClickListener(this);
        carta3.setOnClickListener(this);

        AGSoundManager.vrSoundEffects.play(Efeitos.getSomCartas());

    }

    private void sorteiaCartas(){
        cartasIndex = new ArrayList<>();
        cartasIndex.add(0);
        cartasIndex.add(1);
        cartasIndex.add(2);
        cartasIndex.add(3);
        Collections.shuffle(cartasIndex);
    }

    private void mostraCarta(){
        //mostra a carta de acordo com o que foi selecionado
        //selecionada: 0 carta 1, 1 carta 2, 2 carta 3 e 3 carta4 (na tela)
        //valores: 0 carta rei, 1 carta as, 2 carta 2, 3 carta 3;
        index = cartasIndex.get(cartaSelecionada);
        if(cartaSelecionada == 0){
            if(index == 0) carta0.setBackgroundResource(R.drawable.rei);
            if(index == 1) carta0.setBackgroundResource(R.drawable.as);
            if(index == 2) carta0.setBackgroundResource(R.drawable.dois);
            if(index == 3) carta0.setBackgroundResource(R.drawable.tres);
        }
        if(cartaSelecionada == 1){
            if(index == 0) carta1.setBackgroundResource(R.drawable.rei);
            if(index == 1) carta1.setBackgroundResource(R.drawable.as);
            if(index == 2) carta1.setBackgroundResource(R.drawable.dois);
            if(index == 3) carta1.setBackgroundResource(R.drawable.tres);
        }
        if(cartaSelecionada == 2){
            if(index == 0) carta2.setBackgroundResource(R.drawable.rei);
            if(index == 1) carta2.setBackgroundResource(R.drawable.as);
            if(index == 2) carta2.setBackgroundResource(R.drawable.dois);
            if(index == 3) carta2.setBackgroundResource(R.drawable.tres);
        }
        if(cartaSelecionada == 3){
            if(index == 0) carta3.setBackgroundResource(R.drawable.rei);
            if(index == 1) carta3.setBackgroundResource(R.drawable.as);
            if(index == 2) carta3.setBackgroundResource(R.drawable.dois);
            if(index == 3) carta3.setBackgroundResource(R.drawable.tres);
        }
        chamaDialogo(index);
    }

    private void chamaDialogo(int index){
        String txtTexto = "";
        if(index == 0) txtTexto = "É o Rei. Nenhuma resposta errada será eliminada...";
        if(index == 1) txtTexto = "É o Ás. Uma resposta errada será eliminada.";
        if(index == 2) txtTexto = "É o Dois. Duas respostas erradas serão eliminadas.";
        if(index == 3) txtTexto = "É o Três. Tdoas as resposta erradas serão eliminadas.";

        //inicia os valores da variavel
        confirmacao = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        inflador = this.getLayoutInflater();
        dialogView = inflador.inflate(R.layout.alert_aviso, null);
        confirmacao.setView(dialogView);
        confirmacao.setTitle("Carta "+(cartaSelecionada+1)+" escolhida");
        confirmacaod = confirmacao.create();
        confirmacaod.show();
        confirmacaod.setCancelable(false);
        aviOk = confirmacaod.findViewById(R.id.avisoal_btnok);
        aviNome = confirmacaod.findViewById(R.id.avisoal_txtnome);
        aviTexto = confirmacaod.findViewById(R.id.avisoal_txtaviso);
        aviNome.setText(nomejog+"...");
        aviTexto.setText(txtTexto);
        aviTexto.setTextSize(20);
        aviOk.setText("Voltar");
        aviOk.setOnClickListener(this);
    }

    private void retornaEvento(){
        //Dicionario de dados
        Bundle dicionario = new Bundle();
        dicionario.putBoolean("voltar", false);
        dicionario.putString("tela", "Jogo");
        dicionario.putInt("valor", index);
        Intent resultado = new Intent();
        //Dicionario de dados
        resultado.putExtras(dicionario);
        //Seta o valor do resultado e os que serao retornados
        this.setResult(Activity.RESULT_OK, resultado);
        //volta para a activity anterior
        this.finish();
    }

    @Override
    public void onBackPressed(){
        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }

    //sobrescreve o botão de voltar da barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Dicionario de dados
                Bundle dicionario = new Bundle();
                dicionario.putBoolean("voltar", true);
                dicionario.putString("tela", "Jogo");
                Intent resultado = new Intent();
                //Dicionario de dados
                resultado.putExtras(dicionario);
                //Seta o valor do resultado e os que serao retornados
                this.setResult(Activity.RESULT_OK, resultado);
                //volta para a activity anterior
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cartas_carta0:
                cartaSelecionada = 0;
                carta1.setClickable(false);
                carta2.setClickable(false);
                carta3.setClickable(false);
                mostraCarta();
                break;
            case R.id.cartas_carta1:
                cartaSelecionada = 1;
                carta0.setClickable(false);
                carta2.setClickable(false);
                carta3.setClickable(false);
                mostraCarta();
                break;
            case R.id.cartas_carta2:
                cartaSelecionada = 2;
                carta0.setClickable(false);
                carta1.setClickable(false);
                carta3.setClickable(false);
                mostraCarta();
                break;
            case R.id.cartas_carta3:
                cartaSelecionada = 3;
                carta0.setClickable(false);
                carta1.setClickable(false);
                carta2.setClickable(false);
                mostraCarta();
                break;
            case R.id.avisoal_btnok:
                retornaEvento();
                break;
        }
    }
}
