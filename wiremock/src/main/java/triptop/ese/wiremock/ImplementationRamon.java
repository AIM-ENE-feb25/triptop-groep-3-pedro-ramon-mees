package triptop.ese.wiremock;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import kong.unirest.core.json.JSONObject;

public class ImplementationRamon {

    private final String username = "edevries";
    private final String password = "3g2Rw9sT1x";

    public void run() {
        System.out.println("ImplementationRamon");
        HttpResponse<String> token = getToken(username, password);
        assert token != null;
        checkPerms(token.toString(), "triptop");
    }

    private HttpResponse<String> getToken(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        try {
            return Unirest.post("https://triptop-identity.wiremockapi.cloud/login")
                    .header("Content-type", "application/json")
                    .body(requestBody.toString())
                    .asString();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void checkPerms(String token, String app) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("application", app);

        try {
            HttpResponse<String> res = Unirest.post("https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token)
                    .header("Content-type", "application/json")
                    .body(requestBody.toString())
                    .asString();

            System.out.println(res.getBody());
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }


    }

}
