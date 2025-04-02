workspace "Facade Pattern Example - Triptop Flights" "Illustrates the Facade pattern for flight searching." {

    model {
        # Actors / Clients
        client = person "Client" "e.g., FlightController or other service" "Interacts with the system via the Facade"

        # System Components (Focus on Facade Interaction)
        flightSystem = softwareSystem "Triptop Backend" "Handles trip planning and booking logic." {
            flightContainer = container "flight container" {
                flightFacade = component "FlightFacade" "Provides a simplified interface for flight operations." "Java / Spring Service" "Facade" {
                description "Hides the complexity of interacting with multiple flight adapters and applying search strategies."
            }
            flightAdapterFactory = component "FlightAdapterFactory" "Creates/Provides flight adapter instances." "Java / Spring Component" "Factory"
            flightSearchStrategy = component "IFlightSearchStrategy" "Interface for flight selection algorithms." "Java Interface" "Strategy"
            concreteStrategy = component "ConcreteFlightStrategy" "e.g., CheapestFlightStrategy" "Java / Spring Component" "Strategy Implementation"

            # Adapters are conceptually managed by the Factory, not directly called by client
            flightAdapters = component "Flight Adapters" "Manages communication with specific external flight APIs (e.g., SkyscannerAdapter)." "Adapter" {
                 description "Instances provided by FlightAdapterFactory."
            }

            # Relationships showing Facade usage
            client -> flightFacade "Uses" "findBestFlight(params)"
            flightFacade -> flightAdapterFactory "Uses" "getAvailableAdapters()"
            flightFacade -> flightSearchStrategy "Uses" "findBestFlight(allFlights)"
            flightFacade -> flightAdapters "Aggregates results from" "(via Factory)"

            flightAdapterFactory -> flightAdapters "Creates/Provides"

            concreteStrategy -> flightSearchStrategy "Implements"
            }
        }
    }

    views {
        component flightContainer "FacadePatternDetail" "Component diagram showing the Facade pattern for Flights." {
            include *
            autoLayout
        }

        styles {
            element "Person" {
                shape Robot
                background #08427b
                color #ffffff
            }
            element "Component" {
                background #1168bd
                color #ffffff
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
             element "Strategy" {
                background #1168bd
                color #ffffff
                icon "https://static.structurizr.com/icons/pattern-strategy-16.png"
            }
             element "Adapter" {
                background #dddddd
                color #000000
                icon "https://static.structurizr.com/icons/pattern-adapter-16.png"
            }
            element "Strategy Implementation" {
                 background #1168bd
                 color #ffffff
            }
        }
    }
}
