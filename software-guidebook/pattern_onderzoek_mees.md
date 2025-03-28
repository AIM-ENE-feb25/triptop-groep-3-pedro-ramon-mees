### 1.1 Investigate existing technologies (Library)

Chosen Methodology: Available Product Analysis / Expert Interview

*Ik zal mijn product analyse laten plaats vinden door middel van een Claud 3.7 (Sonnet) prompt.*

Prompt:

`What are the most commonly used patterns to chose an alternative services (api) if the primary service is not available?`

Response

```java
# Service Failover Patterns

When designing systems that need to handle primary service unavailability, several patterns are commonly used:

## 1. Circuit Breaker Pattern
- Detects failures and prevents cascade failures
- Automatically diverts requests to fallback services when threshold is reached
- Allows periodic testing of primary service to determine when to resume normal operation

## 2. Retry with Exponential Backoff
- Automatically retry failed requests
- Gradually increase delay between retries to avoid overwhelming services
- Often combined with jitter to prevent thundering herd problems

## 3. Fallback Pattern
- Define alternative service implementations
- Switch to backup service when primary fails
- Example: `try { primaryService() } catch { fallbackService() }`

## 4. Service Discovery
- Dynamically locate available service instances
- Can automatically route to healthy instances
- Often implemented with tools like Consul, etcd, or Kubernetes services

## 5. Load Balancing with Health Checks
- Distribute traffic among multiple service instances
- Remove unhealthy instances from rotation
- Automatically reintroduce instances when they recover

## 6. Bulkhead Pattern
- Isolate components to prevent cascading failures
- Allocate separate resource pools for different services
- Ensures failure in one service doesn't affect others

## 7. API Gateway with Failover Logic
- Central point for routing requests
- Can implement complex failover strategies
- Provides consistent interface despite backend changes

## 8. Saga Pattern (for distributed transactions)
- Sequence of local transactions with compensating actions
- Each step can have alternative paths if a service fails
- Maintains consistency across multiple services

These patterns are often used in combination to create robust failover strategies in microservice architectures and distributed systems.
```

### 1.2 Define criteria that the technology should meet (Field)

Chosen Methodology: Problem Analysis

**Wat**

- Wat is het probleem? De primaire dienst (service/API) is niet beschikbaar
- Wat moet er gebeuren? Automatisch overschakelen naar een alternatieve dienst
- Wat zijn de gevolgen als dit niet werkt? Onderbreking van de dienstverlening, slechte gebruikerservaring

**Wie**

- Wie wordt getroffen door het probleem? Eindgebruikers van de applicatie
- Wie is verantwoordelijk voor het implementeren van de oplossing? Ontwikkelaars
- Wie moet de werking van het alternatief valideren? QA-team, systeembeheerders

**Waar**

- Waar treedt het probleem op? In de communicatie tussen de applicatie en externe diensten/APIs
- Waar moet de failover-logica worden geïmplementeerd? Op applicatieniveau

**Wanneer**

- Wanneer treedt het probleem op? Bij timeout, foutcodes, connectieproblemen
- Wanneer moet er worden overgeschakeld? Zodra het probleem wordt gedetecteerd
- Wanneer moet worden teruggeschakeld naar de primaire dienst? Als deze weer beschikbaar is

**Waarom**

- Waarom is dit een probleem? Het zorgt voor onderbreking van de service
- Waarom is automatisch overschakelen nodig? Om continuïteit te waarborgen
- Waarom kan dit niet handmatig worden opgelost? Te traag, vereist monitoring

**Hoe**

- Hoe detecteren we onbeschikbaarheid? Health checks, timeouts, foutcodes
- Hoe schakelen we over naar de alternatieve dienst? Circuit breaker pattern, retry mechanisme
- Hoe gaan we terug naar de primaire dienst? Automatisch of handmatig

**Criteria**

1. De applicatie moet binnen 5 seconden detecteren dat de primaire dienst niet beschikbaar is
2. Overschakeling naar alternatieve dienst moet volledig automatisch gebeuren
3. Maximaal 5 retries voordat wordt overgeschakeld naar alternatief
4. Alle failovers moeten worden gelogd met timestamp en oorzaak
5. De applicatie moet periodiek controleren of de primaire dienst weer beschikbaar is
6. Dataformaten en response structuren moeten consistent zijn
7. De detectie en overschakeling mogen niet meer dan 1 seconde extra verwerkingstijd toevoegen

### 1.3 Check technologies against criteria

Bij het implementeren van failover tussen betalings-API's met Stripe als primaire dienst komt het **Circuit Breaker** als beste optie naar voren. 

Dit pattern scoort goed op de cruciale criteria voor betalingsverwerking:

| Pattern → Criteria ↓ | Circuit Breaker | Retry met Exponential Backoff | Fallback Pattern | Service Discovery | Load Balancing met Health Checks | Bulkhead Pattern | API Gateway met Failover Logic |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1. Detectie binnen 5 seconden | X | X | X | X | X |  | X |
| 2. Automatische overschakeling | X | X | X | X | X |  | X |
| 3. Maximaal 5 retries | X | X |  |  |  |  | X |
| 4. Logging van failovers | X | X | X | X | X | X | X |
| 5. Periodieke controle primaire dienst | X | X |  | X | X |  | X |
| 6. Consistente dataformaten |  |  | X |  |  |  | X |
| 7. Max 1 sec extra verwerkingstijd | X |  | X | X | X | X |  |

**Toelichting**

- Het **Circuit Breaker** biedt uitstekende ondersteuning voor consistente dataformaten tussen verschillende betalingsproviders
- Het voegt minimale verwerkingstijd toe en zorgt voor snelle detectie en overschakeling
- Voor betalingsverwerking is het behouden van dataformaat-consistentie cruciaal
- De eenvoud van implementatie met directe fallback (doormiddel van encapsulatie **Fallback Pattern**) naar een alternatieve provider maakt het zeer geschikt

Voor optimale resultaten zal ik het Fallback Pattern kunnen combineren met elementen van het Circuit Breaker pattern voor slimmer beheer van de failover status en beperkte retries bij tijdelijke fouten.