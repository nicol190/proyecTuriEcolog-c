/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Controladores.CUsuarioAdministrador;
import Modelos.MUsuario;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pipe_
 */
public class UsuarioAdministrador extends javax.swing.JFrame {
    DefaultTableModel modelo;//modelo para ser insertado en la tabla.
    /**
     * Creates new form DestinosAdministrador
     */
    public UsuarioAdministrador() throws IOException {
        initComponents();
        modelo = new DefaultTableModel();
        this.listar();
        
    }
    
    void listar() throws IOException{
        if (modelo.getRowCount() > 0){//Si se imprimió previamente, se limpia la tabla y se imprime nuevamente.
            modelo.setRowCount(0);
        }
        
        LinkedList<MUsuario> listaUsuarios;
        CUsuarioAdministrador controlador = new CUsuarioAdministrador();
        listaUsuarios = controlador.getLista();
        
        modelo = (DefaultTableModel)tablaUsuarios.getModel();
        Object[] objeto = new Object[6];
        
        for (int i = 0; i < listaUsuarios.size(); i++){
            objeto[0] = listaUsuarios.get(i).getNombre();
            objeto[1] = listaUsuarios.get(i).getApellido();
            objeto[2] = listaUsuarios.get(i).getCorreo();
            objeto[3] = listaUsuarios.get(i).getUsuario();
            objeto[4] = listaUsuarios.get(i).getTelefono();
            objeto[5] = listaUsuarios.get(i).getIsAdmin();
          
            modelo.addRow(objeto); //Añadiendo las celdas de cada fila del modelo.
        }
        
        this.tablaUsuarios.setModel(modelo);
        
    }
    
    boolean isEmpty(JTextField textBox){
        return textBox.getText().length() == 0;
    }
    
    int crear() throws IOException{
        //Vista
        Component frame = new JFrame();
        if (this.isEmpty(this.txNombre) || 
            this.isEmpty(this.txApellido) ||
            this.isEmpty(this.txCorreoElectronico) || 
            this.isEmpty(this.txNombreUsuario)) //Si todos los campos están vacios. 
        {
            
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
        }else{
            String nombre = txNombre.getText();
            String apellido = txApellido.getText();
            String correo = txCorreoElectronico.getText();
            String nombreUsuario = txNombreUsuario.getText();
            String telefono = txTelefono.getText();
            Boolean isAdmin = "Administrador".equals(ComboBoxIsAdmin.getSelectedItem().toString());
            try {
                //  Block of code to try
                Integer.parseInt(txTelefono.getText());
            }
            catch(Exception e) {
                //  Block of code to handle errors
                JOptionPane.showMessageDialog(frame, "Ingrese solo numeros sin comas ni puntos en el campo 'Telefono' ");
                return 0;
            }
            
            char[] password = null;
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter a password:");
            JPasswordField pass = new JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(null, panel, "The title",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[0]);
            if(option == 0) // presionar OK
            {
                password = pass.getPassword();
                System.out.println("Your password is: " + new String(password));
            }else{
                return 0;
            }
            
            Object[] fila = new Object[6]; //Fila para agregar al modelo de la tabla.
            fila[0] = nombre;  
            fila[1] = apellido;
            fila[2] = correo;
            fila[3] = nombreUsuario;
            fila[4] = telefono;
            fila[5] = isAdmin ? "Administrador" : "Usuario Corriente";

            modelo.addRow(fila);//Agregando nueva fila.
            
            //Controlador
            MUsuario usuario = new MUsuario(nombre, apellido, correo,nombreUsuario, new String(password), telefono, isAdmin);
            CUsuarioAdministrador controlador = new CUsuarioAdministrador();
            controlador.agregarUsuario(usuario);
        }
        return 1;
    }
    
