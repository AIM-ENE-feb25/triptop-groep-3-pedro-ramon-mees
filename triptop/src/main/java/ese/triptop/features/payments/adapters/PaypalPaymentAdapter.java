package ese.triptop.features.payments.adapters;

import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;
import kong.unirest.core.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Component
public class PaypalPaymentAdapter implements IPaymentAdapter {

    private final HttpClient httpClient;

//    @Value("${paypal.api.clientId}")
    private String CLIENT_ID = "AZbz5SEQYIruKXMBnsCihee1z7Xj-otJvFp76y-ZwZTM-WsGh6eXx88BJbwUnZVxRq_XGIpqHbviz4iI";
//    @Value("${paypal.api.secret}")
    private String SECRET = "ED6q1XxLtqDeRZ35hRCoFnJpij2VenXytAyB2IwqqTX26WmtSlCwTTXKHRj4pJ7r8bt_go-tcs28rTeG";

    private final String encodedCredentials;

    public PaypalPaymentAdapter() {
        this.httpClient = HttpClient.newHttpClient();
        encodedCredentials = Base64.getEncoder().encodeToString((CLIENT_ID + ":" + SECRET).getBytes());
    }

    @Override
    public InvoiceResponse processInvoicePayment(InvoiceRequest invoiceRequest) {
        String url = "https://api-m.sandbox.paypal.com/v2/invoicing/invoices";

        JSONObject payload = new JSONObject()
                .put("detail", new JSONObject()
                        .put("currency_code", invoiceRequest.currency())
                        .put("note", "Invoice for your purchase"))
                .put("amount", new JSONObject()
                        .put("value", invoiceRequest.amount()));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getAccessToken())
                .header("Prefer", "return=representation")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                JSONObject jsonResponse = new JSONObject(response.body());
                System.out.println(jsonResponse);
                return new InvoiceResponse(
                        jsonResponse.getString("id"),
                        jsonResponse.getJSONObject("detail").getString("currency_code"),
                        jsonResponse.getJSONObject("amount").getString("value"),
                        "PayPal");
            } else {
                System.out.println("Paypal Payment Adapter Error------------------------------------------");
                System.out.println("Failed to create invoice: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Paypal Payment Adapter Error------------------------------------------");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isAvailable() {
        // Check if PayPal is available
        String accessToken = getAccessToken();
        return accessToken != null;
    }

    private String getAccessToken() {
        String url = "https://api.sandbox.paypal.com/v1/oauth2/token";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + encodedCredentials)
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        try {
            // Send the request and handle the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parse the response to extract the access token
                JSONObject json = new JSONObject(response.body());
                if (json.has("access_token")) {
                    return json.getString("access_token");
                }
            } else {
                System.out.println("Paypal Payment Adapter Error------------------------------------------");
                System.out.println("Failed to get access token: " + response.body());
                return null;
            }

        } catch (Exception e) {
            System.out.println("Paypal Payment Adapter Error------------------------------------------");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
