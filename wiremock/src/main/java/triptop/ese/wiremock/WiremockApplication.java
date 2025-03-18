package triptop.ese.wiremock;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WiremockApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiremockApplication.class, args);

        // Create an instance of the implementation class and call its method
        ImplementationMees implementation = new ImplementationMees();
        implementation.executeLoginAndAuthorisation();
    }
}
