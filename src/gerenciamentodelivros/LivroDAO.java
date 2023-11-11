package gerenciamentodelivros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
