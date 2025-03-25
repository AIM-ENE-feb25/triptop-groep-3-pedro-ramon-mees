Hier is de vertaling op B2-niveau:  

---

# **Componentenlijst en Verantwoordelijkheden**  

## **1. API Gateway**  
**Verantwoordelijkheid**: Fungeert als het centrale toegangspunt voor alle externe API-verzoeken en stuurt deze door naar de juiste API-adapters.  
- Behandelt algemene taken zoals foutafhandeling, herhalingen van mislukte verzoeken en logging.  
- Biedt een uniforme interface voor de applicatie om met externe diensten te communiceren.  
- Zorgt ervoor dat de applicatie geen directe kennis heeft van externe API-eindpunten.  

## **2. API Adapter Interface**  
**Verantwoordelijkheid**: Definieert een standaard contract dat alle API-adapters moeten volgen.  
- Zorgt voor een consistente manier om met API’s te werken.  
- Maakt het eenvoudig om verschillende implementaties te vervangen.  
- Ondersteunt het gebruik van mock-versies voor testdoeleinden.  

## **3. Concrete API Adapters**  
**Verantwoordelijkheid**: Voert de API Adapter Interface uit voor specifieke externe diensten.  
- Verbergt dienst-specifieke details zoals eindpunten, authenticatie en het formaat van verzoeken/antwoorden.  
- Zet gegevens om tussen externe API-formaten en interne datamodellen.  
- Beperkt de impact van wijzigingen in externe API’s tot één component.  

## **4. Service Interface**  
**Verantwoordelijkheid**: Definieert bedrijfslogica die mogelijk externe API-aanroepen vereist.  
- Biedt een domeingerichte interface voor de applicatie.  
- Verbergt hoe de operaties precies worden uitgevoerd.  
- Stelt de applicatie in staat om meerdere API-aanroepen te combineren tot één samenhangende bedrijfsoperatie.  

## **5. Service Implementatie**  
**Verantwoordelijkheid**: Voert de bedrijfslogica uit met behulp van API-adapters.  
- Coördineert aanroepen naar één of meerdere API-adapters.  
- Past bedrijfsregels en validaties toe.  
- Zet gegevens om tussen interne domeinmodellen en API-specifieke formaten.  

## **6. Configuratiebeheerder**  
**Verantwoordelijkheid**: Beheert externe configuraties zoals API-eindpunten en toegangsgegevens.  
- Centraliseert het beheer van configuratie-instellingen.  
- Maakt het mogelijk om configuraties tijdens runtime te wijzigen.  
- Ondersteunt verschillende configuraties voor verschillende omgevingen.  

## **7. Domeinmodellen**  
**Verantwoordelijkheid**: Vertegenwoordigt de belangrijkste zakelijke entiteiten en gegevensstructuren.  
- Zorgt voor een consistente interne weergave van gegevens.  
- Houdt interne gegevensstructuren gescheiden van externe API-formaten.  
- Maakt het mogelijk om bedrijfslogica stabiel te houden, zelfs als API’s veranderen.  

## **8. DTO (Data Transfer Objects)**  
**Verantwoordelijkheid**: Vertegenwoordigt de gegevensstructuren die specifiek zijn voor externe API’s.  
- Bevat de exacte structuur van verzoeken en antwoorden voor externe API’s.  
- Beperkt de impact van wijzigingen in API-gegevensformaten tot één component.  
- Vergemakkelijkt de vertaling tussen externe en interne gegevensformaten.  