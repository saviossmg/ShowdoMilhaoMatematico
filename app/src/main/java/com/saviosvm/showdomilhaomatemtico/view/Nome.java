package com.saviosvm.showdomilhaomatemtico.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.saviosvm.showdomilhaomatemtico.AndGraph.AGSoundManager;
import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.controler.JogadorC;

public class Nome extends AppCompatActivity {

    private EditText nome = null;
    private EditText classe = null;
    private RadioGroup grupoAno = null;
    private RadioButton ano4 = null;
    private RadioButton ano5 = null;
    private RadioButton ano6 = null;
    private RadioButton ano7 = null;

    private Intent solicitacao = null;

    //dados do nivel
    private int niveisPos;

    static JogadorC jogadoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome);

        //referencia visual
        nome = findViewById(R.id.nome_nome);
        classe = findViewById(R.id.nome_serie);
        grupoAno = findViewById(R.id.nome_grupoano);
        ano4 = findViewById(R.id.nome_nivel_4);
        ano5 = findViewById(R.id.nome_nivel_5);
        ano6 = findViewById(R.id.nome_nivel_6);
        ano7 = findViewById(R.id.nome_nivel_7);
        niveisPos = 4;

        ano4.setTextColor(getResources().getColor(R.color.cortexto));
        ano6.setTextColor(getResources().getColor(R.color.corhint));
        ano6.setTextColor(getResources().getColor(R.color.corhint));
        ano7.setTextColor(getResources().getColor(R.color.corhint));

        grupoAno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (ano4.isChecked()) {
                    niveisPos = 4;
                    ano4.setTextColor(getResources().getColor(R.color.cortexto));
                    ano5.setTextColor(getResources().getColor(R.color.corhint));
                    ano6.setTextColor(getResources().getColor(R.color.corhint));
                    ano7.setTextColor(getResources().getColor(R.color.corhint));
                }
                if (ano5.isChecked()){
                    niveisPos = 5;
                    ano4.setTextColor(getResources().getColor(R.color.corhint));
                    ano5.setTextColor(getResources().getColor(R.color.cortexto));
                    ano6.setTextColor(getResources().getColor(R.color.corhint));
                    ano7.setTextColor(getResources().getColor(R.color.corhint));
                }
                if (ano6.isChecked()){
                    niveisPos = 6;
                    ano4.setTextColor(getResources().getColor(R.color.corhint));
                    ano5.setTextColor(getResources().getColor(R.color.corhint));
                    ano6.setTextColor(getResources().getColor(R.color.cortexto));
                    ano7.setTextColor(getResources().getColor(R.color.corhint));
                }
                if (ano7.isChecked()){
                    niveisPos = 7;
                    ano4.setTextColor(getResources().getColor(R.color.corhint));
                    ano5.setTextColor(getResources().getColor(R.color.corhint));
                    ano6.setTextColor(getResources().getColor(R.color.corhint));
                    ano7.setTextColor(getResources().getColor(R.color.cortexto));
                }
            }
        });

    }

    public void clickIniciar(View Botao) {
        if(nome.getText().toString().isEmpty() || classe.getText().toString().isEmpty()){
            Toast.makeText(this.getApplicationContext(),"Atenção: Nome e Classe do jogador não podem ficar vazios", Toast.LENGTH_LONG).show();
        }
        else{
            jogadoBanco.inserir(nome.getText().toString(),classe.getText().toString(),niveisPos);
            //cria a intent para a tela de novo jogador
            solicitacao = new Intent(this, Jogo.class);
            //Dicionario de dados
            startActivityForResult(solicitacao, 10);
        }
    }

    @Override
    public void onActivityResult(int codigo, int resultado, Intent dados) {
        try {
            if(resultado == Activity.RESULT_OK){
                AGSoundManager.vrMusic.stop();
                AGSoundManager.vrMusic.loadMusic("menu.mp3", true);
                AGSoundManager.vrMusic.play();
                Boolean fim = dados.getExtras().getBoolean("fim");
                Intent volta = new Intent();
                //Dicionario de dados
                //Seta o valor do resultado e os que serao retornados
                this.setResult(Activity.RESULT_OK, volta);
                //volta para a activity anterior
                this.finish();
            }
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(), "Atenção: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void validaBanco(JogadorC paramJogador) {
        jogadoBanco = paramJogador;
    }
}
