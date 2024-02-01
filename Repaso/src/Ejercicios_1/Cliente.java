package Ejercicios_1;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost"; // o la dirección IP del servidor
        int port = 1234; // El mismo puerto que el servidor

        try {
            Socket echoSocket = new Socket(hostName, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            // Lee la respuesta del servidor
            String respuestaServidor = in.readLine();
            System.out.println("Respuesta del servidor: " + respuestaServidor);

            echoSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("No se conoce el host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener I/O para la conexión a " + hostName);
            System.exit(1);
        }
    }
}
