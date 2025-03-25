package ese.triptop.onderzoeksvraag.adapters;

import ese.triptop.onderzoeksvraag.api.ApiAdapter;
import ese.triptop.onderzoeksvraag.dto.RestaurantSearchRequest;
import ese.triptop.onderzoeksvraag.dto.RestaurantSearchResponse;

/**
 * Interface for adapters that interact with restaurant recommendation APIs.
 * Extends the generic ApiAdapter interface with restaurant recommendation specific functionality.
 */
public interface TripAdvisorAdapter extends ApiAdapter<RestaurantSearchRequest, RestaurantSearchResponse> {
    
    /**
     * Gets the name of the recommendation provider.
     * 
     * @return The recommendation provider name
     */
    String getRecommendationProvider();
}
