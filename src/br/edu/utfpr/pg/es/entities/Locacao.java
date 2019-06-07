/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.utfpr.pg.es.entities;

import java.util.List;

/**
 *
 * @author Vinicius
 */
public class Locacao {

    private Cliente cliente;
    private List<Filme> filmes;
    private String dataLocacao;
    private String dataPrevistaDevolucao;
    private String dataDevolucao;
    private Double valorLocacao;

    public Locacao(Cliente cliente, List<Filme> filmes, String dataLocacao, String dataPrevistaDevolucao, double valorLocacao) {
        
        this.cliente = cliente;
        this.filmes = filmes;
        this.dataLocacao = dataLocacao;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.valorLocacao = valorLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
    
    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(Double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
}
