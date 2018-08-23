package com.saviosvm.showdomilhaomatemtico.controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.saviosvm.showdomilhaomatemtico.model.PerguntaM;

import java.util.ArrayList;

/**
 * Created by savio on 12/03/2018.
 */

public class PerguntaC {

    private SQLiteDatabase db;
    private DatabaseHelper helper;

    private PerguntaM pergunta;
    private ArrayList<PerguntaM> perguntas;

    private Cursor cursor;

    public PerguntaC(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getDatabase();
    }

    //LISTAR
    public ArrayList<PerguntaM> listarGeral() {
        ArrayList<PerguntaM> result = new ArrayList<>();

        cursor = db.query("pergunta", new String[]{"id","nivel", "enunciado", "alternativa1", "alternativa2", "alternativa3","alternativa4"
                , "alternativacorreta", "usado", "respondido", "ano" },
                null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            pergunta = new PerguntaM();
            pergunta.setId(cursor.getInt(0));
            pergunta.setNivel(cursor.getInt(1));
            pergunta.setEnunciado(cursor.getString(2));
            pergunta.setAlternativa1(cursor.getString(3));
            pergunta.setAlternativa2(cursor.getString(4));
            pergunta.setAlternativa3(cursor.getString(5));
            pergunta.setAlternativa4(cursor.getString(6));
            pergunta.setAlternativaCorreta(cursor.getInt(7));
            pergunta.setUsado(Boolean.getBoolean(cursor.getString(8)));
            pergunta.setRespondido(Boolean.getBoolean(cursor.getString(9)));
            pergunta.setAno(cursor.getInt(10));
            result.add(pergunta);
            cursor.moveToNext();
        }

        cursor.close();

        return result;
    }

    public ArrayList<PerguntaM> listar(int grupo) {
        ArrayList<PerguntaM> result = new ArrayList<>();

        cursor = db.query("pergunta", new String[]{"id","nivel", "enunciado", "alternativa1", "alternativa2", "alternativa3","alternativa4"
                        , "alternativacorreta", "usado", "respondido", "ano" },
                "WHERE nivel = "+grupo, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            pergunta = new PerguntaM();
            pergunta.setId(cursor.getInt(0));
            pergunta.setNivel(cursor.getInt(1));
            pergunta.setEnunciado(cursor.getString(2));
            pergunta.setAlternativa1(cursor.getString(3));
            pergunta.setAlternativa2(cursor.getString(4));
            pergunta.setAlternativa3(cursor.getString(5));
            pergunta.setAlternativa4(cursor.getString(6));
            pergunta.setAlternativaCorreta(cursor.getInt(7));
            pergunta.setUsado(Boolean.getBoolean(cursor.getString(8)));
            pergunta.setRespondido(Boolean.getBoolean(cursor.getString(9)));
            pergunta.setAno(cursor.getInt(10));
            result.add(pergunta);
            cursor.moveToNext();
        }

        cursor.close();

        return result;
    }

    //INSERCAO
    //Só vai ser chamado quando for feita a sincronia dos dados
    public void inserir(PerguntaM dados)
    {
        ContentValues cv =new ContentValues();
        cv.put("nivel", dados.getNivel());
        cv.put("enunciado", dados.getEnunciado());
        cv.put("alternativa1", dados.getAlternativa1());
        cv.put("alternativa2", dados.getAlternativa2());
        cv.put("alternativa3", dados.getAlternativa3());
        cv.put("alternativa4", dados.getAlternativa4());
        cv.put("alternativacorreta", dados.getAlternativaCorreta());
        cv.put("usado", dados.isUsado());
        cv.put("respondido", dados.isRespondido());
        cv.put("ano", dados.getAno());
        db.insert("pergunta", null, cv);
    }

    //UPDATE
    //Só vai ser chamado quando for feita a sincronia dos dados
    public void atualizar(PerguntaM dados)
    {
        /*
        ContentValues cv =new ContentValues();
        cv.put("id", dados.getId());
        cv.put("nome", dados.getNome());
        cv.put("endereco", dados.getEndereco());
        cv.put("cep", dados.getCep());
        cv.put("latitude", dados.getLatitude());
        cv.put("longitude", dados.getLongitude());
        db.update("unidade", cv,  "id = ?", new String[]{String.valueOf(dados.getId())});
        System.out.println("Atualizou Unidade!");
        */
    }

    //DELETAR
    public void deletar(int id){
        db.execSQL("DELETE FROM pergunta WHERE id = "+id);
    }

    //CONSULTA POR ID
    public PerguntaM findById(int id)
    {
        pergunta = null;

        cursor = db.query("pergunta", new String[]{"id","nivel", "enunciado", "alternativa1", "alternativa2", "alternativa3","alternativa4"
                        , "alternativacorreta", "usado", "respondido", "ano" },"id = "+id,
                null, null, null, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            pergunta = new PerguntaM();
            pergunta.setId(cursor.getInt(0));
            pergunta.setNivel(cursor.getInt(1));
            pergunta.setEnunciado(cursor.getString(2));
            pergunta.setAlternativa1(cursor.getString(3));
            pergunta.setAlternativa2(cursor.getString(4));
            pergunta.setAlternativa3(cursor.getString(5));
            pergunta.setAlternativa4(cursor.getString(6));
            pergunta.setAlternativaCorreta(cursor.getInt(7));
            pergunta.setUsado(Boolean.getBoolean(cursor.getString(8)));
            pergunta.setRespondido(Boolean.getBoolean(cursor.getString(9)));
            pergunta.setAno(cursor.getInt(10));
        }

        cursor.close();

        //retorna o objeto
        return pergunta;
    }



}
