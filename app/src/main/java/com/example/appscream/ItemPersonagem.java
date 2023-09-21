package com.example.appscream;

public class ItemPersonagem {
    String idpersonagem;
    String nomepersonagem;
    String imagepersonagem;
    String descricaopersonagem;

    public ItemPersonagem(String idpersonagem, String nomepersonagem, String imagepersonagem, String descricaopersonagem) {
        this.idpersonagem = idpersonagem;
        this.nomepersonagem = nomepersonagem;
        this.imagepersonagem = imagepersonagem;
        this.descricaopersonagem = descricaopersonagem;
    }

    public String getIdpersonagem() {
        return idpersonagem;
    }

    public void setIdpersonagem(String idpersonagem) {
        this.idpersonagem = idpersonagem;
    }

    public String getNomepersonagem() {
        return nomepersonagem;
    }

    public void setNomepersonagem(String nomepersonagem) {
        this.nomepersonagem = nomepersonagem;
    }

    public String getImagepersonagem() {
        return imagepersonagem;
    }

    public void setImagepersonagem(String imagepersonagem) {
        this.imagepersonagem = imagepersonagem;
    }

    public String getDescricaopersonagem() {
        return descricaopersonagem;
    }

    public void setDescricaopersonagem(String descricaopersonagem) {
        this.descricaopersonagem = descricaopersonagem;
    }
}
