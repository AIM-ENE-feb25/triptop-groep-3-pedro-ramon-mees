workspace "Adapter Pattern Example - Triptop Hotels (Booking.com)" "Illustrates the Adapter pattern for hotel searching via Booking.com." {
    model {
        # Define a system to provide context for the components
        triptopSystem = softwareSystem "Triptop Backend" "Handles trip planning and booking logic." {
            triptopContainer = container "Triptop Container" {
                # Client Component (e.g., a Service or Facade using the adapters)
                hotelClient = component "Hotel Client" "e.g., HotelService or HotelFacade" "Java / Spring Component"
                
                # Standard Interface
                hotelAdapterInterface = component "IHotelAdapter" "Standard interface for all hotel providers." "Java Interface" "Adapter Interface"
                
                # Concrete Adapter for Booking.com
                bookingAdapter = component "BookingComAdapter" "Adapts the Booking.com API to the IHotelAdapter interface." "Java / Spring Component" "Adapter"
                
                # Optional: Factory providing the adapters
                hotelAdapterFactory = component "HotelAdapterFactory" "Provides instances of IHotelAdapter." "Java / Spring Component" "Factory"
                
                # Relationships within the system
                hotelClient -> hotelAdapterInterface "Uses"
                bookingAdapter -> hotelAdapterInterface "Implements"
                hotelClient -> hotelAdapterFactory "Gets adapter from"
                hotelAdapterFactory -> hotelAdapterInterface "Creates/Provides"
            }
        }
        
        # External System (API)
        bookingApi = softwareSystem "Booking.com API" "External Hotel Booking Service" "External"
        
        # Relationships to External Systems - note this change referencing the specific component
        bookingAdapter -> bookingApi "Makes API calls to" "HTTPS/JSON"
    }
    
    views {
        # Component Diagram showing Adapters within the Triptop Backend system context
        component triptopContainer "AdapterPatternDetail_Hotels"  "Component diagram showing the Adapter pattern for Hotels (Booking.com)." {
            include hotelClient hotelAdapterInterface bookingAdapter hotelAdapterFactory bookingApi
            autoLayout
        }
        
        # System Context showing interaction with the external Booking.com API
        systemContext bookingApi "BookingApiContext" {
            include *
            autoLayout
        }
        styles {
        element "Software System" {
            background #1168bd 
            color #ffffff
        }
        element "Component" {
            background #85bbf0 
            color #000000
        }
        element "Adapter Interface" {
            shape Component
            background #6c96d6 
            color #ffffff
        }
        element "Adapter" {
            shape Component
            background #1168bd 
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-adapter-16.png"
        }
        element "Factory" {
            shape Component
            background #1168bd
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-factory-method-16.png"
        }
        element "External" {
            background #999999 
            color #ffffff
        }
        element "Container" {
            background #438dd5
            color #ffffff
        }
    }
    }
    
    
}