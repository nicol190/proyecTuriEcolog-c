/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Controladores.CDestinoTuristico;
import Modelos.MDestinoFavorito;
import Modelos.MDestinoTuristico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pipe_
 */
public class DestinoTuristico extends javax.swing.JFrame {
    public CDestinoTuristico controladorDestinos;
    public String usuario;
    
    /**
     * Creates new form DestinoTuristico
     */
    public DestinoTuristico(String usuario)  {
        initComponents();
        this.usuario = usuario;
        this.controladorDestinos = new CDestinoTuristico();
        cargarListasFavorito();
        Stack<MDestinoTuristico>  listaDestinosU = controladorDestinos.obtenerListaPila();
        refrescarListaDestinos(listaDestinosU);
        
    }
     public void refrescarListaDestinos(Stack<MDestinoTuristico>  listaNueva) {


        String[] lista = new String [listaNueva.size()];
        for (int i = 0; i < listaNueva.size(); i++) {
            //prueba
            MDestinoTuristico destinos = listaNueva.get(i);
            System.out.println(destinos.getNombreDestino());
           
            lista[i] = destinos.getCodigo() +" - "+ destinos.getNombreDestino();
            
        }
        ListaDestinos.setListData(lista);
        ListaDestinos.setSelectedIndex(0);
    }
    
    public void refrescarListaDestinosFavoritos(Stack<MDestinoTuristico>  listaNueva) {


        String[] lista = new String [listaNueva.size()];
        for (int i = 0; i < listaNueva.size(); i++) {
            //prueba
            MDestinoTuristico destinos = listaNueva.get(i);
            System.out.println(destinos.getNombreDestino());
           
            lista[i] = destinos.getCodigo() +" - "+ destinos.getNombreDestino();
            
        }
        listaFavoritosD.setListData(lista);
        listaFavoritosD.setSelectedIndex(0);
    }
    public void cargarListasFavorito(){
         Stack<MDestinoTuristico> listaFavoritos = controladorDestinos.obtenerListaPilaPorUsuario(usuario);
            refrescarListaDestinosFavoritos(listaFavoritos);
    }
    
     
   
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Perfil = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaFavoritosD = new javax.swing.JList<>();
        BotonEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDestinos = new javax.swing.JList<>();
        botoAgregarFavorito = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txDestino = new javax.swing.JTextField();
        txmunicipio = new javax.swing.JTextField();
        txtarifa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txDescripcion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Perfil.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        Perfil.setText("Mis Destinos ");

        jScrollPane2.setViewportView(listaFavoritosD);

