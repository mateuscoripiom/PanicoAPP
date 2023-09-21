package com.example.appscream;

public class ItemAtor {
    String idator;
    String nomeator;
    String imageator;
    String descricaoator;

    public ItemAtor(String idator, String nomeator, String imageator, String descricaoator) {
        this.idator = idator;
        this.nomeator = nomeator;
        this.imageator = imageator;
        this.descricaoator = descricaoator;
    }

    public String getIdator() {
        return idator;
    }

    public void setIdator(String idator) {
        this.idator = idator;
    }

    public String getNomeator() {
        return nomeator;
    }

    public void setNomeator(String nomeator) {
        this.nomeator = nomeator;
    }

    public String getImageator() {
        return imageator;
    }

    public void setImageator(String imageator) {
        this.imageator = imageator;
    }

    public String getDescricaoator() {
        return descricaoator;
    }

    public void setDescricaoator(String descricaoator) {
        this.descricaoator = descricaoator;
    }
}
