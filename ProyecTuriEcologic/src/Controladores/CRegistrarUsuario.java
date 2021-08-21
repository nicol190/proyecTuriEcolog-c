/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.MUsuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author pipe_
 */
//El la clase controladores se recibe y asigna los datos obtenidos de la vista utilizando get y set
//aqui se crean metodos solamente para pasar la informacion a modelos 
public class CRegistrarUsuario {
    private String url;
    private String urlTemp;
    private ArrayList<MUsuario> listaUsuarios;

    public CRegistrarUsuario() {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/usuarioDocumento.txt";
        //urlTemp = directoryName + "/Base de datos/usuarioDocumentoTemp.txt";
    }
   
    

    public boolean registroUsuarios(MUsuario usuario) throws IOException{
        boolean respuesta=false;
     
        String informacion = usuario.prepararInformacion();
     
        BufferedWriter bw = new BufferedWriter(new FileWriter(url, true));

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
               String admin = st.nextToken();
              
              if (admin.equals("1")){
                   usuarioBusqueda.setIsAdmin(true);
                   
              }else{
                 usuarioBusqueda.setIsAdmin(false);
              }
             
              
              if (usuarioBusqueda.getUsuario().equals(usuario)&& usuarioBusqueda.getPassword().equals(password)){
                  return usuarioBusqueda;
              }
        }

        br.close();

        return null;
        
    }
}
