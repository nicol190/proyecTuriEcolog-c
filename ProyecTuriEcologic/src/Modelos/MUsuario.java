/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

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
    private String nombreUsuario;
    private String password;
    private String telefono;
    private boolean isAdmin;

    public MUsuario(String nombre, String apellido, String correo,String telefono,String nombreUsuario, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public MUsuario(String nombre, String apellido, String correo,String telefono,String nombreUsuario, String password, boolean isAdmin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public MUsuario(){
        
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
        return this.nombreUsuario;
    }

    public void setUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    

    
      public String prepararInformacion(){
          String informacion;
        if (isAdmin == true){
            informacion = nombre +"," + apellido + "," + correo + "," + telefono + "," + nombreUsuario + "," + password + "," + "1" ;
        }else{
            informacion = nombre +"," + apellido + "," + correo + "," + telefono + "," + nombreUsuario + "," + password + "," + "0" ;
        }
        return informacion;
        
    }
    
 
}
