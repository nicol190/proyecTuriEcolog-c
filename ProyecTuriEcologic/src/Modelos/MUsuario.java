/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author pipe_
 */
//en la clase modelos se escriben los metodos y los datos se obtienen mediante controladores 
//
public class MUsuario {
    
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String password;
    private String telefono;
    private boolean isAdmin;

    public MUsuario(String nombre, String apellido, String correo,String usuario, String password, String telefono, boolean isAdmin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.telefono = telefono;
        this.isAdmin = isAdmin;
    }

    public MUsuario() {
       
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    

    
      public String prepararInformacion(){
          String informacion;
        if (isAdmin == true){
            informacion = nombre +"," + apellido + "," + correo + "," + telefono + "," + usuario + "," + password + "," + "1" ;
        }else{
            informacion = nombre +"," + apellido + "," + correo + "," + telefono + "," + usuario + "," + password + "," + "0" ;
        }
        return informacion;
        
    }
    
 
}
