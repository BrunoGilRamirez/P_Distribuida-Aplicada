package P3RMI.RMIserver;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

public class IOArchivo extends UnicastRemoteObject implements IOArchivoInterface{
    public IOArchivo() throws RemoteException{}

    //Contador de lineas del archivo
    public int cuentaLineas(String nombreArchivo){
        int nLineas = 0;
        try{
            BufferedReader br = abrirArchivo(nombreArchivo);
            while(br.readLine() !=null){ //Mientras no encuentre vacío
                nLineas++;
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return nLineas;
    }

    //Contador de vocales del archivo
    public int cuentaVocales(String nombreArchivo) throws RemoteException{
        int numeroVocales = 0;
        String texto;
        try{
            BufferedReader br = abrirArchivo(nombreArchivo);
            while((texto = br.readLine()) !=null){ //Mientras haya lineas por leer
                for (char caracter : texto.toLowerCase().toCharArray()){
                    switch (caracter){
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                            numeroVocales++;
                        break;
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
        return numeroVocales;
    }

    //Escribe el contenido del archivo
    public void escribe(OutputStream os, String texto, String nombreArchivo) throws RemoteException{
        try {
            os = new FileOutputStream(nombreArchivo); //Crea el archivo
            Writer osWriter = new OutputStreamWriter(os); //Escritor del archivo
            osWriter.write(texto);
            osWriter.close();
            os.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Escribe en patalla el contenido del archivo
    public String imprimir(String nombreArchivo) throws RemoteException{
        String texto, retorno = ""; // Linea que se lee, el texto completo a retornar
        try{
            BufferedReader br = abrirArchivo(nombreArchivo);
            while((texto = br.readLine()) !=null)retorno += texto + "\n"; //Mientras haya lineas por leer
            br.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    //Respalda un archivo en otro con un nombre por default
    public void respaldar(String nombreArchivo) throws RemoteException{
        try{
            //Creación del archivo de respaldo
            File original = new File(nombreArchivo);
            File respaldo = new File("Respaldo.txt");
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
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Copia contenido de un archivo a otro
    public void copiar(String nombreArchivo, String nombreDestino) throws RemoteException{
        FileWriter destino = null;
        PrintWriter pw = null;
        String texto;
        try{
            //Creación del archivo o tomando en cuenta el archivo destino
            destino = new FileWriter(nombreDestino + ".txt",true);
            pw = new PrintWriter(destino);
            //Copiando el texto del archivo origen al destino
            BufferedReader br = abrirArchivo(nombreArchivo);
            while( (texto = br.readLine()) != null)pw.println(texto);
            destino.close();
            pw.close();
            br.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Renombra un archivo ya existente
    public void renombrar(String nombreArchivo, String nombreNuevo) throws RemoteException{
        File original = new File(nombreArchivo);
    	File nuevo = new File(nombreNuevo + ".txt");
        original.renameTo(nuevo);
    }

    //Elimina un archivo específico
    public void eliminar(String nombreArchivo) throws RemoteException{
        File archivo = new File(nombreArchivo);
        archivo.delete();
    }

    //Lectura del texto del archivo en forma de buffer
    private BufferedReader abrirArchivo(String nombreArchivo) throws FileNotFoundException{
        File archivo = new File(nombreArchivo);
        FileReader fr = new FileReader(archivo);
        return new BufferedReader(fr);
    }
}
