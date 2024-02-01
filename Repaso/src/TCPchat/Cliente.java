package TCPchat;
import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {
	public static void main(String[] args) throws IOException {
		Socket clientSocket = new Socket("localhost",1111);
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
			
			
			   while (true) {
	                String input = scanner.nextLine();
	                System.err.println(input);
	                if (input.equals("*")) {
	                    break;
	                }
	                objectOutput.writeObject(input);
	                objectOutput.flush();
	            }
			
			objectOutput.writeObject("Terminando conexión");
			
			
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			clientSocket.close();
		}
	}
}
