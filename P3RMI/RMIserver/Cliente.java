package P3RMI.RMIserver;

import java.rmi.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

public class Cliente{
    static String nombreArchivo;
    public static void main (String[] argv) {
        int op; //Opciones del menu
        String cadena; //Texto a leer o bien nombre de un segundo archivo
        OutputStream escribeArchivo = null;
        Scanner input = new Scanner(System.in);

        try {
            //Conecta con la clase del servidor y la interface
            IOArchivoInterface servicioArchivo = (IOArchivoInterface) Naming.lookup ("//172.18.106.144/Archivo");
            do{
                System.out.println("\n************************************");
                System.out.println("**********Elija una opcion**********");
                System.out.println("************************************");
                System.out.println("1) Contar lineas de un archivo");
                System.out.println("2) Contar las vocales de un archivo");
                System.out.println("3) Escribir contenido de un archivo");
                System.out.println("4) Imprimir contenido de un archivo");
                System.out.println("5) Respaldar archivo");
                System.out.println("6) Copiar archivo");
                System.out.println("7) Renombrar archivo");
                System.out.println("8) Eliminar archivo");
                System.out.println("0) Salir");
                System.out.println("************************************");
                System.out.print("su opcion: ");
                try {
                    op = input.nextInt(); 
                } catch (InputMismatchException e) {
                    op = 99; //Una opción invalida
                }
                input.nextLine(); //Evita que se salte la lectura del siguiente input
                switch(op){
                    case 1: //Cuenta lineas
                        leerNombre(input);
                        System.out.println("\n   El numero de lineas que tiene el archivo es: " + servicioArchivo.cuentaLineas(nombreArchivo));
                    break;
                    case 2: //Cuenta vocales
                        leerNombre(input);
                        System.out.println("\n   El numero de vocales que tiene el archivo es: " + servicioArchivo.cuentaVocales(nombreArchivo));
                    break;
                    case 3: //Escribe archivo
                        leerNombre(input);
                        System.out.println("Introduzca el texto que desea escribir:");
                        cadena = input.nextLine();
                        servicioArchivo.escribe(escribeArchivo, cadena, nombreArchivo); 
                    break;
                    case 4: //Imprime contenido
                        leerNombre(input);
                        System.out.println("\n" + servicioArchivo.imprimir(nombreArchivo));
                    break;
                    case 5: //Respalda archivo
                        leerNombre(input);
                        servicioArchivo.respaldar(nombreArchivo);
                    break;
                    case 6: //Copia contenido de archivo a otro
                        leerNombre(input);
                        System.out.println("Introduzca el nombre del archivo al cual pasar el contenido:");
                        cadena = input.nextLine();
                        servicioArchivo.copiar(nombreArchivo, cadena);
                    break;
                    case 7: //Renombra archivo
                        leerNombre(input);
                        System.out.println("Introduzca el nuevo nombre para el archivo:");
                        cadena = input.nextLine();
                        servicioArchivo.renombrar(nombreArchivo, cadena);
                    break;
                    case 8: //Elimina archivo
                        leerNombre(input);
                        servicioArchivo.eliminar(nombreArchivo);
                    break;
                    case 0:
                        System.out.println("\n###Ha salido del programa###\n");
                    break;
                    default:
                        System.out.println("\n###Opcion invalida###\n");
                }
                input.nextLine(); //Espera 10 segundos para que el servidor pueda terminar de ejecutar la operacion
            
            }while (op != 0);
        } catch (Exception e) {
            System.out.println (e);
        }
    }

    // Lectura del nombre del archivo
    public static void leerNombre(Scanner input){
        System.out.println("Introduzca el nombre del archivo: ");
        nombreArchivo = input.nextLine();
        nombreArchivo+= ".txt";
    }
}
