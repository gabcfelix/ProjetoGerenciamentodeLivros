/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamentodelivros;

import javax.swing.JLabel;
import javax.swing.JFrame;

public class AdminScreen extends JFrame {

    public AdminScreen() {
        // Definindo o título da janela
        setTitle("Tela de Administrador");
        
        // Definindo o tamanho da janela
        setSize(400, 300);
        
        // Definindo o comportamento ao fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Criando e adicionando um label para indicar que esta é a tela do administrador
        JLabel label = new JLabel("Bem-vindo, Administrador!", JLabel.CENTER);
        add(label);
        
        // Centralizando a janela na tela
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Método main para testar a janela AdminScreen
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminScreen().setVisible(true);
            }
        });
    }
}    

