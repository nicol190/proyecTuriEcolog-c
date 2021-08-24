/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.MDestinoTuristico;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 *
 * @author pipe_
 */
public class CDestinoAdministrador {   
    private String url;
    private String urlTemp;

    public CDestinoAdministrador() {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";
        urlTemp = directoryName + "/Base de datos/DestinoTuristicoDoc.txt";
        
    }
    
    public LinkedList<MDestinoTuristico> obtenerLista() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(url));// Reader para obtener la lista de destinos en el futuro
       
        
        LinkedList<MDestinoTuristico> lista = new LinkedList();
       
        
        lista.add(new MDestinoTuristico(1, "Cañon del Rio Claro" , "Reserva natural", "Entre San Francisco Y sanson", 180000));
        lista.add(new MDestinoTuristico(2, "Rio Melcocho" , "cristalino para Rafting y senderismo", "Cocorná", 60000));
        lista.add(new MDestinoTuristico(3, "Páramo del sol" ,"lugar mas alto de Antioquia para senderismo", "Urrao", 70000));
        lista.add(new MDestinoTuristico(4, "Cerro Quitasol" ,"Cerro natural para senderismo", "Bello Antioquia", 25000));
        lista.add(new MDestinoTuristico(5, "Salto del Ángel" ,"Cascada natural para senderismo", "Envigado", 194000));
        lista.add(new MDestinoTuristico(6, "Los Saltos Ecoparque" , "Cascadas y nacimientos de agua", "Entre la Ceja y Abejorral", 120000));        
        
        return lista;
    
    }
   
}
