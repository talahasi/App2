package com.csgamer.han.app2.Domain;

public class Carro {
    private long id;

    private String nome;
    private String modelo;
    private String ano;
    private String cor;
    private String lugares;
    private String motivo;
    private String proprietario;
    private String cidade;
    private String telefone;

    public Carro(){}
    public Carro(long id, String nome, String modelo, String ano, String cor, String lugares, String motivo, String proprietario, String cidade, String telefone) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.lugares = lugares;
        this.motivo = motivo;
        this.proprietario = proprietario;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getLugares() {
        return lugares;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
