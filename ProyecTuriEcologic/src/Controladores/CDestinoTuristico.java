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
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author pipe_
 */
public class CDestinoTuristico {
    private String url;
    private String urlTemp;

    public CDestinoTuristico() {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";
        urlTemp = directoryName + "/Base de datos/DestinoTuristicoDoc.txt";
        
    }
    
    public Stack<MDestinoTuristico> obtenerListaPila() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(url));
       
        
        Stack<MDestinoTuristico> pila = new Stack<>();
       
        
        pila.add(new MDestinoTuristico("1","Cañon del Rio Claro" , "Reserva natural", "Entre San Francisco Y sanson", "desde 180.000"));
        pila.add(new MDestinoTuristico("2","Rio Melcocho" , "cristalino para Rafting y senderismo", "Cocorná", "desde 60.000"));
        pila.add(new MDestinoTuristico("3","Páramo del sol" ,"lugar mas alto de Antioquia para senderismo", "Urrao", "desde 70.000 "));
        pila.add(new MDestinoTuristico("4","Cerro Quitasol" ,"Cerro natural para senderismo", "Bello Antioquia", "desde 25.000"));
        pila.add(new MDestinoTuristico("5","Salto del Ángel" ,"Cascada natural para senderismo", "Envigado", "desde 194.000"));
        pila.add(new MDestinoTuristico("6","Los Saltos Ecoparque" , "Cascadas y nacimientos de agua", "Entre la Ceja y Abejorral", "desde 120.000"));
        MDestinoTuristico cachar = pila.pop();
        
        
        return pila;
    
    }
    
    public Stack<MDestinoTuristico> obtenerListaCola() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(url));
        
        Queue<MDestinoTuristico> cola = new LinkedList<>();
        
        cola.add(new MDestinoTuristico("1", "Cañon del Rio Claro", "Reserva natural", "Entre San Francisco Y sanson", "desde 180.000"));
        cola.add(new MDestinoTuristico("2", "Rio Melcocho", "cristalino para Rafting y senderismo", "Cocorná", "desde 60.000"));
        cola.add(new MDestinoTuristico("3", "Páramo del sol", "lugar mas alto de Antioquia para senderismo", "Urrao", "desde 70.000 "));
        cola.add(new MDestinoTuristico("4", "Cerro Quitasol", "Cerro natural para senderismo", "Bello Antioquia", "desde 25.000"));
        cola.add(new MDestinoTuristico("5", "Salto del Ángel", "Cascada natural para senderismo", "Envigado", "desde 194.000"));
        cola.add(new MDestinoTuristico("6", "Los Saltos Ecoparque", "Cascadas y nacimientos de agua", "Entre la Ceja y Abejorral", "desde 120.000"));

        return (Stack<MDestinoTuristico>) cola;
    }
   
}
