/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pg.es.services;

import br.edu.utfpr.pg.es.entities.Cliente;
import br.edu.utfpr.pg.es.entities.Filme;
import br.edu.utfpr.pg.es.entities.Locacao;
import br.edu.utfpr.pg.es.exceptions.FilmeIndisponivelException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class LocacaoService {

    private final Double VALOR_MULTA_DIARIA;

    public LocacaoService() {

        this.VALOR_MULTA_DIARIA = 2.00;
    }

    public Locacao alugarFilme(Cliente cliente, List<Filme> filmes) throws FilmeIndisponivelException {

        this.verificarDisponibilidadeFilmes(filmes);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date dataAtual = new Date();

        String dataLocacao = df.format(dataAtual);
        String dataPrevistaDevolucao = df.format(calcularDataDevolucao(dataAtual, 3));

        Locacao locacao = new Locacao(cliente, filmes, dataLocacao, dataPrevistaDevolucao, this.calcularValorLocacao(filmes));

        for (Filme filme : filmes) {

            filme.setDisponivel(false);
        }

        return locacao;
    }

    public void verificarDisponibilidadeFilmes(List<Filme> filmes) throws FilmeIndisponivelException {

        for (Filme filme : filmes) {

            if (!filme.isDisponivel()) {

                throw new FilmeIndisponivelException();
            }
        }
    }

    public double calcularValorLocacao(List<Filme> filmes) {

        double valorLocacao = 0;

        for (int i = 0; i < filmes.size(); i++) {

            Filme filme = filmes.get(i);

            switch (i) {
                
                case 2:
                    valorLocacao += filme.getPreco() * 0.75;
                    break;
                
                case 3:
                    valorLocacao += filme.getPreco() * 0.50;
                    break;
                
                default:
                    valorLocacao += filme.getPreco();
                    break;
            }
        }

        return valorLocacao;
    }

    public void devolverFilme(Locacao locacao) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date();

        locacao.setDataDevolucao(df.format(dataAtual));

        locacao.setValorLocacao(locacao.getValorLocacao() + this.calcularValorMulta(locacao));

        for (Filme filme : locacao.getFilmes()) {

            filme.setDisponivel(true);
        }
    }

    public Date calcularDataDevolucao(Date data, int diasLocacao) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(DAY_OF_MONTH, diasLocacao);

        return calendar.getTime();
    }

    public Double calcularValorMulta(Locacao locacao) throws ParseException {

        long diasAtraso = this.verificarDiasAtraso(locacao);

        if (diasAtraso > 0) {

            return diasAtraso * locacao.getFilmes().size() * this.VALOR_MULTA_DIARIA;
        }

        return 0.0;
    }

    public long verificarDiasAtraso(Locacao locacao) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        Date dataPrevistaDevolucao = df.parse(locacao.getDataPrevistaDevolucao());
        Date dataDevolucao = df.parse(locacao.getDataDevolucao());

        long dt = (dataDevolucao.getTime() - dataPrevistaDevolucao.getTime()) + 3600000;
        return (dt / 86400000L);
    }
}
