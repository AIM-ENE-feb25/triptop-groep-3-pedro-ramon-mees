workspace "Triptop" "Context en Container diagrammen voor de Triptop applicatie" {

    !identifiers hierarchical

    model {
        // Gebruikers
        reiziger = person "Reiziger" {
            description "Gebruiker die zijn reis plant, boekt en beheert."
        }
        
        reisagent = person "Tweede-lijns Reisagent" {
            description "Ondersteunt reizigers bij het plannen en boeken van hun reis."
        }
        
        // Externe systemen
        identityProvider = softwareSystem "Identity Provider" {
            description "OAuth2-provider waarmee gebruikers kunnen inloggen via Google, Microsoft, AirBnb, etc."
        }
        
        vervoerAPI = softwareSystem "Vervoer API" {
            description "Externe API's zoals NS, KLM en Deutsche Bahn voor het boeken van vervoer."
        }
        
        betaalprovider = softwareSystem "Betaalprovider" {
            description "Externe dienst voor betalingsverwerking."
        }

        // Triptop Systeem
        triptop = softwareSystem "Triptop" {
            description "Een platform waarmee gebruikers zelf hun vakantietrip kunnen samenstellen en boeken."
            
            frontEnd = container "Frontend Webapplicatie" {
                description "De UI waarmee gebruikers hun reizen plannen en boeken."
                technology "React, JavaScript"
            }

            backEnd = container "Backend API" {
                description "Beheert logica voor reizen, boekingen, betalingen en authenticatie."
                technology "Java Spring Boot"
            }
            
            database = container "Database" {
                description "Slaat gebruikersgegevens, boekingen en reisopties op."
                technology "MySQL"
                tags "Database"
            }
        }

        // Relaties tussen gebruikers en systemen
        reiziger -> triptop "Plant, boekt en beheert reizen via"
        reisagent -> triptop "Ondersteunt reizigers via"
        
        // Relaties tussen gebruikers en containers
        reiziger -> triptop.frontEnd "Gebruikt voor het plannen en boeken van reizen ""HTTPS"
        reisagent -> triptop.frontEnd "Gebruikt voor het ondersteunen van reizigers""HTTPS"
        
        // Relaties tussen systemen
        triptop -> identityProvider "Gebruikt voor authenticatie""HTTPS/OAuth2"
        triptop -> vervoerAPI "Haalt reisopties op van""HTTPS/JSON"
        triptop -> betaalprovider "Verwerkt betalingen via""HTTPS/JSON"
        
        // Relaties tussen containers
        triptop.frontEnd -> triptop.backEnd "Maakt API-aanvragen naar" "HTTPS/JSON"
        triptop.backEnd -> triptop.database "Leest en schrijft gegevens" "JDBC"
        
        // Relaties tussen containers en externe systemen
        triptop.backEnd -> identityProvider "Authenticeert gebruikers via "HTTPS/OAuth2/JWT"
        triptop.backEnd -> vervoerAPI "Haalt reisopties op van ""HTTPS/REST/JSON"
        triptop.backEnd -> betaalprovider "Verwerkt betalingen via" "HTTPS/REST/JSON"
    }

    views {
        // System Context diagram
        systemContext triptop "TriptopContextDiagram" {
            include *
            autolayout tb
        }
    
        // Container diagram
        container triptop "TriptopContainerDiagram" {
            include *
            autolayout tb
        }
        
        theme default
    }
}