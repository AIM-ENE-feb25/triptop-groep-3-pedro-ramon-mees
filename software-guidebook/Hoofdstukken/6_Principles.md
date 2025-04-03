## 6. Principes

- **Single Responsibility Principle (SRP):**  
   - Elke klasse heeft één enkele verantwoordelijkheid. Bijvoorbeeld:  
     - `IFlightAdapter` definieert het contract voor vluchtadapters.  
     - `SkyscannerAdapter` kapselt de logica in voor interactie met de Skyscanner API.  
     - `FlightFacade` coördineert de interactie tussen adapters en strategieën.  
     - `CheapestFlightStrategy` en `FastestFlightStrategy` richten zich op specifieke selectiecriteria.  
- **Open/Closed Principle (OCP):**  
   - Het systeem is open voor uitbreiding, maar gesloten voor wijzigingen. Bijvoorbeeld:  
     - Nieuwe adapters kunnen worden toegevoegd door de `IFlightAdapter`-interface te implementeren zonder bestaande code aan te passen.  
     - Nieuwe strategieën kunnen worden geïntroduceerd door de `IFlightSearchStrategy`-interface te implementeren.  
- **Dependency Inversion Principle (DIP):**  
   - Hoog-niveau modules (bijv. `FlightFacade`) zijn afhankelijk van abstracties (`IFlightAdapter`, `IFlightSearchStrategy`) in plaats van concrete implementaties.  
- **Program to Interfaces, Not Implementations:**  
   - Het systeem is gebaseerd op interfaces (`IFlightAdapter`, `IFlightSearchStrategy`) in plaats van concrete klassen, wat flexibiliteit en eenvoudiger testen mogelijk maakt.  
- **Separation of Concerns (SoC):**  
   - Verschillende verantwoordelijkheden worden afgehandeld door verschillende componenten:  
     - Adapters verwerken API-specifieke logica.  
     - Factories beheren de aanmaak en beschikbaarheid van adapters.  
     - Strategieën regelen de selectielogica.  
     - Facades bieden een vereenvoudigde interface voor clients.  
- **Composition Over Inheritance:**  
   - Het systeem maakt gebruik van compositie (bijv. `FlightFacade` combineert adapters en strategieën) in plaats van overerving.
- **DRY (Don't Repeat Yourself):**  
    - Gemeenschappelijke logica (bijv. beschikbaarheidscontroles van adapters) is gecentraliseerd in factories om duplicatie te voorkomen.  
- **Encapsulatie**: Implementatiedetails van externe API-interacties zijn verborgen binnen adapterklassen en bieden alleen de interface aan.
- **Information Hiding**: Clients hoeven alleen te weten *wat* de Facade doet, niet *hoe*.


### Toegepaste Patterns

**Facade:** domeinspecifieke interface (bijv. FlightFacade, HotelFacade) die de complexiteit verbergt van interactie met meerdere onderliggende adapters en selectie-logica toepast. Clients werken voornamelijk met Facades.

**Adapter:** Zet de interface van een specifieke externe dienst (bijv. Skyscanner API, Booking.com API, Stripe API) om naar een standaardinterface binnen Triptop (bijv. IFlightAdapter, IHotelAdapter). Elke externe dienst krijgt een eigen Adapter. (Zie ADR-007 voor Payment Adapters).

**Strategy:** Definieert een groep algoritmes voor het verwerken of selecteren van resultaten (bijv. het vinden van de goedkoopste vlucht, de snelste vlucht of het best beoordeelde hotel). De Facade bevat en gebruikt een specifieke Strategy-instantie om te werken met de verzamelde gegevens uit de Adapters.

**Factory method:** Verantwoordelijk voor het aanmaken van Adapter-instanties. Dit ontkoppelt de Facade van concrete adapterimplementaties en kan adapterconfiguraties of beschikbaarheid beheren (bijv. met een Circuit Breaker per adapter). (Zie het PaymentAdapterFactory-voorbeeld).


---
