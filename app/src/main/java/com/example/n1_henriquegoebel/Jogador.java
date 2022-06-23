package com.example.n1_henriquegoebel;

public class Jogador {

    public String id;
    public String numeroCamiseta;
    public String nomeCompleto, nomeCamiseta, pePreferencial;
    public String goleiro, lateral, zagueiro, meia, atacante;
    public String idUsuario;

    public Jogador() {
    }

    public Jogador(String id, String numeroCamiseta, String nomeCamiseta) {
        this.id = id;
        this.numeroCamiseta = numeroCamiseta;
        this.nomeCamiseta = nomeCamiseta;
    }

    public Jogador(String id, String numeroCamiseta, String nomeCamiseta, String idUsuario) {
        this.id = id;
        this.numeroCamiseta = numeroCamiseta;
        this.nomeCamiseta = nomeCamiseta;
        this.idUsuario = idUsuario;
    }

    public Jogador(String id, String numeroCamiseta, String nomeCompleto, String nomeCamiseta, String pePreferencial, String goleiro,
                   String lateral, String zagueiro, String meia, String atacante, String idUsuario) {
        this.id = id;
        this.numeroCamiseta = numeroCamiseta;
        this.nomeCompleto = nomeCompleto;
        this.nomeCamiseta = nomeCamiseta;
        this.pePreferencial = pePreferencial;
        this.goleiro = goleiro;
        this.lateral = lateral;
        this.zagueiro = zagueiro;
        this.meia = meia;
        this.atacante = atacante;
        this.idUsuario = idUsuario;
    }

    public Jogador(String id, String nomeCompleto) {
        this.id = id;
        this.numeroCamiseta = numeroCamiseta;
        this.nomeCompleto = nomeCompleto;
        this.nomeCamiseta = nomeCamiseta;
        this.pePreferencial = pePreferencial;
        this.goleiro = goleiro;
        this.lateral = lateral;
        this.zagueiro = zagueiro;
        this.meia = meia;
        this.atacante = atacante;
    }

    public String toString(){
        return nomeCamiseta + "\n" + numeroCamiseta;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(String numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCamiseta() {
        return nomeCamiseta;
    }

    public void setNomeCamiseta(String nomeCamiseta) {
        this.nomeCamiseta = nomeCamiseta;
    }

    public String getPePreferencial() {
        return pePreferencial;
    }

    public void setPePreferencial(String pePreferencial) {
        this.pePreferencial = pePreferencial;
    }

    public String getGoleiro() {
        return goleiro;
    }

    public void setGoleiro(String goleiro) {
        this.goleiro = goleiro;
    }

    public String getLateral() {
        return lateral;
    }

    public void setLateral(String lateral) {
        this.lateral = lateral;
    }

    public String getZagueiro() {
        return zagueiro;
    }

    public void setZagueiro(String zagueiro) {
        this.zagueiro = zagueiro;
    }

    public String getMeia() {
        return meia;
    }

    public void setMeia(String meia) {
        this.meia = meia;
    }

    public String getAtacante() {
        return atacante;
    }

    public void setAtacante(String atacante) {
        this.atacante = atacante;
    }
}
