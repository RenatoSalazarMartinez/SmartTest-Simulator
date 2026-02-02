package API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {
    private static final String BASE_URL = "https://opentdb.com/api.php?amount=10&type=multiple";

    public String obtenerJsonDeAPI() {
        try {
            // 1. El Cliente: Nuestro "navegador" interno 🌐
            HttpClient client = HttpClient.newHttpClient();

            // 2. La Petición: ¿A dónde vamos y qué pedimos? 📝
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            // 3. La Respuesta: Enviamos y esperamos el texto
            // BodyHandlers.ofString() le dice a Java que queremos el resultado como texto plano
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Devolvemos el cuerpo de la respuesta (el JSON)
            return response.body();

        } catch (Exception e) {
            // Si no hay internet o la URL está mal, atrapamos el error aquí 
            System.out.println("Error en la conexión: " + e.getMessage());
            return null;
        }
    }
}