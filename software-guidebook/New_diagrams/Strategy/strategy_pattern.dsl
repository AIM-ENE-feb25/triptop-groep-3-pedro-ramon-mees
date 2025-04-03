workspace "Strategy Pattern Example - Triptop Flight Selection" "Illustrates the Strategy pattern for selecting flights." {

    model {
        # Define a system to provide context for the components
        triptopSystem = softwareSystem "Triptop Backend" "Handles trip planning and booking logic." {

            triptopContainer = container "Triptop Container" {
                # Client Component (e.g., a Service or Facade using the strategies)

                # Strategy Context/Facade
                flightFacade = component "FlightFacade" "Orchestrates flight search and uses a strategy for selection." "Java / Spring Component" {
                    description "Holds a reference to an IFlightSearchStrategy instance."
                    tags "FlightFacade"
                }

                # Strategy Interface
                strategyInterface = component "IFlightSearchStrategy" "Defines the interface for flight selection algorithms." "Java Interface" {
                    description "Standard interface for all flight selection strategies."
                    tags "IFlightSearchStrategy"
                }

                # Concrete Strategy Implementations
                cheapestStrategy = component "CheapestFlightStrategy" "Selects the flight with the lowest price." "Java / Spring Component" {
                    description "Implements the IFlightSearchStrategy interface."
                }
                fastestStrategy = component "FastestFlightStrategy" "Selects the flight with the shortest duration." "Java / Spring Component" {
                    description "Implements the IFlightSearchStrategy interface."
                }
            }
            # Relationships within the system
            flightFacade -> strategyInterface "Gets strategies from" "findBestFlight(allFlights)"
            cheapestStrategy -> strategyInterface "Implements"
            fastestStrategy -> strategyInterface "Implements"
        }
    }

    views {
        # Component Diagram showing Strategy pattern within the Triptop Backend system context
        component triptopContainer "StrategyPatternDetail" "Component diagram showing the Strategy pattern for Flight Selection." {
            include flightFacade strategyInterface cheapestStrategy fastestStrategy
            
            autoLayout
        }
        styles {
         element "FlightFacade" {
            background #1168bd 
            color #ffffff
        }
        element "Component" {
            background #85bbf0 
            color #000000
        }
        element "Context" {
             shape Component
             background #1168bd 
             color #ffffff
        }
        element "IFlightSearchStrategy" {
            shape Component
            background #6c96d6 
            color #ffffff
            icon "https://static.structurizr.com/icons/pattern-strategy-16.png"
        }
        element "Concrete Strategy" {
            shape Component
            background #1168bd 
            color #ffffff
        }
    }
    }
}