    public void eliminar() throws IOException {
        int filaSeleccionada = this.tablaUsuarios.getSelectedRow();
        JFrame frame = new JFrame();
        if (filaSeleccionada >= 0){
            int seleccion = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar el Usuario " + (this.modelo.getValueAt(filaSeleccionada, 0).toString()) + " ?");
            if (seleccion != 0){//Selecciona diferente de SI.
            }else{//Selecciona SI:
                //Vista
                this.modelo.removeRow(seleccion);
                //Conexión con el controlador.
                
                CUsuarioAdministrador controlador = new CUsuarioAdministrador();
                controlador.eliminarUsuario(this.modelo.getValueAt(filaSeleccionada, 0).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 1).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 2).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 3).toString(),
                                            this.modelo.getValueAt(filaSeleccionada, 4).toString().replace(".0", ""),
                                            this.modelo.getValueAt(filaSeleccionada, 5).toString().equals("true"));
                
                
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Por favor seleccione una fila.");
        }
    }
    
    public void actualizar() throws IOException{
        int filaSeleccionada = this.tablaUsuarios.getSelectedRow();
        
        if (filaSeleccionada >= 0){
            modelo.setValueAt(this.txNombre.getText(), filaSeleccionada, 0);
            modelo.setValueAt(this.txApellido.getText(), filaSeleccionada, 1);
            modelo.setValueAt(this.txCorreoElectronico.getText(), filaSeleccionada, 2);
            modelo.setValueAt(this.txNombreUsuario.getText(), filaSeleccionada, 3);
            modelo.setValueAt(this.txTelefono.getText(), filaSeleccionada, 4);
            modelo.setValueAt(this.ComboBoxIsAdmin.getSelectedItem().toString().equals("Administrador") ? "true":"false" , filaSeleccionada, 5);
            
            CUsuarioAdministrador controlador = new CUsuarioAdministrador();
            controlador.actualizarUsuario(modelo.getValueAt(filaSeleccionada, 0).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 1).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 2).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 3).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 4).toString(),
                                        this.modelo.getValueAt(filaSeleccionada, 5).toString().equals("true"));
        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Por favor seleccione una fila.");
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
        txNombre = new javax.swing.JTextField();
        txApellido = new javax.swing.JTextField();
        txCorreoElectronico = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        BotonCerrarSesión = new javax.swing.JButton();
        txNombreUsuario = new javax.swing.JTextField();
        txTelefono = new javax.swing.JTextField();
        ComboBoxIsAdmin = new javax.swing.JComboBox<>();

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
        jLabel1.setText("Gestionar Usuarios");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("TuriEcologic");

        txNombre.setForeground(new java.awt.Color(153, 153, 153));
        txNombre.setText("Nombre");
        txNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txNombreFocusLost(evt);
            }
        });
        txNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombreActionPerformed(evt);
            }
        });

        txApellido.setForeground(new java.awt.Color(153, 153, 153));
        txApellido.setText("Apellido");
        txApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txApellidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txApellidoFocusLost(evt);
            }
        });
        txApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoActionPerformed(evt);
            }
        });

        txCorreoElectronico.setForeground(new java.awt.Color(153, 153, 153));
        txCorreoElectronico.setText("Correo Electronico");
        txCorreoElectronico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txCorreoElectronicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txCorreoElectronicoFocusLost(evt);
            }
        });

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Correo Electronico", "Nombre de Usuario", "Telefono", "Is Admin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaUsuarios);
        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(1);
            tablaUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(20);
            tablaUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(30);
            tablaUsuarios.getColumnModel().getColumn(4).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(5).setResizable(false);
        }

        BotonCerrarSesión.setText("Cerrar Sesión");
        BotonCerrarSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarSesiónActionPerformed(evt);
            }
        });

        txNombreUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txNombreUsuario.setText("Nombre de Usuario");
        txNombreUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txNombreUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txNombreUsuarioFocusLost(evt);
            }
        });

        txTelefono.setForeground(new java.awt.Color(153, 153, 153));
        txTelefono.setText("Telefono");
        txTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txTelefonoFocusLost(evt);
            }
        });
        txTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTelefonoActionPerformed(evt);
            }
        });

        ComboBoxIsAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario corriente", "Administrador" }));

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotonCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonEliminar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ComboBoxIsAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxIsAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            Logger.getLogger(UsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonCrearActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        try {
            // TODO add your handling code here:
            this.eliminar();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void BotonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonListarActionPerformed
        try {
            // TODO add your handling code here:
            this.listar();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonListarActionPerformed

    private void txNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNombreActionPerformed

    private void BotonCerrarSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarSesiónActionPerformed
        // TODO add your handling code here:
        this.cerrarSesion();
    }//GEN-LAST:event_BotonCerrarSesiónActionPerformed

    private void txNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txNombreFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Nombre";
        this.createPlaceHolder(this.txNombre, placeHolderText);
    }//GEN-LAST:event_txNombreFocusGained

    private void txNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txNombreFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Nombre";
        this.createPlaceHolder(this.txNombre, placeHolderText);
    }//GEN-LAST:event_txNombreFocusLost

    private void txApellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txApellidoFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Apellido";
        this.createPlaceHolder(this.txApellido, placeHolderText);
    }//GEN-LAST:event_txApellidoFocusGained

    private void txApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txApellidoFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Apellido";
        this.createPlaceHolder(this.txApellido, placeHolderText);
    }//GEN-LAST:event_txApellidoFocusLost

    private void txCorreoElectronicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txCorreoElectronicoFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Correo Electronico";
        this.createPlaceHolder(this.txCorreoElectronico, placeHolderText);
    }//GEN-LAST:event_txCorreoElectronicoFocusGained

    private void txCorreoElectronicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txCorreoElectronicoFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Correo Electronico";
        this.createPlaceHolder(this.txCorreoElectronico, placeHolderText);
    }//GEN-LAST:event_txCorreoElectronicoFocusLost

    private void txApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = this.tablaUsuarios.getSelectedRow();
        this.txNombre.setText(this.modelo.getValueAt(filaSeleccionada, 0).toString());
        this.txApellido.setText(this.modelo.getValueAt(filaSeleccionada, 1).toString());
        this.txCorreoElectronico.setText(this.modelo.getValueAt(filaSeleccionada, 2).toString());
        this.txNombreUsuario.setText(this.modelo.getValueAt(filaSeleccionada, 3).toString());
        this.txTelefono.setText(this.modelo.getValueAt(filaSeleccionada, 4).toString());
        this.ComboBoxIsAdmin.setSelectedIndex(this.modelo.getValueAt(filaSeleccionada, 4).toString().equals("Administrador") ? 1: 0);
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        try {
            // TODO add your handling code here:
            this.actualizar();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void txNombreUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txNombreUsuarioFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Nombre de Usuario";
        this.createPlaceHolder(this.txCorreoElectronico, placeHolderText);
    }//GEN-LAST:event_txNombreUsuarioFocusGained

    private void txNombreUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txNombreUsuarioFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Nombre de Usuario";
        this.createPlaceHolder(this.txCorreoElectronico, placeHolderText);
    }//GEN-LAST:event_txNombreUsuarioFocusLost

    private void txTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txTelefonoFocusGained
        // TODO add your handling code here:
        String placeHolderText = "Telefono";
        this.createPlaceHolder(this.txCorreoElectronico, placeHolderText);
    }//GEN-LAST:event_txTelefonoFocusGained

    private void txTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txTelefonoFocusLost
        // TODO add your handling code here:
        String placeHolderText = "Telefono";
        this.createPlaceHolder(this.txCorreoElectronico, placeHolderText);
    }//GEN-LAST:event_txTelefonoFocusLost

    private void txTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTelefonoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuarioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new UsuarioAdministrador().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCerrarSesión;
    private javax.swing.JButton BotonCrear;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonListar;
    private javax.swing.JComboBox<String> ComboBoxIsAdmin;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txApellido;
    private javax.swing.JTextField txCorreoElectronico;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNombreUsuario;
    private javax.swing.JTextField txTelefono;
    // End of variables declaration//GEN-END:variables
}
