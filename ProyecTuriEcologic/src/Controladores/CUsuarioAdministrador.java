package Controladores;

import Modelos.MDestinoAdministrador;
import Modelos.MDestinoTuristico;
import Modelos.MUsuario;
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
public class CUsuarioAdministrador {   
    private String url;
    private String urlTemp;
    private Path path;
    private String directoryName;
    
    public CUsuarioAdministrador() {
        this.path = Paths.get("");
        this.directoryName = path.toAbsolutePath().toString();
        this.url = this.directoryName + "/BASE DE DATOS PPI/UsuarioDocumento.txt";
        this.urlTemp = this.directoryName + "/BASE DE DATOS PPI/UsuarioDocumentoTemp.txt";
        
    }
    
    public LinkedList<MUsuario> getLista() throws FileNotFoundException, IOException {
        LinkedList<MUsuario> lista = new LinkedList();

        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {
            String record;
            
            while ((record = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(record, ",");
                MUsuario usuarioA  = new MUsuario(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken().substring(0,1), st.nextToken());
                usuarioA.setIsAdmin(st.nextToken().equals("1"));
                lista.add(usuarioA);
                
            }
        }

        return lista;

    }
    
    public boolean actualizarUsuario(String nombre, String apellido, String correo, String nombreUsuario, String telefono, boolean isAdmin) throws FileNotFoundException, IOException {
        File archivoEntrada = new File (this.url);
        File archivoTemporal = new File(this.urlTemp);

        BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada));
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal));
        
        String valorAdmin = isAdmin ? "1" : "0";
        
        String lineaActual;

        while((lineaActual = reader.readLine()) != null) {
            // Recortando linea a los extremos para comparla con linea a actualizar. 
            String lineaRecortada = lineaActual.trim();
            if(lineaRecortada.startsWith(nombre + "," + apellido)){//No se puede cambiar nombre ni apellido a menos que se use un id en la tabla.
                    String[] passwordArray = lineaActual.split(",");
                    String password = passwordArray[4];
                    String lineaAActualizar = nombre + "," + apellido + "," + correo + "," + nombreUsuario + "," + password + "," + telefono + "," + valorAdmin;
                    writer.write(lineaAActualizar + System.getProperty("line.separator"));
                    continue;
            } //Encuentra linea con ocurrencia y escribe el reemplazo en el archivo temporal.
            writer.write(lineaActual + System.getProperty("line.separator")); //linea + separador de linea.
        }
        writer.close(); 
        reader.close();
        archivoEntrada.delete();
        return archivoTemporal.renameTo(archivoEntrada); //Renombrando el archivo temporal al nombre del de entrada para reemplazarlo
    }
    
    public void agregarUsuario(MUsuario usuario) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.url, true));
            
            String isAdminString = usuario.isIsAdmin() ? "1" : "0";
            String linea = usuario.getNombre() + "," +
                    usuario.getApellido() + "," +
                    usuario.getCorreo() + "," +
                    usuario.getUsuario() + "," +
                    usuario.getTelefono() + "," +
                    usuario.getPassword() + "," +
                    isAdminString;
            
            bw.write(linea);
            bw.flush();
            bw.newLine();
            bw.close();
    }
    
    public boolean eliminarUsuario() {
        
        return false;
        
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

}