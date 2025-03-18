workspace "Triptop" "Online vakantietrip planner" {

    !identifiers hierarchical

    model {
        reiziger = person "Reiziger" "Een gebruiker die zelf een reis plant."
        reizigerAgent = person "Reis Agent" "Een gebruiker die de reiziger helpt bij het plannen van een reis"
        triptop = softwareSystem "Triptop" "Online platform voor vakantieplanning."
        
        identityProvider = softwareSystem "Identity Provider" "Biedt authenticatie via Google, Microsoft, AirBnb, etc."
        boekingService = softwareSystem "Boeking Service" "Externe systemen zoals Booking.com en Airbnb."
        transportService = softwareSystem "Transport Service" "Externe vervoersdiensten zoals NS, Deutsche Bahn, KLM."
        betaalService = softwareSystem "Betaal Service" "Betaalsysteem voor afrekeningen."
        
        reiziger -> triptop "Plant en beheert reizen via"
        reizigerAgent -> triptop "Helpt reiziger plannen via"
        triptop -> identityProvider "Authenticeert gebruikers via"
        triptop -> boekingService "Boekt accommodaties via"
        triptop -> transportService "Regelt vervoer via"
        triptop -> betaalService "Verwerkt betalingen via"
    }

    views {
        systemContext triptop "SoftwareSystem" {
            include *
            autolayout lr
        }

        styles {
            element "Element" {
                color #ffffff
            }
            element "Person" {
                background #8EAED5
                shape person
            }
            element "Software System" {
                background #509CF9
            }
        }
    }

    configuration {
        scope softwaresystem
    }
}
