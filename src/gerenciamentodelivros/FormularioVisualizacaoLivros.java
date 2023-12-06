package gerenciamentodelivros;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

public class FormularioVisualizacaoLivros extends javax.swing.JFrame {

    private JTable tabelaLivros;
    private DefaultTableModel modeloTabela;
    private static final Logger logger = Logger.getLogger(FormularioVisualizacaoLivros.class.getName());
    private JButton btnVoltar;

    public FormularioVisualizacaoLivros() {
        initComponents();
        inicializarTabela();
        carregarLivrosNaTabela();
        
        setTitle("Livros Cadastrados - LiteraLynx");
        
        setResizable(false);
        setExtendedState(JFrame.NORMAL);
        
        setSize(800, 600);
        
        setLocationRelativeTo(null);
        
    }

    private void inicializarTabela() {
        modeloTabela = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloTabela.addColumn("Título");
        modeloTabela.addColumn("Autor");
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Nota Média");
        modeloTabela.addColumn("Número de Avaliações");

        tabelaLivros = new JTable(modeloTabela);

        tabelaLivros.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabelaLivros);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnVoltarActionPerformed(e);
            }
        });
        add(btnVoltar, BorderLayout.SOUTH);

    }

    private void carregarLivrosNaTabela() {
        List<InformacoesLivro> listaLivros = obterLivrosDoBanco();

        logger.info("Quantidade de livros encontrados na visualização: " + listaLivros.size());

        DefaultTableModel modeloTabela = (DefaultTableModel) tabelaLivros.getModel();

        modeloTabela.setRowCount(0);

        for (InformacoesLivro livro : listaLivros) {
            Object[] dados = {livro.getTitulo(), livro.getAutor(), livro.getTipo(), livro.getNotaMedia(), livro.getNumeroAvaliacoes()};
            modeloTabela.addRow(dados);

            logger.info("Livro adicionado à tabela: " + livro.getTitulo() + ", " + livro.getAutor() + ", " + livro.getTipo() + ", " + livro.getNotaMedia() + ", " + livro.getNumeroAvaliacoes());
        }

        logger.info("Quantidade de livros na tabela: " + tabelaLivros.getRowCount());

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                modeloTabela.fireTableDataChanged();
            }
        });

        logger.info("Quantidade de componentes no painel: " + getContentPane().getComponentCount());
    }

    private List<InformacoesLivro> obterLivrosDoBanco() {
        LivroDAO livroDAO = new LivroDAO();
        return livroDAO.obterLivrosOrdenados();
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        
        FormularioOpcoesUsuarioComum formularioOpcoesUsuarioComum = new FormularioOpcoesUsuarioComum();

        
        formularioOpcoesUsuarioComum.setVisible(true);

        dispose();
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioVisualizacaoLivros().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
