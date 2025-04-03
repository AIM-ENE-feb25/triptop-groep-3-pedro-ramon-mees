# Software Guidebook Triptop

## 1. Inleiding

Dit software guidebook geeft een overzicht van de Triptop-applicatie. Het bevat een samenvatting van de vereisten, beperkingen en principes die als basis dienen voor het project.

Daarnaast beschrijft het de software-architectuur, waarbij zowel de technologiekeuzes op hoog niveau als de structurele opbouw van de software worden toegelicht.

Het document gaat ook in op de belangrijkste ontwerp- en codebeslissingen die genomen zijn om de software te realiseren zoals beoogd. Tot slot wordt de infrastructurele architectuur behandeld, inclusief een gedetailleerde uitleg over hoe de software geïnstalleerd, geconfigureerd en uitgevoerd kan worden voor gebruik.

### 1.1 Inhoudsopgave

Dit software guidebook bestaat uit de volgende hoofdstukken:

1. **Inleiding**
   - 1.1 Inhoudsopgave
   - 1.2 Leeswijzer

2. **Context**
   - 2.1 Contextdiagram
   - 2.2 Externe systemen

3. **Functioneel Overzicht**
   - 3.1 User stories
   - 3.2 Domain stories (AS-IS)
   - 3.3 Domain stories (TO-BE)
   - 3.4 Domeinmodel

4. **Kwaliteitsattributen**
   - 4.1 ISO 25010 kwaliteitsattributen
   - 4.2 Compatibility
   - 4.3 Reliability
   - 4.4 Maintainability
   - 4.5 Security

5. **Beperkingen**
   - 5.1 Projectbeperkingen
   - 5.2 Prototype vragen
      - 5.2.1 Interoperability
      - 5.2.2 Fault Tolerance

6. **Principes**
   - 6.1 Ontwerpprincipes
      - 6.1.1 Single Responsibility Principle
      - 6.1.2 Open/Closed Principle
      - 6.1.3 Dependency Inversion Principle
   - 6.2 Ontwerppatronen
      - 6.2.1 Facade Pattern
      - 6.2.2 Adapter Pattern
      - 6.2.3 Strategy Pattern
      - 6.2.4 Factory Method Pattern

7. **Software Architectuur**
   - 7.1 Container diagram
   - 7.2 Component diagram
   - 7.3 Design & Code details
      - 7.3.1 API Lijst
      - 7.3.2 API Mapping Table
   - 7.4 Sequence Diagram
   - 7.5 Class Diagram
   - 7.6 Uitbreidbaarheid met nieuwe API's

8. **Architectuurbeslissingsrecords**
   - 8.1 ADR-001: Postgres database
   - 8.2 ADR-002: Strategy pattern
   - 8.3 ADR-003: Stripe API Test Modus
   - 8.4 ADR-004: API Gateway Pattern (deprecated)
   - 8.5 ADR-005: Toepassen van het Facade-patroon
   - 8.6 ADR-006: Passend pattern voor "Fallback"
   - 8.7 ADR-007: Adapter Pattern voor betalingsintegraties

9. **Installatie, Werking en Ondersteuning**
   - 9.1 Installatie-instructies
   - 9.2 Configuratie
   - 9.3 Lokale ontwikkelomgeving
   - 9.4 Productie-deployment
   - 9.5 Monitoring en onderhoud

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
