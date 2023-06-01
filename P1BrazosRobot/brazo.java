package P1BrazosRobot;

import java.util.ArrayList;
import java.util.List;

public class brazo implements Runnable {
    private int id;
    public int cantidad_desc;
    public List<contenedor> contenedor;
    public brazo(int id, int cantidad_desc) {
            this.id = id;
            this.cantidad_desc = cantidad_desc;
            contenedor = new ArrayList<contenedor>();
    }
     public void asignaContenedor(contenedor contenedor) {
        this.contenedor.add(contenedor); 
    }
    public void ListaContenedor (){
        for(int i=0; i<contenedor.size();i++){
            System.out.println(contenedor.get(i).getId());
        }
    }
    public int getId(){
        return this.id;
    }
    public int getCantidad(){
        return cantidad_desc;
    }
    @Override
    public void run() {
        
       for(int i = 1; i <=cantidad_desc;i++){
            for (int j = 0; j<contenedor.size();j++){
                if (contenedor.get(j).getContenido()>0){
                    contenedor.get(j).Descargarpieza();
                    System.out.println("el brazo: "+this.id+" descargo: "+i+" Contenido restante: "+contenedor.get(j).getContenido()+" ID Contenedor: "+contenedor.get(j).getId());
                }
            }
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nBrazo: "+ this.id+" termino.\n");
        for (int j = 0; j<contenedor.size();j++){
            if (contenedor.get(0).getContenido()<=0)
            System.out.println("El contenedor se ha quedado vacio");
        }
    }

}