package com.saviosvm.showdomilhaomatemtico.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.saviosvm.showdomilhaomatemtico.R;

public class Cartas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);
    }

    @Override
    public void onBackPressed()
    {
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

}
