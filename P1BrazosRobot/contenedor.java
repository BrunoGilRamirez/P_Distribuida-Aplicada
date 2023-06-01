package P1BrazosRobot;

public class contenedor {
    private int id;
    private int cantidad_contenido;
    public contenedor(int id, int cantidad_contenido) {
            this.id = id;
            this.cantidad_contenido = cantidad_contenido;
    }
    public int getId(){
        return id;
    }
    public int getContenido(){
        return cantidad_contenido;
    }
    public synchronized void Descargarpieza(){
        notifyAll();
        if (getContenido()<=0){
            System.out.println("El contenedor: "+id+" se ha quedado vacio\n");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cantidad_contenido--;
            

    }
    
}
