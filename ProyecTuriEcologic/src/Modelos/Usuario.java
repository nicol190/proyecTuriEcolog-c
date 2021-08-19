/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author pipe_
 */
public class Usuario {
    
    private String nombre;
    private String apellido;
    private String correo;
    private String rutatxt;
    

    public Usuario(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        rutatxt = directoryName + "/BASE DE DATOS/UsuarioDocumento.txt";
        //urlTemp = directoryName + "/BASE DE DATOS/estudianteFicheroTemp.txt";
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

   
    public void registrar_usuario(){
        
    }
    

}
