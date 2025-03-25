package ese.triptop.onderzoeksvraag.adapters;

import ese.triptop.onderzoeksvraag.api.ApiAdapter;
import ese.triptop.onderzoeksvraag.dto.HotelSearchRequest;
import ese.triptop.onderzoeksvraag.dto.HotelSearchResponse;

/**
 * Interface for adapters that interact with hotel booking APIs.
 * Extends the generic ApiAdapter interface with hotel booking specific functionality.
 */
public interface BookingAdapter extends ApiAdapter<HotelSearchRequest, HotelSearchResponse> {
    
    /**
     * Gets the name of the booking provider.
     * 
     * @return The booking provider name
     */
    String getBookingProvider();
}