        BotonEliminar.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        BotonEliminar.setText("Eliminar Destino ");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel4.setText("Perfil ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotonEliminar)
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Perfil)
                .addGap(112, 112, 112))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Perfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(BotonEliminar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ListaDestinos.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                ListaDestinosVetoableChange(evt);
            }
        });
        ListaDestinos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaDestinosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ListaDestinos);

        botoAgregarFavorito.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        botoAgregarFavorito.setText("Agregar a favorito");
        botoAgregarFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoAgregarFavoritoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel3.setText("Destinos Turisticos ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(95, 95, 95))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(botoAgregarFavorito))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(botoAgregarFavorito)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel5.setText("Destino");

        jLabel6.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel6.setText("Descripcion");

        jLabel7.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel7.setText("Municipio");

        jLabel8.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel8.setText("Tarifa");

        jLabel9.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel9.setText("Foto");

        txDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDestinoActionPerformed(evt);
            }
        });

        txmunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txmunicipioActionPerformed(evt);
            }
        });

        txtarifa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txDescripcion.setColumns(20);
        txDescripcion.setRows(5);
        jScrollPane3.setViewportView(txDescripcion);

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\pipe_\\Documents\\Proyectos\\proyecTuriEcolog-c\\ProyecTuriEcologic\\build\\classes\\Imagenes\\logo (1).png")); // NOI18N
        jLabel13.setText("jLabel13");

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\pipe_\\Documents\\Proyectos\\proyecTuriEcolog-c\\ProyecTuriEcologic\\build\\classes\\Imagenes\\destinot.jpg")); // NOI18N
        jLabel12.setText("jLabel12");

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setForeground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 18)); // NOI18N
        jLabel2.setText("Descubre uno de los mejores lugares para visitar ");

        jLabel1.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 18)); // NOI18N
        jLabel1.setText("TuriEcologíc Destinos Turisticos ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtarifa)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9)
                    .addComponent(txmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addGap(329, 329, 329))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botoAgregarFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoAgregarFavoritoActionPerformed
         try {
            //retorna lo que se selecciono en la lista interfaz
            String ve = (String) ListaDestinos.getSelectedValue();
            String datos[] = ve.split(" - ");
            String codigoDestino = datos[0];
            // se busca un destino turistico por medio del campo codigo
            // se crea un objeto de tipo MDestinoTuristico para guardar lo que retorna buscar destino que es de tipo MDestinoTuristico
            MDestinoTuristico destinoU = controladorDestinos.buscarDestinoTuristico(Integer.parseInt(codigoDestino));
            
            MDestinoFavorito favorito = new MDestinoFavorito(usuario,destinoU.getCodigo(),destinoU.getNombreDestino(),destinoU.getDescripcion(),destinoU.getMunicipio(),destinoU.getTarifa(),destinoU.getFoto());
            boolean agregarFavorito = controladorDestinos.agregarDestinoFavorito(favorito);
            Stack<MDestinoTuristico> listaFavoritos = controladorDestinos.obtenerListaPilaPorUsuario(usuario);
            refrescarListaDestinosFavoritos(listaFavoritos);
            
           
        } catch (IOException ex) {
            Logger.getLogger(DestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }//GEN-LAST:event_botoAgregarFavoritoActionPerformed

    private void txmunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txmunicipioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txmunicipioActionPerformed

    private void txDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDestinoActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
       
            //retorna lo que se selecciono en la lista interfaz
            String ve = (String) listaFavoritosD.getSelectedValue();
            String datos[] = ve.split(" - ");
            String codigoDestino = datos[0];
           
            
            // se busca un destino turistico por medio del campo codigo
            // se crea un objeto de tipo MDestinoTuristico para guardar lo que retorna buscar destino que es de tipo MDestinoTuristico
            boolean destinoF = controladorDestinos.eliminarDestinoFavorito(usuario, Integer.parseInt(codigoDestino));
            
            
            Stack<MDestinoTuristico> listaFavoritos = controladorDestinos.obtenerListaPilaPorUsuario(usuario);
            refrescarListaDestinosFavoritos(listaFavoritos);
            
            
            
       
       
            
        
                                               
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void ListaDestinosVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_ListaDestinosVetoableChange
        
    }//GEN-LAST:event_ListaDestinosVetoableChange

    private void ListaDestinosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaDestinosValueChanged
            try {
            //retorna lo que se selecciono en la lista interfaz
            String ve = (String) ListaDestinos.getSelectedValue();
            String datos[] = ve.split(" - ");
            String codigoDestino = datos[0];
            MDestinoTuristico destinoU = controladorDestinos.buscarDestinoTuristico(Integer.parseInt(codigoDestino));
            if (destinoU != null && ListaDestinos.getSelectedValue() != null) {
                txDestino.setText(destinoU.getNombreDestino());
                txDescripcion.setText(destinoU.getDescripcion());
                txmunicipio.setText(destinoU.getMunicipio());
                txtarifa.setText(String.valueOf(destinoU.getTarifa()));
               
            }
        } catch (IOException ex) {
            Logger.getLogger(DestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ListaDestinosValueChanged

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JList<String> ListaDestinos;
    private javax.swing.JLabel Perfil;
    private javax.swing.JButton botoAgregarFavorito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaFavoritosD;
    private javax.swing.JTextArea txDescripcion;
    private javax.swing.JTextField txDestino;
    private javax.swing.JTextField txmunicipio;
    private javax.swing.JTextField txtarifa;
    // End of variables declaration//GEN-END:variables
}
