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
public class MDestinoAdministrador {
    private int codigo;
    private String nombreDestino;
    private String descripcion;
    private String municipio;
    private int tarifa;
    

    public MDestinoAdministrador(int codigo,String nombreDestino, String descripcion, String municipio, int tarifa) {
        this.codigo = codigo;
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.municipio = municipio;
        this.tarifa = tarifa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

  

  
    
    
    
}
