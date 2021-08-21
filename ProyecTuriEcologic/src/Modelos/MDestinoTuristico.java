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
public class MDestinoTuristico {
    private int codigo;
    private String nombreDestino;
    private String descripcion;
    private String municipio;
    private float tarifa;
    private String foto;

    public MDestinoTuristico(int codigo,String nombreDestino, String descripcion, String municipio, float tarifa,String foto) {
        this.codigo = codigo;
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.municipio = municipio;
        this.tarifa = tarifa;
        this.foto = foto;
    }
  

    public MDestinoTuristico() {
        
    } 

    public MDestinoTuristico(String cañon_del_Rio_Claro) {
    }

    public MDestinoTuristico(String cañon_del_Rio_Claro, String reserva_natural, String entre_San_Francisco_Y_sanson, String desde_180000) {
    }

    public MDestinoTuristico(String string, String cañon_del_Rio_Claro, String reserva_natural, String entre_San_Francisco_Y_sanson, String desde_180000) {
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

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

  

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
}
