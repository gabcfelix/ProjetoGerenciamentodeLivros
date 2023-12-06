package gerenciamentodelivros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.Objects;

public class FormularioCadastroLivro extends javax.swing.JFrame {

    private JTextField txtTitulo, txtAutor, txtNota;
    private JComboBox<String> cmbTipo;
    private JButton btnVoltar, btnCadastrar;

    public FormularioCadastroLivro() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Cadastro de Livros - LiteraLynx");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtTitulo = new JTextField(15);
        add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtAutor = new JTextField(15);
        add(txtAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        cmbTipo = new JComboBox<>(new String[]{"", "Romance", "Ficção", "Técnico"});
        add(cmbTipo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Nota:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        txtNota = new JTextField(5);
        txtNota.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c)) {
                    e.consume();
                }

                if (txtNota.getText().length() >= 2) {
                    e.consume();
                }

                if (!txtNota.getText().isEmpty() && Integer.parseInt(txtNota.getText() + c) > 10) {
                    e.consume();
                }
            }
        });
        add(txtNota, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
        add(btnCadastrar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltarActionPerformed(e);
            }
        });
        add(btnVoltar, gbc);

        pack();

        setSize(getWidth() + 100, getHeight() + 100);

        setLocationRelativeTo(null);
        setResizable(true); 

        setResizable(false);
        setExtendedState(JFrame.NORMAL);

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
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

    private void cadastrarLivro() {
        System.out.println("Iniciando o cadastro do livro...");

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String tipo = Objects.requireNonNull(cmbTipo.getSelectedItem()).toString().toLowerCase();

        if (titulo.isEmpty() || autor.isEmpty() || tipo.isEmpty() || txtNota.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String notaText = txtNota.getText();
        if (!isValidNota(notaText)) {
            JOptionPane.showMessageDialog(this, "A nota deve ser um número entre 0 e 10.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int nota = Integer.parseInt(notaText);

        LivroDAO livroDAO = new LivroDAO();
        int idUsuarioLogado = 1;

        livroDAO.cadastrarLivro(titulo, autor, tipo, nota, idUsuarioLogado);

        System.out.println("Cadastro do livro concluído.");

        int resposta = JOptionPane.showOptionDialog(
                this,
                "Livro cadastrado com sucesso! Deseja cadastrar outro?",
                "Cadastro bem-sucedido",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Sim", "Não"},
                "Sim");

        if (resposta == JOptionPane.YES_OPTION) {
            limparCampos();
        } else if (resposta == JOptionPane.NO_OPTION) {
            new FormularioVisualizacaoLivros().setVisible(true);
            dispose();
        }
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        FormularioOpcoesUsuarioComum formularioOpcoesUsuarioComum = new FormularioOpcoesUsuarioComum();
        formularioOpcoesUsuarioComum.setVisible(true);
        dispose();
    }

    private void limparCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        cmbTipo.setSelectedIndex(0);
        txtNota.setText("");
    }

    private boolean isValidNota(String notaText) {
        return notaText.matches("\\b(?:[0-9]|10)\\b");
    }

    public static void main(String args[]) {

        Locale.setDefault(new Locale("pt", "BR"));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioCadastroLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
