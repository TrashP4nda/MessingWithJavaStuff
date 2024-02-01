package Ejercicios_1;


import java.net.*;
import java.io.*;


public class Servidor {
    public static void main(String[] args) throws IOException {
        int port = 1234; // Puerto donde escucha el servidor
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado, escuchando en el puerto " + port);

        try {
            while (true) {
                // Acepta la conexión del cliente
                Socket clientSocket = serverSocket.accept();
                try {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("HTTP/1.1 200 OK"); // Envía el código 200
                } finally {
                    clientSocket.close();
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
