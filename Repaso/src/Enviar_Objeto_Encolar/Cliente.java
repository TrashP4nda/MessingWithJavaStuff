package Enviar_Objeto_Encolar;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
	
	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket("localhost",1111);
		
		try {
			
			ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
			objectOutput.flush();
			
			Persona persona = new Persona("Juan","Pepez");
			
			objectOutput.writeObject(persona);
			
			objectOutput.flush();
			
			ObjectInputStream objectInput = new ObjectInputStream (clientSocket.getInputStream());
			
			System.out.println(objectInput.readObject());
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
