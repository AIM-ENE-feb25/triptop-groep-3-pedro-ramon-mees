package triptop.ese.wiremock;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import kong.unirest.core.json.JSONObject;

public class ImplementationMees {

    public void run() {
        try {
            HttpResponse<String> response = doLogin("edevries", "3g2Rw9sT1x");

            System.out.println("||||||||||||||||||||");
            System.out.println("Authentication response:");
            System.out.println(response.getBody());
            System.out.println("||||||||||||||||||||");

            if (response.getStatus() == 201) {
                JSONObject responseBody = new JSONObject(response.getBody());

                HttpResponse<String> authResponse = doAuthorisation(
                        "edevries",
                        responseBody.getJSONObject("token").getString("value")
                );

                System.out.println("||||||||||||||||||||");
                System.out.println("Authorization response:");
                System.out.println(authResponse.getBody());
                System.out.println("||||||||||||||||||||");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private HttpResponse<String> doLogin(String username, String password) throws UnirestException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        return Unirest.post("https://triptop-identity.wiremockapi.cloud/login")
                .header("Content-type", "application/json")
                .body(requestBody.toString())
                .asString();
    }

    private HttpResponse<String> doAuthorisation(String username, String token) throws UnirestException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("application", "triptop");

        return Unirest.post("https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token)
                .header("Content-type", "application/json")
                .body(requestBody.toString())
                .asString();
    }
}