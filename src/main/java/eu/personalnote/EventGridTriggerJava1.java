package eu.personalnote;

import com.microsoft.azure.functions.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Event Grid trigger.
 */
public class EventGridTriggerJava1 {
    /**
     * This function will be invoked when an event is received from Event Grid.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @FunctionName("EventGridTriggerJava1")
    public void run(@EventGridTrigger(name = "eventGridEvent") String message, final ExecutionContext context)
            throws IOException, InterruptedException {

        // URL url = new URL("https://5586-77-236-214-15.ngrok.io/api/HttpExample?name=" + message);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://5586-77-236-214-15.ngrok.io/api/HttpExample?name=" + message))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        context.getLogger().info("Java Event Grid trigger function executed with send GET.");
        context.getLogger().info(message);
    }
}
