package Servicios_HTTP_Ejemplo;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Hacemos uso de los recursos de la RAE para consultar el significado de las
 * palabras. El interfaz de su web es un formulario simple. Por ejemplo, para
 * buscar "patata" basta con solicitar el recurso https://dle.rae.es/patata
 * Este tipo de peticion es de tipo GET
 * 
 * Este ejemplo hace peticiones a la RAE y muestra por consola la pagina html de
 * la respuesta
 */
public class AccesoRAE {

	private static final String SCHEMA = "https://";
	private static final String SERVER = "dle.rae.es";

	public static void main(String[] args) {
		String palabraABuscar = "patata";

		try {
			// Montamos la direccion del recurso
			String resource = URLEncoder.encode(palabraABuscar, StandardCharsets.UTF_8.name());
			String direccion = SCHEMA + SERVER + "/" + resource;

			// Contruimos y abrimos la conexion
			URL url = new URL(direccion);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Conetent-type", "text/plain");
			httpURLConnection.setRequestProperty("charset", "utf-8");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

			// Envio de la peticion y procesamiento del codigo de respuesta HTTP
			int codigoRespuestaHttp = httpURLConnection.getResponseCode();

			// SI es 200 - OK
			StringBuilder respuesta = new StringBuilder();
			if (codigoRespuestaHttp == HttpURLConnection.HTTP_OK) {
				Reader reader = new InputStreamReader(httpURLConnection.getInputStream());

				int myByte;
				while ((myByte = reader.read()) != -1) {
					respuesta.append((char) myByte);
				}
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

			httpURLConnection.disconnect();

			// Mostramos la respuesta...
			System.out.println(respuesta.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
