package eu.personalnote;

import com.microsoft.azure.functions.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Event Grid trigger.
 */
public class EventGridTriggerJava1 {
    /**
     * This function will be invoked when an event is received from Event Grid.
     * 
     * @throws IOException
     */
    @FunctionName("EventGridTriggerJava1")
    public void run(@EventGridTrigger(name = "eventGridEvent") String message, final ExecutionContext context)
            throws IOException {

        URL url = new URL("https://5586-77-236-214-15.ngrok.io/api/HttpExample?name=" + message);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        context.getLogger().info("Java Event Grid trigger function executed with send GET.");
        context.getLogger().info(message);
    }
}
