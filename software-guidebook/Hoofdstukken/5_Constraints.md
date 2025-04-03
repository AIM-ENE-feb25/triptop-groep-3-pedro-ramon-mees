## 5. Beperkingen

Initieel heeft het team uit een lijst met ontwerpvragen enkele kernvraagstukken geselecteerd die relevant zijn voor de beperkingen van het Triptop-systeem. Elk teamlid heeft één of meerdere van deze vragen onderzocht om passende oplossingen te vinden voor de architecturale uitdagingen. Deze onderzoeken hebben geleid tot concrete implementaties in het prototype en zijn gedocumenteerd in de vorm van [Architectuurbeslissingsrecords](#8-architectuurbeslissingsrecords) (Architectural Decision Records, ADRs).

Het project moet met prototypes de volgende vragen kunnen beantwoorden:

- **Interoperability:** Hoe kunnen we verschillende externe vervoersservices (zoals Google Maps of een veerdienst API) integreren zonder afhankelijk te worden van hun specifieke implementaties?
(Zie [ADR-005](#85-adr-005-toepassen-van-het-facade-patroon))
  
- **Fault Tolerance:** Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?
(zie [ADR-002](#82-adr-002-strategy-pattern))
  
- **Fault Tolerance:** Hoe kunnen we ervoor zorgen dat bepaalde bouwstenen automatisch een alternatieve dienst kiezen als de primaire dienst niet beschikbaar is?
(Zie [ADR-006](#86-adr-006-passend-pattern-kiezen-voor-bij-fallback-onderzoeksvraag) en [ADR-007](#87-adr-007-implementatie-van-adapter-pattern-voor-betalingsintegraties) voor voorbeelden met de `PaymentAdapterFactory`).

De prototypes hierboven vormen samen een walking skeleton en moeten binnen 3 weken worden afgemaakt door 3 ontwikkelaars.