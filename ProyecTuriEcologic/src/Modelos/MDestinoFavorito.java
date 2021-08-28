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
public class MDestinoFavorito extends MDestinoTuristico {
    private String usuario;

    public MDestinoFavorito(String usuario, int codigo, String nombreDestino, String descripcion, String municipio, float tarifa) {
        super(codigo, nombreDestino, descripcion, municipio, tarifa);
        this.usuario = usuario;
    }

    public MDestinoFavorito(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
     
}
