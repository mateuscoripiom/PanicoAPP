package com.example.appscream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonagensActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public static List<ItemPersonagem> itemspersonagens = new ArrayList<>();
    ImageButton imgbtnhome4, imgbtnatores4, imgbtnp4, imgbtntelefone4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagens);
        buscaPersonagens();

        imgbtnhome4 = findViewById(R.id.imgbtnhomeinicial4);
        imgbtnatores4 = findViewById(R.id.imgbtnatoresinicial4);
        imgbtnp4 = findViewById(R.id.imgbtnpinicial4);
        imgbtntelefone4 = findViewById(R.id.imgbtntelefoneinicial4);

        imgbtnhome4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PersonagensActivity.this, HomeActivity.class));
            }
        });


        imgbtnatores4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PersonagensActivity.this, AtoresActivity.class));
            }

        });
        imgbtnp4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PersonagensActivity.this, PersonagensActivity.class));
            }

        });

        imgbtntelefone4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PersonagensActivity.this, Telefone.class));
            }

        });
    }
    public void buscaPersonagens() {
        // Recupera a string de busca.
        String movieString = null;
        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("movieString", movieString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            Toast.makeText(PersonagensActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CarregaPersonagens(this);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            // Obtem o JSONArray dos itens de livros
            // JSONArray itemsArray = jsonObject.getJSONArray("genres");
            // Toast.makeText(this, itemsArray.toString(), Toast.LENGTH_SHORT).show();
            // inicializa o contador
            int i = 0;
            String idpersonagem = null;
            String nomepersonagem = null;
            String imgpersonagem = null;
            String descricaopersonagem = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length()) {
                // Obtem a informação
                Object idc = jsonObject.get("idPerson");
                Object namec = jsonObject.get("nomePerson"); // pega o title no object json
                Object imgc = jsonObject.get("imagemPerson");
                Object overviewc = jsonObject.get("descricaoPerson");

                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    idpersonagem = idc.toString();
                    nomepersonagem = namec.toString();
                    imgpersonagem = imgc.toString();
                    descricaopersonagem = overviewc.toString();

                    itemspersonagens.add(new ItemPersonagem(idpersonagem, nomepersonagem, imgpersonagem, descricaopersonagem));

                    RecyclerView recyclerViewP = findViewById(R.id.recyclerViewPersonagens);


                    recyclerViewP.setLayoutManager(new LinearLayoutManager(this));
                    recyclerViewP.setAdapter(new MyAdapterP(getApplicationContext(), itemspersonagens));

                } catch (Exception err) {
                    err.printStackTrace();
                }
                i++;
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(PersonagensActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}