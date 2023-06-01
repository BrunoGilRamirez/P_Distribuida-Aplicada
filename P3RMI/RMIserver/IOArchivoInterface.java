package P3RMI.RMIserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.OutputStream;

public interface IOArchivoInterface extends Remote{
    public int cuentaLineas(String nombreArchivo) throws RemoteException;
    public int cuentaVocales(String nombreArchivo) throws RemoteException;
    public void escribe(OutputStream os, String texto, String nombreArchivo) throws RemoteException;
    public String imprimir(String nombreArchivo) throws RemoteException;
    public void respaldar(String nombreArchivo) throws RemoteException;
    public void copiar(String nombreArchivo, String nombreDestino) throws RemoteException;
    public void renombrar(String nombreArchivo, String nombreNuevo) throws RemoteException;
    public void eliminar(String nombreArchivo) throws RemoteException;
}