package ese.triptop.onderzoeksvraag.adapters;

import ese.triptop.onderzoeksvraag.api.ApiAdapter;
import ese.triptop.onderzoeksvraag.dto.FlightSearchRequest;
import ese.triptop.onderzoeksvraag.dto.FlightSearchResponse;

/**
 * Interface for adapters that interact with flight booking APIs.
 * Extends the generic ApiAdapter interface with flight booking specific functionality.
 */
public interface FlightAdapter extends ApiAdapter<FlightSearchRequest, FlightSearchResponse> {
    
    /**
     * Gets the name of the flight provider.
     * 
     * @return The flight provider name
     */
    String getFlightProvider();
}
