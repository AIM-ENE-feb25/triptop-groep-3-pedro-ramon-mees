# Software Guidebook Triptop

## 1. Inleiding

Dit software guidebook geeft een overzicht van de Triptop-applicatie. Het bevat een samenvatting van de vereisten, beperkingen en principes die als basis dienen voor het project.

Daarnaast beschrijft het de software-architectuur, waarbij zowel de technologiekeuzes op hoog niveau als de structurele opbouw van de software worden toegelicht.

Het document gaat ook in op de belangrijkste ontwerp- en codebeslissingen die genomen zijn om de software te realiseren zoals beoogd. Tot slot wordt de infrastructurele architectuur behandeld, inclusief een gedetailleerde uitleg over hoe de software geïnstalleerd, geconfigureerd en uitgevoerd kan worden voor gebruik.

### 1.1 Inhoudsopgave

Dit software guidebook bestaat uit de volgende hoofdstukken:

- [Software Guidebook Triptop](#software-guidebook-triptop)
  - [1. Inleiding](#1-inleiding)
    - [1.1 Inhoudsopgave](#11-inhoudsopgave)
    - [1.2 Leeswijzer](#12-leeswijzer)
  - [2. Context](#2-context)
    - [2.1 Contextdiagram](#21-contextdiagram)
  - [3. Functioneel Overzicht](#3-functioneel-overzicht)
    - [3.1 User Stories](#31-user-stories)
      - [3.1.1 User Story 1: Reis plannen](#311-user-story-1-reis-plannen)
      - [3.1.2 User Story 2: Reis boeken](#312-user-story-2-reis-boeken)
      - [3.1.3 User Story 3: Reis cancelen](#313-user-story-3-reis-cancelen)
      - [3.1.4 User Story 4: Reisstatus bewaren](#314-user-story-4-reisstatus-bewaren)
      - [3.1.5 User Story 5: Bouwstenen flexibel uitbreiden](#315-user-story-5-bouwstenen-flexibel-uitbreiden)
    - [3.2 Domain Story Reis Boeken (AS IS)](#32-domain-story-reis-boeken-as-is)
    - [3.3 Domain Story Reis Boeken (TO BE)](#33-domain-story-reis-boeken-to-be)
    - [3.4 Domeinmodel](#34-domeinmodel)
  - [4. Kwaliteitsattributen](#4-kwaliteitsattributen)
  - [5. Beperkingen](#5-beperkingen)
    - [5.1. Prototype vragen](#51-prototype-vragen)
      - [5.1.1. Modularity](#511-modularity)
      - [5.1.2. Fault Tolerance](#512-fault-tolerance)
      - [5.1.3. Fault Tolerance](#513-fault-tolerance)
  - [6. Principes](#6-principes)
    - [6.1. Ontwerpprincipes](#61-ontwerpprincipes)
      - [6.1.1 Single Responsibility Principle (SRP)](#611-single-responsibility-principle-srp)
      - [6.1.2 Open/Closed Principle (OCP)](#612-openclosed-principle-ocp)
      - [6.1.3 Dependency Inversion Principle (DIP)](#613-dependency-inversion-principle-dip)
      - [6.1.4 Program to Interfaces, Not Implementations](#614-program-to-interfaces-not-implementations)
      - [6.1.5. Separation of Concerns (SoC)](#615-separation-of-concerns-soc)
      - [6.1.6. Composition Over Inheritance](#616-composition-over-inheritance)
      - [6.1.7. DRY (Don't Repeat Yourself)](#617-dry-dont-repeat-yourself)
      - [6.1.8. Encapsulatie](#618-encapsulatie)
      - [6.1.9. Information Hiding](#619-information-hiding)
    - [6.2. Ontwerppatronen](#62-ontwerppatronen)
      - [6.2.1. Facade](#621-facade)
      - [6.2.2. Adapter](#622-adapter)
      - [6.2.3. Strategy](#623-strategy)
      - [6.2.4. Factory method](#624-factory-method)
  - [7. Software Architectuur](#7-software-architectuur)
    - [7.1. Containerdiagram](#71-containerdiagram)
      - [7.1.1. Dynamische Diagram: Haal beste vluchten op](#711-dynamische-diagram-haal-beste-vluchten-op)
    - [7.2. Componenten](#72-componenten)
      - [7.2.1 Componentdiagram: Adapter \& Factory](#721-componentdiagram-adapter--factory)
      - [7.2.2 Componentdiagram: Facade](#722-componentdiagram-facade)
      - [7.2.3 Componentdiagram: Strategy](#723-componentdiagram-strategy)
      - [7.2.4 Code Diagram: Strategy](#724-code-diagram-strategy)
      - [7.2.5 Sequentiediagram: Strategy](#725-sequentiediagram-strategy)
      - [7.2.6 Componentdiagram: alle patterns](#726-componentdiagram-alle-patterns)
    - [7.3 Design \& Code](#73-design--code)
      - [7.3.1 API Lijst](#731-api-lijst)
      - [7.3.2 API Mapping Tabel](#732-api-mapping-tabel)
      - [7.3.3 Sequenciediagram](#733-sequenciediagram)
      - [7.3.4 Klassendiagram](#734-klassendiagram)
      - [7.3.5 Uitbreidbaarheid met Nieuwe API's](#735-uitbreidbaarheid-met-nieuwe-apis)
  - [8. Architectuurbeslissingsrecords](#8-architectuurbeslissingsrecords)
    - [8.1. ADR-001 Postgres database](#81-adr-001-postgres-database)
      - [Auteur](#auteur)
      - [Context](#context)
      - [Overwogen opties](#overwogen-opties)
      - [Keuze](#keuze)
      - [Status](#status)
      - [Gevolgen](#gevolgen)
    - [8.2. ADR-002 Strategy pattern](#82-adr-002-strategy-pattern)
      - [Auteur](#auteur-1)
      - [Context](#context-1)
      - [Overwogen opties](#overwogen-opties-1)
      - [Keuze](#keuze-1)
      - [Status](#status-1)
      - [Gevolgen](#gevolgen-1)
    - [8.3. ADR-003 Stripe API Test Modus](#83-adr-003-stripe-api-test-modus)
      - [Auteur:](#auteur-2)
      - [Status](#status-2)
      - [Context](#context-2)
      - [Besluit](#besluit)
    - [Gevolgen](#gevolgen-2)
      - [Positief:](#positief)
      - [Negatief:](#negatief)
      - [Implementatiedetails](#implementatiedetails)
    - [8.4. ADR-004 API Gateway Pattern voor externe API-integratie](#84-adr-004-api-gateway-pattern-voor-externe-api-integratie)
      - [Auteur](#auteur-3)
      - [Status](#status-3)
      - [Context](#context-3)
      - [Besluit](#besluit-1)
    - [Gevolgen](#gevolgen-3)
      - [Positief:](#positief-1)
      - [Negatief:](#negatief-1)
      - [Implementatiedetails](#implementatiedetails-1)
    - [8.5. ADR-005 Toepassen van het Facade-patroon](#85-adr-005-toepassen-van-het-facade-patroon)
      - [Auteur](#auteur-4)
      - [Status](#status-4)
      - [Context](#context-4)
      - [Beslissing](#beslissing)
      - [Gevolgen](#gevolgen-4)
        - [Positief:](#positief-2)
        - [Negatief:](#negatief-2)
      - [Toegepaste ontwerpprincipes](#toegepaste-ontwerpprincipes)
    - [8.6. ADR-006 Passend pattern kiezen voor bij "Fallback" onderzoeksvraag](#86-adr-006-passend-pattern-kiezen-voor-bij-fallback-onderzoeksvraag)
      - [Auteur](#auteur-5)
      - [Status](#status-5)
      - [Context](#context-5)
      - [Besluit](#besluit-2)
      - [Gevolgen](#gevolgen-5)
        - [Positief:](#positief-3)
        - [Negatief:](#negatief-3)
      - [Implementatiedetails](#implementatiedetails-2)
    - [8.7. ADR-007 Implementatie van Adapter Pattern voor betalingsintegraties](#87-adr-007-implementatie-van-adapter-pattern-voor-betalingsintegraties)
      - [Auteur](#auteur-6)
      - [Status](#status-6)
      - [Context](#context-6)
      - [Besluit](#besluit-3)
      - [Gevolgen](#gevolgen-6)
        - [Positief:](#positief-4)
        - [Negatief:](#negatief-4)
      - [Implementatiedetails](#implementatiedetails-3)
  - [9. Installatie, Werking en Ondersteuning](#9-installatie-werking-en-ondersteuning)
    - [9.1 Triptrop applicatie (walking skeleton)](#91-triptrop-applicatie-walking-skeleton)
      - [9.1.1 Bouwen](#911-bouwen)
      - [9.1.2 Uitvoeren](#912-uitvoeren)
    - [9.2 API Prototype Ramon](#92-api-prototype-ramon)
      - [9.2.1 Bouwen](#921-bouwen)
      - [9.2.2 Uitvoeren](#922-uitvoeren)

### 1.2 Leeswijzer

Dit software guidebook bestaat uit de volgende hoofdstukken:

1. **Inleiding**: Een kort overzicht van het document en wat hierin te vinden is.

2. **Context**: Beschrijft de context van het Triptop systeem met een contextdiagram dat de relaties toont tussen Triptop en externe systemen zoals HotelAPI en FlightAPI.

3. **Functioneel Overzicht**: Geeft een functioneel overzicht via user stories, domain stories (zowel AS-IS als TO-BE) en een domeinmodel.

4. **Kwaliteitsattributen**: Beschrijft de belangrijkste kwaliteitsattributen op basis van ISO 25010, waaronder Compatibility, Reliability, Maintainability en Security.

5. **Beperkingen**: Beschrijft de beperkingen van het project, waaronder de vragen die het prototype moet beantwoorden met betrekking tot Interoperability en Fault Tolerance.

6. **Principes**: Documenteert de ontwerpprincipes zoals Single Responsibility, Open/Closed, Dependency Inversion en de toegepaste ontwerppatronen (Facade, Adapter, Strategy, Factory method).

7. **Software Architectuur**: Beschrijft de softwarearchitectuur via diverse diagrammen:
   - Containers en Componenten
   - Design & Code details inclusief API Lijst en API Mapping Table
   - Sequence Diagram, Class Diagram
   - Een uitleg over uitbreidbaarheid met nieuwe API's

8. **Architectuurbeslissingsrecords**: Documenteert de architecturale beslissingen:
   - ADR-001: Postgres database
   - ADR-002: Strategy pattern
   - ADR-003: Stripe API Test Modus
   - ADR-004: API Gateway Pattern (deprecated)
   - ADR-005: Toepassen van het Facade-patroon
   - ADR-006: Passend pattern voor "Fallback"
   - ADR-007: Adapter Pattern voor betalingsintegraties

9. **Installatie, Werking en Ondersteuning**: Beschrijft hoe de applicatie geïnstalleerd, uitgevoerd en gedeployed kan worden, inclusief stappen voor lokale ontwikkeling en productie-omgevingen.

---

## 2. Context

### 2.1 Contextdiagram

![Context Diagram](New_diagrams/Context_diagram.png)

Het contextdiagram bevat de volgende hoofdcomponenten:

1. **Centraal systeem: "Triptop"**  

2. **Externe entiteiten:**  
   - **External HotelAPI**: Verwerkt boekingen binnen het systeem.  
   - **External FlightAPI**: Regelt het transport voor de reiziger.  

3. **Relaties en datastromen:**  
   - Triptop communiceert met verschillende services om processen zoals boekingen te beheren.  
   - Reizigers en reisagenten hebben interactie met Triptop via een gebruikersinterface.

---

## 3. Functioneel Overzicht

Om de belangrijkste features toe te lichten zijn er user stories en twee domain stories gemaakt en een overzicht van het domein in de vorm van een domeinmodel. Op deze plek staat typisch een user story map maar die ontbreekt in dit voorbeeld.

### 3.1 User Stories

#### 3.1.1 User Story 1: Reis plannen

Als gebruiker wil ik een zelfstandig op basis van diverse variabelen (bouwstenen) een reis kunnen plannen op basis van mijn reisvoorkeuren (wel/niet duurzaam reizen, budget/prijsklasse, 's nachts reizen of overdag etc.) zodat ik op vakantie kan gaan zonder dat hiervoor een reisbureau benodigd is.

#### 3.1.2 User Story 2: Reis boeken

Als gebruiker wil ik een geplande reis als geheel of per variabele (bouwsteen) boeken en betalen zodat ik op vakantie kan gaan zonder dat hiervoor een reisbureau benodigd is.

#### 3.1.3 User Story 3: Reis cancelen

Als gebruiker wil ik een geboekte reis, of delen daarvan, kunnen annuleren zodat ik mijn geld terug kan krijgen zonder inmenging van een intermediair zoals een reisbureau.

#### 3.1.4 User Story 4: Reisstatus bewaren 

Als gebruiker wil ik mijn reisstatus kunnen bewaren zonder dat ik een extra account hoef aan te maken zodat ik mijn reis kan volgen zonder dat ik daarvoor extra handelingen moet verrichten.

#### 3.1.5 User Story 5: Bouwstenen flexibel uitbreiden

Als gebruiker wil ik de bouwstenen van mijn reis flexibel kunnen uitbreiden met een zelf te managen stap (bijv. met providers die niet standaard worden aangeboden zoals een andere reisorganisatie, hotelketen etc.) zodat ik mijn reis helemaal kan aanpassen aan mijn wensen.

### 3.2 Domain Story Reis Boeken (AS IS)

![Domain Story Reis Boeken AS IS](../opdracht-diagrammen/reis-boeken-asis-coursegrained_2024-06-11.egn.svg)

### 3.3 Domain Story Reis Boeken (TO BE)

![Domain Story Reis Boeken TO BE](../opdracht-diagrammen/reis-boeken-tobe-coursegrained_2024-06-11.egn.svg)

### 3.4 Domeinmodel

![Domain Model](../opdracht-diagrammen/Domain%20Model.png)

---

## 4. Kwaliteitsattributen

Voordat deze casusomschrijving tot stand kwam, heeft de opdrachtgever de volgende ISO 25010 kwaliteitsattributen benoemd als belangrijk:

* Compatibility -> Interoperability (Degree to which a system, product or component can exchange information with other products and mutually use the information that has been exchanged)
* Reliability -> Fault Tolerance (Degree to which a system or component operates as intended despite the presence of hardware or software faults)
* Maintainability -> Modularity (Degree to which a system or computer program is composed of discrete components such that a change to one component has minimal impact on other components)
* Maintainability -> Modifiability (Degree to which a product or system can be effectively and efficiently modified without introducing defects or degrading existing product quality)
* Security -> Integrity (Degree to which a system, product or component ensures that the state of its system and data are protected from unauthorized modification or deletion either by malicious action or computer error)
* Security -> Confidentiality (Degree to which a system, product or component ensures that data are accessible only to those authorized to have access)

---

## 5. Beperkingen

Initieel heeft het team uit een lijst met ontwerpvragen enkele kernvraagstukken geselecteerd die relevant zijn voor de beperkingen van het Triptop-systeem. Elk teamlid heeft één of meerdere van deze vragen onderzocht om passende oplossingen te vinden voor de architecturale uitdagingen. Deze onderzoeken hebben geleid tot concrete implementaties in het prototype en zijn gedocumenteerd in de vorm van [Architectuurbeslissingsrecords](#8-architectuurbeslissingsrecords) (Architectural Decision Records, ADRs).

### 5.1. Prototype vragen

De prototypes vormen samen een walking skeleton en moeten binnen 3 weken worden afgemaakt door 3 ontwikkelaars.

Het project moet met prototypes de volgende vragen kunnen beantwoorden:

#### 5.1.1. Modularity

Hoe zorg je ervoor dat je makkelijk de ene externe service kan vervangen door een andere die ongeveer hetzelfde doet?
(Zie [ADR-005](#85-adr-005-toepassen-van-het-facade-patroon))

#### 5.1.2. Fault Tolerance

Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?
(zie [ADR-002](#82-adr-002-strategy-pattern))

#### 5.1.3. Fault Tolerance

Hoe kunnen we ervoor zorgen dat bepaalde bouwstenen automatisch een alternatieve dienst kiezen als de primaire dienst niet beschikbaar is?
(Zie [ADR-006](#86-adr-006-passend-pattern-kiezen-voor-bij-fallback-onderzoeksvraag) en [ADR-007](#87-adr-007-implementatie-van-adapter-pattern-voor-betalingsintegraties) voor voorbeelden met de `PaymentAdapterFactory`).

---

## 6. Principes

Dit hoofdstuk beschrijft de belangrijkste ontwerpprincipes en ontwerppatronen die zijn toegepast in de Triptop-architectuur.
Deze principes dienen als fundamentele richtlijnen voor onze softwareontwikkeling en zorgen voor een hoge kwaliteit, onderhoudbaarheid en uitbreidbaarheid van het systeem.

### 6.1. Ontwerpprincipes

Elk principe wordt toegelicht met concrete voorbeelden uit de codebase, samen met de belangrijkste ontwerpvoor- en nadelen die ze bevorderen.

#### 6.1.1 Single Responsibility Principle (SRP)

Elke klasse heeft één enkele verantwoordelijkheid. Bijvoorbeeld:

- `IFlightAdapter` definieert het contract voor vluchtadapters.  
- `SkyscannerAdapter` kapselt de logica in voor interactie met de Skyscanner API.  
- `FlightFacade` coördineert de interactie tussen adapters en strategieën.  
- `CheapestFlightStrategy` en `FastestFlightStrategy` richten zich op specifieke selectiecriteria.
- `ApiContext` zorgt voor schakeling tussen cache en realtime data als externe API niet beschikbaar is middels het strategy design pattern

**Design Properties**: Dit principe bevordert **Cohesion** en **Separation of Concerns**, wat leidt tot betere onderhoudbaarheid en testbaarheid.

#### 6.1.2 Open/Closed Principle (OCP)  

Het systeem is open voor uitbreiding, maar gesloten voor wijzigingen. Bijvoorbeeld:  

- Nieuwe adapters kunnen worden toegevoegd door de `IFlightAdapter`-interface te implementeren zonder bestaande code aan te passen.  
- Nieuwe strategieën kunnen worden geïntroduceerd door de `IFlightSearchStrategy`-interface te implementeren.  

**Design Properties**: Dit principe bevordert **Extensibility** en vermindert het risico van regressiefouten bij het toevoegen van nieuwe functionaliteit.

#### 6.1.3 Dependency Inversion Principle (DIP)  

Hoog-niveau modules (bijv. `FlightFacade`) zijn afhankelijk van abstracties (`IFlightAdapter`, `IFlightSearchStrategy`) in plaats van concrete implementaties.  

**Design Properties**: Dit principe bevordert lage **Coupling**, wat leidt tot betere **Extensibility** en testbaarheid.

#### 6.1.4 Program to Interfaces, Not Implementations

Het systeem is gebaseerd op interfaces (`IFlightAdapter`, `IFlightSearchStrategy`) in plaats van concrete klassen, wat flexibiliteit en eenvoudiger testen mogelijk maakt.  

**Design Properties**: Verhoogt **Extensibility** en vermindert **Coupling**.

#### 6.1.5. Separation of Concerns (SoC)  

Verschillende verantwoordelijkheden worden afgehandeld door verschillende componenten:  

- Adapters verwerken API-specifieke logica.  
- Factories beheren de aanmaak en beschikbaarheid van adapters.  
- Strategieën regelen de selectielogica.  
- Facades bieden een vereenvoudigde interface voor clients. 

**Design Properties**: Verhoogt **Separation of Concerns**, **Cohesion** en verbetert onderhoudbaarheid.

#### 6.1.6. Composition Over Inheritance  

Het systeem maakt gebruik van compositie (bijv. `FlightFacade` combineert adapters en strategieën) in plaats van overerving.

 **Design Properties**: Verbetert **Extensibility** en vermindert rigide hiërarchieën.

#### 6.1.7. DRY (Don't Repeat Yourself)  

Gemeenschappelijke logica (bijv. beschikbaarheidscontroles van adapters) is gecentraliseerd in factories om duplicatie te voorkomen.

**Design Properties**: Verbetert **Cohesion** en vermindert inconsistenties.  

#### 6.1.8. Encapsulatie

Implementatiedetails van externe API-interacties zijn verborgen binnen adapterklassen en bieden alleen de interface aan.

**Design Properties**: Verhoogt **Information Hiding** en vermindert **Coupling**.

#### 6.1.9. Information Hiding

Clients hoeven alleen te weten *wat* de Facade doet, niet *hoe*.

**Design Properties**: Bevordert **Information Hiding** en vermindert complexiteit voor clients.

### 6.2. Ontwerppatronen

#### 6.2.1. Facade

Domeinspecifieke interface (bijv. FlightFacade, HotelFacade) die de complexiteit verbergt van interactie met meerdere onderliggende adapters en selectie-logica toepast. Clients werken met Facades (Zie [ADR-005](#85-adr-005-toepassen-van-het-facade-patroon)).

#### 6.2.2. Adapter

Zet de interface van een specifieke externe dienst (bijv. Skyscanner API, Booking.com API, Stripe API) om naar een standaardinterface binnen Triptop (bijv. IFlightAdapter, IHotelAdapter). Elke externe dienst krijgt een eigen Adapter. (Zie [ADR-007](#87-adr-007-implementatie-van-adapter-pattern-voor-betalingsintegraties)).

#### 6.2.3. Strategy

Definieert een groep algoritmes voor het verwerken of selecteren van resultaten (bijv. het vinden van de goedkoopste vlucht, de snelste vlucht of het best beoordeelde hotel). De Facade bevat en gebruikt een specifieke Strategy-instantie om te werken met de verzamelde gegevens uit de Adapters (Zie [ADR-002](#82-adr-002-strategy-pattern)).

#### 6.2.4. Factory method

Verantwoordelijk voor het aanmaken van Adapter-instanties. Dit ontkoppelt de Facade van concrete adapterimplementaties en kan adapterconfiguraties of beschikbaarheid beheren (bijv. met een Circuit Breaker per adapter). (Zie het PaymentAdapterFactory-voorbeeld) (Zie [ADR-006](#86-adr-006-passend-pattern-kiezen-voor-bij-fallback-onderzoeksvraag)) .

---

## 7. Software Architectuur

### 7.1. Containerdiagram

![alt text](New_diagrams/Container_diagram.svg)

>Elke aanbieder heeft zijn eigen API-specificaties, wat leidt tot complexiteit en verlies van overaciht.
> 
> In het model hebben we verschillende Vervoer API als samengestelde entiteiten gedefinieerd. Dit is gedaan om de complexiteit van het systeem te vereenvoudigen en de overzichtelijkheid te behouden. In werkelijkheid bestaan deze services uit meerdere afzonderlijke API’s van verschillende providers.
>   De FlightAPI in het model vertegenwoordigt meerdere reis-diensten waarmee gebruikers tickets kunnen boeken bij Triptop. In werkelijkheid zou Triptop zich verbinden met verschillende externe APIs Zie ![7.3.1 API Lijst](#731-api-lijst), zoals:
- Booking.com 
- Tripadvisor


#### 7.1.1. Dynamische Diagram: Haal beste vluchten op

> **Begeleidende tekst Dynamic Diagram (Reis Plannen):**
Dit diagram laat de interacties zien tussen de gebruiker, de Triptop applicatie en externe services tijdens het plannen van een reis. De gebruiker start het proces, waarna Triptop via de Facade laag communiceert met verschillende Adapters (bijvoorbeeld voor vluchten, hotels) die op hun beurt externe API's aanroepen. De resultaten worden verzameld en via een Strategy (bijvoorbeeld 'goedkoopste eerst') gepresenteerd aan de gebruiker.

![alt text](New_diagrams/Dynamic_Diagram_Flights.svg)

### 7.2. Componenten

#### 7.2.1 Componentdiagram: Adapter & Factory

![alt text](New_diagrams/Factory/factory_component.svg)

![alt text](New_diagrams/Adapter/adapter_Component.svg)
Voor de onderzoeksvraag behandeld in [ADR-006: Passend pattern kiezen voor bij "Fallback" onderzoeksvraag](#86-adr-006-passend-pattern-kiezen-voor-bij-fallback-onderzoeksvraag), willen we het Factory Method & Adapter patroon toepassen in het volgende component diagram:

> **Begeleidende tekst Component Diagram (Adapter & Factory):**  
Dit diagram toont hoe het Adapter en Factory Method patroon samenwerken binnen de architectuur. De Factory Method zorgt voor het dynamisch leveren van de juiste Adapter-implementatie, afhankelijk van de context of beschikbaarheid. De Adapters zelf dienen als tussenlaag tussen de applicatie en externe API's, waardoor de complexiteit van API-specifieke logica wordt geïsoleerd. Dit maakt het systeem flexibel en uitbreidbaar, omdat nieuwe API's eenvoudig kunnen worden toegevoegd door nieuwe Adapters te implementeren.

#### 7.2.2 Componentdiagram: Facade

![alt text](New_diagrams/Facade/facade_Component.svg)

> **Begeleidende tekst Component Diagram (Facade):**  
Het Facade patroon wordt gebruikt om een vereenvoudigde klasse te bieden aan de subsystemen van de applicatie. In dit diagram zien we hoe de Facade werkt als een centrale toegangspoort voor de client, terwijl het de interacties met de onderliggende Adapters en Strategies beheerd. Dit patroon helpt bij het verminderen van de afhankelijkheden tussen de client en de interne structuur van het systeem.

#### 7.2.3 Componentdiagram: Strategy

Voor de onderzoeksvraag behandeld in [ADR-002: Strategy pattern](#82-adr-002-strategy-pattern) ("Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?"), willen we het strategy pattern toepassen. Dit resulteert in het volgende component diagram:

![component_diagram_onderzoeksvraag1_ramon.svg](../opdracht-diagrammen/component_diagram_onderzoeksvraag1_ramon.svg)

Toelichting:
  - `CacheRepository` en `ApiRepository` zijn hier de 'strategies' die de `ApiStrategy` interface implementeren.

#### 7.2.4 Code Diagram: Strategy

Als we gaan inzoomen op het code niveau krijgen we het volgende code diagram:

![code_diagram_ramon.svg](../opdracht-diagrammen/code_diagram_ramon.svg)

#### 7.2.5 Sequentiediagram: Strategy

![sequence_diagram_ramon.svg](../opdracht-diagrammen/sequence_diagram_ramon.svg)

Toelichting:
  - De aanroep voor `apiRepository.isAvailable()` wordt niet op de `ApiStrategy` interface uitgevoerd maar direct op de `ApiRepository`, dit is puur een persoonlijk voorkeur.

#### 7.2.6 Componentdiagram: alle patterns

![alt text](New_diagrams/Overarching.svg)

### 7.3 Design & Code

#### 7.3.1 API Lijst

> [!NOTE]
> De onderstaande lijst toont een overzicht van potentiële externe API's die geïntegreerd *kunnen* worden met Triptop via de Adapter-structuur. Voor het huidige prototype zijn voornamelijk **Booking.com** (voor overnachtingen) en **TripAdvisor** (voor activiteiten) actief geïmplementeerd als voorbeeld. De architectuur is echter ontworpen om eenvoudig uitbreidbaar te zijn met andere aanbieders. Zie [Sectie 7.3.3](#733-uitbreidbaarheid-met-nieuwe-apis) voor hoe je een nieuwe API toevoegt.


**Overnachtingen**

* **Trawix Hotel API**
  * **Requirements**:  
    * (Affiliate) Application needed  


**Vervoer**

* **TripAdvisor API**
  * **Base URL**: https://api.tripadvisor.com/api  
  * **Pricing**:  
    * 5k API calls per month for free, overages will be charged  

* **GetYourGuide API**
  * **Requirements**:  
    * (Affiliate) Application needed  

* **Trawix Sightseeing API**
  * **Requirements**:  
    * (Affiliate) Application needed  

**Vervoer**

* **TripAdvisor API**
  * **Base URL**: https://api.tripadvisor.com/api  
  * **Pricing**:  
    * 5k API calls per month for free, overages will be charged  

* **Booking.com - Demand API**
  * **Base URL**: https://developers.booking.com/api  
  * **Requirements**:  
    * Booking.com Affiliate Partnership needed ([free signup](https://spadmin.booking.com/pc/sign-up.html?lang=en))  

* **Google Maps - Routes API**
  * **Base URL**: https://maps.googleapis.com/maps/api  
  * **Requirements**:  
    * Credit Card needed  
  * **Pricing**:  
    * 0 - 10,000 free events cap, overages will be charged  

* **NS API**
  * **Base URL**: https://www.ns.nl/en/travel-information/ns-api  
  * **Description**:  
    * Dutch railways information and booking  

* **Amadeus (Asmodeus)**
  * **Base URL**: https://api.amadeus.com/v1  
  * **Description**:  
    * Flight information and booking  



#### 7.3.2 API Mapping Tabel

| Class::Attribuut           | Is input voor API+Endpoint         | Wordt gevuld door API+Eindpoint | Wordt geleverd door eindgebruiker | Moet worden opgeslagen in de applicatie |
|----------------------------|---------------------------------|--------------------------------|---------------------------------|---------------------------------|
| FlightOffer::departureDate | Flight API /flight-offers (GET) | x                              | x                               | x                               |
| FlightOffer::origin        | Flight API /flight-offers (GET) | x                              | x                               | x                               |
| FlightOffer::destination   | Flight API /flight-offers (GET) | x                              | x                               | x                               |
| FlightOffer::adults        | Flight API /flight-offers (GET) | x                              | x                               |                                 |
| `Verblijf::startDatum`    | `https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels` |  | x | x |
| `Verblijf::eindDatum`     | `https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels` |  | x | x |
| `Verblijfplaats::locatie` |   | `https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?` |  | x |
| `Verblijfplaats::prijs`   |   | `https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels` |  | x |
| ProductCreateParams::name | Stripe API /? | x                              |                                | x                               |
| ProductCreateParams::description        | Stripe API /? | x                              |                                | x                               |
| Product::create   | Stripe API /? | x                              |                                | x                               |
| CustomerCreateParams::name   | Stripe API /? | x                              | x                             |                                |
| CustomerCreateParams::email   | Stripe API /? | x                              | x                               |                                |
| Customer::create   | Stripe API /? | x                              |                                | x                               |
| InvoiceCreateParams::customer   | Stripe API /? | x                              |                                | x                               |
| Invoice::create   | Stripe API /? | x                              |                                | x                               |
_Stripe endpoint addressen worden niet gegeven in de documentatie._

#### 7.3.3 Sequenciediagram

![Sequence Diagram](New_diagrams/sequence_diagram.svg)

**Begeleidende tekst Sequence Diagram (7.3.3):**  
Dit diagram toont de interacties tussen de verschillende componenten tijdens het zoeken naar de beste vlucht. De client roept de Facade aan, die via de Factory beschikbare Adapters ophaalt. Elke Adapter communiceert met een externe API om vluchtgegevens op te halen. De Facade verzamelt de resultaten en gebruikt een Strategy om de beste vlucht te bepalen, die uiteindelijk aan de client wordt teruggegeven.

#### 7.3.4 Klassendiagram

![Class Diagram](New_diagrams/Class_Diagram.svg)

**Begeleidende tekst Class Diagram (7.3.4):**
Dit diagram geeft een overzicht van de klassenstructuur voor het zoeken naar vluchten. Het toont de interfaces (`IFlightAdapter`, `IFlightSearchStrategy`), concrete implementaties (zoals `SkyscannerAdapter` en `CheapestFlightStrategy`), en de Facade (`FlightFacade`) die de interactie tussen de client en de adapters/strategieën beheert. De Factory (`FlightAdapterFactory`) zorgt voor het leveren van beschikbare adapters, terwijl de Facade de strategie gebruikt om de beste vlucht te selecteren.

#### 7.3.5 Uitbreidbaarheid met Nieuwe API's

Een belangrijk doel van de gekozen architectuur (met Facades, Adapters en Factories) is de **uitbreidbaarheid**. Het toevoegen van een nieuwe externe dienst (bijvoorbeeld een extra hotelprovider zoals Expedia, of een andere vluchtaanbieder) is relatief eenvoudig en vereist minimale aanpassingen aan de bestaande code buiten de nieuwe Adapter zelf.

**Stappen voor het toevoegen van een nieuwe API (bijv. Expedia voor hotels):**

1.  **Identificeer/Gebruik de Standaard Interface:** Bepaal welke bestaande interface de nieuwe dienst moet implementeren. Voor hotels is dit `IHotelAdapter`. Als er nog geen interface bestaat voor het domein (bijv. autoverhuur), definieer dan eerst een nieuwe interface (bv. `IRentalCarAdapter`).
    ```java
    // Voorbeeld: Bestaande interface (vereenvoudigd)
    package ese.triptop.features.hotels.adapter;
    import ese.triptop.features.hotels.domain.Hotel;
    import java.util.List;

    public interface IHotelAdapter {
        List<Hotel> getHotels(String location, String checkInDate, String checkOutDate, int guests);
        boolean isAvailable();
    }
    ```

2.  **Implementeer de Concrete Adapter:** Maak een nieuwe klasse die de interface implementeert. Deze klasse bevat alle logica specifiek voor de nieuwe API (authenticatie, request opbouw, response parsing, mapping naar Triptop's domeinmodel).
    ```java
    package ese.triptop.features.hotels.adapter; // Plaats in de juiste package

    import ese.triptop.features.hotels.domain.Hotel;
    import org.springframework.stereotype.Component; // Belangrijk voor Spring DI / Factory
    import java.util.List;
    import java.util.ArrayList;
    // Importeer je HTTP client (bv. kong.unirest.core.*)

    @Component // Maakt de adapter vindbaar voor de HotelAdapterFactory (via Spring DI)
    public class ExpediaHotelAdapter implements IHotelAdapter {

        private static final String EXPEDIA_API_ENDPOINT = "https://api.expedia.com/v2/hotels"; // Fictief endpoint
        private static final String API_KEY = System.getenv("EXPEDIA_API_KEY"); // Laad API key veilig (bv. via environment variable)

        @Override
        public List<Hotel> getHotels(String location, String checkInDate, String checkOutDate, int guests) {
            List<Hotel> hotels = new ArrayList<>();
            System.out.println("[ExpediaHotelAdapter] Searching hotels via Expedia API for: " + location);

            if (API_KEY == null || API_KEY.isEmpty()) {
                 System.err.println("[ExpediaHotelAdapter] API Key not configured.");
                 return hotels; // Return empty list if key is missing
            }

            try {
                // === Logica specifiek voor Expedia API ===
                // 1. Bouw het request (parameters, headers met API Key)
                // HttpResponse<JsonNode> response = Unirest.get(EXPEDIA_API_ENDPOINT)
                //         .header("Authorization", "Bearer " + API_KEY)
                //         .queryString("location", location)
                //         .queryString("checkIn", checkInDate)
                //         .queryString("checkOut", checkOutDate)
                //         .queryString("guests", guests)
                //         .asJson();

                // 2. Verwerk de response
                // if (response.getStatus() == 200) {
                //     System.out.println("[ExpediaHotelAdapter] Expedia API call successful.");
                //     // 3. Map de JSON response naar Triptop's List<Hotel> objecten
                //     // hotels = mapExpediaResponseToHotels(response.getBody());
                       // Dummy data voor dit voorbeeld:
                       hotels.add(new Hotel("Expedia", "Marriott", 180.0, 4.7));
                       hotels.add(new Hotel("Expedia", "Ibis Budget", 85.0, 3.9));
                // } else {
                //     System.err.println("[ExpediaHotelAdapter] Expedia API call failed: " + response.getStatus() + " " + response.getStatusText());
                // }
                 System.out.println("[ExpediaHotelAdapter] Simulated search complete."); // Simulatie voor guidebook

            } catch (Exception e) { // Vang specifieke exceptions (bv. UnirestException)
                System.err.println("[ExpediaHotelAdapter] Error during Expedia API call: " + e.getMessage());
                // Optioneel: log de stack trace
            }
            return hotels;
        }

        @Override
        public boolean isAvailable() {
            // Implementeer een health check specifiek voor de Expedia API
            // Dit kan een simpele ping zijn of een check op een status endpoint
            System.out.println("[ExpediaHotelAdapter] Checking availability (simulated: true).");
            // Voorbeeld: probeer een simpele request
            // try {
            //    HttpResponse<String> response = Unirest.get("https://api.expedia.com/status").asString(); // Fictief status endpoint
            //    return response.getStatus() == 200;
            // } catch (Exception e) {
            //    return false;
            // }
            return true; // Simpel voorbeeld voor guidebook
        }

        // Helper methode voor het mappen van de API response naar domein objecten
        // private List<Hotel> mapExpediaResponseToHotels(JsonNode responseBody) {
        //     List<Hotel> mappedHotels = new ArrayList<>();
        //     // ... Logica om JSON te parsen en Hotel objecten te maken ...
        //     return mappedHotels;
        // }
    }
    ```

3.  **Registratie (Automatisch via `@Component`):** Door de nieuwe adapterklasse te annoteren met `@Component` (of een vergelijkbare Spring-stereotype zoals `@Service`), zal Spring's dependency injection mechanisme deze automatisch detecteren. De `HotelAdapterFactory` (of een vergelijkbare factory die alle `IHotelAdapter` beans injecteert) zal de nieuwe adapter dan automatisch meenemen wanneer `getAvailableAdapters()` wordt aangeroepen. Er zijn geen handmatige registratiestappen nodig in de Factory zelf.

4.  **Configuratie:** Zorg ervoor dat eventuele benodigde configuratie (zoals API keys) beschikbaar is voor de nieuwe adapter (bijvoorbeeld via environment variables of een configuratiebestand).

Na deze stappen zal de `HotelFacade` automatisch de nieuwe `ExpediaHotelAdapter` gebruiken (indien beschikbaar volgens de `isAvailable()` check) bij het zoeken naar hotels, zonder dat de code van de Facade of de client aangepast hoeft te worden. Dit demonstreert de kracht van de gekozen architectuur voor schaalbaarheid en onderhoudbaarheid.

---

## 8. Architectuurbeslissingsrecords

### 8.1. ADR-001 Postgres database

#### Auteur

Ramon Bijl

#### Context

Voor de TripTop applicatie willen we een hoog-beschikbare en schaalbare
backend implementeren waarbij gegevens uit meerdere API's worden opgeslagen
in een database.

#### Overwogen opties

| Factor             | MySQL            | Postgres         | MariaDB          | SQL Server       |
|--------------------|------------------|------------------|------------------|------------------|
| **Prestaties**       | Minder geschikt voor grote datasets | Sterk bij complexe queries en grote datasets | Over het algemeen sneller dan MySQL | Goede prestaties |
| **Uitbreidbaarheid** | Beperkt          | Zeer hoog | Beperkt          | Gemiddeld |
| **Licentie**         | Open-source (GPL) | Open-source (PostgreSQL License) | Open-source (GPL) | Proprietair (Microsoft) |
| **Complexiteit**     | Eenvoudig | Complexer maar krachtiger | Eenvoudig (MySQL compatible) | Gemiddelde leercurve |

#### Keuze

We hebben gekozen om gebruik te maken van Postgres omdat dit uitstekende prestaties bied
en een hoge uitbreidbaarheid heeft, dit sluit goed aan bij de wensen van onze applicatie. Daarnaast
is het gratis te gebruiken en zijn er geen licentie kosten aan verbonden wat
voor ons erg belangrijk is.

#### Status 

Geaccepteerd

#### Gevolgen

Positief:

 - Geen extra licentie kosten
 - Hoge schaalbaarheid en makkelijk uit te breiden
 - Hoge prestaties

Negatief:

 - Relatief hogere leercurve voor (nieuwe) teamleden

### 8.2. ADR-002 Strategy pattern

#### Auteur

Ramon Bijl

#### Context

We moeten gegevens ophalen die afkomstig kunnen zijn van een externe API of een
gecachte database, afhankelijk van beschikbaarheid. De externe API biedt de
meest actuele gegevens, maar kan op ieder moment onbereikbaar zijn. De
database functioneert als een alternatief voor wanneer de externe API
onbereikbaar is. We hebben een software ontwerp nodig dat tussen deze 2
opties kan schakelen, waarbij het ophalen van gegevens uit de externe API de
hogste prioriteit heeft terwijl de code schoon, onderhoudbaar en leesbaar
blijft.

#### Overwogen opties

Voor de implementatie van deze oplossing hebben we de volgende design
patterns overwogen:

| Design pattern | Flexibiliteit | Onderhoudbaarheid | Testbaarheid |
|-------|---------------|-------------------|--------------|
| **Adapter Pattern** | Hoog - Makkelijk uit te breiden zonder classes aan te passen | Gemiddeld - Het verkeerd toepassen ervan kan leiden tot moeilijk onderhoudbare code | Gemiddeld - Kan testbaarheid verhogen door gebruik te maken van mock objects of stubs |
| **Strategy Pattern** | Hoog – Eenvoudig nieuwe strategieën toe te voegen | Hoog – Scheidt verantwoordelijkheden in aparte klassen | Hoog – Strategieën kunnen onafhankelijk worden getest |
| **Factory Pattern** | Gemiddeld - Kan tot tight-coupling leiden | Gemiddeld - Kan complex worden als er veel classes worden toegevoegd | Gemiddeld - Code kan makkelijker te testen zijn |

#### Keuze

We hebben gekozen voor het Strategy Pattern omdat dit biedt:
- Een duidelijke scheiding van verantwoordelijkheden.
- De mogelijkheid om gemakkelijk opties uit te breiden en te wijzigen.
- Verbeterde testbaarheid.
- Lage complexiteit ten opzichte van andere design patterns.

#### Status

Geaccepteerd

#### Gevolgen

Positief:

 - Verbeterde onderhoudbaarheid en uitbreidbaarheid.
 - Maken van unit-tests gaat makkelijker
 - Duidelijke scheiding van verantwoordelijkheden.

Negatief:

 - Vereist dat ontwikkelaars bekend zijn met het Strategy Pattern, dit kan extra tijd kosten.


### 8.3. ADR-003 Stripe API Test Modus

#### Auteur: 
Mees van Aarsen

#### Status
_**Geaccepteerd**_
#### Context
Ik ga een prototype feature opzetten voor het verwerken van betalingen. Voor het opzetten van een prototype waren meerdere API opties beschikbaar (RapidAPI, Stripe API). 

- De RapidAPI geeft enkel mockdata terug.
- De Stripe API heeft een test modus, waarin men direct gebruik kan maken van de echte API. Zonder de kans dat men gefactureerd wordt voor gebruik.

#### Besluit
Ik ga het prototype ontwikkelen doormiddel van de Stripe API. Daarmee kan men zonder de mogelijkheid gefactureerd te worden hen integratie ontwikkelen en testen.

### Gevolgen

#### Positief:

- Alle functionaliteit wordt volgens de door Stripe gegeven modellen ontwikkeld
- Implementatie zal direct van prototype naar product over kunnen gaan.

#### Negatief:

- Vergroot mogelijk de data-strucuur van de DB met Stripe modellen.
- Contact naar Stripe gaat via de Stripe.class, geen direct zicht op de endpoints.
- Vereist ontwikkeling en onderhoud van adapter-services

#### Implementatiedetails

- Voor elke categorie externe services (vervoer, betalingen, authenticatie) ontwikkelen we een dedicated adapter-service
- Elke adapter implementeert een standaard interface die onze core backend gebruikt
- Adapters vertalen de specifieke formaten/protocollen van de Stripe API naar ons interne datamodel


### 8.4. ADR-004 API Gateway Pattern voor externe API-integratie

#### Auteur
Pedro van Douveren

> vervangen door een ander, refactor

> **Interoperability:** "Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie?

#### Status
_**Deprecated**_
#### Context
> Triptop integreert met meerdere externe APIs (vervoersaanbieders, betalingssystemen, identity providers). Wijzigingen in deze APIs kunnen grote impact hebben op onze applicatie als we deze direct integreren. 
> We moeten een manier vinden om wijzigingen in externe APIs op te vangen zonder dat dit leidt tot grootschalige aanpassingen in onze front-end of core back-end systemen.
#### Besluit
> We implementeren een API Gateway pattern waarbij alle communicatie met externe diensten via speciale adapter-services verloopt. 
> Deze services vormen een abstraherende laag tussen onze applicatie en externe APIs.

### Gevolgen



#### Positief:

- Wijzigingen in externe APIs worden opgevangen in de gateway/adapter laag
- Front-end communiceert alleen met onze eigen gestandardiseerde interne API
- Eenvoudiger monitoring van externe API-aanroepen op één plaats
- Maakt A/B testing tussen verschillende externe providers mogelijk


#### Negatief:

- Extra architectuurlaag verhoogt complexiteit
- Potentiële performance overhead
- Vereist ontwikkeling en onderhoud van adapter-services



#### Implementatiedetails

- Voor elke categorie externe services (vervoer, betalingen, authenticatie) ontwikkelen we een dedicated adapter-service
- Elke adapter implementeert een standaard interface die onze core backend gebruikt
- Adapters vertalen de specifieke formaten/protocollen van externe APIs naar ons interne datamodel


### 8.5. ADR-005 Toepassen van het Facade-patroon

#### Auteur
Pedro van Douveren

>**Modularity:** Hoe zorg je ervoor dat je makkelijk de ene externe service kan vervangen door een andere die ongeveer hetzelfde doet?
#### Status
_**Geaccepteerd**_

#### Context
> De huidige code in het pakket `ese.triptop.prototype` communiceert direct met meerdere externe APIs (Identity, Flight Offers, Booking, TripAdvisor) via de `Unirest`-bibliotheek. Dit leidt tot de volgende problemen:  
> - **Verspreide logica**: De details van API-communicatie (URL-opbouw, verzoeken maken, reacties verwerken, foutafhandeling) zijn verspreid over meerdere klassen.
> - **Herhaling**
> - **Hoge koppeling**: Clientcode is direct afhankelijk van de specifieke externe API’s en de `Unirest`-bibliotheek.
> - **Complexiteit**: Om de interactie met de APIs te begrijpen, moet men door meerdere klassen en methodes kijken.
> - **Moeilijk onderhoud**: Wijzigingen in een externe API vereisen aanpassingen op meerdere plekken. Hardcoded URLs en API-sleutels maken dit nog lastiger.

Het doel is om de interactie met deze externe diensten te vereenvoudigen en de afhankelijkheden te verminderen.

#### Beslissing
We passen het **Facade-patroon** toe om de complexiteit van communicatie met de externe reis-API’s te verbergen.  

Daarom introduceren we een **`FlightFacade`** die eenvoudige methodes aanbiedt, zoals:
- `findFlights`
- `findBestFlights`
Met een **`RestaurantFacade`** volgens dezelfde principes zou je dan bijvoorbeeld:
- `findRestaurants`

De **`FlightFacade`** zal de volgende details te verbergen:
- HTTP-verzoeken uitvoeren via `Unirest`.
- service-level logica
- specifieke implementaties foor polymorphisme

![alt text](New_diagrams/Facade/facade_Component.svg)

Clientcode communiceert alleen met de **Facade-interface**, zonder afhankelijk te zijn van de onderliggende API’s.

#### Gevolgen

##### Positief:
- **Eenvoudigere interface**: Eén toegangspunt voor interactie met externe reis-API's.
- **Lagere koppeling**: De clientcode is niet direct afhankelijk van API-details of `Unirest`.
- **Betere leesbaarheid & onderhoudbaarheid**: Alle API-logica zit op één plek. Wijzigingen in externe API’s hebben minder impact.
- **Encapsulatie**: Verbergt de complexiteit van authenticatie en API-aanroepen.

##### Negatief:
- **Mogelijke 'God Object'-valkuil**: Als de Facade te veel taken krijgt, kan het een te grote verantwoordelijkheid krijgen. *(Oplossing: Focus beperken tot reis-API's.)*
- **Extra abstractielaag**: Dit voegt een kleine overhead toe, maar is hier gerechtvaardigd door de complexiteit die wordt verborgen.

#### Toegepaste ontwerpprincipes
- **Encapsulatie**: De interne werking van API-communicatie wordt verborgen achter een interface.
- **Information Hiding**: Clients hoeven alleen te weten *wat* de Facade doet, niet *hoe*.
- **Single Responsibility Principle (SRP)**: De Facade biedt een vereenvoudigde interface, terwijl de onderliggende logica in de implementatie zit. *(Let op: de Facade moet niet te veel verantwoordelijkheden krijgen!)* 
- **Law of Demeter**: Clientcode communiceert alleen met de Facade en niet direct met externe API's of de `Unirest`-bibliotheek.


![alt text](Class_Diagram_Pedro.svg)

### 8.6. ADR-006 Passend pattern kiezen voor bij "Fallback" onderzoeksvraag

#### Auteur
Mees van Aarsen

#### Status
_**Geaccepteerd**_

#### Context
Bij Triptop moet de betalingsverwerking betrouwbaar werken, zelfs wanneer externe betalingsproviders uitvallen of onbereikbaar zijn. Onze onderzoeksvraag naar "Fallback" technieken heeft uitgewezen dat we een robuust mechanisme nodig hebben dat:

- Snel detecteert wanneer een primaire betalingsprovider (Stripe) niet beschikbaar is.
- Automatisch overschakelt naar een alternatieve provider (Paypal) zonder gebruikersinterventie.
- Periodiek controleert of de primaire provider weer beschikbaar is.
- Consistente dataformaten behoudt tussen verschillende providers.

Voor het opzetten van de bovenstaande criteria is het ICT Research Pattern ['Choose fitting technologies'](https://ictresearchmethods.nl/patterns/choose-fitting-technology/) toegepast. Doormiddel van de 5xW en 1xH vragen op te stellen als onderdeel van het "Veld" onderzoek.

[-> Pattern Onderzoek](pattern_onderzoek_mees.md)

#### Besluit

Bij het implementeren van failover tussen betalings-API's komt het Circuit Breaker als beste optie naar voren.

| Pattern → Criteria ↓       | Circuit Breaker | Retry met Exponential Backoff | Fallback Pattern | Service Discovery | Load Balancing met Health Checks | Bulkhead Pattern | API Gateway met Failover Logic |
|-----------------------------|-----------------|--------------------------------|-------------------|-------------------|-----------------------------------|------------------|---------------------------------|
| 1. Detectie binnen 5 seconden | ✅             | ✅                            | ✅               | ✅               | ✅                               | ❌              | ✅                             |
| 2. Automatische overschakeling | ✅             | ✅                            | ✅               | ✅               | ✅                               | ❌              | ✅                             |
| 3. Maximaal 5 retries        | ✅             | ✅                            | ❌               | ❌               | ❌                               | ❌              | ✅                             |
| 4. Logging van failovers     | ✅             | ✅                            | ✅               | ✅               | ✅                               | ✅              | ✅                             |
| 5. Periodieke controle primaire dienst | ✅     | ✅                            | ❌               | ✅               | ✅                               | ❌              | ✅                             |
| 6. Consistente dataformaten  | ❌             | ❌                            | ✅               | ❌               | ❌                               | ❌              | ✅                             |
| 7. Max 1 sec extra verwerkingstijd | ✅       | ❌                            | ✅               | ✅               | ✅                               | ✅              | ❌                             |

We implementeren een Circuit Breaker Pattern in combinatie met het Fallback Pattern voor onze betalingsverwerkingsmodule. Dit betekent:

1. We bouwen een `PaymentAdapterFactory` die het circuit breaker mechanisme implementeert.
2. We definiëren een generieke `IPaymentAdapter` interface voor alle betalingsproviders.
3. We creëren concrete adapter-implementaties voor Stripe (primair) en Paypal (fallback).
4. Het circuit "opent" wanneer Stripe een bepaald aantal fouten geeft binnen een tijdsperiode.
5. Wanneer het circuit open is, worden verzoeken direct doorgestuurd naar Paypal.
6. Het circuit "sluit" weer zodra een verbinding mogelijk is met Stripe.


Zie klassendiagram:

![ClassDiagram_Mees.svg](../opdracht-diagrammen/component-diagrammen/MEES/ClassDiagram_Mees.svg)

#### Gevolgen

##### Positief:

* Wijzigingen in externe APIs worden opgevangen in de adapter laag
* Front-end communiceert alleen met onze eigen gestandardiseerde interne API
* Eenvoudiger monitoring van externe API-aanroepen op één plaats
* Maakt A/B testing tussen verschillende externe providers mogelijk

##### Negatief:

* Extra architectuurlaag verhoogt complexiteit
* Potentiële performance overhead
* Vereist ontwikkeling en onderhoud van adapter-services

#### Implementatiedetails

* Voor elke categorie externe services (vervoer, betalingen, authenticatie) ontwikkelen we een dedicated adapter-service
* Elke adapter implementeert een standaard interface die onze core backend gebruikt
* Adapters vertalen de specifieke formaten/protocollen van externe APIs naar ons interne datamodel

### 8.7. ADR-007 Implementatie van Adapter Pattern voor betalingsintegraties

#### Auteur
Mees van Aarsen

#### Status
Geaccepteerd

#### Context
Na het besluit om het Circuit Breaker Pattern te implementeren voor betalingsfallback ([ADR-004](#84-adr-004-passend-pattern-kiezen-voor-bij-fallback-onderzoeksvraag)), moesten we een geschikte architectuur kiezen om verschillende betalingsproviders te integreren. We hadden behoefte aan een uniforme manier om met verschillende APIs te communiceren, waarbij elk van deze APIs zijn eigen formaten, authenticatiemethodes en endpoints heeft.

Tijdens de implementatie van het Circuit Breaker mechanisme werd duidelijk dat we een gestandaardiseerde interface nodig hadden om:

* De business logic volledig te scheiden van API-specifieke implementatiedetails
* Een consistente manier te hebben om verschillende betalingsproviders uit te wisselen
* Te zorgen dat nieuwe betalingsproviders eenvoudig kunnen worden toegevoegd

#### Besluit
We implementeren het Adapter Pattern voor alle betalingsintegraties. Dit betekent:

* We definiëren een abstracte `IPaymentAdapter` interface die de gemeenschappelijke operaties voor alle betalingsproviders beschrijft
* Voor elke betalingsprovider creëren we een specifieke adapter-klasse die deze interface implementeert
* De business logic werkt uitsluitend met de abstracte interface, zonder kennis van de concrete implementaties
* De Factory uit ADR-003 is verantwoordelijk voor het leveren van de juiste adapter-instantie

#### Gevolgen

##### Positief:

* Volledige ontkoppeling tussen business logic en externe API implementaties
* Eenvoudig toevoegen van nieuwe betalingsproviders zonder wijzigingen in de core applicatie
* Gestandaardiseerde foutafhandeling en logging over alle providers
* Mogelijkheid om mocks en test-adapters te gebruiken voor efficiënte testbaarheid
* Consistente response formats ongeacht de gebruikte provider

##### Negatief:

* Mogelijke overhead door de extra abstractielaag
* Risico op "lowest common denominator" API door gemeenschappelijke interface
* Meer code en klassen om te onderhouden
* Mogelijke complexiteit bij het mappen van provider-specifieke functies naar het gestandaardiseerde model

#### Implementatiedetails

* De `IPaymentAdapter` interface definieert methoden zoals:

  * `processInvoicePayment(InvoiceRequest request): InvoiceResponse`
  * `isAvailable(): boolean`

* De concrete adapters zoals `StripePaymentAdapter` en `PaypalPaymentAdapter` implementeren deze interface
* Elke adapter is verantwoordelijk voor:
* Mappings tussen ons interne datamodel en het provider-specifieke formaat
* Provider-specifieke authenticatie en API-aanroepen

---

## 9. Installatie, Werking en Ondersteuning

In dit hoofdstuk wordt toegelicht hoe je de prototype(s) en het walking skeleton op je eigen (lokale) machine kunt uitvoeren.
Er wordt vanuit gegaan dat je beschikt over een Linux/Unix omgeving met de volgende software:

  - Bash
  - Git
  - Maven
  - Java JDK (Benodigde versie kan afhankelijk zijn van het prototype/walking skeleton)
  - Docker
  - PostgreSQL

```bash
git clone git@github.com:AIM-ENE-feb25/triptop-groep-3-pedro-ramon-mees.git
cd triptop-groep-3-pedro-ramon-mees
```

### 9.1 Triptrop applicatie (walking skeleton)

#### 9.1.1 Bouwen

Zorg ervoor dat je Java JDK 21 of hoger hebt geinstalleerd en voer de volgende stappen uit:

```bash
cd triptop
mvn clean package
```

#### 9.1.2 Uitvoeren

```bash
mvn spring-boot:run
```

### 9.2 API Prototype Ramon

#### 9.2.1 Bouwen

Zorg ervoor dat je Java JDK 24 hebt geinstalleerd en voer de volgende stappen uit:

Voor het bouwen van de applicatie is het eerst nodig dat we de database aanmaken en opstarten:

```bash
cd postgres
# Inloggevens staan in compose.yaml
sudo docker compose up
./ins.sh # Alleen nodig voor de eerste keer om het database schema in te laden
```

Vervolgens kunnen we de applicatie bouwen door naar de root folder van de repository te navigeren en de volgende stappen uit te voeren:

```bash
cd api-proto-ramon
mvn clean package # Deze stap zal falen als je postgres niet draaiende hebt staan
```

#### 9.2.2 Uitvoeren

```bash
mvn spring-boot:run
```

Als de applicatie is opgestart kan de API worden aangeroepen via `localhost:8080`

Een voorbeeld in de vorm van een curl-script:

```bash
CITY="arnhem"

curl -X GET \
        "http://localhost:8080/hotels?city=$CITY"
```

### 9.3. API Prototype Mees

#### 9.3.1. Payment Prototype

Zorg ervoor dat je Java JDK 24 & Postman hebt geinstalleerd en voer de volgende stappen uit:

Voor het bouwen van de applicatie is het eerst nodig dat we de database aanmaken en opstarten:

```bash
cd triptop
mvn clean package
```

**Controller**

Nu is het payment endpoint toegankelijk. Pak Postman, voer daar de volgende data in:

URL: `localhost:8080`

```json
userId: "cus_S1MthklVNcSUB6", -- User bound to Stripe 
amount: 200L,
currency: "EUR" -- Drie letter currency code
```

User gebonden aan Stripe zijn gemockt voor nu.

**Runner**

Ook is een Runner binnen de main toegankelijk. Voor toegang daar naar verander de main naar de onderstaande code. En uncomment de runPayment methode.

```java
public static void main(String[] args) {
		SpringApplication.run(TriptopApplication.class, args)
 				.getBean(TriptopApplication.class)
				.runPayment();
	}

```



#### 9.3.2. Factory Oefening Prototype

**Runner**

Ook is een Runner binnen de main toegankelijk. Voor toegang daar naar verander de main naar de onderstaande code. En uncomment de runPayment methode.

```java
public static void main(String[] args) {
		SpringApplication.run(TriptopApplication.class, args)
 				.getBean(TriptopApplication.class)
				.runFactory();
	}

```
