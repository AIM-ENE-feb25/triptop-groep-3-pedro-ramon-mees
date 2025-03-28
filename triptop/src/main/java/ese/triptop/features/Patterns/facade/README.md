# **Architectonisch Beslissingsdocument: Toepassen van het Facade-patroon**  

## **Status**  
Geaccepteerd  

## **Context**  

De huidige code in het pakket `ese.triptop.features.wiremock` communiceert direct met meerdere externe APIs (Identity, Flight Offers, Booking, TripAdvisor) via de `Unirest`-bibliotheek. Dit leidt tot de volgende problemen:  

*   **Verspreide logica:** De details van API-communicatie (URL-opbouw, verzoeken maken, reacties verwerken, foutafhandeling) zijn verspreid over meerdere klassen .  
*   **Herhaling**  
*   **Hoge koppeling:** Clientcode is direct afhankelijk van de specifieke externe API’s en de `Unirest`-bibliotheek.  
*   **Complexiteit:** Om de interactie met de APIs te begrijpen, moet men door meerdere klassen en methodes kijken.  
*   **Moeilijk onderhoud:** Wijzigingen in een externe API vereisen aanpassingen op meerdere plekken. Hardcoded URLs en API-sleutels maken dit nog lastiger.  

Het doel is om de interactie met deze externe diensten te vereenvoudigen en de afhankelijkheden te verminderen.  

## **Beslissing**  

We passen het **Facade-patroon** toe om de complexiteit van communicatie met de externe reis-API’s te verbergen.  

Daarom introduceren we een **`TravelApiServiceFacade`-interface** die eenvoudige methodes aanbiedt, zoals:  
- `findFlights`  
- `findHotels`  
- `findRestaurants`  

De implementatie **`TravelApiServiceFacadeImpl`** zal deze interface gebruiken om de volgende details te verbergen:  
*   HTTP-verzoeken uitvoeren via `Unirest`.  
*   Specifieke API-eindpunten en parameters afhandelen.  
*   Basisfouten verwerken en API-reacties omzetten.  

Clientcode communiceert alleen met de **Facade-interface**, zonder afhankelijk te zijn van de onderliggende API’s.  

## **Gevolgen**  

### **Voordelen**  

**Eenvoudigere interface:** Eén toegangspunt voor interactie met externe reis-API's.  
**Lagere koppeling:** De clientcode is niet direct afhankelijk van API-details of `Unirest`.  
**Betere leesbaarheid & onderhoudbaarheid:** Alle API-logica zit op één plek. Wijzigingen in externe API’s hebben minder impact.  
**Encapsulatie:** Verbergt de complexiteit van authenticatie en API-aanroepen.  

### **Nadelen**  

**Mogelijke 'God Object'-valkuil:** Als de Facade te veel taken krijgt, kan het een te grote verantwoordelijkheid krijgen. *(Oplossing: Focus beperken tot reis-API's.)*  
**Extra abstractielaag:** Dit voegt een kleine overhead toe, maar is hier gerechtvaardigd door de complexiteit die wordt verborgen.  

## **Toegepaste ontwerpprincipes**  

**Encapsulatie:** De interne werking van API-communicatie wordt verborgen achter een interface.  
**Information Hiding:** Clients hoeven alleen te weten *wat* de Facade doet, niet *hoe*.  
**Single Responsibility Principle (SRP):** De Facade biedt een vereenvoudigde interface, terwijl de onderliggende logica in de implementatie zit. *(Let op: de Facade moet niet te veel verantwoordelijkheden krijgen!)*   
**Law of Demeter:** Clientcode communiceert alleen met de Facade en niet direct met externe API's of de `Unirest`-bibliotheek.  
