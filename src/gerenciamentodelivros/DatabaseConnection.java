package gerenciamentodelivros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

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
        String query = "SELECT tipo FROM usuarios_simples WHERE nome = ? AND senha = ?";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            if (con == null) return null;

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipo"); 
                }
            }

        } catch (Exception e) {
            System.err.println("Erro na validação do usuário: " + e.getMessage());
        }

        return null; 
    }
}
