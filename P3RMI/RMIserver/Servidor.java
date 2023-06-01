package P3RMI.RMIserver;

import java.rmi.*;

public class Servidor{
    public static void main (String[] argv){
      System.out.println("Desplegando servidor");
        try{
          Naming.rebind("Archivo", new IOArchivo()); //Naming es una clase de java.rmi, rebind es un metodo de la clase Naming,que hace que el objeto se pueda encontrar en el registro de objetos remotos
          System.out.println ("Servidor desplegado");
        } catch(Exception e){
          System.out.println ("Servidor fallido: " + e);
        }
    }
}
