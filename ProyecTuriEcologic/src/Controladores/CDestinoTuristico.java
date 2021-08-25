/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.MDestinoFavorito;
import Modelos.MDestinoTuristico;
import Modelos.MUsuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        urlTemp = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";

    }

    public Stack<MDestinoTuristico> obtenerListaPila() {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";
        urlTemp = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";
        Stack<MDestinoTuristico> pila = new Stack<>();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(url));
            String record;

            while ((record = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(record, ",");
                pila.add(new MDestinoTuristico(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), st.nextToken()));

            }

            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CDestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CDestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pila;

    }

    public Queue<MDestinoTuristico> obtenerListaCola() throws FileNotFoundException, IOException {
        Queue<MDestinoTuristico> cola = new LinkedList<>();

        BufferedReader br = new BufferedReader(new FileReader(url));
        String record;

        while ((record = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(record, ",");
            cola.add(new MDestinoTuristico(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), st.nextToken()));

        }

        br.close();

        return cola;

    }

    public MDestinoTuristico buscarDestinoTuristico(int codigo) throws IOException {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";
        urlTemp = directoryName + "/BASE DE DATOS PPI/DestinoTuristicoDoc.txt";

        BufferedReader br = new BufferedReader(new FileReader(url));// carga el archivo 
        String record;

        while ((record = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(record, ",");

            MDestinoTuristico destino = new MDestinoTuristico(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()), st.nextToken());

            if (destino.getCodigo() == codigo) {

                return destino;

            }

        }

        br.close();

        return null;

    }
    

    public boolean agregarDestinoFavorito(MDestinoFavorito destinos) throws IOException {
        
        boolean respuesta = false;

        String informacion = destinos.getCodigo() + "," + destinos.getUsuario();
        MDestinoFavorito favorito = buscarDestinoFavorito(destinos.getCodigo(),destinos.getUsuario());
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinosFavoritos.txt";
        urlTemp = directoryName + "/Base de datos/DestinosFavoritos.txt";
        if(favorito!=null){
            JOptionPane.showMessageDialog(null, "El destino ya esta agregado en la lista de favoritos ");
            
        }else{
            BufferedWriter bw = new BufferedWriter(new FileWriter(url, true));

            bw.write(informacion);
            bw.flush();
            bw.newLine();
            bw.close();
            respuesta = true;
            
        }
        
        
        return respuesta;
    }

    
    public Stack<MDestinoTuristico> obtenerListaPilaPorUsuario(String usuario) {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinosFavoritos.txt";
        urlTemp = directoryName + "/BASE DE DATOS PPI/DestinosFavoritosTem.txt";

        Stack<MDestinoTuristico> pila = new Stack<>();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(url));
            String record;

            while ((record = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(record, ",");
                MDestinoTuristico turistico = buscarDestinoTuristico(Integer.parseInt(st.nextToken()));
                if (turistico != null) {
                    MDestinoFavorito favoritos = new MDestinoFavorito(st.nextToken(), turistico.getCodigo(), turistico.getNombreDestino(), turistico.getDescripcion(), turistico.getMunicipio(), turistico.getTarifa(), turistico.getFoto());
                    if (favoritos.getUsuario().equals(usuario)) {
                        pila.add(turistico);

                    }
                }

            }

            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CDestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CDestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pila;

    }

    public boolean eliminarDestinoFavorito(String usuario, int codigo) {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinosFavoritos.txt";
        urlTemp = directoryName + "/BASE DE DATOS PPI/DestinosFavoritosTem.txt";
        ArrayList<MDestinoFavorito> listaFavoritos = new ArrayList();

        BufferedReader br;
        BufferedWriter bw;

        try {
            File db = new File(url);
            File tempDB = new File(urlTemp);

            br = new BufferedReader(new FileReader(url));
            bw = new BufferedWriter(new FileWriter(tempDB));
            String record;
            boolean eliminado = false;

            while ((record = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(record, ",");
                MDestinoTuristico turistico = buscarDestinoTuristico(Integer.parseInt(st.nextToken()));
                if (turistico != null) {
                    MDestinoFavorito favoritos = new MDestinoFavorito(st.nextToken(), turistico.getCodigo(), turistico.getNombreDestino(), turistico.getDescripcion(), turistico.getMunicipio(), turistico.getTarifa(), turistico.getFoto());
                    if (favoritos != null) {
                        if (favoritos.getUsuario().equals(usuario) && favoritos.getCodigo() == codigo) {
                            eliminado = true;
                            continue;

                        } else {
                            listaFavoritos.add(favoritos);
                        }

                    }

                }

                bw.write(record);
                bw.flush();
                bw.newLine();

            }

            br.close();
            bw.close();

            db.delete();
            tempDB.renameTo(db);

            if (eliminado == true) {
                JOptionPane.showMessageDialog(null, "Destino eliminado correctamente.");

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el Destino");
            }

            /*for (int i = 0; i < listaFavoritos.size(); i++) {
                agregarDestinoFavorito(listaFavoritos.get(i));
                
            }*/
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CDestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CDestinoTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public MDestinoFavorito buscarDestinoFavorito(int codigo, String usuario) throws IOException {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().toString();
        url = directoryName + "/BASE DE DATOS PPI/DestinosFavoritos.txt";

        BufferedReader br = new BufferedReader(new FileReader(url));// carga el archivo 
        String record;

        while ((record = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(record, ",");

            MDestinoTuristico turistico = buscarDestinoTuristico(Integer.parseInt(st.nextToken()));
            if (turistico != null) {
                MDestinoFavorito favorito = new MDestinoFavorito(st.nextToken(), turistico.getCodigo(), turistico.getNombreDestino(), turistico.getDescripcion(), turistico.getMunicipio(), turistico.getTarifa(), turistico.getFoto());
                if (favorito != null) {
                    if (favorito.getUsuario().equals(usuario) && favorito.getCodigo() == codigo) {
                        return favorito;

                    }

                }

            }
        }
        br.close();
        return null;
    }
 }
