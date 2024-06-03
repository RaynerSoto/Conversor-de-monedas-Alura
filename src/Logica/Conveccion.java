package Logica;

import Clases.Conversion;
import com.google.gson.Gson;
import Clases.Moneda;
import Clases.MoneyValue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Conveccion {
    private Gson gson = new Gson();
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request ;

    public TreeMap<String,Double> listado_monedas(String mone) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create("https://v6.exchangerate-api.com/v6/34df65c2a3ed651307ffcb62/latest/"+mone))
                .build();
        ArrayList<String> listado_monedas = new ArrayList<>();
        HttpResponse response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        Moneda moneda = gson.fromJson((String) response.body(), Moneda.class);
        return moneda.conversion_rates();
    }

    public TreeMap<String,String> claveValorMoney()throws IOException, InterruptedException{
        request = HttpRequest
                .newBuilder(URI.create("https://v6.exchangerate-api.com/v6/34df65c2a3ed651307ffcb62/codes"))
                .build();
        TreeMap<String,String> listado_monedas = new TreeMap<>();
        HttpResponse response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        MoneyValue moneda = gson.fromJson((String) response.body(), MoneyValue.class);
        for (Map.Entry<String, String> elemento : moneda.supported_codes().entrySet()) {
            listado_monedas.put(elemento.getKey(),elemento.getValue());
        }
        return listado_monedas;
    }

    public double cambio(String moneda1, String moneda2, double cantidad)throws IOException, InterruptedException{
        request = HttpRequest
                .newBuilder(URI.create("https://v6.exchangerate-api.com/v6/34df65c2a3ed651307ffcb62/pair/"+moneda1+"/"+moneda2))
                .build();
        HttpResponse response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        Conversion moneda = gson.fromJson((String) response.body(), Conversion.class);
        return moneda.conversion_rate() * cantidad;
    }
}
