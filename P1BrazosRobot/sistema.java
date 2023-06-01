package P1BrazosRobot;
public class sistema {
    public static void main(String[] args){
        contenedor contenedor1= new contenedor(123, 50);
        contenedor contenedor2= new contenedor(122,30);
        brazo brazo1 = new brazo(100,30);
        brazo brazo2 = new brazo(202,20);
        brazo1.asignaContenedor(contenedor1);
        brazo1.asignaContenedor(contenedor2);
        brazo2.asignaContenedor(contenedor1);
        brazo2.asignaContenedor(contenedor2);
        brazo1.ListaContenedor(); 
        Thread Procbrazo1 = new Thread(brazo1);
        Thread Probrazo2= new Thread (brazo2);
        Procbrazo1.start();
        Probrazo2.start();
        

    }
    
}
