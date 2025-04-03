## 6. Principes

Dit hoofdstuk beschrijft de belangrijkste ontwerpprincipes die zijn toegepast in de Triptop-architectuur.
Deze principes dienen als fundamentele richtlijnen voor onze softwareontwikkeling en zorgen voor een hoge kwaliteit, onderhoudbaarheid en uitbreidbaarheid van het systeem.

Elk principe wordt toegelicht met concrete voorbeelden uit de codebase, samen met de belangrijkste ontwerpvoor- en nadelen die ze bevorderen.

**Single Responsibility Principle (SRP):**  

- Elke klasse heeft één enkele verantwoordelijkheid. Bijvoorbeeld:
  - `IFlightAdapter` definieert het contract voor vluchtadapters.  
  - `SkyscannerAdapter` kapselt de logica in voor interactie met de Skyscanner API.  
  - `FlightFacade` coördineert de interactie tussen adapters en strategieën.  
  - `CheapestFlightStrategy` en `FastestFlightStrategy` richten zich op specifieke selectiecriteria.
- **Design Properties**: Dit principe bevordert **Cohesion** en **Separation of Concerns**, wat leidt tot betere onderhoudbaarheid en testbaarheid.

**Open/Closed Principle (OCP):**  

- Het systeem is open voor uitbreiding, maar gesloten voor wijzigingen. Bijvoorbeeld:  
  - Nieuwe adapters kunnen worden toegevoegd door de `IFlightAdapter`-interface te implementeren zonder bestaande code aan te passen.  
  - Nieuwe strategieën kunnen worden geïntroduceerd door de `IFlightSearchStrategy`-interface te implementeren.  
- **Design Properties**: Dit principe bevordert **Extensibility** en vermindert het risico van regressiefouten bij het toevoegen van nieuwe functionaliteit.

**Dependency Inversion Principle (DIP):**  

- Hoog-niveau modules (bijv. `FlightFacade`) zijn afhankelijk van abstracties (`IFlightAdapter`, `IFlightSearchStrategy`) in plaats van concrete implementaties.  
- **Design Properties**: Dit principe bevordert lage **Coupling**, wat leidt tot betere **Extensibility** en testbaarheid.

**Program to Interfaces, Not Implementations:**  

- Het systeem is gebaseerd op interfaces (`IFlightAdapter`, `IFlightSearchStrategy`) in plaats van concrete klassen, wat flexibiliteit en eenvoudiger testen mogelijk maakt.  
- **Design Properties**: Verhoogt **Extensibility** en vermindert **Coupling**.

**Separation of Concerns (SoC):**  

- Verschillende verantwoordelijkheden worden afgehandeld door verschillende componenten:  
  - Adapters verwerken API-specifieke logica.  
  - Factories beheren de aanmaak en beschikbaarheid van adapters.  
  - Strategieën regelen de selectielogica.  
  - Facades bieden een vereenvoudigde interface voor clients. 
- **Design Properties**: Verhoogt **Separation of Concerns**, **Cohesion** en verbetert onderhoudbaarheid.

**Composition Over Inheritance:**  

- Het systeem maakt gebruik van compositie (bijv. `FlightFacade` combineert adapters en strategieën) in plaats van overerving.
- **Design Properties**: Verbetert **Extensibility** en vermindert rigide hiërarchieën.

**DRY (Don't Repeat Yourself):**  

- Gemeenschappelijke logica (bijv. beschikbaarheidscontroles van adapters) is gecentraliseerd in factories om duplicatie te voorkomen.
- **Design Properties**: Verbetert **Cohesion** en vermindert inconsistenties.  

**Encapsulatie**:

- Implementatiedetails van externe API-interacties zijn verborgen binnen adapterklassen en bieden alleen de interface aan.
- **Design Properties**: Verhoogt **Information Hiding** en vermindert **Coupling**.

**Information Hiding**:

- Clients hoeven alleen te weten *wat* de Facade doet, niet *hoe*.
- **Design Properties**: Bevordert **Information Hiding** en vermindert complexiteit voor clients.
