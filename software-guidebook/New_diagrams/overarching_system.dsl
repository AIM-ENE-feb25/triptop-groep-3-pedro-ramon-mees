workspace "Triptop System Architecture Overview" "Integrates Facade, Adapter, Strategy, and Factory patterns." {
    model {
        # Actors
        user = person "Traveler/Agent" "Uses Triptop to plan and book trips."

        # External Systems (Grouped by Domain)
        group "External APIs" {
            flightApis = softwareSystem "External Flight APIs" "e.g., Skyscanner, KLM API" "External" {
                tags "External API"
            }
            hotelApis = softwareSystem "External Hotel APIs" "e.g., Booking.com, Expedia API" "External" {
                tags "External API"
            }
            paymentApis = softwareSystem "External Payment APIs" "e.g., Stripe, Paypal API" "External" {
                tags "External API"
            }
            // Removed dedicated IdentityProvider system as per README update
            // Authentication might use external OAuth providers via adapters if needed, but not shown as a primary system here.
        }

        # Triptop System
        triptopSystem = softwareSystem "Triptop Platform" "Web application for planning and booking trips." {

            # Containers
            webApp = container "Web Application / API" "Provides the user interface and exposes REST APIs." "Java / Spring Boot" "Web/API Layer" {
                restController = component "REST Controllers" "Handles incoming HTTP requests (e.g., FlightController, PaymentController)." "Java / Spring @RestController" "Client"
            }

            backendService = container "Backend Service" "Contains core business logic, pattern implementations, and external API interactions." "Java / Spring Boot" "Business Logic Layer" {

                # Facades (Entry points for domains)
                flightFacade = component "FlightFacade" "Simplifies flight searching and booking interactions." "Java / Spring Service" "Facade"
                hotelFacade = component "HotelFacade" "Simplifies hotel searching and booking interactions." "Java / Spring Service" "Facade"
                paymentFacade = component "PaymentFacade" "Simplifies payment processing interactions." "Java / Spring Service" "Facade"

                # Factories (Provide Adapters)
                flightAdapterFactory = component "FlightAdapterFactory" "Provides IFlightAdapter instances." "Java / Spring Component" "Factory"
                hotelAdapterFactory = component "HotelAdapterFactory" "Provides IHotelAdapter instances." "Java / Spring Component" "Factory"
                paymentAdapterFactory = component "PaymentAdapterFactory" "Provides IPaymentAdapter instances (may include Circuit Breaker)." "Java / Spring Component" "Factory"

                # Adapter Interfaces
                flightAdapterInterface = component "IFlightAdapter" "Standard interface for flight providers." "Java Interface" "Adapter Interface"
                hotelAdapterInterface = component "IHotelAdapter" "Standard interface for hotel providers." "Java Interface" "Adapter Interface"
                paymentAdapterInterface = component "IPaymentAdapter" "Standard interface for payment providers." "Java Interface" "Adapter Interface"

                # Concrete Adapters (Examples)
                skyscannerAdapter = component "SkyscannerAdapter" "Adapts Skyscanner API." "Java / Spring Component" "Adapter"
                bookingAdapter = component "BookingComAdapter" "Adapts Booking.com API." "Java / Spring Component" "Adapter"
                stripeAdapter = component "StripePaymentAdapter" "Adapts Stripe API." "Java / Spring Component" "Adapter"
                paypalAdapter = component "PaypalPaymentAdapter" "Adapts Paypal API." "Java / Spring Component" "Adapter"

                # Strategy Interfaces
                flightSearchStrategy = component "IFlightSearchStrategy" "Interface for flight selection algorithms." "Java Interface" "Strategy Interface"
                // Add other strategy interfaces if applicable (e.g., IHotelSortStrategy)

                # Concrete Strategies (Examples)
                cheapestFlightStrategy = component "CheapestFlightStrategy" "Selects cheapest flight." "Java / Spring Component" "Concrete Strategy"
                fastestFlightStrategy = component "FastestFlightStrategy" "Selects fastest flight." "Java / Spring Component" "Concrete Strategy"

                # --- Relationships within Backend ---
                # Facades use Factories
                flightFacade -> skyscannerAdapter "Calls" "via IFlightAdapter"
                paymentFacade -> stripeAdapter "Calls" "Via IPaymentAdapter"
                flightFacade -> flightAdapterFactory "Uses"
                hotelFacade -> hotelAdapterFactory "Uses"
                paymentFacade -> paymentAdapterFactory "Uses"
                

                # Facades use Strategy Interfaces
                flightFacade -> flightSearchStrategy "Uses"

                # Factories provide Adapter Interfaces
                flightAdapterFactory -> flightAdapterInterface "Provides"
                hotelAdapterFactory -> hotelAdapterInterface "Provides"
                paymentAdapterFactory -> paymentAdapterInterface "Provides"

                # Concrete Adapters implement Interfaces
                skyscannerAdapter -> flightAdapterInterface "Implements"
                bookingAdapter -> hotelAdapterInterface "Implements"
                stripeAdapter -> paymentAdapterInterface "Implements"
                paypalAdapter -> paymentAdapterInterface "Implements"

                 # Concrete Strategies implement Interfaces
                cheapestFlightStrategy -> flightSearchStrategy "Implements"
                fastestFlightStrategy -> flightSearchStrategy "Implements"

                # Adapters interact with External APIs (Defined below)
            }

            # --- Relationships between Containers ---
            user -> webApp "Uses" "HTTPS"
            webApp -> backendService "Invokes Facades via" "Java API / REST (Internal)"
            restController -> flightFacade "Uses"
            restController -> hotelFacade "Uses"
            restController -> paymentFacade "Uses"

            # --- Relationships to External Systems ---
            backendService -> flightApis "Uses Adapters to call" "HTTPS/JSON"
            backendService -> hotelApis "Uses Adapters to call" "HTTPS/JSON"
            backendService -> paymentApis "Uses Adapters to call" "HTTPS/JSON"

            # More specific adapter relationships
            skyscannerAdapter -> flightApis "Calls" "HTTPS/JSON"
            bookingAdapter -> hotelApis "Calls" "HTTPS/JSON"
            stripeAdapter -> paymentApis "Calls" "HTTPS/JSON"
            paypalAdapter -> paymentApis "Calls" "HTTPS/JSON"
        }
    }
    
    

    views {
        # System Context View
        systemContext triptopSystem "SystemContext" "Overall context of the Triptop Platform and its external dependencies." {
            include *
            autoLayout
        }

        # Container View
        container triptopSystem "Containers" "Shows the main containers within the Triptop Platform." {
            include *
            autoLayout
        }

        # Component View (Focus on Backend Service Patterns)
        component backendService "BackendComponents" "Details the components and patterns within the Backend Service." {
            include * 
            # Add external systems interacted with by adapters
            include flightApis hotelApis paymentApis

            # Explicitly add key relationships if autoLayout is insufficient


            autoLayout
        }

        # Dynamic View (Example: Flight Search) - Based on mockup sequence
        dynamic backendService "FlightSearchSequence" "Illustrates the sequence for finding the best flight." {
            restController -> flightFacade " searchBestFlight(params)"
            flightFacade -> flightAdapterFactory " getAvailableAdapters()"
            flightAdapterFactory -> flightFacade " List<IFlightAdapter>"
            flightFacade -> skyscannerAdapter " getFlights(params)"
            skyscannerAdapter -> flightApis " API Call"
            flightApis -> skyscannerAdapter " Results"
            skyscannerAdapter -> flightFacade " List<Flight>"
            # Repeat 4-7 for other flight adapters...
            flightFacade -> flightSearchStrategy " findBestFlight(allFlights)"
            flightSearchStrategy -> flightFacade " bestFlight"
            flightFacade -> restController " bestFlight"

            autoLayout
        }

         # Dynamic View (Example: Payment) - Based on ADR-007
        dynamic backendService "PaymentSequence" "Illustrates the sequence for processing a payment." {
            restController -> paymentFacade " processPayment(request)"
            paymentFacade -> paymentAdapterFactory " getAdapter()"
            paymentAdapterFactory -> paymentFacade " IPaymentAdapter (e.g., Stripe)"
            paymentFacade -> stripeAdapter " processInvoicePayment(request)"
            stripeAdapter -> paymentApis " Stripe API Call"
            paymentApis -> stripeAdapter " Stripe Response"
            stripeAdapter -> paymentFacade " InvoiceResponse"
            paymentFacade -> restController " InvoiceResponse"

            autoLayout
        }
        styles {
        element "Person" {
            shape Person
            background #08427b
            color #ffffff
        }
        element "Software System" {
            background #1168bd
            color #ffffff
        }
         element "External" {
            background #999999
            color #ffffff
        }
         element "External API" {
            background #999999
            color #ffffff
        }
        element "Container" {
            background #438dd5
            color #ffffff
        }
        element "Component" {
            background #85bbf0
            color #000000
        }
        element "Facade" {
            background #1168bd
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-facade-16.png"
        }
        element "Factory" {
            background #1168bd
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-factory-method-16.png"
        }
        element "Adapter Interface" {
            shape Component
            background #6c96d6
            color #ffffff
        }
        element "Adapter" {
            background #1168bd
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-adapter-16.png"
        }
        element "Strategy Interface" {
            shape Component
            background #6c96d6
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-strategy-16.png"
        }
        element "Concrete Strategy" {
            background #1168bd
            color #ffffff
        }
        element "Client" {
             background #85bbf0
             color #000000
        }
    }
    }
}
