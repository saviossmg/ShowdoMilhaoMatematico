package com.saviosvm.showdomilhaomatemtico.controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.saviosvm.showdomilhaomatemtico.model.JogadorM;

import java.util.ArrayList;

/**
 * Created by savio on 12/03/2018.
 */

public class JogadorC {

    private SQLiteDatabase db;
    private DatabaseHelper helper;

    private JogadorM jogador;
    private ArrayList<JogadorM> jogadores;

    private Cursor cursor;

    public JogadorC(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getDatabase();
    }

    //LISTAR
    public ArrayList<JogadorM> listar() {
        ArrayList<JogadorM> result = new ArrayList<>();

        cursor = db.query("jogador", new String[]{"id","nome", "classe", "acertos", "pontos", "tempo","mediatempo", "faltas", "ano"},
                null, null, null, null, "pontos DESC, acertos DESC, faltas ASC, tempo ASC, mediatempo ASC");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            jogador = new JogadorM();
            jogador.setId(cursor.getInt(0));
            jogador.setNome(cursor.getString(1));
            jogador.setClasse(cursor.getString(2));
            jogador.setAcertos(cursor.getInt(3));
            jogador.setPontos(cursor.getDouble(4));
            jogador.setTempo(cursor.getDouble(5));
            jogador.setMediaTempo(cursor.getDouble(6));
            jogador.setFaltas(cursor.getInt(7));
            jogador.setAno(cursor.getInt(8));
            result.add(jogador);
            cursor.moveToNext();
        }
        cursor.close();

        return result;
    }

    //INSERCAO
    //Só vai ser chamado quando for feita a sincronia dos dados
    public void inserir(String nome, String classe,int ano)
    {
        ContentValues cv =new ContentValues();
        cv.put("nome", nome);
        cv.put("classe", classe);
        cv.put("acertos", 0);
        cv.put("pontos", 0);
        cv.put("tempo", 0);
        cv.put("mediatempo", 0);
        cv.put("faltas", 0);
        cv.put("ano", ano);
        db.insert("jogador", null, cv);
    }

    //UPDATE
    //Só vai ser chamado quando for feita a sincronia dos dados
    public void atualizar(JogadorM dados)
    {
        ContentValues cv =new ContentValues();
        cv.put("nome", dados.getNome());
        cv.put("classe", dados.getClasse());
        cv.put("acertos", dados.getAcertos());
        cv.put("pontos", dados.getPontos());
        cv.put("tempo", dados.getTempo());
        cv.put("mediatempo", dados.getMediaTempo());
        cv.put("faltas", dados.getFaltas());
        db.update("jogador", cv,  "id = ?", new String[]{String.valueOf(dados.getId())});
    }

    //DELETAR
    public void deletar(int id){
        db.execSQL("DELETE FROM jogador WHERE  id ="+id);
    }

    public JogadorM findUltimo()
    {
        jogador = null;
        cursor = db.query("jogador", new String[]{"id","nome", "classe", "acertos", "pontos", "tempo", "mediatempo", "faltas", "ano"},null,
                null, null, null, "id DESC LIMIT 1");
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            jogador = new JogadorM();
            jogador.setId(cursor.getInt(0));
            jogador.setNome(cursor.getString(1));
            jogador.setClasse(cursor.getString(2));
            jogador.setAcertos(cursor.getInt(3));
            jogador.setPontos(cursor.getDouble(4));
            jogador.setTempo(cursor.getDouble(5));
            jogador.setMediaTempo(cursor.getDouble(6));
            jogador.setFaltas(cursor.getInt(7));
            jogador.setAno(cursor.getInt(8));
        }
        cursor.close();
        //retorna o objeto
        return jogador;
    }

}
