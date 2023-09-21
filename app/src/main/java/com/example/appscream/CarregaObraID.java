package com.example.appscream;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CarregaObraID extends AsyncTaskLoader<String> {
    private String mMovieString;
    CarregaObraID(Context context, String movieString) {
        super(context);
        mMovieString = movieString;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.buscaObraID("tbObras", mMovieString);
    }
}
