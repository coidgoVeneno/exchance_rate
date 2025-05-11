package exachange.com.modelos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequester {

    public String getJsonResponse() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/654df65956487af786f2778b/latest/USD"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
           // System.out.println(response.body()); // Mostrar en consola (opcional)
            return response.body(); // Devolver el JSON como String
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }



}
