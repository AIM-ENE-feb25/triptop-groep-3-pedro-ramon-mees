workspace "Context Diagram voor TripTop" "Description" {

    model {
        reiziger = person Reiziger "Maakt vakantieplannen via de app" "Person"
        reisagent = person ReisAgent "Tweedelijns support voor raadpleging" "Person"
        
        triptop = softwareSystem "TripTop Vakantieplan App" "Verbind reiziger met verschillende vakantie plan opties" "System"
        
        reiziger -> triptop "Maakt vakantieplannen"
        reisAgent -> triptop "Raadpleging/Risico bespreking"
        
        stripeAPI = softwareSystem "Stripe API" "Handelt betalingen af" "ExternalSystem"    
        googleMapsAPI = softwareSystem "Google Maps API" "Biedt verschillende reis opties van A naar B" "ExternalSystem"
        takeawayAPI = softwareSystem "Takeaway API" "Biedt voedselbezorging van verschillende restauranten" "ExternalSystem"
        idProvider = softwareSystem "IdentificatieProvider (Google, Microsoft, AirBnb)" "Verwerkt gebruiker authenticatie & authorizatie" "ExternalSystem"
        overnachtingProvider = softwareSystem "OvernachtingProvider (Booking.com, AirBnB)" "Biedt overnachtingen aan" "ExternalSystem"
        autoHuurProvider = softwareSystem "AutoHuurProvider (Sixt, Hertz)" "Biedt verschillende huurautos aan" "ExternalSystem"
        activiteitProvider = softwareSystem "ActiviteitProvider (GetYourGuide, TripAdvisor)" "Biedt verschillende activiteiten aan" "ExternalSystem"
    
        triptop -> stripeAPI "Verstuurt betaalverzoeken"
        triptop -> googleMapsAPI "Vraagt voor vervoers suggesties"
        triptop -> takeawayAPI "Vraagt voor voedings suggesties"
        triptop -> idProvider "Stuurt login process door"
        triptop -> overnachtingProvider "Vraagt voor overnachtings suggesties"
        triptop -> autoHuurProvider "Vraagt voor huurautos suggesties"
        triptop -> activiteitProvider "Vraagt voor suggesties van activiteiten"
    }

    configuration {
        scope softwaresystem
    }
    
    views {
        systemLandscape {
            include *
            autolayout tb
        }
        
        styles {
            element "Person" {
                background #1168bd
                color #ffffff
                shape Person
            }
            element "System" {
                background #09335d
                color #ffffff
                shape RoundedBox
            }
            element "System" {
                shape Box
            }
            
        }
    }

}