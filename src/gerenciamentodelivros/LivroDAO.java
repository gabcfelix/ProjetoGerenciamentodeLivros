package gerenciamentodelivros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void cadastrarLivro(String titulo, String autor, String tipo, int nota, int idUsuario) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            try {
                String sql = "INSERT INTO livros (titulo, autor, tipo, nota, id_usuario) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, titulo);
                preparedStatement.setString(2, autor);
                preparedStatement.setString(3, tipo);
                preparedStatement.setInt(4, nota);
                preparedStatement.setInt(5, idUsuario);

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Livro cadastrado com sucesso!");
                } else {
                    System.out.println("Falha no cadastro do livro.");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static List<Livro> obterLivrosOrdenados() {
        List<Livro> livros = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM livros ORDER BY titulo"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getString("tipo"),
                        resultSet.getInt("nota")
                );

                livros.add(livro);
            }

            System.out.println("Quantidade de livros encontrados na consulta SQL: " + livros.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}
