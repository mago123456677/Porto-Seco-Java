/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package oper;

import conecta.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import mapeamento_poo.Cargas;

/**
 *
 * @author Mateus
 */
public class Despachar extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private int qtRec = 0;
    ArrayList<Cargas> lista = new ArrayList<>();

    /**
     * Creates new form Despachar
     */
    public Despachar() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        conexao = Conexao.conector();

        inicializarTabela();
    }

    private void inicializarTabela() {
        tabelacargas.setRowHeight(30);
        listarcargas(txtNomePesquisa.getText());
        qtRecord();
    }

    public void despachar(int pId, String pStatus) {
        String sql = "update tb_cadastro set ds_status = ? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pStatus);
            pst.setInt(2, pId);

            int alterado = pst.executeUpdate();
            if (alterado > 0) {
                JOptionPane.showMessageDialog(null, "Realizado despacho com sucesso");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public ArrayList<Cargas> consultaCarga() {
        String sql = "SELECT * FROM cargas";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Cargas carga = new Cargas();
                carga.setId(rs.getInt("id"));
                carga.setTipo(rs.getString("tipo"));
                carga.setQuantidade(rs.getInt("quantidade"));
                carga.setPeso(rs.getFloat("peso"));
                carga.setDimensao(rs.getString("dimensao"));
                carga.setOrigem(rs.getString("origem"));
                carga.setDestino(rs.getString("destino"));
                carga.setTransportadora(rs.getString("transportadora"));
                carga.setDocumentacao(rs.getString("documentacao"));
                carga.setDataChegada(rs.getString("data_chegada"));
                carga.setDataSaida(rs.getString("data_saida"));
                carga.setStatus(rs.getString("status"));

                lista.add(carga);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar carga: " + e.getMessage());
        }

        return lista;
    }

    public void qtRecord() {
        String sql = "SELECT COUNT(*) quantidade FROM cargas";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                qtRec = rs.getInt(1);
            }
            lblqtRegistro.setText(String.valueOf(qtRec));

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Consultar Cargas" + erro);
        }
    }

    private void listarcargas(String text) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabelacargas.getModel();
            model.setNumRows(0);
            ArrayList<Cargas> lista = consultaCarga();

            for (int num = 0; num < lista.size(); num++) {
                model.addRow(new Object[]{
                    lista.get(num).getId(),
                    lista.get(num).getTipo(),
                    lista.get(num).getQuantidade(),
                    lista.get(num).getPeso(),
                    lista.get(num).getDimensao(),
                    lista.get(num).getOrigem(),
                    lista.get(num).getDestino(),
                    lista.get(num).getTransportadora(),
                    lista.get(num).getDocumentacao(),
                    lista.get(num).getDataChegada(),
                    lista.get(num).getDataSaida(),
                    lista.get(num).getStatus()
                });
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Listar Cargas" + erro);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomePesquisa = new rojeru_san.rsfield.RSTextFullRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelacargas = new javax.swing.JTable();
        rSButtonRound1 = new rojerusan.RSButtonRound();
        jLabel2 = new javax.swing.JLabel();
        lblqtRegistro = new javax.swing.JLabel();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(104, 34, 139));

        jLabel1.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        jLabel1.setText("Digite o nome da carga:");

        txtNomePesquisa.setBackground(new java.awt.Color(104, 34, 139));
        txtNomePesquisa.setText("Digite o nome da carga");

        tabelacargas.setBackground(new java.awt.Color(104, 34, 139));
        tabelacargas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Quantidade", "Peso", "dimensão", "origem", "destino", "transportadora", "documentação", "data_da_chegada", "data_da_saida"
            }
        ));
        jScrollPane1.setViewportView(tabelacargas);

        rSButtonRound1.setText("Editar");
        rSButtonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        jLabel2.setText("Numeros de cargas:");

        lblqtRegistro.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(txtNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(476, 476, 476)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblqtRegistro)
                        .addGap(254, 254, 254))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rSButtonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(420, 420, 420))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(txtNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblqtRegistro)
                    .addComponent(jLabel2))
                .addGap(55, 55, 55)
                .addComponent(rSButtonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound1ActionPerformed
        int[] selectedRows = tabelacargas.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            String id = tabelacargas.getModel().getValueAt(selectedRows[i], 0).toString();
            int id1 = Integer.parseInt(id);
            despachar(id1, "D");
        }
        lista.clear();
        listarcargas(txtNomePesquisa.getText());


    }//GEN-LAST:event_rSButtonRound1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblqtRegistro;
    private rojerusan.RSButtonRound rSButtonRound1;
    private javax.swing.JTable tabelacargas;
    private rojeru_san.rsfield.RSTextFullRound txtNomePesquisa;
    // End of variables declaration//GEN-END:variables

   
}