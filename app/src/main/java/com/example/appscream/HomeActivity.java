package com.example.appscream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    ImageButton imgbtnhome, imgbtnatores, imgbtnp, imgbtntelefone;

    //public static Integer ID = null;

    public static List<Item> items = new ArrayList<>();

    public static String ID = null;
    public static RecyclerView recyclerView;

    public static String IDPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgbtnhome = findViewById(R.id.imgbtnhomeinicial);
        imgbtnatores = findViewById(R.id.imgbtnatoresinicial);
        imgbtnp = findViewById(R.id.imgbtnpinicial);
        imgbtntelefone = findViewById(R.id.imgbtntelefoneinicial);

        buscaIDFilme();

        imgbtnhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });


        imgbtnatores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, AtoresActivity.class));
            }

        });
        imgbtnp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, PersonagensActivity.class));
            }

        });

        imgbtntelefone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, Telefone.class));
            }

        });
    }


    public void buscaIDFilme() {
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
                Toast.makeText(HomeActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CarregaObra(this);
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
            String idObra = null;
            String imgposter = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length()) {
                // Obtem a informação
                Object ido = jsonObject.get("idObra"); // pega o title no object json
                Object poster = jsonObject.get("imagemPoster");

                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    idObra = ido.toString();
                    imgposter = poster.toString();

                    items.add(new Item(idObra, imgposter));

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);

                    LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL,false);
                    recyclerView.setLayoutManager(horizontalLayoutManager);
                    recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

                    recyclerView.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                                @Override public void onItemClick(View view, int position) {
                                    IDPosition = items.get(position).getIdpop();
                                    startActivity(new Intent(HomeActivity.this, FilmeActivity.class));
                                }

                                @Override public void onLongItemClick(View view, int position) {
                                    // do whatever
                                }
                            })
                    );
                } catch (Exception err) {
                    err.printStackTrace();
                }
                i++;
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(HomeActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }

}