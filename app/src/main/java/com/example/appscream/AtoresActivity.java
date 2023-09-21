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

public class AtoresActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    public static List<ItemAtor> itemsatores = new ArrayList<>();
    ImageButton imgbtnhome2, imgbtnatores2, imgbtnp2, imgbtntelefone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atores);
        buscaAtores();

        imgbtnhome2 = findViewById(R.id.imgbtnhomeinicial2);
        imgbtnatores2 = findViewById(R.id.imgbtnatoresinicial2);
        imgbtnp2 = findViewById(R.id.imgbtnpinicial2);
        imgbtntelefone2 = findViewById(R.id.imgbtntelefoneinicial2);

        imgbtnhome2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AtoresActivity.this, HomeActivity.class));
            }
        });


        imgbtnatores2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AtoresActivity.this, AtoresActivity.class));
            }

        });
        imgbtnp2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AtoresActivity.this, PersonagensActivity.class));
            }

        });

        imgbtntelefone2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AtoresActivity.this, Telefone.class));
            }

        });
    }

    public void buscaAtores() {
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
            Toast.makeText(AtoresActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CarregaAtores(this);
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
            String idator = null;
            String nomeator = null;
            String imgator = null;
            String descricaoator = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length()) {
                // Obtem a informação
                Object idactor = jsonObject.get("idAtor");
                Object nameactor = jsonObject.get("nomeElenco"); // pega o title no object json
                Object imgactor = jsonObject.get("imagemElenco");
                Object overviewactor = jsonObject.get("descricaoElenco");

                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    idator = idactor.toString();
                    nomeator = nameactor.toString();
                    imgator = imgactor.toString();
                    descricaoator = overviewactor.toString();

                    itemsatores.add(new ItemAtor(idator, nomeator, imgator, descricaoator));

                    RecyclerView recyclerViewAtor = findViewById(R.id.recyclerViewAtores);


                    recyclerViewAtor.setLayoutManager(new LinearLayoutManager(this));
                    recyclerViewAtor.setAdapter(new MyAdapterAtor(getApplicationContext(), itemsatores));

                } catch (Exception err) {
                    err.printStackTrace();
                }
                i++;
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(AtoresActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}