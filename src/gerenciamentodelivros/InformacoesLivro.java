package gerenciamentodelivros;

import java.util.ArrayList;
import java.util.List;

public class InformacoesLivro {

    private int id;
    private String titulo;
    private String autor;
    private String tipo;
    private List<Integer> avaliacoes;
    private double notaMedia;
    private int numeroAvaliacoes;

    public InformacoesLivro(int id, String titulo, String autor, String tipo, int nota) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.avaliacoes = new ArrayList<>();
        this.avaliacoes.add(nota);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }

    public double calcularMedia() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        int soma = 0;
        for (int avaliacao : avaliacoes) {
            soma += avaliacao;
        }
        return (double) soma / avaliacoes.size();
    }

    public void setNumeroAvaliacoes(int numeroAvaliacoes) {
        this.numeroAvaliacoes = numeroAvaliacoes;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void adicionarAvaliacao(int novaNota) {
        avaliacoes.add(novaNota);
    }
}
