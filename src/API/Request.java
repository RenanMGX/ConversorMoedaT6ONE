package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Request {
    public static HttpResponse get(String url, Map<String, String> headers) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();
        for (Map.Entry<String, String> entry : headers.entrySet()){
            requestBuilder.headers(entry.getKey(), entry.getValue());
        }
        HttpRequest request = requestBuilder.build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }
}
