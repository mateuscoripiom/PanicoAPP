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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    public static String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btncadastrar = findViewById(R.id.btncadastrar);
        EditText email = (EditText) findViewById(R.id.editemail);
        EditText password = (EditText) findViewById(R.id.editsenha);

        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });

        Button btnscream = findViewById(R.id.btnscream);
        btnscream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        btnscream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = email.getText().toString();
                pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else {
                   buscaUser();
                }


            }
        });
    }

    public void buscaUser() {
        // Recupera a string de busca.
        String movieString = null;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("movieString", movieString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            Toast.makeText(LoginActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CarregaUsuario(this);
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
            String usuario = null;
            String senha = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length()) {
                // Obtem a informação
                Object username = jsonObject.get("nome"); // pega o title no object json
                Object password = jsonObject.get("senha");
                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    usuario = username.toString();
                    senha = password.toString();
                    if ((usuario != user) && (senha != pass)) {
                        Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciais Invalidas", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.

        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(LoginActivity.this, "Erro na busca de dados", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }


}

