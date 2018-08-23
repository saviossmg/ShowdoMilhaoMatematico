package com.saviosvm.showdomilhaomatemtico.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.controler.JogadorC;
import com.saviosvm.showdomilhaomatemtico.model.NivelM;

import java.util.ArrayList;

public class Nome extends AppCompatActivity {

    private EditText nome = null;
    private EditText classe = null;
    private Spinner nivelCombo = null;
    private TextView textoSpinner = null;

    private Intent solicitacao = null;

    //dados do nivel
    private ArrayList<NivelM> niveis;

    //adapters dos spinners
    private ArrayAdapter<NivelM> nivelAdapter;
    private int niveisPos;

    static JogadorC jogadoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome);
        carregaNiveis();

        //referencia visual
        nome = (EditText) findViewById(R.id.nome_nome);
        classe = (EditText) findViewById(R.id.nome_serie);
        nivelCombo = (Spinner) findViewById(R.id.nome_nivelspi);

        //carrega adapter
        getAdapSpinner();

        //listeners
        nivelCombo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView nivelD, View v, int posicao, long id) {
                niveisPos = posicao;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    private void carregaNiveis(){
        niveis = new ArrayList<>();
        NivelM aux = new NivelM();
        aux.setId(0);
        aux.setDescricao("Selecione seu nível...");
        niveis.add(aux);

        for(int i = 4; i <= 6; i++){
            aux = new NivelM();
            aux.setId(i);
            aux.setDescricao(i+"º Ano");
            niveis.add(aux);
        }
    }

    private void getAdapSpinner() {
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        nivelAdapter = new ArrayAdapter<NivelM>(this, R.layout.estilo_spinner, niveis);
        //nivelAdapter = ArrayAdapter.createFromResource(this, R.layout.spinner_item, R.layout.estilo_spinner);
        nivelAdapter.setDropDownViewResource(R.layout.estilo_spinner);
        nivelCombo.setAdapter(nivelAdapter);
        nivelAdapter.notifyDataSetChanged();
        this.niveisPos = 0;
    }

    public void clickIniciar(View Botao) {
        if(nome.getText().toString().isEmpty() || classe.getText().toString().isEmpty()){
            Toast.makeText(this.getApplicationContext(),"Atenção: Nome e Classe do jogador não podem ficar vazios", Toast.LENGTH_LONG).show();
        }
        else
        if(niveisPos == 0){
            Toast.makeText(this.getApplicationContext(),"Atenção: selecione o seu nível!", Toast.LENGTH_LONG).show();
        }
        else{
            jogadoBanco.inserir(nome.getText().toString(),classe.getText().toString(),niveis.get(niveisPos).getId());
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
