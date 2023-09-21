package com.example.appscream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    TextView txtsinopse, txtnomef, txtavaliacao;
    ImageView imgfilmef, imgbtnvoltar;
    ImageButton imgbtnhome3, imgbtnatores3, imgbtnp3, imgbtntelefone3;
    Button btnano, btndiretor, btnavaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        imgbtnvoltar = findViewById(R.id.imgbtnvoltar4);
        btnano = findViewById(R.id.button4);
        btndiretor = findViewById(R.id.button5);
        txtsinopse = findViewById(R.id.txtsinopse);
        btnavaliacao = findViewById(R.id.button3);
        txtavaliacao = findViewById(R.id.txtavaliacao);

        buscaInfoFilme();

        imgbtnhome3 = findViewById(R.id.imgbtnhomeinicial3);
        imgbtnatores3 = findViewById(R.id.imgbtnatoresinicial3);
        imgbtnp3 = findViewById(R.id.imgbtnpinicial3);
        imgbtntelefone3 = findViewById(R.id.imgbtntelefoneinicial3);

        imgbtnhome3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, HomeActivity.class));
            }
        });


        imgbtnatores3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, AtoresActivity.class));
            }

        });
        imgbtnp3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, PersonagensActivity.class));
            }

        });

        imgbtntelefone3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, Telefone.class));
            }

        });
        imgbtnvoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, HomeActivity.class));
            }
        });
    }

    public void buscaInfoFilme() {
        // Recupera a string de busca.
        String movieString = null;
        movieString = HomeActivity.IDPosition;
        // esconde o teclado qdo o botão é clicado
        /*InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } */

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()
                && movieString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("movieString", movieString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (movieString.length() == 0) {
                Toast.makeText(FilmeActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FilmeActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String movieString = "";
        if (args != null) {
            movieString = args.getString("movieString");
        }
        return new CarregaObraID(this, movieString);
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
            String nome = null;
            String imgfundo = null;
            String imgposter = null;
            String sinopse = null;
            String diretores = null;
            String ano = null;
            String avaliacao = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length() &&
                    (nome == null) && (imgfundo == null) && (imgposter == null) && (sinopse == null) && (diretores == null) && (ano == null) && (avaliacao == null)) {
                // Obtem a informação
                Object title = jsonObject.get("nome"); // pega o title no object json
                Object backdrop = jsonObject.get("imagemFundo");
                Object poster = jsonObject.get("imagemPoster");
                Object overview = jsonObject.get("descricao");
                Object directors = jsonObject.get("diretores");
                Object year = jsonObject.get("ano");
                Object score = jsonObject.get("avaliacao");

                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    nome = title.toString();
                    imgfundo = backdrop.toString();
                    imgposter = poster.toString();
                    sinopse = overview.toString();
                    diretores = directors.toString();
                    ano = year.toString();
                    avaliacao = score.toString();
                    // Toast.makeText(this, "NOME:" + nome, Toast.LENGTH_SHORT).show();
                } catch (Exception err) {
                    err.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.
            if ((nome != null) && (imgfundo != null) && (imgposter != null) && (sinopse != null) && (diretores != null) && (ano != null) && (avaliacao != null)) {
                txtnomef.setText(nome);
                btnano.setText("Ano: " + ano);
                btndiretor.setText("Diretores: " + diretores);
                txtsinopse.setText(sinopse);
                txtavaliacao.setText(avaliacao);
                int red = Color.parseColor("#970000");
                int green = Color.parseColor("#699C6B");
                if(Integer.parseInt(avaliacao) <= 59){
                    btnavaliacao.setBackgroundColor(red);
                }
                else{
                    btnavaliacao.setBackgroundColor(green);
                }
                Picasso
                        .get()
                        .load(imgfundo)
                        .into(imgfilmef);
            } else {
                Toast.makeText(FilmeActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(FilmeActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}