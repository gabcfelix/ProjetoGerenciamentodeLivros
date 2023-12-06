package gerenciamentodelivros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoBancoDeDados {

    private static final String URL = "jdbc:mysql://localhost:3306/gerenciamentodelivros";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DATABASE_PASSWORD");

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            System.err.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
            return null;
        }
    }

    public static String isValidUser(String username, String password) {
        String query = "SELECT tipo FROM usuarios WHERE nome = ? AND senha = ?";

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {

            if (con == null) {
                System.out.println("Conexão com o banco de dados não estabelecida.");
                return null;
            }

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String userType = rs.getString("tipo");
                    if (userType != null) {
                        System.out.println("Usuário encontrado na tabela usuarios. Tipo de usuário: " + userType);
                        return userType;
                    } else {
                        System.out.println("Usuário encontrado na tabela usuarios. Tipo de usuário: comum (valor nulo na coluna 'tipo')");
                        return "comum";
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro na validação do usuário na tabela usuarios: " + e.getMessage());
        }

        return null;
    }
}