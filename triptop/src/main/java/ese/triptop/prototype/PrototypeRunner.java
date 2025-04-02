package ese.triptop.prototype;

import ese.triptop.prototype.adapter.BookingComFlightAdapter;
import org.springframework.stereotype.Component;

@Component
public class PrototypeRunner {

    BookingComFlightAdapter bookingComFlightAdapter = new BookingComFlightAdapter();

    public void run () {
        bookingComFlightAdapter.getFlights("AMS", "LHR", "2024-02-01Z", 2);
    }
}
