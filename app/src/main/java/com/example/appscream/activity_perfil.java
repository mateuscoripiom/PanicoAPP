package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_perfil extends AppCompatActivity {

    TextView nome ,senha;
    public static TextView email;
    ImageView imgview;
    ImageButton btnsair;
    DatabaseHelper myDb;
    private static int RESULT_LOAD_IMG = 1;
    private String PREFS_NAME = "image";
    private String SharedDados = "dados";
    private Context mContext;
    String nomedb;
    String emaildb;
    String senhadb;
    String user_email;
    String imagedb;
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

    }

}