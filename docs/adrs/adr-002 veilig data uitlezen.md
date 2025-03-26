### ADR 0002: Stripe API Test Modus

#### Status
_**Voorgesteld**_
#### Context
In het ontwikkelen van verbindingen is d'r een methode nodig waarmee men veilig data kan uitlezen. Zodat data niet direct in de code als hardcoded variabelen opgeslagen wordt, dat opent de applicatie op tot grootste veiligheidsproblemen. 

Daarvoor is de standaard methodiek het opslaan en uitlezen van de data doormiddel van een `.env`-bestand. Tijdens de courses DOEX & TEEX hebben een hier een library voor in ons 't schoot geworpen. 

- De RapidAPI geeft enkel mockdata terug.
- De Stripe API heeft een test modus, waarin men direct gebruik kan maken van de echte API. Zonder de kans dat men gefactureerd wordt voor gebruik.

#### Besluit
Ik ga het prototype ontwikkelen doormiddel van de Stripe API. Daarmee kan men zonder de mogelijkheid gefactureerd te worden hen integratie ontwikkelen en testen.

### Gevolgen

##### Positief:

- Alle functionaliteit wordt volgens de door Stripe gegeven modellen ontwikkeld
- Implementatie zal direct van prototype naar product over kunnen gaan.

##### Negatief:

- Vergroot mogelijk de data-strucuur van de DB met Stripe modellen.
- Contact naar Stripe gaat via de Stripe.class, geen direct zicht op de endpoints.
- Vereist ontwikkeling en onderhoud van adapter-services

#### Implementatiedetails

- Voor elke categorie externe services (vervoer, betalingen, authenticatie) ontwikkelen we een dedicated adapter-service
- Elke adapter implementeert een standaard interface die onze core backend gebruikt
- Adapters vertalen de specifieke formaten/protocollen van de Stripe API naar ons interne datamodel
