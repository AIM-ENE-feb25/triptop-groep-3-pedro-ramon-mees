workspace "Triptop API Architecture" {

    model {
        user = person "User" "A user of the Triptop application"
        
        triptopSystem = softwareSystem "Triptop System" {
            frontendApp = container "Frontend Application" "Provides UI for users to interact with the system" "React"
            backendApi = container "Backend API" "Provides business logic and API integration" "Java Spring Boot" {
                travelController = component "TravelController" "Handles travel-related requests from the frontend"
                travelService = component "TravelServiceImpl" "Implements travel-related business logic"
                apiGateway = component "ApiGateway" "Manages external API interactions"
                configManager = component "ConfigurationManager" "Manages API configuration"
                
                // API Adapters
                bookingAdapter = component "BookingAdapter" "Handles interactions with Booking.com API"
                tripAdvisorAdapter = component "TripAdvisorAdapter" "Handles interactions with TripAdvisor API"
                flightAdapter = component "FlightAdapter" "Handles interactions with Flight API"
            }
            database = container "Database" "Stores user data, bookings, etc." "MySQL"
        }
        
        // External Systems
        bookingApi = softwareSystem "Booking.com API" "Provides hotel booking functionality" "External"
        tripAdvisorApi = softwareSystem "TripAdvisor API" "Provides restaurant recommendations" "External"
        flightApi = softwareSystem "Flight API" "Provides flight booking functionality" "External"
        
        // Relationships
        user -> frontendApp "Uses"
        frontendApp -> backendApi "Makes API calls to" "JSON/HTTPS"
        backendApi -> database "Reads from and writes to" "JDBC"
        
        // Component relationships
        travelController -> travelService "Uses"
        travelService -> apiGateway "Uses"
        apiGateway -> bookingAdapter "Routes requests to"
        apiGateway -> tripAdvisorAdapter "Routes requests to"
        apiGateway -> flightAdapter "Routes requests to"
        
        bookingAdapter -> bookingApi "Makes API calls to" "JSON/HTTPS"
        tripAdvisorAdapter -> tripAdvisorApi "Makes API calls to" "JSON/HTTPS"
        flightAdapter -> flightApi "Makes API calls to" "JSON/HTTPS"
        bookingAdapter -> bookingAdapter "Changes formatting"
        
        apiGateway -> configManager "Gets configuration from"
        bookingAdapter -> configManager "Gets configuration from"
        tripAdvisorAdapter -> configManager "Gets configuration from"
        flightAdapter -> configManager "Gets configuration from"
    }
    
    views {
        systemContext triptopSystem {
            include *
            autoLayout
        }
        
        container triptopSystem {
            include *
            autoLayout
        }
        
        component backendApi {
            include *
            autoLayout
        }
        
        dynamic triptopSystem "UC1" "User searches for hotel options" {
            user -> frontendApp "1. Enters search criteria"
            frontendApp -> backendApi "2. Sends search request"
            backendApi -> bookingApi "3. Retrieves hotel options"
            bookingApi -> backendApi "4. Returns hotel options"
            backendApi -> frontendApp "5. Returns hotel options"
            frontendApp -> user "6. Displays hotel options"
            autoLayout
        }
        
        dynamic triptopSystem "UC2" "User searches for restaurant recommendations" {
            user -> frontendApp "1. Enters location"
            frontendApp -> backendApi "2. Sends recommendation request"
            backendApi -> tripAdvisorApi "3. Retrieves restaurant recommendations"
            tripAdvisorApi -> backendApi "4. Returns restaurant recommendations"
            backendApi -> frontendApp "5. Returns restaurant recommendations"
            frontendApp -> user "6. Displays restaurant recommendations"
            autoLayout
        }
        
        dynamic triptopSystem "UC3" "API Provider Changes Response Format" {
            bookingApi -> backendApi "1. API provider changes response format"
            backendApi -> frontendApp "2. Service continues to provide consistent data"
            frontendApp -> user "3. User experience remains unchanged"
            autoLayout
        }
        
        dynamic backendApi "DetailedUC1" "Detailed Hotel Search Workflow" {
            travelController -> travelService "1. Calls getHotelBookingOptions()"
            travelService -> apiGateway "2. Calls executeApiCall() with BookingAdapter"
            apiGateway -> configManager "3. Gets API configuration"
            apiGateway -> bookingAdapter "4. Routes request to adapter"
            bookingAdapter -> bookingApi "5. Makes API call to Booking.com"
            bookingApi -> bookingAdapter "6. Returns hotel options"
            bookingAdapter -> apiGateway "7. Returns mapped response"
            apiGateway -> travelService "8. Returns response"
            travelService -> travelController "9. Returns hotel options"
            autoLayout
        }
        
        dynamic backendApi "DetailedUC2" "Detailed Restaurant Recommendation Workflow" {
            travelController -> travelService "1. Calls getRestaurantRecommendations()"
            travelService -> apiGateway "2. Calls executeApiCall() with TripAdvisorAdapter"
            apiGateway -> configManager "3. Gets API configuration"
            apiGateway -> tripAdvisorAdapter "4. Routes request to adapter"
            tripAdvisorAdapter -> tripAdvisorApi "5. Makes API call to TripAdvisor"
            tripAdvisorApi -> tripAdvisorAdapter "6. Returns restaurant recommendations"
            tripAdvisorAdapter -> apiGateway "7. Returns mapped response"
            apiGateway -> travelService "8. Returns response"
            travelService -> travelController "9. Returns restaurant recommendations"
            autoLayout
        }
        
        dynamic backendApi "DetailedUC3" "Detailed API Response Format Change" {
            bookingAdapter -> bookingAdapter "1. Adapter handles format change internally"
            bookingAdapter -> apiGateway "2. Provides consistent mapped response"
            apiGateway -> travelService "3. Service receives consistent response"
            travelService -> travelController "4. Controller receives consistent data"
            autoLayout
        }
        
        styles {
            element "Person" {
                background #08427B
                color #ffffff
                fontSize 22
                shape Person
            }
            element "Software System" {
                background #1168BD
                color #ffffff
            }
            element "Container" {
                background #438DD5
                color #ffffff
            }
            element "Component" {
                background #85BBF0
                color #000000
            }
            element "External" {
                background #999999
                color #ffffff
            }
        }
    }
}
