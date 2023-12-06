package gerenciamentodelivros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioOpcoesUsuarioComum extends javax.swing.JFrame {

    public FormularioOpcoesUsuarioComum() {
        initComponents();
        personalizarComponents();
        
        setTitle("Opções - LiteraLynx");
        
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.NORMAL);

    }

    private void personalizarComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JButton btnVisualizarLista = new JButton("Visualizar Lista Ordenada");
        btnVisualizarLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVisualizarListaActionPerformed(evt);
            }
        });

        JButton btnCadastrarLivro = new JButton("Cadastrar Novo Livro");
        btnCadastrarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCadastrarLivroActionPerformed(evt);
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnVisualizarLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCadastrarLivro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnVisualizarLista)
                                .addGap(18, 18, 18)
                                .addComponent(btnCadastrarLivro)
                                .addGap(18, 18, 18)
                                .addComponent(btnVoltar)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnVisualizarListaActionPerformed(ActionEvent evt) {
        FormularioVisualizacaoLivros visualizacaoLivrosForm = new FormularioVisualizacaoLivros();
        visualizacaoLivrosForm.setVisible(true);
        dispose();
    }

    private void btnCadastrarLivroActionPerformed(ActionEvent evt) {
        FormularioCadastroLivro cadastroLivroForm = new FormularioCadastroLivro();
        cadastroLivroForm.setVisible(true);
        dispose();
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {

        FormularioLogin formularioLogin = new FormularioLogin();

        formularioLogin.setVisible(true);

        dispose();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioOpcoesUsuarioComum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
