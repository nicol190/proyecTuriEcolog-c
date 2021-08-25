/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.MDestinoAdministrador;
import Modelos.MDestinoTuristico;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author pipe_
 */
public class CDestinoAdministrador {   
    private String url;
    private String urlTemp;
    private Path path;
    private String directoryName;
    public CDestinoAdministrador() {
        this.path = Paths.get("");
        this.directoryName = path.toAbsolutePath().toString();
        this.url = this.directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";
        this.urlTemp = this.directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDocTemp.txt";
        
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlTemp() {
        return urlTemp;
    }

    public void setUrlTemp(String urlTemp) {
        this.urlTemp = urlTemp;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
    
    public LinkedList<MDestinoTuristico> obtenerListaCola() throws FileNotFoundException, IOException {
        LinkedList<MDestinoTuristico> lista = new LinkedList();

        BufferedReader reader = new BufferedReader(new FileReader(url));
        String record;

        while ((record = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(record, ",");
            lista.add(new MDestinoTuristico(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken())));

        }

        reader.close();

        return lista;

    }
    
    public void agregarDestino(MDestinoAdministrador destino) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.url, true));
            
            String linea = destino.getCodigo() + "," +
                    destino.getNombreDestino() + "," +
                    destino.getDescripcion() + "," +
                    destino.getMunicipio() + "," +
                    destino.getTarifa();
            
            bw.write(linea);
            bw.flush();
            bw.newLine();
            bw.close();
    }
    
public boolean eliminarDestino(String codigo, String nombreDestino, String descripcion, String municipio, String tarifa) throws FileNotFoundException, IOException {
        File archivoEntrada = new File (this.url);
        File archivoTemporal = new File(this.urlTemp);

        BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada));
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal));

        String lineaABorrar = codigo + "," + nombreDestino + "," + descripcion + "," + municipio + "," + tarifa;
        String lineaActual;

        while((lineaActual = reader.readLine()) != null) {
            // Recortando linea a los extremos para comparla con linea a borrar. 
            String lineaRecortada = lineaActual.trim();
            System.out.println("A borrar:" + lineaABorrar);
            System.out.println("Noborrar:" + lineaRecortada);
            if(lineaRecortada.equals(lineaABorrar)){
                System.out.println("Test");
                    continue;
            } //Omite la linea con ocurrencia y no la escribe en el archivo temporal
            writer.write(lineaActual + System.getProperty("line.separator")); //Separador de lineas.
        }
        writer.close(); 
        reader.close();
        archivoEntrada.delete();
        return archivoTemporal.renameTo(archivoEntrada); //Renombrando el archivo temporal al nombre del de entrada para reemplazarlo
    }

}