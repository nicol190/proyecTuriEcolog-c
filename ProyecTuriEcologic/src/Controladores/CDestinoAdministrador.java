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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

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

}