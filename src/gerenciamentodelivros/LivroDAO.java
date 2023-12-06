package gerenciamentodelivros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void cadastrarLivro(String titulo, String autor, String tipo, int nota, int idUsuario) {
        Connection connection = ConexaoBancoDeDados.getConnection();

        if (connection != null) {
            try {
                String verificaLivro = "SELECT id, nota_media, numero_avaliacoes FROM livros WHERE titulo = ? AND autor = ? AND tipo = ?";
                PreparedStatement verificaLivroStatement = connection.prepareStatement(verificaLivro);
                verificaLivroStatement.setString(1, titulo);
                verificaLivroStatement.setString(2, autor);
                verificaLivroStatement.setString(3, tipo);
                ResultSet resultSet = verificaLivroStatement.executeQuery();

                if (resultSet.next()) {
                    int livroId = resultSet.getInt("id");
                    double notaMediaAtual = resultSet.getDouble("nota_media");
                    int numeroAvaliacoesAtual = resultSet.getInt("numero_avaliacoes");

                    double novaNotaMedia = (notaMediaAtual * numeroAvaliacoesAtual + nota) / (numeroAvaliacoesAtual + 1);
                    int novoNumeroAvaliacoes = numeroAvaliacoesAtual + 1;

                    System.out.println("Livro encontrado. ID: " + livroId);
                    System.out.println("Nota Média Atual: " + notaMediaAtual);
                    System.out.println("Número de Avaliações Atual: " + numeroAvaliacoesAtual);

                    System.out.println("Nova Nota a ser Adicionada: " + nota);
                    System.out.println("Calculando Nova Nota Média: " + novaNotaMedia);
                    System.out.println("Novo Número de Avaliações: " + novoNumeroAvaliacoes);

                    String updateLivro = "UPDATE livros SET nota_media = ?, numero_avaliacoes = ? WHERE id = ?";
                    PreparedStatement updateLivroStatement = connection.prepareStatement(updateLivro);
                    updateLivroStatement.setDouble(1, novaNotaMedia);
                    updateLivroStatement.setInt(2, novoNumeroAvaliacoes);
                    updateLivroStatement.setInt(3, livroId);

                    int linhasAfetadas = updateLivroStatement.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Avaliação do livro atualizada com sucesso!");
                    } else {
                        System.out.println("Falha na atualização da avaliação do livro.");
                    }
                } else {
                    System.out.println("Livro não encontrado. Realizando cadastro...");

                    String sql = "INSERT INTO livros (titulo, autor, tipo, nota, numero_avaliacoes, nota_media) VALUES (?, ?, ?, ?, 1, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, titulo);
                    preparedStatement.setString(2, autor);
                    preparedStatement.setString(3, tipo);
                    preparedStatement.setInt(4, nota);
                    preparedStatement.setDouble(5, nota);

                    int linhasAfetadas = preparedStatement.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Livro cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha no cadastro do livro.");
                    }
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<InformacoesLivro> obterLivrosOrdenados() {
        List<InformacoesLivro> livros = new ArrayList<>();

        try (Connection connection = ConexaoBancoDeDados.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, titulo, autor, tipo, nota, numero_avaliacoes, nota_media FROM livros"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                InformacoesLivro livro = new InformacoesLivro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getString("tipo"),
                        resultSet.getInt("nota")
                );

                livro.setNumeroAvaliacoes(resultSet.getInt("numero_avaliacoes"));
                livro.setNotaMedia(resultSet.getDouble("nota_media"));

                livros.add(livro);
            }

            livros.sort((livro1, livro2) -> {
                double media1 = livro1.getNotaMedia();
                double media2 = livro2.getNotaMedia();

                if (media2 != media1) {
                    return Double.compare(media2, media1);
                }

                int numeroAvaliacoes1 = livro1.getNumeroAvaliacoes();
                int numeroAvaliacoes2 = livro2.getNumeroAvaliacoes();

                if (numeroAvaliacoes2 != numeroAvaliacoes1) {
                    return Integer.compare(numeroAvaliacoes2, numeroAvaliacoes1);
                }

                return livro1.getTitulo().compareToIgnoreCase(livro2.getTitulo());
            });

            for (InformacoesLivro livro : livros) {
                System.out.println("Titulo: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Tipo: " + livro.getTipo());
                System.out.println("Nota Média: " + livro.getNotaMedia());
                System.out.println("Número de Avaliações: " + livro.getNumeroAvaliacoes());
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}