package Enviar_Objeto_Encolar;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Servidor {
	
	private static ConcurrentLinkedQueue<Persona> colaDeTareas = new ConcurrentLinkedQueue<>();;
	
	public static void main(String[] args) throws IOException {
		ServerSocket sockete = new ServerSocket(1111);
		
		
		
		try {
			while(true) {
				Socket clientSocket = sockete.accept();
				new ClientHandler(clientSocket).start();
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sockete.close();
		}
	}
	  public static void encolarTarea(Persona persona) {
	        colaDeTareas.add(persona);
	        
	    }
	  
	  public static void procesarTareas() {
	        while (!colaDeTareas.isEmpty()) {
	            Persona persona = colaDeTareas.poll(); 
	           
	        }
	    }

}



class ClientHandler extends Thread{
	private Socket clientSocket;
	
	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}
	
	public void run() {
		try {
		ObjectInputStream objectInput = new ObjectInputStream(this.clientSocket.getInputStream());
		Persona persona = (Persona) objectInput.readObject();
		
		Servidor.encolarTarea(persona);
		
		ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        objectOutput.writeObject("OK");
        objectOutput.flush();
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
