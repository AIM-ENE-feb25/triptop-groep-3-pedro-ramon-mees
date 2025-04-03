package ese.triptop.prototype;

import ese.triptop.prototype.adapter.BookingComFlightAdapter;
import ese.triptop.prototype.adapter.SkyscannerFlightAdapter;
import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import ese.triptop.prototype.factory.FlightAdapterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrototypeRunner {

    IFlightAdapter adapter;

    @Autowired
    public PrototypeRunner(FlightAdapterFactory factory) {
        this.adapter = factory.getFlightAdapter(SkyscannerFlightAdapter.class);
    }


    public void run () {
        System.out.println(adapter.getFlights("AMS", "LHR", "2024-02-01Z", 2));
    }
}
