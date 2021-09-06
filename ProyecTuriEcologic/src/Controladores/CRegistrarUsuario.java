/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.MUsuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pipe_
 */
//El la clase controladores se recibe y asigna los datos obtenidos de la vista utilizando get y set
//aqui se crean metodos solamente para pasar la informacion a modelos 
public class CRegistrarUsuario {
    private String url;
    private String urlTemp;
    private Path path;
    private ArrayList<MUsuario> listaUsuarios;

    public CRegistrarUsuario() {
        this.path = Paths.get("");
        String directoryName = this.path.toAbsolutePath().toString();
        this.url = directoryName + "/BASE DE DATOS PPI/UsuarioDocumento.txt";
        this.urlTemp = directoryName + "/BASE DE DATOS PPI/UsuarioDocumentoTemp.txt";
    }
   
    

    public boolean registroUsuarios(MUsuario usuario) throws IOException{
        boolean respuesta=false;
     
        String informacion = usuario.prepararInformacion();
     
        BufferedWriter bw = new BufferedWriter(new FileWriter(url));

            bw.write(informacion);
            bw.flush();
            bw.newLine();
            bw.close();
            respuesta = true;
           return respuesta;
    }
    
    public MUsuario buscarUsuario(String usuario, String password) throws IOException{
        
        BufferedReader br = new BufferedReader(new FileReader(url));// carga el archivo 
        String record;
            
        while ((record = br.readLine()) != null) {
              StringTokenizer st = new StringTokenizer(record, ",");
             
              MUsuario usuarioBusqueda = new MUsuario(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
              String isAdmin =st.nextToken();
              if(isAdmin.equals("1")){
                  usuarioBusqueda.setIsAdmin(true);
                  
              }else{
                  usuarioBusqueda.setIsAdmin(false);
              }
              if (usuarioBusqueda.getUsuario().equals(usuario) && usuarioBusqueda.getPassword().equals(password)){
                  return usuarioBusqueda;
              }
        }

        br.close();

        return null;
        
    }
    
    public MUsuario buscarUsuarioContraseña(String usuario) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(url));// carga el archivo 
        String record;

        while ((record = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(record, ",");

            MUsuario usuarioBusqueda = new MUsuario(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());

            if (usuarioBusqueda.getUsuario().equals(usuario)) {
                return usuarioBusqueda;
            }
        }

        br.close();

        return null;

    }
    
     public boolean Actualizar(String usuario, String contraseñaNueva) {

        File archivoEntrada = new File(this.url);
        File archivoSalida = new File(this.urlTemp);

        BufferedReader lectura;
        BufferedWriter escritura;
        String lecturaLinea;
        try {
            lectura = new BufferedReader(new FileReader(archivoEntrada));
            escritura = new BufferedWriter(new FileWriter(archivoSalida));

            while ((lecturaLinea = lectura.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(lecturaLinea, ",");
                MUsuario usuarioBusqueda = new MUsuario(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());

                if (usuarioBusqueda.getUsuario().equals(usuario)) {
                    usuarioBusqueda.setPassword(contraseñaNueva);
                    escritura.write(usuarioBusqueda.prepararInformacion());
                    escritura.newLine();
                    continue;

                } else {
                    escritura.write(usuarioBusqueda.prepararInformacion());
                }

                escritura.newLine();
            }
            escritura.close();
            lectura.close();
            archivoEntrada.delete();
            return archivoSalida.renameTo(archivoEntrada);

        } catch (IOException ex) {
            Logger.getLogger(CRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;


        /*if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "No es posible actualizar la contraseña, intentelo de nuevo");
        } else if (success == true) {
            JOptionPane.showMessageDialog(null, " Actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        }*/
    }
     
    
    
    
    
    
}

