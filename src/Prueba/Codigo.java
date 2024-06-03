package Prueba;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Codigo {
    public static void main(String[] args) {
        System.out.println("Indica la película a buscar");
        var pokemon = new Scanner(System.in).nextLine();
        String direccion = "http://www.omdbapi.com/?apikey=b55f6168&t=" + ((String) pokemon).toLowerCase();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        Pelicula pelicula = gson.fromJson(response.body(), Pelicula.class);
        System.out.println("El nombre de la película es:"+pelicula);
    }
}
