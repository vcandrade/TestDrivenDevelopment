package br.edu.utfpr.pg.es.services;

import br.edu.utfpr.pg.es.entities.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Vinicius
 */
public class LocacaoServiceTest {

    @Test
    public void calcularValorLocacaoTest() {

        // ==================== MONTAGEM DO CENÁRIO ==================== //
        Cliente cliente = new Cliente(123, "João da Silva");
        Filme filme1 = new Filme(1, "Vingadores Ultimato", "Ação", 4.00);
        Filme filme2 = new Filme(2, "X-Men: Fênix Negra", "Ação", 4.00);
        
        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme1);
        filmes.add(filme2);

        LocacaoService locacaoService = new LocacaoService();
        
        // ========================= EXECUÇÃO ========================= //
        double valorLocacao = locacaoService.calcularValorLocacao(filmes);


        // ======================= VERIFICAÇÃO ======================== //
        assertThat(valorLocacao, is(8.00));
    }
    
    @Test
    public void calcularValorLocacaoTresFilmesTest() {

        // ==================== MONTAGEM DO CENÁRIO ==================== //
        Cliente cliente = new Cliente(123, "João da Silva");
        Filme filme1 = new Filme(1, "Vingadores Ultimato", "Ação", 4.00);
        Filme filme2 = new Filme(2, "X-Men: Fênix Negra", "Ação", 4.00);
        Filme filme3 = new Filme(3, "O Rei Leão", "Animação", 4.00);
        
        List<Filme> filmes = new ArrayList<>();
        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);

        LocacaoService locacaoService = new LocacaoService();
        
        // ========================= EXECUÇÃO ========================= //
        double valorLocacao = locacaoService.calcularValorLocacao(filmes);


        // ======================= VERIFICAÇÃO ======================== //
        assertThat(valorLocacao, is(11.00));
    }
}
