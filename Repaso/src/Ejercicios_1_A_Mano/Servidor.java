package Ejercicios_1_A_Mano;

import java.net.*; import java.io.*;



public class Servidor {

	public static void main(String[] args) throws IOException {
		int puerto = 1111;
		ServerSocket servidorSocket = new ServerSocket(puerto);
		
		try {
		while(true) {
			Socket clientSocket = servidorSocket.accept();
			new ClientHandler(clientSocket).start();
			
			
		}
		}catch(Exception e) {
			System.err.println(e.getStackTrace());
		}finally {
			servidorSocket.close();
		}
		
	}

}


class ClientHandler extends Thread {
	
	private Socket clientSocket;
	
	public ClientHandler (Socket socket) {
		this.clientSocket = socket;
		
	}
	
	public void run() {
		
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			out.println("Funciona");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				clientSocket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
