import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
public static void main(String[] args) {
    try {
        // Tu API Key y URL de la API
        String apiKey = "a34c4c715519a5ea0a67bb21";
        String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        // Crear un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear la solicitud GET con la URL de la API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .GET()
                .build();

        // Realizar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Obtener el cuerpo de la respuesta como un String
        String responseBody = response.body();

        // Parsear la respuesta JSON con Gson
        JsonObject myResponse = JsonParser.parseString(responseBody).getAsJsonObject();

        // Obtener las tasas de conversión desde la respuesta
        JsonObject conversionRates = myResponse.getAsJsonObject("conversion_rates");

        // Crear un objeto Scanner para que el usuario ingrese la cantidad
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese la cantidad de dinero en USD
        System.out.print("Ingrese la cantidad en USD: ");
        double amountInUSD = scanner.nextDouble();

        // Convertir la cantidad ingresada a diferentes divisas
        System.out.println("\nTasas de cambio para " + amountInUSD + " USD:");

        // Ejemplo: convertir a EUR, GBP, JPY
        double eur = conversionRates.get("EUR").getAsDouble();
        double gbp = conversionRates.get("GBP").getAsDouble();
        double jpy = conversionRates.get("JPY").getAsDouble();

        System.out.println("EUR: " + (amountInUSD * eur));
        System.out.println("GBP: " + (amountInUSD * gbp));
        System.out.println("JPY: " + (amountInUSD * jpy));

        // Puedes añadir más monedas aquí si lo deseas

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
