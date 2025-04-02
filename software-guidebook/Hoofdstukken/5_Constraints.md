## 5. Constraints

> [!NOTE]
> Hieronder vallen de beperkingen die vooraf bekend zijn en invloed hebben op de architectuur- en ontwerpkeuzes. Denk hierbij aan budget, tijd, beschikbare technologie, wet- en regelgeving of specifieke eisen van de opdrachtgever.
-  Het prototype moet binnen 3 weken voltooid zijn met een team van 3 ontwikkelaars.
- Het project moet met prototypes de volgende vragen kunnen beantwoorden:
    - **Interoperability:** Hoe kunnen we verschillende externe vervoersservices (zoals Google Maps of een veerdienst API) integreren zonder afhankelijk te worden van hun specifieke implementaties?
    - **Fault Tolerance:** Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?
    - **Fault Tolerance:** Hoe kunnen we ervoor zorgen dat bepaalde bouwstenen automatisch een alternatieve dienst kiezen als de primaire dienst niet beschikbaar is?