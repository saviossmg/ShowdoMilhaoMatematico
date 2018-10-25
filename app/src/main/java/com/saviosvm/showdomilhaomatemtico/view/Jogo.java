package com.saviosvm.showdomilhaomatemtico.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.saviosvm.showdomilhaomatemtico.AndGraph.AGSoundManager;
import com.saviosvm.showdomilhaomatemtico.R;
import com.saviosvm.showdomilhaomatemtico.controler.Efeitos;
import com.saviosvm.showdomilhaomatemtico.controler.JogadorC;
import com.saviosvm.showdomilhaomatemtico.model.JogadorM;
import com.saviosvm.showdomilhaomatemtico.model.PerguntaM;
import com.saviosvm.showdomilhaomatemtico.model.PontuacaoM;
import com.saviosvm.showdomilhaomatemtico.model.ScoreM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo extends AppCompatActivity implements View.OnClickListener {

    //referencias visuais
    private TextView enunciado = null;
    private TextView nome = null;

    private Button alternativa1 = null;
    private Button alternativa2 = null;
    private Button alternativa3 = null;
    private Button alternativa4 = null;
    private Button sair = null;
    private Button cartas = null;
    private Button convidados = null;
    private Button placas = null;
    private Button pular1 = null;
    private Button pular2 = null;
    private Button pular3 = null;

    private TextView errarVal = null;
    private TextView sairVal = null;
    private TextView acertarVal = null;

    private TextView faltas = null;
    private TextView tempo = null;

    //dialogo confirmacao
    private Button confSim = null;
    private Button confNao = null;
    private TextView confNomejog = null;
    private TextView confTexto = null;

    //dialogo aviso
    private TextView aviNome = null;
    private TextView aviTexto = null;
    private Button aviOk = null;

    //objetos do jogo
    private JogadorM jogador = null;
    private ArrayList<PerguntaM> grupo1 = null;
    private ArrayList<PerguntaM> grupo2 = null;
    private ArrayList<PerguntaM> grupo3 = null;
    private ArrayList<PerguntaM> grupo4 = null;

    //banco e dados estaticos
    static JogadorC bancoJogador;
    static ArrayList<PerguntaM> perguntas;

    //variaveis de controle
    private double pontuacao;
    private int acertos;
    private double tempoJog;
    private double mediaTempo;
    private ArrayList<PontuacaoM> scores = null;

    private int escolha = -1;
    private int pulos = 0;
    private int punicoes = 0;
    private int card = 1;
    private PerguntaM selecionada = null;
    private ArrayList<PerguntaM> perguntasRespondidas = null;
    private int pergSelecionada;
    private int tempoUsado;
    private ArrayList<Integer> vistas = null;
    private Random gerador = null;

    //builders visuais
    private AlertDialog.Builder confirmacao = null;
    private AlertDialog dconfirmacao = null;
    private AlertDialog.Builder valendoxpts = null;
    private AlertDialog valendoxptsd = null;
    private LayoutInflater inflador = null;
    private View dialogView = null;

    //varivaveis que controlarão o cronometro
    private CountDownTimer ct = null;
    private long timeRemaing = 0;
    private boolean paraCronometro;
    private boolean corrente ;
    private boolean acertou ;
    private boolean parou;
    private boolean conferirReposta;
    private boolean desistiu;
    private boolean pulou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        AGSoundManager.vrMusic.stop();
        //AGSoundManager.vrSoundEffects.play(Efeitos.getMostraPerunta());
        //refernecias visuais
        enunciado = findViewById(R.id.jogo_txtpergunta);
        nome = findViewById(R.id.jogo_txtnomejogador);
        errarVal = findViewById(R.id.jogo_valerrar);
        sairVal = findViewById(R.id.jogo_valparar);
        acertarVal = findViewById(R.id.jogo_valacertar);

        alternativa1 = findViewById(R.id.jogo_btnalternativa1);
        alternativa2 = findViewById(R.id.jogo_btnalternativa2);
        alternativa3 = findViewById(R.id.jogo_btnalternativa3);
        alternativa4 = findViewById(R.id.jogo_btnalternativa4);
        sair = findViewById(R.id.jogo_btnparar);
        cartas = findViewById(R.id.jogo_cartas);
        convidados = findViewById(R.id.jogo_convidados);
        placas = findViewById(R.id.jogo_placas);
        pular1 = findViewById(R.id.jogo_pular1);
        pular2 = findViewById(R.id.jogo_pular2);
        pular3 = findViewById(R.id.jogo_pular3);

        faltas = findViewById(R.id.jogo_faltas);
        tempo = findViewById(R.id.jogo_cronometro);
        tempo.setText("0");

        grupo1 = new ArrayList<>();
        grupo2 = new ArrayList<>();
        grupo3 = new ArrayList<>();
        grupo4 = new ArrayList<>();

        //carrega as perguntas conforme o grupo
        perguntasRespondidas = new ArrayList<>();
        for (PerguntaM a: perguntas){
            if(a.getNivel() == 1)
                grupo1.add(a);
            if(a.getNivel() == 2)
                grupo2.add(a);
            if(a.getNivel() == 3)
                grupo3.add(a);
            if(a.getNivel() == 4)
                grupo4.add(a);
        }

        //carrega o ultimo jogador registrado
        jogador = bancoJogador.findUltimo();

        //seta o nome
        nome.setText(jogador.getNome());

        //seta o listener
        alternativa1.setOnClickListener(this);
        alternativa2.setOnClickListener(this);
        alternativa3.setOnClickListener(this);
        alternativa4.setOnClickListener(this);
        sair.setOnClickListener(this);
        cartas.setOnClickListener(this);
        convidados.setOnClickListener(this);
        placas.setOnClickListener(this);
        pular1.setOnClickListener(this);
        pular2.setOnClickListener(this);
        pular3.setOnClickListener(this);

        //desabilita os dois botões, só o primeiro vai ser usado no começo
        pular2.setEnabled(false);
        pular3.setEnabled(false);

        //inicia as variaveis
        pontuacao = 1000;
        acertos = 0;
        tempoJog = 0;
        mediaTempo = 0;
        vistas = new ArrayList<>();
        parou = false;
        pulou = false;

        //começa o jogo
        carregaScore();
        iniciarJogo();
    }

    @Override
    public void onBackPressed(){
        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }

    @Override
    public void onActivityResult(int codigo, int resultado, Intent dados) {
        try {
            if(resultado == Activity.RESULT_OK){
                if(resultado == Activity.RESULT_OK) {
                    String mensagem;
                    Boolean voltar = dados.getExtras().getBoolean("voltar");
                    String tela = dados.getExtras().getString("tela");
                    int erradas = dados.getExtras().getInt("valor");

                    //verifica se esta voltando pelo backbutton, se estiver habilita de novo o botao
                    if (tela.contains("Jogo")) {
                        this.paraCronometro = false;
                        valendoTempo();
                        if (voltar) {
                            Toast.makeText(this.getApplicationContext(), "Cartas não jogadas... segue o jogo! ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            cartas.setEnabled(false);
                            cartas.setBackground(getDrawable(R.drawable.btncartasd));
                            anulaQuestaoErrada(erradas);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(), "Atenção: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //metodo que fará  jogo iniciar seu fluxo
    private void iniciarJogo(){
        enunciado.setText("");
        alternativa1.setText("");
        alternativa1.setEnabled(true);
        alternativa2.setText("");
        alternativa2.setEnabled(true);
        alternativa3.setText("");
        alternativa3.setEnabled(true);
        alternativa4.setText("");
        alternativa4.setEnabled(true);

        tempo.setText("01:00");
        tempo.setTextColor(Color.GRAY );
        desistiu = false;
        pulou = false;

        alternativa1.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
        alternativa2.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
        alternativa3.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
        alternativa4.setBackground(getDrawable(R.drawable.design_btn_jogo_on));

        errarVal.setText("");
        sairVal.setText("");
        acertarVal.setText("");

        //inicia os valores da variavel
        valendoxpts = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        inflador = this.getLayoutInflater();
        dialogView = inflador.inflate(R.layout.alert_aviso, null);
        valendoxpts.setView(dialogView);
        if(acertos < 15)
            valendoxpts.setTitle((acertos+1)+"ª Pergunta");
        else
            valendoxpts.setTitle("Pergunta Final!");
        valendoxptsd = valendoxpts.create();
        valendoxptsd.show();
        valendoxptsd.setCancelable(false);
        aviOk = valendoxptsd.findViewById(R.id.avisoal_btnok);
        aviNome = valendoxptsd.findViewById(R.id.avisoal_txtnome);
        aviTexto = valendoxptsd.findViewById(R.id.avisoal_txtaviso);
        aviNome.setText(jogador.getNome()+"...");
        aviTexto.setText("Valendo "+scores.get(acertos).getDescricaoAcerto());
        aviTexto.setTextSize(50);
        aviOk.setText("Estou pronto!");
        aviOk.setOnClickListener(this);
        AGSoundManager.vrSoundEffects.play(Efeitos.getMostraPerunta());

        this.timeRemaing = 60*1000;
        this.paraCronometro = false;
        this.corrente = false;
        this.conferirReposta = false;
        this.escolha = -1;

        habilitarClicks(false);

        //se for a pergunta final desabilita todas as ajudas
        if(acertos == 15){
            cartas.setEnabled(false);
            cartas.setBackground(getDrawable(R.drawable.btncartasd));
            convidados.setEnabled(false);
            convidados.setBackground(getDrawable(R.drawable.btnconvd));
            placas.setEnabled(false);
            placas.setBackground(getDrawable(R.drawable.btnplacasd));
            pular1.setEnabled(false);
            pular1.setBackground(getDrawable(R.drawable.btnpulard));
            pular2.setEnabled(false);
            pular2.setBackground(getDrawable(R.drawable.btnpulard));
            pular3.setEnabled(false);
            pular3.setBackground(getDrawable(R.drawable.btnpulard));
        }

    }

    //metodo que carrega a pergunta
    private void carregarPergunta()  {
        //seleciona a pergunta de acordo com o card
        selecionada = new PerguntaM();
        pergSelecionada = -1;
        AGSoundManager.vrMusic.loadMusic("suspensea.mp3", true);
        if (card == 1){
            pergSelecionada = sorteiaPergunta(grupo1, jogador.getAno());
            selecionada = grupo1.get(pergSelecionada);
            AGSoundManager.vrMusic.loadMusic("suspensea.mp3", true);
        }
        if(card == 2){
            pergSelecionada = sorteiaPergunta(grupo2, jogador.getAno());
            selecionada = grupo2.get(pergSelecionada);
            AGSoundManager.vrMusic.loadMusic("suspenseb.mp3", true);
        }
        if(card == 3){
            pergSelecionada = sorteiaPergunta(grupo3, jogador.getAno());
            selecionada = grupo3.get(pergSelecionada);
            AGSoundManager.vrMusic.loadMusic("suspensec.mp3", true);
        }
        if(card == 4){
            pergSelecionada = sorteiaPergunta(grupo4, jogador.getAno());
            selecionada = grupo4.get(pergSelecionada);
            AGSoundManager.vrMusic.loadMusic("suspensed.mp3", true);
        }
        AGSoundManager.vrMusic.play();
        mostraPerguntas();
    }

    private void criarDecisao(int i){
        escolha = i;
        //alerta aqui
        confirmacao = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        inflador = this.getLayoutInflater();
        dialogView = inflador.inflate(R.layout.alert_confirmacao, null);
        confirmacao.setView(dialogView);
        confirmacao.setTitle("E então....");
        dconfirmacao = confirmacao.create();
        dconfirmacao.show();
        dconfirmacao.setCancelable(false);
        confSim = dconfirmacao.findViewById(R.id.confiral_btnsim);
        confNao = dconfirmacao.findViewById(R.id.confiral_btnnao);
        confNomejog = dconfirmacao.findViewById(R.id.confiral_txtnomejog);
        confTexto = dconfirmacao.findViewById(R.id.confiral_txtaviso);
        confNomejog.setText(jogador.getNome()+"...");
        if(i == 0){
            confTexto.setText("Está certo de que deseja PARAR o jogo?!");
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomParar());
        }
        if(i >=1 && i <= 4){
            confTexto.setText("Está certo da alternativa "+i+" ser a resposta correta?!");
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomEstacertodisso());
        }
        if(i == 10){
            confTexto.setText("Está certo de pedir a opinião dos convidados ?");
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomCerteza());
        }
        if(i == 20){
            confTexto.setText("Está certo de usar as cartas ?");
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomCerteza());
        }
        if(i == 30){
            confTexto.setText("Está certo de usar as placas ?");
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomCerteza());
        }
        if(i == 40){
            confTexto.setText("Está certo de pular está pergunta ? Você ainda pode pular "+(3-pulos)+" vez(es)");
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomVaipular());
        }

        confSim.setOnClickListener(this);
        confNao.setOnClickListener(this);
    }

    //confirmação da decisão
    private void confirmaDecisao(int i){
        //se ele parar o jogo, salva a pontuação
        if(i == 0){
            dconfirmacao.dismiss();
            parou = true;
            mostraResultado(true);
        }
        if(i >=1 && i <= 4){
            dconfirmacao.dismiss();
            resultadoDecisao(i);
        }
        if(i == 10){
            //convidados
            dconfirmacao.dismiss();
            convidados.setEnabled(false);
            convidados.setBackground(getDrawable(R.drawable.btnconvd));
            mostrarAjuda(i);
        }
        if(i == 20){
            //cartas
            dconfirmacao.dismiss();
            mostraTelaCartas();
        }
        if(i == 30){
            //placas
            dconfirmacao.dismiss();
            placas.setEnabled(false);
            placas.setBackground(getDrawable(R.drawable.btnplacasd));
            mostrarAjuda(i);
        }
        if(i == 40){
            dconfirmacao.dismiss();
            pulos++;
            pulou = true;
            if(pulos == 1){
                pular1.setEnabled(false);
                pular1.setBackground(getDrawable(R.drawable.btnpulard));
                pular2.setEnabled(true);
            }
            if(pulos == 2){
                pular2.setEnabled(false);
                pular2.setBackground(getDrawable(R.drawable.btnpulard));
                pular3.setEnabled(true);
            }
            if(pulos == 3){
                pular3.setEnabled(false);
                pular3.setBackground(getDrawable(R.drawable.btnpulard));
            }
            paraCronometro = true;
            ct.cancel();
            this.tempoJog += this.tempoUsado;
            AGSoundManager.vrMusic.stop();
            mostraResultado(false);
            //Toast.makeText(this,"Ainda restam "+(3-pulos)+" pulos.",Toast.LENGTH_LONG).show();
        }
    }

    //faz a ação para verificar se está certo ou errado
    private void resultadoDecisao(final int alter){
        if(alter == 1) alternativa1.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
        if(alter == 2) alternativa2.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
        if(alter == 3) alternativa3.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
        if(alter == 4) alternativa4.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
        habilitarClicks(false);
        ct.cancel();
        this.tempoJog += this.tempoUsado;
        //congela a tela por 2 segundos antes de mostrar a alternativa correta
        CountDownTimer v =  new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished){
            }
            public void onFinish() {
                this.cancel();
                resultadoDecisao2(alter);
            }
        };
        v.start();
    }

    //continuação do metodo anterior
    private void resultadoDecisao2(final int alter){
        acertou = false;
        //faz o som tocar
        AGSoundManager.vrMusic.stop();
        if(alter == selecionada.getAlternativaCorreta())
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomAcerto());
        else
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomErro());

        CountDownTimer d =  new CountDownTimer(8000, 500) {
            int seg = 0;
            public void onTick(long millisUntilFinished) {
                //formata a string conforme o tempo avança
                //pisca por 6 segundos a alternativa correta
                seg = (int)( (millisUntilFinished%8000)/500);
                if(seg % 2 == 0){
                    if(selecionada.getAlternativaCorreta() == 1) alternativa1.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                    if(selecionada.getAlternativaCorreta() == 2) alternativa2.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                    if(selecionada.getAlternativaCorreta() == 3) alternativa3.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                    if(selecionada.getAlternativaCorreta() == 4) alternativa4.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                }
                else{
                    if(selecionada.getAlternativaCorreta() == 1){
                        if(alter == 1 )alternativa1.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
                        else alternativa1.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
                    }
                    if(selecionada.getAlternativaCorreta() == 2){
                        if(alter == 2 )alternativa2.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
                        else alternativa2.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
                    }
                    if(selecionada.getAlternativaCorreta() == 3){
                        if(alter == 3 )alternativa3.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
                        else alternativa3.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
                    }
                    if(selecionada.getAlternativaCorreta() == 4){
                        if(alter == 4 )alternativa4.setBackground(getDrawable(R.drawable.design_btn_jogo_off));
                        else alternativa4.setBackground(getDrawable(R.drawable.design_btn_jogo_on));
                    }
                }
            }
            public void onFinish() {
                if(selecionada.getAlternativaCorreta() == 1) alternativa1.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                if(selecionada.getAlternativaCorreta() == 2) alternativa2.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                if(selecionada.getAlternativaCorreta() == 3) alternativa3.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                if(selecionada.getAlternativaCorreta() == 4) alternativa4.setBackground(getDrawable(R.drawable.design_btn_jogo_correto));
                //mostra a janela para a proxima pergunta ou sair do jogo por erro
                if(alter == selecionada.getAlternativaCorreta()){
                    acertou = true;
                    acertos++;
                    //verifica o card
                    if(card == 1) grupo1.get(pergSelecionada).setRespondido(true);
                    if(card == 2) grupo2.get(pergSelecionada).setRespondido(true);
                    if(card == 3) grupo3.get(pergSelecionada).setRespondido(true);
                    if(card == 4) grupo4.get(pergSelecionada).setRespondido(true);
                    //pula de card conforme os acertos
                    if(acertos == 5) card = 2;
                    if(acertos == 10) card = 3;
                    if(acertos == 15) card = 4;
                }
                this.cancel();
                mostraResultado(false);
            }
        };
        d.start();
    }

    private void mostraResultado(boolean parou){
        conferirReposta = true;
        valendoxpts = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        inflador = this.getLayoutInflater();
        dialogView = inflador.inflate(R.layout.alert_aviso, null);
        valendoxpts.setView(dialogView);
        valendoxpts.setCancelable(false);
        if(pulou){
            valendoxpts.setTitle("Ok...");
        } else {
            if(!parou){
                if(acertou)
                    valendoxpts.setTitle("Parabéns");
                else
                    valendoxpts.setTitle("Que pena...");


            }
            else{
                AGSoundManager.vrSoundEffects.play(Efeitos.getSomSelecparou());
                valendoxpts.setTitle("Tudo bem...");
            }
        }
        valendoxptsd = valendoxpts.create();
        valendoxptsd.show();
        valendoxptsd.setCancelable(false);
        aviOk = valendoxptsd.findViewById(R.id.avisoal_btnok);
        aviNome = valendoxptsd.findViewById(R.id.avisoal_txtnome);
        aviTexto = valendoxptsd.findViewById(R.id.avisoal_txtaviso);
        aviNome.setText(jogador.getNome()+"...");
        if(pulou){
            aviTexto.setText("Você pulou!\nVamos selecionar outra pergunta.");
            aviOk.setText("Próxima pergunta");
        } else {
            if(!parou){
                if(acertou){
                    perguntasRespondidas.add(selecionada);
                    if(acertos <= 15){
                        aviTexto.setText("Você acertou!\nGanhou "+scores.get(acertos-1).getDescricaoAcerto()+" pontos!");
                        aviOk.setText("Próxima pergunta");
                    }
                    else{
                        AGSoundManager.vrSoundEffects.play(Efeitos.getSomParabens());
                        AGSoundManager.vrMusic.loadMusic("ganhou.mp3", false);
                        AGSoundManager.vrMusic.play();
                        aviTexto.setText("Você venceu o jogo!");
                        aviOk.setText("Sair do jogo");
                    }
                }
                else{
                    AGSoundManager.vrMusic.loadMusic("perdeu.mp3", false);
                    AGSoundManager.vrMusic.play();
                    //verifica se é a primeira ou a ultima pergunta
                    String textoPerca = "Você perdeu";
                    if(acertos == 0 || acertos == 15)
                        textoPerca += " e saiu sem nada!";
                    else
                        textoPerca += "!\nMas saiu com "+scores.get(acertos).getDescricaoErro()+" pontos!";

                    aviTexto.setText(textoPerca);
                    aviOk.setText("Sair do jogo");
                }
            }
            else{
                AGSoundManager.vrMusic.loadMusic("parou.mp3", false);
                AGSoundManager.vrMusic.play();
                aviTexto.setText("Você Parou o jogo!\nMas saiu com "+scores.get(acertos).getDescricaoDesistir()+" pontos!");
                aviOk.setText("Sair do jogo");
                this.acertou = false;
            }
        }
        aviTexto.setTextSize(30);
        aviOk.setOnClickListener(this);
    }

    //carega o score
    private void carregaScore(){
        int card;
        int i;
        scores = new ArrayList<>();
        ScoreM ptsDesc = new ScoreM();
        PontuacaoM auxScore = null;
        //laço que vai percorrer de acordo com o card
        for(card=1;card <4;card++){
            //se executa os 3 cards
            for(i=0;i<5;i++){
                auxScore = new PontuacaoM();
                auxScore.setCard(card);
                if(card==1){
                    auxScore.setValorAcerto(ptsDesc.getCard1Acertarpts().get(i));
                    auxScore.setValorDesistir(ptsDesc.getCard1Pararpts().get(i));
                    auxScore.setValorErro(ptsDesc.getCard1Errarpts().get(i));
                    auxScore.setDescricaoAcerto(ptsDesc.getCard1Acertartxt().get(i));
                    auxScore.setDescricaoDesistir(ptsDesc.getCard1Parartxt().get(i));
                    auxScore.setDescricaoErro(ptsDesc.getCard1Errartxt().get(i));
                }
                if(card==2){
                    auxScore.setValorAcerto(ptsDesc.getCard2Acertarpts().get(i));
                    auxScore.setValorDesistir(ptsDesc.getCard2Pararpts().get(i));
                    auxScore.setValorErro(ptsDesc.getCard2Errarpts().get(i));
                    auxScore.setDescricaoAcerto(ptsDesc.getCard2Acertartxt().get(i));
                    auxScore.setDescricaoDesistir(ptsDesc.getCard2Parartxt().get(i));
                    auxScore.setDescricaoErro(ptsDesc.getCard2Errartxt().get(i));
                }
                if(card==3){
                    auxScore.setValorAcerto(ptsDesc.getCard3Acertarpts().get(i));
                    auxScore.setValorDesistir(ptsDesc.getCard3Pararpts().get(i));
                    auxScore.setValorErro(ptsDesc.getCard3Errarpts().get(i));
                    auxScore.setDescricaoAcerto(ptsDesc.getCard3Acertartxt().get(i));
                    auxScore.setDescricaoDesistir(ptsDesc.getCard3Parartxt().get(i));
                    auxScore.setDescricaoErro(ptsDesc.getCard3Errartxt().get(i));
                }
                scores.add(auxScore);
            }
        }
        //1 milhão
        auxScore = new PontuacaoM();
        auxScore.setCard(4);
        auxScore.setValorAcerto(ptsDesc.getCard4Acertarpts());
        auxScore.setValorDesistir(ptsDesc.getCard4Pararpts());
        auxScore.setValorErro(ptsDesc.getCard4Errarpts());
        auxScore.setDescricaoAcerto(ptsDesc.getCard4Acertartxt());
        auxScore.setDescricaoDesistir(ptsDesc.getCard4Parartxt());
        auxScore.setDescricaoErro(ptsDesc.getCard4Errartxt());
        scores.add(auxScore);
    }

    //inicia o cronometro
    private void valendoTempo(){
        tempo.setTextColor(Color.WHITE);
        long millisInFuture = timeRemaing; //60 segundos
        long countDownInterval = 1000; //1 segundo
        this.tempoUsado = 0;
        ct =  new CountDownTimer(millisInFuture, countDownInterval) {
            int fimTempo = 0;
            int seg = 0;
            public void onTick(long millisUntilFinished) {
                if(paraCronometro)
                {
                    //pausa ou cancela, dependendo do contexto
                    cancel();
                }
                else{
                    //formata a string conforme o tempo avança
                    String v = String.format("%02d", millisUntilFinished/60000);
                    timeRemaing = millisUntilFinished;
                    seg = (int)( (millisUntilFinished%60000)/1000);
                    tempoUsado += 1;
                    if(seg <= 10 && seg > 0)
                        fimTempo++;
                    if(fimTempo%2!= 0)
                        tempo.setTextColor(Color.RED);
                    else
                        tempo.setTextColor(Color.WHITE);
                    tempo.setText(v+":"+String.format("%02d",seg));
                }
            }
            public void onFinish() {
                if(!paraCronometro){
                    tempo.setTextColor(Color.RED);
                    tempo.setText("Fim do tempo!");
                    punicoes++;
                    faltas.setText(String.valueOf(punicoes));
                    tempoUsado +=1;
                    Toast.makeText(getApplicationContext(),"Você tomou uma punição por limite de tempo.",Toast.LENGTH_LONG).show();
                }
            }
        };
        ct.start();
    }

    //mostra os menus de ajuda
    private void mostrarAjuda(int opt){
        paraCronometro = true;
        valendoxpts = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        inflador = this.getLayoutInflater();
        dialogView = inflador.inflate(R.layout.alert_aviso, null);
        valendoxpts.setView(dialogView);
        if(opt == 10)
            valendoxpts.setTitle("Ajuda dos Convidados");
        if(opt == 30)
            valendoxpts.setTitle("Ajuda das Placas");
        valendoxptsd = valendoxpts.create();
        valendoxptsd.show();
        valendoxptsd.setCancelable(false);
        aviOk = valendoxptsd.findViewById(R.id.avisoal_btnok);
        aviNome = valendoxptsd.findViewById(R.id.avisoal_txtnome);
        aviTexto = valendoxptsd.findViewById(R.id.avisoal_txtaviso);
        if(opt == 10){
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomAjudaconv());
            aviNome.setText("Convidados...");
            aviTexto.setText("Dêem sua opinião ao jogador(a).");
        }
        if(opt == 30){
            AGSoundManager.vrSoundEffects.play(Efeitos.getSomAjudaplacas());
            aviNome.setText("Platéia...");
            aviTexto.setText("Levante a placa que você acredita ser a resposta correta.");
        }
        aviTexto.setTextSize(20);
        aviOk.setText("Já me decidi!");
        this.corrente = true;
        aviOk.setOnClickListener(this);
    }

    //Mostra as perguintass
    private void mostraPerguntas(){
        long millisInFuture = 22000; //60 segundos
        long countDownInterval = 1000; //1 segundo
        enunciado.setText(selecionada.getEnunciado());
        CountDownTimer kk =  new CountDownTimer(millisInFuture, countDownInterval) {
            int seg = 0;
            public void onTick(long millisUntilFinished) {
                seg = (int)( (millisUntilFinished%21000)/1000);
                if(seg == 14)
                    alternativa1.setText("\t1) "+selecionada.getAlternativa1());
                if(seg == 10)
                    alternativa2.setText("\t2) "+selecionada.getAlternativa2());
                if(seg == 6)
                    alternativa3.setText("\t3) "+selecionada.getAlternativa3());
                if(seg == 2)
                    alternativa4.setText("\t4) "+selecionada.getAlternativa4());
            }
            public void onFinish() {
                errarVal.setText(scores.get(acertos).getDescricaoErro());
                sairVal.setText(scores.get(acertos).getDescricaoDesistir());
                acertarVal.setText(scores.get(acertos).getDescricaoAcerto());
                habilitarClicks(true);
                valendoTempo();
                AGSoundManager.vrSoundEffects.play(Efeitos.getSomQualaresposta());
                this.cancel();
            }
        };
        kk.start();
    }

    //sorteia a pergunta
    private int sorteiaPergunta(ArrayList<PerguntaM> sort, int ano){
        int pos = 0;
        boolean passa = false;
        while (!passa){
            gerador = new Random();
            pos = gerador.nextInt(sort.size());
            //verifica se é do mesmo nivel
            if(sort.get(pos).getAno() == ano && sort.get(pos).isRespondido() == false){
                //verifica se já existe algum registro visualizado pelo jogador
                passa = true;
                for(Integer a: vistas){
                    if(a == pos)
                        passa = false;
                }
            }
        }
        vistas.add(pos);
        return pos;
    }

    //metodo que habilita e desabilita o click dos botões
    private void habilitarClicks(boolean param){
        alternativa1.setClickable(param);
        alternativa2.setClickable(param);
        alternativa3.setClickable(param);
        alternativa4.setClickable(param);
        sair.setClickable(param);
        cartas.setClickable(param);
        convidados.setClickable(param);
        placas.setClickable(param);
        pular1.setClickable(param);
        pular2.setClickable(param);
        pular3.setClickable(param);
    }

    //Metodo que finaliza o jogo
    private void finalizarJogo(int status){
        AGSoundManager.vrSoundEffects.play(Efeitos.getSomTchau());
        //status 0 - errou, 1 - desistiu, 2 - venceu o jogo
        if(parou)
            status = 1;
        if(desistiu)
            status = 1;

        paraCronometro  = true;
        ct.cancel();

        //verifica o que fazer
        int indexAcertos = acertos;
        if(acertos > 0 && status == 2)
            indexAcertos = acertos -1;

        if(status == 0)
            pontuacao = scores.get(indexAcertos).getValorErro();
        if(status == 1)
            pontuacao = scores.get(indexAcertos).getValorDesistir();
        if(status == 2)
            pontuacao = scores.get(indexAcertos).getValorAcerto();

        if(acertos == 0){
            mediaTempo = tempoUsado;
            tempoJog = tempoUsado;
        }
        else{
            mediaTempo = tempoJog/acertos;
        }

        //modifica o objeto
        jogador.setAcertos(acertos);
        jogador.setPontos(pontuacao);
        jogador.setMediaTempo(mediaTempo);
        jogador.setTempo(tempoJog);
        jogador.setFaltas(punicoes);

        //atualiza no banco
        bancoJogador.atualizar(jogador);

        //atualiza as perguntas em tempo de execução, para as mesmas nao aparecerem de novo no proximo jogo
        for (PerguntaM a: perguntasRespondidas){
            for(int i = 0; i< perguntas.size(); i++){
                if(a.getId() == perguntas.get(i).getId()){
                    perguntas.get(i).setRespondido(true);
                }
            }
        }

        //faz a intent para sair do jogo
        Bundle dicionario = new Bundle();
        dicionario.putBoolean("fim", true);
        Intent resultado = new Intent();
        //Dicionario de dados
        resultado.putExtras(dicionario);
        //Seta o valor do resultado e os que serao retornados
        this.setResult(Activity.RESULT_OK, resultado);
        //volta para a activity anterior
        this.finish();

    }

    //referencias
    public static void validaJogo(JogadorC paramJogador, ArrayList<PerguntaM> paramPergunta){
        bancoJogador = paramJogador;
        perguntas = paramPergunta;
    }

    //carrega a tela das cartas
    private void mostraTelaCartas(){
        paraCronometro = true;
        Bundle dicionario = new Bundle();
        dicionario.putString("nome", jogador.getNome());
        Intent solicitacao = new Intent(this, Cartas.class);
        solicitacao.putExtras(dicionario);
        startActivityForResult(solicitacao, 20);
    }

    //anula as perguntas erradas
    private void anulaQuestaoErrada(int anular){
        //se vier 0 ele nem anula
        if(anular !=0){
            boolean next = false;
            ArrayList<Integer> listaAnuladas = new ArrayList<>();
            int i;
            for(i=0;i<anular;i++){
                next = false;
                while(!next){
                    gerador = new Random();
                    int pos = gerador.nextInt(5);
                    if(pos != selecionada.getAlternativaCorreta() && pos != 0 && !listaAnuladas.contains(pos)){
                        listaAnuladas.add(pos);
                        next = true;
                    }
                }
            }
            //segundo for que anula
            for(Integer errada:listaAnuladas){
                if(errada == 1){
                    alternativa1.setEnabled(false);
                    alternativa1.setText("");
                }
                if(errada == 2){
                    alternativa2.setEnabled(false);
                    alternativa2.setText("");
                }
                if(errada == 3){
                    alternativa3.setEnabled(false);
                    alternativa3.setText("");
                }
                if(errada == 4){
                    alternativa4.setEnabled(false);
                    alternativa4.setText("");
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jogo_btnalternativa1:
                criarDecisao(1);
                break;
            case R.id.jogo_btnalternativa2:
                criarDecisao(2);
                break;
            case R.id.jogo_btnalternativa3:
                criarDecisao(3);
                break;
            case R.id.jogo_btnalternativa4:
                criarDecisao(4);
                break;
            case R.id.jogo_btnparar:
                desistiu = true;
                criarDecisao(0);
                break;
            case R.id.jogo_convidados:
                criarDecisao(10);
                break;
            case R.id.jogo_cartas:
                criarDecisao(20);
                break;
            case R.id.jogo_placas:
                criarDecisao(30);
                break;
            case R.id.jogo_pular1:
                criarDecisao(40);
                break;
            case R.id.jogo_pular2:
                criarDecisao(40);
                break;
            case R.id.jogo_pular3:
                criarDecisao(40);
                break;
            //decisões
            case R.id.confiral_btnsim:
                confirmaDecisao(escolha);
                break;
            case R.id.confiral_btnnao:
                dconfirmacao.dismiss();
                desistiu = false;
                break;
            //ok
            case R.id.avisoal_btnok:
                valendoxptsd.dismiss();
                if(pulou){
                    //recomeça o jogo
                    iniciarJogo();
                }
                else{
                    if(conferirReposta){
                        //é pra parar e continuar
                        if(acertou){
                            //verifica se o jogo terminou e se é a ultima pergunta
                            if(acertos > 15){
                                //se for finaliza
                                finalizarJogo(2);
                            }
                            else{
                                //se não for recomeça  o jogo
                                iniciarJogo();
                            }
                        }
                        else{
                            //termina o jogo
                            finalizarJogo(0);
                        }
                    }
                    else{
                        //nao é pra parar o jogo
                        if(!this.corrente)
                            carregarPergunta();
                        else {
                            this.paraCronometro = false;
                            valendoTempo();
                        }
                    }
                }
                break;
        }
    }

}
