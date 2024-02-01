package Ejercicios_1_A_Mano;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",1111);
        
        
        try {
        	ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	socket.close();
        }
    }
}
