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
            description "Authenticeert gebruikers. Een samengestelde representatie van meerdere OAuth2-providers (Google, Microsoft, AirBnb, etc.) waarmee gebruikers kunnen inloggen."
        }
        
        vervoerAPI = softwareSystem "Vervoer API" {
            description "Een samengestelde representatie van meerdere vervoerders zoals NS, KLM en Deutsche Bahn."
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
                description "Beheert logica voor reizen, boekingen en betalingen."
                technology "Java Spring Boot"
            }
            
            database = container "Database" {
                description "Slaat gebruikersgegevens, boekingen en reisopties op."
                technology "MySQL"
                tags "Database"
            }
        }
        
        // Relaties
        reiziger -> triptop "Plant, boekt en beheert reizen via"
        reisagent -> triptop "Ondersteunt reizigers via"
        
        reiziger -> triptop.frontEnd "Gebruikt voor plannen en boeken" "HTTPS"
        reisagent -> triptop.frontEnd "Gebruikt voor ondersteuning" "HTTPS"
        
        triptop -> identityProvider "Gebruikt voor authenticatie" "HTTPS"
        triptop -> vervoerAPI "Haalt reisopties op van" "HTTPS/JSON"
        triptop -> betaalprovider "Verwerkt betalingen via" "HTTPS/JSON"
        
        triptop.frontEnd -> triptop.backEnd "Maakt API-aanvragen naar" "HTTPS/JSON"
        triptop.backEnd -> triptop.database "Leest en schrijft gegevens" "JDBC"
        triptop.backEnd -> identityProvider "Verwerkt login verzoeken" "HTTPS/JWT"
        triptop.backEnd -> vervoerAPI "Haalt reisopties op van" "HTTPS/REST/JSON"
        triptop.backEnd -> betaalprovider
    }
    
    views {
        systemContext triptop "TriptopContextDiagram" {
            include *
            autolayout tb
            description "Toont Triptop en de interacties met externe systemen en gebruikers."
        }
    
        container triptop "TriptopContainerDiagram" {
            include *
            autolayout tb
            description "Toont de hoofdcomponenten van het Triptop systeem en hun onderlinge interacties."
        }
        
        dynamic triptop "InlogprocesView" {
            title "Gebruiker logt in op Triptop"
            autolayout tb
            reiziger -> triptop.frontEnd "1. Bezoekt Triptop website en klikt op 'Inloggen'"
            triptop.frontEnd -> triptop.backEnd "2. Vraagt beschikbare login providers"
            triptop.backEnd -> identityProvider "3. Stuurt authenticatieverzoek"
            identityProvider -> triptop.backEnd "4. Bevestigt login en stuurt gebruikersgegevens"
            triptop.backEnd -> triptop.database "5. Slaat sessie op"
            triptop.backEnd -> triptop.frontEnd "6. Stuurt sessietoken"
            triptop.frontEnd -> reiziger "7. Toont ingelogde status"
            
            autoLayout
            description "Deze view toont het vereenvoudigde inlogproces waarbij een gebruiker via een externe identity provider inlogt."
        }
        
        dynamic triptop "ReisplannenView" {
            title "Gebruiker plant een reis"
            reiziger -> triptop.frontEnd "1. Voert reisdetails in"
            triptop.frontEnd -> triptop.backEnd "2. Vraagt beschikbare reisopties"
            triptop.backEnd -> vervoerAPI "3. Haalt reisopties op"
            vervoerAPI -> triptop.backEnd "4. Stuurt beschikbare opties"
            triptop.backEnd -> triptop.frontEnd "5. Toont opties aan gebruiker"
            reiziger -> triptop.frontEnd "6. Selecteert transport en bevestigt"
            triptop.frontEnd -> triptop.backEnd "7. Slaat keuze op en start betaling"
            triptop.backEnd -> betaalprovider "8. Initialiseert betaling"
            
            autoLayout
            description "Deze view toont het proces van reisplanning tot betaling."
        }
        
        theme default
    }
    
}
