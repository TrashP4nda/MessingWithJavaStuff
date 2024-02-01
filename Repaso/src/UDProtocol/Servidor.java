package UDProtocol;

import java.net.*;


public class Servidor {

	public static void main(String[] args)throws Exception {
		DatagramSocket socket = new DatagramSocket(1111);
		
		
		byte[] data = new byte[1024];
	try {
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(data,data.length);
			socket.receive(receivePacket);
			
			String mensaje = new String(receivePacket.getData(),0,receivePacket.getLength());
			System.out.println(mensaje);
		}}finally {
			socket.close();
		}
		
		
		
	}
	
	
	
	
	
	
}
