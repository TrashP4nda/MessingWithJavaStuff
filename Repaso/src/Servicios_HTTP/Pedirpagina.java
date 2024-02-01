package Servicios_HTTP; 

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;

public class Pedirpagina {
	
	private static final String schema = "https://";
	private static final String server = "imdb.com";
	
	public static void main(String[] args) {
		String palabra = "Star wars";
		
		try {
			
				String resource = URLEncoder.encode(palabra,StandardCharsets.UTF_8.name());
				String direccion = schema + server + "/find?q=" + resource;
				
				
				URL url = new URL(direccion);
				
				HttpClient cliente = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).followRedirects(HttpClient.Redirect.NORMAL).build();
				HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(direccion)).headers("Conetent-type","text/plain").setHeader("User-Agent", "Mozilla/5.0").build();
				
				
				HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
				
				int code = respuesta.statusCode();
				
				if (code == 200) {
					BufferedWriter writer = new BufferedWriter(new FileWriter("index.html"));
		            writer.write(respuesta.body());
		            writer.close();
				}
			
			
			
			
			
		
			
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
