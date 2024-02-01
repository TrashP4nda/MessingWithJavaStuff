package Enviar_Objeto;

import java.net.*;
import java.io.*;

public class Cliente_Objeto {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",1111);
   
        try {
        	ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
        	objectOutput.flush();
        	
        	Persona persona2 = new Persona("Juan","Pepez");
        	System.out.println("Enviando objeto al servidor");
        	objectOutput.writeObject(persona2);
        	objectOutput.flush();
        	
        	ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        	try {
        	    Persona persona3 = (Persona) objectInput.readObject();
        	    if (persona3 != null) {
        	        System.out.println(persona3.getNombre() + " " + persona3.getApellido());
        	    } else {
        	        System.err.println("Nothing Received");
        	    }
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	socket.close();
        }
    }
}
