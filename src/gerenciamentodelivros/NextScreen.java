/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamentodelivros;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gab
 */
public class NextScreen extends JFrame {

public NextScreen() {
        // Define as configurações básicas da janela
        setTitle("Próxima Tela");
        setSize(300, 200); // define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela na tela

        // Adiciona um rótulo simples
        JLabel label = new JLabel("Bem-vindo à próxima tela!");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
    }
}
