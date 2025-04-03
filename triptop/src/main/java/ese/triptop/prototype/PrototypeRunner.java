package ese.triptop.prototype;

import ese.triptop.prototype.adapter.BookingComFlightAdapter;
import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import org.springframework.stereotype.Component;

@Component
public class PrototypeRunner {

    IFlightAdapter adapter = new BookingComFlightAdapter();

    public void run () {
        adapter.getFlights("AMS", "LHR", "2024-02-01Z", 2);
    }
}
