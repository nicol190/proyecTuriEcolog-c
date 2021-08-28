/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Controladores.CDestinoAdministrador;
import Modelos.MDestinoAdministrador;
import Modelos.MDestinoTuristico;
import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pipe_
 */
public class DestinoAdministrador extends javax.swing.JFrame {
    DefaultTableModel modelo;//modelo para ser insertado en la tabla.
    /**
     * Creates new form DestinosAdministrador
     */
    public DestinoAdministrador(){
        initComponents();
        modelo = new DefaultTableModel();
        try {
            this.listar();
        } catch (IOException ex) {
            Logger.getLogger(DestinoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void listar() throws IOException{
        if (modelo.getRowCount() > 0){//Si se imprimió previamente, se limpia la tabla y se imprime nuevamente.
            modelo.setRowCount(0);
        }
        
        LinkedList<MDestinoTuristico> listaDestinos = null;
        try {
            listaDestinos = new CDestinoAdministrador().obtenerLista();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DestinoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        modelo = (DefaultTableModel)tablaDestinos.getModel();
        Object[] objeto = new Object[6];
        
        for (int i = 0; i < listaDestinos.size(); i++){
            objeto[0] = listaDestinos.get(i).getCodigo();
            objeto[1] = listaDestinos.get(i).getNombreDestino();
            objeto[2] = listaDestinos.get(i).getDescripcion();
            objeto[3] = listaDestinos.get(i).getMunicipio();
            objeto[4] = listaDestinos.get(i).getTarifa();
          //objeto[5] = listaDestinos.get(i).getFoto();
          
            modelo.addRow(objeto); //Añadiendo las celdas de cada fila del modelo.
        }
        
        this.tablaDestinos.setModel(modelo);
        
    }
    
    boolean isEmpty(JTextField textBox){
        return textBox.getText().length() == 0;
    }
    
    int crear() throws IOException{
        if (this.isEmpty(this.txNombreDestino) || 
            this.isEmpty(this.txDescripcion) ||
            this.isEmpty(this.txMunicipio) || 
            this.isEmpty(this.txTarifa)) //Si todos los campos están vacios. 
        {
            Component frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
        }else{
            int codigo = tablaDestinos.getRowCount() + 1;
            String nombreDestinoTXT = txNombreDestino.getText();
            String descripcionTXT = txDescripcion.getText();
            String municipioTXT = txMunicipio.getText();
            int tarifaTXT = 0;
            try {
                //  Block of code to try
                tarifaTXT = Integer.parseInt(txTarifa.getText());
            }
            catch(Exception e) {
                //  Block of code to handle errors
                Component frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Ingrese solo numeros enteros sin comas ni puntos");
                return 0;
            }
            
            MDestinoAdministrador destino = new MDestinoAdministrador(codigo, nombreDestinoTXT, descripcionTXT, municipioTXT, tarifaTXT);
            
            Object[] fila = new Object[5]; //Fila para agregar al modelo de la tabla.
            fila[0] = codigo;  
            fila[1] = nombreDestinoTXT;
            fila[2] = descripcionTXT;
            fila[3] = municipioTXT;
            fila[4] = tarifaTXT;
            //fila[5] = foto;

            modelo.addRow(fila);//Agregando nueva fila.
            
            
            CDestinoAdministrador controlador = new CDestinoAdministrador();
            controlador.agregarDestino(destino);
        }
        return 1;
    }
    
    public void eliminar() throws IOException {
        int filaSeleccionada = this.tablaDestinos.getSelectedRow();
        JFrame frame = new JFrame();
        if (filaSeleccionada >= 0){
            int seleccion = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar el destino " + (filaSeleccionada + 1) + " ?");
            if (seleccion != 0){//Selecciona diferente de SI.
            }else{//Selecciona SI:
                CDestinoAdministrador controlador = new CDestinoAdministrador();
                controlador.eliminarDestino(this.modelo.getValueAt(filaSeleccionada, 0).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 1).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 2).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 3).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 4).toString().replace(".0", ""));
                
                this.modelo.removeRow(seleccion);
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Por favor seleccione una fila.");
        }
    }
    
    public void actualizar() throws IOException{
        int filaSeleccionada = this.tablaDestinos.getSelectedRow();
        
        if (filaSeleccionada >= 0){
            modelo.setValueAt(this.txNombreDestino.getText(), filaSeleccionada, 1);
            modelo.setValueAt(this.txDescripcion.getText(), filaSeleccionada, 2);
            modelo.setValueAt(this.txMunicipio.getText(), filaSeleccionada, 3);
            modelo.setValueAt(this.txTarifa.getText(), filaSeleccionada, 4);
            
            CDestinoAdministrador controlador = new CDestinoAdministrador();
            controlador.actualizarDestino(modelo.getValueAt(filaSeleccionada, 0).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 1).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 2).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 3).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 4).toString().replace(".0", ""));
        }
    }

    
    void cerrarSesion(){
        Component frame = new JFrame();
        int confirmacion = JOptionPane.showConfirmDialog(frame, "¿Desea cerrar sesión?");
        if (confirmacion == 0){//El usario confirma que quiere cerrar sesión.
            //Vuelve a la ventana de Login.
            Login login = new Login();
            login.setVisible(true);
            dispose();
        }
               
    }
    
    void createPlaceHolder(JTextField textField, String placeHolderText){
        if(textField.getText().equals(placeHolderText)){
            textField.setText("");
            textField.setForeground(Color.black);
        }
        else{ 
            if(textField.getText().equals("")){
                textField.setText(placeHolderText);
                textField.setForeground(Color.GRAY);
            }
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

        BotonCrear = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        BotonListar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txNombreDestino = new javax.swing.JTextField();
        txDescripcion = new javax.swing.JTextField();
        txMunicipio = new javax.swing.JTextField();
        txTarifa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDestinos = new javax.swing.JTable();
        BotonCerrarSesión = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BotonCrear.setText("Crear");
        BotonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCrearActionPerformed(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        BotonListar.setText("Listar");
        BotonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonListarActionPerformed(evt);
            }
        });

        BotonEliminar.setText("Eliminar");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Gestionar Destinos");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("TuriEcologic");

        txNombreDestino.setForeground(new java.awt.Color(153, 153, 153));
        txNombreDestino.setText("Nombre");
        txNombreDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txNombreDestinoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txNombreDestinoFocusLost(evt);
            }
        });
        txNombreDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombreDestinoActionPerformed(evt);
            }
        });

        txDescripcion.setColumns(1);
        txDescripcion.setForeground(new java.awt.Color(153, 153, 153));
        txDescripcion.setText("Descripcion");
        txDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txDescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txDescripcionFocusLost(evt);
            }
        });

        txMunicipio.setForeground(new java.awt.Color(153, 153, 153));
        txMunicipio.setText("Municipio");
        txMunicipio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txMunicipioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txMunicipioFocusLost(evt);
            }
        });
        txMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMunicipioActionPerformed(evt);
            }
        });

        txTarifa.setForeground(new java.awt.Color(153, 153, 153));
        txTarifa.setText("Tarifa");
        txTarifa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txTarifaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txTarifaFocusLost(evt);
            }
        });

        tablaDestinos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción", "Municipio", "Tarifa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDestinos.getTableHeader().setReorderingAllowed(false);
        tablaDestinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDestinosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDestinos);
        if (tablaDestinos.getColumnModel().getColumnCount() > 0) {
            tablaDestinos.getColumnModel().getColumn(0).setResizable(false);
            tablaDestinos.getColumnModel().getColumn(0).setPreferredWidth(1);
            tablaDestinos.getColumnModel().getColumn(1).setResizable(false);
            tablaDestinos.getColumnModel().getColumn(1).setPreferredWidth(20);
            tablaDestinos.getColumnModel().getColumn(2).setResizable(false);
            tablaDestinos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaDestinos.getColumnModel().getColumn(3).setResizable(false);
            tablaDestinos.getColumnModel().getColumn(3).setPreferredWidth(30);
            tablaDestinos.getColumnModel().getColumn(4).setResizable(false);
        }

        BotonCerrarSesión.setText("Cerrar Sesión");
        BotonCerrarSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarSesiónActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotonCerrarSesión)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.CENTER)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotonListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonCrear)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txDescripcion)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(BotonCerrarSesión)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonListar)
                    .addComponent(botonActualizar)
                    .addComponent(BotonEliminar)
                    .addComponent(BotonCrear))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCrearActionPerformed
        try {
            // TODO add your handling code here:
            this.crear();
        } catch (IOException ex) {
            Logger.getLogger(DestinoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonCrearActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        try {
            // TODO add your handling code here:
            this.eliminar();
        } catch (IOException ex) {
            Logger.getLogger(DestinoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void BotonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonListarActionPerformed
        try {
            // TODO add your handling code here:
            this.listar();
        } catch (IOException ex) {
            Logger.getLogger(DestinoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonListarActionPerformed

    private void txNombreDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombreDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNombreDestinoActionPerformed

    private void BotonCerrarSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarSesiónActionPerformed
        // TODO add your handling code here:
        this.cerrarSesion();
    }//GEN-LAST:event_BotonCerrarSesiónActionPerformed

    private void txNombreDestinoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txNombreDestinoFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Nombre";
        this.createPlaceHolder(this.txNombreDestino, placeHolderText);
    }//GEN-LAST:event_txNombreDestinoFocusGained

    private void txNombreDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txNombreDestinoFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Nombre";
        this.createPlaceHolder(this.txNombreDestino, placeHolderText);
    }//GEN-LAST:event_txNombreDestinoFocusLost

    private void txMunicipioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txMunicipioFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Municipio";
        this.createPlaceHolder(this.txMunicipio, placeHolderText);
    }//GEN-LAST:event_txMunicipioFocusGained

    private void txMunicipioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txMunicipioFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Municipio";
        this.createPlaceHolder(this.txMunicipio, placeHolderText);
    }//GEN-LAST:event_txMunicipioFocusLost

    private void txTarifaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txTarifaFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Tarifa";
        this.createPlaceHolder(this.txTarifa, placeHolderText);
    }//GEN-LAST:event_txTarifaFocusGained

    private void txTarifaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txTarifaFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Tarifa";
        this.createPlaceHolder(this.txTarifa, placeHolderText);
    }//GEN-LAST:event_txTarifaFocusLost

    private void txDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txDescripcionFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Descripcion";
        this.createPlaceHolder(this.txDescripcion, placeHolderText);
    }//GEN-LAST:event_txDescripcionFocusGained

    private void txDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txDescripcionFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Descripcion";
        this.createPlaceHolder(this.txDescripcion, placeHolderText);
    }//GEN-LAST:event_txDescripcionFocusLost

    private void txMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMunicipioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMunicipioActionPerformed

    private void tablaDestinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDestinosMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = this.tablaDestinos.getSelectedRow();
        this.txNombreDestino.setText(this.modelo.getValueAt(filaSeleccionada, 1).toString());
        this.txDescripcion.setText(this.modelo.getValueAt(filaSeleccionada, 2).toString());
        this.txMunicipio.setText(this.modelo.getValueAt(filaSeleccionada, 3).toString());
        this.txTarifa.setText(this.modelo.getValueAt(filaSeleccionada, 4).toString());
    }//GEN-LAST:event_tablaDestinosMouseClicked

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        try {
            // TODO add your handling code here:
            this.actualizar();
        } catch (IOException ex) {
            Logger.getLogger(DestinoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCerrarSesión;
    private javax.swing.JButton BotonCrear;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonListar;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDestinos;
    private javax.swing.JTextField txDescripcion;
    private javax.swing.JTextField txMunicipio;
    private javax.swing.JTextField txNombreDestino;
    private javax.swing.JTextField txTarifa;
    // End of variables declaration//GEN-END:variables
}
