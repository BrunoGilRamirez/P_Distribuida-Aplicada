package pq;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebService(serviceName = "IOArchivoWebService")
public class IOArchivoWebService {
    private String path="/home/bruno/Documentos/textos/";

    private String nombreArchivo;

    @WebMethod(operationName = "cuentaLineas")
    public int cuentaLineas(@WebParam(name = "nombreArchivo") String nombreArchivo) {
        int contador = 0;
        String ruta= this.path + nombreArchivo;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while (br.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    @WebMethod(operationName = "cuentaVocales")
    public int cuentaVocales(@WebParam(name = "nombreArchivo") String nombreArchivo) {
        int contador = 0;
        String ruta= this.path + nombreArchivo;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contador += contarVocales(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    private int contarVocales(String linea) {
        int contador = 0;
        String ruta= this.path + nombreArchivo;
        for (char c : linea.toLowerCase().toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contador++;
            }
        }
        return contador;
    }

    @WebMethod(operationName = "escribe")
    public boolean escribe(@WebParam(name = "nombreArchivo") String nombreArchivo, @WebParam(name = "texto") String texto) {
        boolean r =false;
        String ruta= this.path + nombreArchivo;
        try {
            OutputStream os = new FileOutputStream(ruta,true); //Crea el archivo
            Writer osWriter = new OutputStreamWriter(os); //Escritor del archivo
            osWriter.write(texto);
            osWriter.close();
            os.close();
            r=true;
        } catch(IOException e){
            e.printStackTrace();
        }
        return r;
    }

    @WebMethod(operationName = "imprimir")
    public String imprimir(@WebParam(name = "nombreArchivo") String nombreArchivo) {
        String texto="";
        String ruta= this.path + nombreArchivo;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) texto+=linea+"\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }

    @WebMethod(operationName = "respaldar")
    public boolean respaldar(@WebParam(name = "nombreArchivo") String nombreArchivo) {
        String ruta= this.path + nombreArchivo;
        boolean r=false;
         try{
            //Creación del archivo de respaldo
            File original = new File(ruta);
            File respaldo = new File((this.path+"Respaldo.txt"));
            InputStream inputStream = new FileInputStream(original);
            OutputStream outputStream = new FileOutputStream(respaldo);
            //Respaldo del contenido
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            r=true;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
         return r;
    }

    @WebMethod(operationName = "copiar")
    public boolean copiar(@WebParam(name = "nombreArchivo") String nombreArchivo, @WebParam(name = "nombreArchivoDestino") String nombreArchivoDestino) {
        String ruta= this.path + nombreArchivo;
        String ruta1= this.path + nombreArchivoDestino;
        boolean r=false;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ruta1,true))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
            bw.flush();
            r=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    @WebMethod(operationName = "renombrar")
    public boolean renombrar(@WebParam(name = "nombreArchivo") String nombreArchivo, @WebParam(name = "nombreArchivoNuevo") String nombreArchivoNuevo) {
        String ruta= this.path + nombreArchivo;
        String ruta1= this.path + nombreArchivoNuevo;
        boolean r=false;
        File archivoActual = new File(ruta);
        File archivoNuevo = new File(ruta1);
        if (archivoActual.renameTo(archivoNuevo)) {
            nombreArchivo = nombreArchivoNuevo;
            r=true;
        }
        return r;
    }

    @WebMethod(operationName = "eliminar")
    public boolean eliminar(@WebParam(name = "nombreArchivo") String nombreArchivo) {
        String ruta= this.path + nombreArchivo;
        File archivo = new File(ruta);
        boolean r=false;
        if (archivo.delete()) {
            r=true;
        } 
        return r;
    }
    @WebMethod(operationName = "obtenerNombresArchivos")
    public String obtenerNombresArchivos() {
        File directorio = new File(path);
        File[] archivos = directorio.listFiles();
        StringBuilder nombres = new StringBuilder();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    nombres.append(archivo.getName()).append("\n");
                }
            }
        }
        return nombres.toString();
    }

}

