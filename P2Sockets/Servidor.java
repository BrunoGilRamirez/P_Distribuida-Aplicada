package P2Sockets;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Servidor{
	private static ServerSocket creaListenSocket(int serverSockNum){
		ServerSocket server = null;

		try{
    		server = new ServerSocket(serverSockNum);
  		} catch (IOException e) {
   			System.err.println("Problems in port: " + serverSockNum);
   			System.exit(-1);
   		}
   		return server;
  	}
  	
	private static Socket creaClientSocket(ServerSocket server){
  		Socket res = null;
  		try {
			res = server.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		return res;
  	}

  	//Devuelve la cantidad de vocales de la frase

	public static void main(String[] args) {
	    int SERVER_PORT = 1024;
		ServerSocket serverSocket = null; 	//para escuchar
		Socket clientSocket = null;       	//uno por cliente
		serverSocket = creaListenSocket(SERVER_PORT);
		while (true) {
			clientSocket = creaClientSocket(serverSocket);
			new echoserver(clientSocket).start();
			
		}
	}

}