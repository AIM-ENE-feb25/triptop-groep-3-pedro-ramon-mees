## 2. Context
![Context_Diagram_groep_3_Pedro.svg](../..%2Fopdracht-diagrammen%2FContext_Diagram_groep_3_Pedro.svg)

Het contextdiagram bevat de volgende hoofdcomponenten:

1. **Centraal systeem: "Triptop"**  

2. **Externe entiteiten:**  
   - **IdentityProvider**: Zorgt voor authenticatie en autorisatie.  
   - **BoekingService**: Verwerkt boekingen binnen het systeem.  
   - **TransportService**: Regelt het transport voor de reiziger.  
   - **BetaalService**: Afhandeling van betalingen binnen het systeem.

3. **Relaties en datastromen:**  
   - Triptop communiceert met de verschillende services om de processen te beheren, zoals boekingen, betalingen en identificatie.  
   - Reizigers en reisagenten hebben interactie met Triptop, waarschijnlijk via een gebruikersinterface. 

Toelichting op de context van de software inclusief System Context Diagram:
* Functionaliteit
* Gebruikers
* Externe systemen