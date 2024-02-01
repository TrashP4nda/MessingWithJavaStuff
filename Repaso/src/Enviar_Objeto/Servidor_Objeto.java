package Enviar_Objeto;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Servidor_Objeto {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(1111);
		System.out.println("Server Started");
		
		try {
		while(true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Cliente conectado: " + clientSocket.getRemoteSocketAddress());
			new ClientHandler(clientSocket).start();
				
		}}catch(Exception e) {
			
		}finally {
			serverSocket.close();
		}
	}
	
	
	
}

class ClientHandler extends Thread {
	private Socket clientSocket;
	
	public ClientHandler(Socket sock) {
		this.clientSocket = sock;
		
	}
	
	
	public void run() {
		try {
		ObjectInputStream objectInput = new ObjectInputStream(this.clientSocket.getInputStream());
		Persona persona = (Persona) objectInput.readObject();
		System.out.println("Recibido: " + persona.getNombre() + " " + persona.getApellido());
		
		if (persona.getNombre().equals("Juan")) {
			ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
			Persona persona2 = new Persona("Pepe","Juanez");
			objectOutput.writeObject(persona2);
			objectOutput.flush();
		}else {
			System.err.println("Nothing happening");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				clientSocket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
