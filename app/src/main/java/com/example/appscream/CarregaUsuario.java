package com.example.appscream;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CarregaUsuario extends AsyncTaskLoader<String> {
    CarregaUsuario(Context context) {
        super(context);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.buscaUsuarios("tbUsers");
    }
}
