package UDProtocol;

import java.net.*;

public class Cliente {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket();
		
		
		InetAddress IP = InetAddress.getByName("localhost");
		int port = 1111;
		try {
		for (int i = 0; i < 1000; i++) {
			String mensaje = "Mensaje :" + i;
			byte[] sendData = mensaje.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IP,port);
			socket.send(sendPacket);
		}}finally {
			socket.close();
		}
		
	}
}
