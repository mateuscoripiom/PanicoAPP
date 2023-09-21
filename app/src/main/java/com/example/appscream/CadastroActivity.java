package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity{

    public static String userCadastro, passCadastro, repassCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button cadastrar = findViewById(R.id.btnscreamcad);
        EditText username = findViewById(R.id.editnome);
        EditText password = findViewById(R.id.editemailcad);
        EditText repassword = findViewById(R.id.editsenhacad);

        Button btncadastro = findViewById(R.id.btncadastro);

        btncadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCadastro = username.getText().toString();
                passCadastro = password.getText().toString();
                repassCadastro = repassword.getText().toString();

                if (userCadastro.equals("") || passCadastro.equals("") || repassCadastro.equals("")) {
                    Toast.makeText(CadastroActivity.this, "Por favor preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    if (passCadastro.equals(repassCadastro)) {
                        NetworkUtils.cadastraUsuario(userCadastro, passCadastro);
                        Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso. Por favor, logue-se.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CadastroActivity.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
}