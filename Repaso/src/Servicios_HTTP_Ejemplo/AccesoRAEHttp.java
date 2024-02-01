package Servicios_HTTP_Ejemplo;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
/**
 * Hacemos uso de los recursos de la RAE para consultar el significado de las
 * palabras. El interfaz de su web es un formulario simple. Por ejemplo, para
 * buscar "patata" basta con solicitar el recurso https://dle.rae.es/patata.
 * Este tipo de peticion es de tipo GET
 * 
 * Este ejemplo hace peticiones a la RAE y muestra por consola la pagina html de
 * la respuesta
 */
public class AccesoRAEHttp {

	private static final String SCHEMA = "https://";
	private static final String SERVER = "dle.rae.es";

	public static void main(String[] args) {
		String palabraABuscar = "cosa";

		try {
			// Montamos la direccion del recurso
			String resource = URLEncoder.encode(palabraABuscar, StandardCharsets.UTF_8.name());
			String direccion = SCHEMA + SERVER + "/" + resource;

			// Cliente Http
			HttpClient httpClient = HttpClient
					.newBuilder()
					.version(HttpClient.Version.HTTP_1_1)
					.followRedirects(HttpClient.Redirect.NORMAL)
					.build();

			// La peticion GET
			HttpRequest httpRequest = HttpRequest
					.newBuilder()
					.GET()
					.uri(URI.create(direccion))
					.headers("Conetent-type", "text/plain")
					.setHeader("User-Agent", "Mozilla/5.0").build();

			// Envio de la peticion y procesamiento del codigo de respuesta HTTP
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

			// Procesamiento del codigo de respuesta HTTP
			int codigoRespuestaHttp = httpResponse.statusCode();

			// SI es 200 - OK
			if (codigoRespuestaHttp == HttpURLConnection.HTTP_OK) {
				// Mostramos la respuesta...
				System.out.println(httpResponse.body());

			} else {
				// Procesa los distintos codigos...
				switch (codigoRespuestaHttp) {

				case HttpURLConnection.HTTP_INTERNAL_ERROR:
					System.out.println("Servidor respondio: Internal Server Error");
					break;
				case HttpURLConnection.HTTP_NOT_FOUND:
					System.out.println("Servidor respondio: Not Found");
					break;
				default:
					System.out.println("Servidor respondio: HTTP Code Error : " + codigoRespuestaHttp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
