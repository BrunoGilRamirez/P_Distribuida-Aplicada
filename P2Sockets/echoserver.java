package P2Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class echoserver extends Thread {
    protected Socket clientSocket;
    public echoserver(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private static int numeroDeVocales(String frase) {
		int res = 0;
		String fraseMin = frase.toLowerCase();

		for (int i = 0; i < fraseMin.length(); ++i) {
    		switch(fraseMin.charAt(i)) {
        		case 'a': case 'e': case 'i':
        		case 'o': case 'u': case 'A':
				case 'E': case 'I': case 'O':
				case 'U':
            		res++;
            		break;
        		default:
            		// se ignoran las dem�s letras
   			}
   		}
		return res;
	}
    public void run() {
			PrintWriter salHaciaCliente = null;
			BufferedReader entDesdeCliente = null;
			try{
				salHaciaCliente = new PrintWriter(clientSocket.getOutputStream(), true);
				entDesdeCliente = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			}catch (IOException e) {
				System.err.println(e);
				System.exit(-1);
			}

			// Contar las vocales de las frases enviadas por el cliente
			String inputLine = "";
			try{
				inputLine = entDesdeCliente.readLine();
				System.out.println("LISTO");
				
				while ((inputLine != null) && (!inputLine.equals("END OF SERVICE"))) {
					// Contar la cantidad de vocales que
					String respuesta = "'" + inputLine + "' has " + + numeroDeVocales(inputLine) + " vowels";
					salHaciaCliente.println(respuesta);
					inputLine = entDesdeCliente.readLine();
				}
				salHaciaCliente.close();
				clientSocket.close();
			}catch (IOException e) {
				System.err.println(e);
				System.exit(-1);
			}

			System.out.println("Bye, bye.");
	//}
    }
}
