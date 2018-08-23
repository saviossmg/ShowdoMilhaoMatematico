package com.saviosvm.showdomilhaomatemtico.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.controler.JogadorC;
import com.saviosvm.showdomilhaomatemtico.controler.PerguntaC;
import com.saviosvm.showdomilhaomatemtico.model.PerguntaM;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    private Button novo = null;
    private Button regra = null;
    private Button placar = null;
    private Button sobre = null;

    //intent
    private Intent solicitacao = null;
    private Bundle dicionario = null;

    //banco
    private JogadorC jogadorBanco;
    private PerguntaC perguntaBanco;

    //objetos
    private PerguntaM pergunta = null;
    private ArrayList<PerguntaM> perguntas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //referencias visuais
        novo = (Button) findViewById(R.id.principal_btnNovojogo);
        regra = (Button) findViewById(R.id.principal_btnRegras);
        placar = (Button) findViewById(R.id.principal_btnPlacar);
        sobre = (Button) findViewById(R.id.principal_btnAbout);

        //banco
        this.jogadorBanco = new JogadorC(this);
        this.perguntaBanco = new PerguntaC(this);


        this.perguntas = new ArrayList<>();
        if(perguntaBanco.listarGeral().isEmpty())
            carregarPerguntas();

        this.perguntas = perguntaBanco.listarGeral();

        //clicklistener
        novo.setOnClickListener(this);
        regra.setOnClickListener(this);
        placar.setOnClickListener(this);
        sobre.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.principal_btnNovojogo:
                novaTela(1);
                break;
            case R.id.principal_btnRegras:
                novaTela(2);
                break;
            case R.id.principal_btnPlacar:
                novaTela(3);
                break;
            case R.id.principal_btnAbout:
                novaTela(4);
                break;
        }
    }

    //Metodo que vai carregar os arquivos de perguntas
    private void carregarPerguntas(){
        try {
            //instancia o assetmanager e inputstream e buffer reader com o nome do arquivo
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("perguntas3.txt");
            InputStreamReader lerPlayers = new InputStreamReader(inputStream);
            BufferedReader lerArq = new BufferedReader(lerPlayers);
            //array auxiliar de strings
            String aux[];
            // lê a primeira linha para iniciar a leitura
            String linha;
            linha = lerArq.readLine();
            //laço que percorrera enquanto houverem linhas para serem lidas do aglomerados.txt
            while (linha != null) {
                if(linha.contains("PRIMEIRA LINHA")){
                    //lê a proxima linha
                    linha = lerArq.readLine();
                }
                else{
                    //quebra a linha em um array
                    //enunciado, card, ano, alternativa 1, alternativa 2, alternativa 3, alternativa 4, alternativa correta (o numero)
                    aux = linha.split(";");
                    //instancia novo objeto
                    pergunta = new PerguntaM();
                    //preenche o objeto
                    pergunta.setEnunciado(aux[0]);
                    pergunta.setNivel(Integer.parseInt(aux[1]));
                    pergunta.setAno(Integer.parseInt(aux[2]));
                    pergunta.setAlternativa1(aux[3]);
                    pergunta.setAlternativa2(aux[4]);
                    pergunta.setAlternativa3(aux[5]);
                    pergunta.setAlternativa4(aux[6]);
                    pergunta.setAlternativaCorreta(Integer.parseInt(aux[7]));
                    perguntas.add(pergunta);
                    //lê a proxima linha
                    linha = lerArq.readLine();
                }

            }
            //fecha o arquivo
            lerArq.close();
            for(PerguntaM a: perguntas){
                perguntaBanco.inserir(a);
            }
            Toast.makeText(this, "Perguntas inseridas com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //tratamento de erro
            Toast.makeText(this, "Atenção em carregarPerguntas: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    //METODOS DAS TELAS
    //inicia a intent conforme o click
    private void novaTela(int btn){
        if(btn == 1)
            novoJogo();
        if(btn == 2)
            regras();
        if(btn == 3)
            placar();
        if(btn == 4)
            sobre();

    }

    //inicia o nobo jogos
    public void novoJogo(){
/*
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_nome, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Antes de inciar, insira:");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        final EditText nome = (EditText) b.findViewById(R.id.nomeal_nome);
        final EditText classe = (EditText) b.findViewById(R.id.nomeal_serie);
        final Button nao = (Button) b.findViewById(R.id.nomeal_btnnao);
        final Button sim = (Button) b.findViewById(R.id.nomeal_btnsim);

        nao.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                b.dismiss();
            }
        });

        sim.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(nome.getText().toString().isEmpty() || classe.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Atenção: Nome e Classe do jogador não podem ficar vazios", Toast.LENGTH_LONG).show();
                }
                else{
                    jogadorBanco.inserir(nome.getText().toString(),classe.getText().toString());
                    Toast.makeText(getApplicationContext(),"Vamo jogar OEEE", Toast.LENGTH_LONG).show();
                    //cria a intent para a tela de novo jogador
                    solicitacao = new Intent(getApplicationContext(), Jogo.class);
                    //Dicionario de dados
                    Jogo.validaJogo(jogadorBanco,perguntas);
                    b.dismiss();
                    startActivityForResult(solicitacao, 10);
                }
            }
        });
        */

       //cria a intent para a tela de novo jogador
        solicitacao = new Intent(this, Nome.class);
        //Dicionario de dados
        Nome.validaBanco(jogadorBanco);
        Jogo.validaJogo(jogadorBanco,perguntas);
        startActivityForResult(solicitacao, 1);
    }

    //inicia as regras
    private void regras(){
        //cria a intent para a tela
        solicitacao = new Intent(this, Regra.class);
        startActivityForResult(solicitacao, 2);
    }

    //inicia as regras
    private void placar(){
        //cria a intent para a tela
        solicitacao = new Intent(this, Placar.class);
        Placar.validaBanco(jogadorBanco);
        startActivityForResult(solicitacao, 3);
    }

    //inicia o sobre
    private void sobre(){
        //cria a intent para a tela
        solicitacao = new Intent(this, Sobre.class);
        startActivityForResult(solicitacao, 4);
    }

}
