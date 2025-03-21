# API Mapping Document



| Class::Attribuut           | Is input voor API+Endpoint         | Wordt gevuld door API+Eindpoint | Wordt geleverd door eindgebruiker | Moet worden opgeslagen in de applicatie |
|----------------------------|---------------------------------|--------------------------------|---------------------------------|---------------------------------|
| FlightOffer::departureDate | Flight API /flight-offers (GET) | x                              | x                               | x                               |
| FlightOffer::origin        | Flight API /flight-offers (GET) | x                              | x                               | x                               |
| FlightOffer::destination   | Flight API /flight-offers (GET) | x                              | x                               | x                               |
| FlightOffer::adults        | Flight API /flight-offers (GET) | x                              | x                               |                                 |

## Vragen voor de casus:

**Interoperability:** "Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie?
**Fault Tolerance:** "Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?"