package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btncadastrar = findViewById(R.id.btncadastrar);
        EditText email = (EditText) findViewById(R.id.editemail);
        EditText password = (EditText) findViewById(R.id.editsenha);
        DBhelper DB;

        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
    });

        Button btnscream = findViewById(R.id.btnscream);
        btnscream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        DB = new DBhelper(this);

        btnscream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = email.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(LoginActivity.this, "Credenciais Invalidas", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }


}

