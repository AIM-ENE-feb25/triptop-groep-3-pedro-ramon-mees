package triptop.ese.wiremock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WiremockApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiremockApplication.class, args);

        // Create an instance of the implementation class and call its method
        ImplementationMees implementation = new ImplementationMees();
        implementation.executeLoginAndAuthorisation();

        ImplementationRamon implementationRamon = new ImplementationRamon();
        implementationRamon.run();

        ImplementationPedro implementationPedro = new ImplementationPedro();
        implementationPedro.run();
    }
}
