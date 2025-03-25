package ese.triptop.features.wiremock;

import org.springframework.stereotype.Component;

@Component
public class WiremockTesting {

    public void run() {
        System.out.println("Wiremock testing");
        // Create an instance of the implementation class and call its method
        ImplementationMees implementationMees = new ImplementationMees();
        implementationMees.run();

        ImplementationRamon implementationRamon = new ImplementationRamon();
        implementationRamon.run();

        ImplementationPedro implementationPedro = new ImplementationPedro();
        implementationPedro.run();
        implementationPedro.getFlightOffers("ITF","RTR", "2024-02-01Z",7);
        implementationPedro.getBooking();
        implementationPedro.getTripAdvisor();


    }
}
