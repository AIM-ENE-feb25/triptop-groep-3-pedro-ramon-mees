# Running the API Architecture Demonstration

This document provides instructions on how to run and test the API architecture that minimizes the impact of API changes on the application.

## Overview

The architecture has been integrated with Spring Boot, making it easy to run and test. The main components are:

1. **API Gateway**: Centralizes all external API interactions
2. **API Adapters**: Encapsulate service-specific details
3. **Configuration Manager**: Manages external configuration
4. **Domain Models**: Provide a consistent internal representation of data
5. **DTOs**: Represent data structures specific to external APIs
6. **Service Layer**: Implements business logic using API adapters

## Running the Demonstration

### Option 1: Using Spring Boot

1. Open the project in your IDE
2. Run the `TriptopApplication` class as a Spring Boot application
3. The application will automatically run the API architecture demonstration
4. Check the console output to see the results

```bash
# From the command line
cd Triptop/triptop
./mvnw spring-boot:run
```

### Option 2: Using Maven

```bash
# From the command line
cd Triptop/triptop
./mvnw clean package
java -jar target/triptop-0.0.1-SNAPSHOT.jar
```

## What to Expect

When you run the demonstration, you'll see:

1. Spring Boot starting up
2. Configuration being loaded from application.properties
3. API adapters being registered with the gateway
4. Hotel search scenario being executed
5. Restaurant recommendation scenario being executed
6. Flight search scenario being executed
7. Demonstration of how API changes are handled by the architecture

## Modifying the Configuration

You can modify the API configuration in the `application.properties` file:

```properties
# Booking.com API configuration
booking.api.key=your-api-key
booking.api.host=booking-com15.p.rapidapi.com
booking.api.baseUrl=https://booking-com15.p.rapidapi.com/api/v1/hotels

# TripAdvisor API configuration
tripadvisor.api.key=your-api-key
tripadvisor.api.host=tripadvisor16.p.rapidapi.com
tripadvisor.api.baseUrl=https://tripadvisor16.p.rapidapi.com/api/v1/restaurant

# Flight API configuration
flight.api.key=your-api-key
flight.api.host=kk039.wiremockapi.cloud
flight.api.baseUrl=https://kk039.wiremockapi.cloud/shopping
```

## Testing API Changes

To test how the architecture handles API changes:

1. Modify one of the adapter implementations (e.g., `BookingComAdapter.java`)
2. Update the parsing logic to handle a different response format
3. Run the application again
4. Observe that the rest of the application continues to work without changes

For example, if the Booking.com API changes its response format from using `hotel_id` to `id`:

```java
// Before API change
if (hotelJson.has("hotel_id")) {
    hotel.setId(hotelJson.getString("hotel_id"));
}

// After API change
if (hotelJson.has("id")) {
    hotel.setId(hotelJson.getString("id"));
} else if (hotelJson.has("hotel_id")) {
    // Fallback for backward compatibility
    hotel.setId(hotelJson.getString("hotel_id"));
}
```

## Adding New API Providers

To add a new API provider:

1. Create a new adapter interface that extends `ApiAdapter`
2. Create a new adapter implementation that implements the interface
3. Register the adapter with the API gateway
4. Update the service layer to use the new adapter

For example, to add a new car rental API:

```java
// 1. Create a new adapter interface
public interface CarRentalAdapter extends ApiAdapter<CarRentalRequest, CarRentalResponse> {
    String getCarRentalProvider();
}

// 2. Create a new adapter implementation
public class AvisCarRentalAdapter implements CarRentalAdapter {
    // Implementation details
}

// 3. Register the adapter with the API gateway
apiGateway.registerAdapter(CarRentalAdapter.class, avisCarRentalAdapter);

// 4. Update the service layer to use the new adapter
public List<CarRentalOption> getCarRentalOptions(CarRentalCriteria criteria) {
    CarRentalRequest request = createCarRentalRequest(criteria);
    CarRentalResponse response = apiGateway.executeApiCall(CarRentalAdapter.class, request);
    return mapToCarRentalOptions(response);
}
```

## Conclusion

This architecture successfully minimizes the impact of API changes on the application by:

1. Isolating API-specific details in adapters
2. Using DTOs to decouple external data formats from internal domain models
3. Using a configuration manager to externalize API configuration
4. Using a gateway to provide a unified interface for API interactions

By following these principles, the application can easily adapt to changes in external APIs without affecting the rest of the codebase.
