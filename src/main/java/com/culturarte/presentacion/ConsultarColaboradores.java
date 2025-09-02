/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.culturarte.presentacion;

import com.culturarte.logica.IControlador;
import com.culturarte.logica.datatypes.DTColaborador;
import com.culturarte.logica.datatypes.DTPropuesta;
import com.culturarte.logica.enums.TipoEstado;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maicol
 */
public class ConsultarColaboradores extends javax.swing.JInternalFrame {

    private IControlador controlador;
    private DefaultTableModel tabla;
    private DefaultTableModel tabla2;
        
    public ConsultarColaboradores(IControlador IC) {
        initComponents();
        controlador = IC;
        
        tabla=(DefaultTableModel)jTable1.getModel();
        tabla2=(DefaultTableModel)jTable2.getModel();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        btnCargar = new javax.swing.JButton();
        listaColaboradores = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar colaborador");
        setVisible(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        listaColaboradores.setName("comboProponentes"); // NOI18N
        listaColaboradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaColaboradoresActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Proponente", "Propuesta", "Dinero recaudado", "Estado actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(listaColaboradores, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listaColaboradores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargar))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        cargarComboBox();
    }//GEN-LAST:event_btnCargarActionPerformed

    private void listaColaboradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaColaboradoresActionPerformed
        String colaboradorSeleccionado = (String) listaColaboradores.getSelectedItem();
        if(colaboradorSeleccionado != null)
        mostrarInfoColaborador(colaboradorSeleccionado);
    }//GEN-LAST:event_listaColaboradoresActionPerformed
    
    private void mostrarInfoColaborador(String nickname){
        DTColaborador c = controlador.getDTColaborador(nickname); // Acá ya tenemos toda la info para mostrar ...
        ArrayList<DTPropuesta> propuesta=c.getPropuestas();
        ArrayList<String> proponente = new ArrayList<>();
        ArrayList<Float> recaudacion= new ArrayList<>();
        ArrayList<TipoEstado> estadoActual = new ArrayList<>();
       
        for(DTPropuesta p:propuesta){
            proponente.add(p.getProponente());
            recaudacion.add(p.getMontoRecaudado());
            estadoActual.add(p.getEstadoActual());
        }
        
        tabla.setRowCount(0);
        Object[] cuadro = {
        c.getNombre().trim(),
        c.getApellido().trim()};
        tabla.addRow(cuadro);
        File imagenFile = c.getImagen();
        
        if (imagenFile != null && imagenFile.exists()) {
            ImageIcon icon = new ImageIcon(imagenFile.getAbsolutePath());
           //escala la imagen al tamaño del JLabel
            Image imagenEscalada = icon.getImage().getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(),Image.SCALE_SMOOTH);
            jLabel1.setIcon(new ImageIcon(imagenEscalada));
        }   
        else {
            jLabel1.setIcon(null); // limpia si no hay imagen
        }
        
        tabla2.setRowCount(0);
        for (int i = 0; i < propuesta.size(); i++) {
            Object[] fila = {
            propuesta.get(i).getTitulo(),         
            proponente.get(i),                   
            recaudacion.get(i),                  
            estadoActual.get(i)                   
            };
        tabla2.addRow(fila);
        } 
    }
        
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarComboBox();
    }//GEN-LAST:event_formComponentShown
  
    private void cargarComboBox() {
        ArrayList<String> nomColaboradores = controlador.getNickColaboradores();

        this.listaColaboradores.removeAllItems(); // limpia cualquier elemento previo

        for (String p : nomColaboradores) {
            this.listaColaboradores.addItem(p); // agregamos solo el nickname
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> listaColaboradores;
    // End of variables declaration//GEN-END:variables
}
