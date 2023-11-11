package gerenciamentodelivros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class CadastroLivroForm extends javax.swing.JFrame {

    private JTextField txtTitulo, txtAutor, txtNota;
    private JComboBox<String> cmbTipo;
    private JButton btnCadastrar;

    public CadastroLivroForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Cadastro de Livros");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"", "Romance", "Ficção", "Técnico"});
        add(cmbTipo);

        add(new JLabel("Nota:"));
        txtNota = new JTextField();

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
        add(txtNota);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
        add(btnCadastrar);

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

        JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");

        limparCampos();
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroLivroForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
