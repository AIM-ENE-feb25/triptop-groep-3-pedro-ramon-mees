## Agenda

1. **Inleiding**
   - Doel van de presentatie en overzicht van de software guidebook incl. uitleg over checklist
2. **Context**
   - System Context Diagram.
5. **Beperkingen**
   - Prototypevragen en beperkingen.
6. **Principes**
   - Ontwerpprincipes en design patterns.
7. **Software Architectuur**
   - Containerdiagram, componentdiagrammen en dynamische diagrammen.
8. **Architectuurbeslissingsrecords**
   - Belangrijke ADR's en hun impact.
9. **Installatie, Werking en Ondersteuning**
   - Hoe de applicatie te bouwen en uit te voeren.
---
   ### **Checklist: System Context Diagram**
- [ ] Is er een System Context diagram aanwezig?
- [ ] Bevat het diagram alle actoren en het systeem?
- [ ] Bevat het diagram alle externe systemen?
- [ ] Bevat het diagram de juiste relaties tussen actoren, systemen en het centrale systeem?
- [ ] Zijn alle labels volledig beschrijvend?
  - [ ] Zijn er ontbrekende relaties of onvolledige labels?
  - [ ] Is alles correct beschreven en zijn alle relaties aanwezig?
---
  ### **Checklist: Beperkingen**
- [ ] Zijn de prototypevragen duidelijk beschreven?
- [ ] Zijn de beperkingen van het project goed uitgelegd?
- [ ] Is er een relatie gelegd tussen de beperkingen en de gekozen oplossingen?
---
### **Checklist: Ontwerpprincipes**
- [ ] Zijn de ontwerpprincipes benoemd?
- [ ] Zijn de benoemde ontwerpprincipes passend voor de casus?
- [ ] Zijn de ontwerpprincipes zichtbaar toegepast in de software?
- [ ] Is de toepassing van de ontwerpprincipes correct?
  - [ ] Gedeeltelijk correct toegepast?
  - [ ] Overwegend correct toegepast?
  - [ ] Volledig correct toegepast?
---
  ### **Checklist: Design Patterns**
- [ ] Zijn er design patterns toegepast?
- [ ] Zijn er minimaal 3 design patterns toegepast?
- [ ] Zijn de patterns correct toegepast?
  - [ ] Minimaal 1 correct toegepast?
  - [ ] Minimaal 2 correct toegepast, 1 deels correct?
  - [ ] Alle 3 patterns correct toegepast?
- [ ] Zijn de patterns logisch en relevant voor de casus?
---
### **Checklist: Software Architectuur**
- [ ] Is er een statisch containerdiagram aanwezig?
- [ ] Bevat het diagram alle essentiële containers?
- [ ] Zijn de labels op de onderlinge relaties volledig beschrijvend?
- [ ] Is er minstens 1 dynamisch containerdiagram aanwezig?
- [ ] Passen de dynamische containerdiagrammen goed bij de gekozen ontwerpvraag?
- [ ] Zijn er componentdiagrammen aanwezig?
- [ ] Dekt elk componentdiagram een ontwerpvraag?
- [ ] Bevat het componentdiagram alle essentiële componenten?
- [ ] Zijn de componenten en hun onderlinge relaties beschreven met labels?
  - [ ] Zijn de labels beschrijvend genoeg?
---
  ###  **Checklist: Class- en Sequence Diagrammen**  
- [ ] Zijn er class- en sequence diagrammen aanwezig?  
- [ ] Hebben de diagrammen een relatie met de ontwerpvragen?  
- [ ] Bevatten de diagrammen alleen correcte UML-elementen?  
- [ ] Is voor de meerderheid van de ontwerpvragen 1 component uitgewerkt in een UML class diagram?  
- [ ] Is voor de meerderheid van de ontwerpvragen minimaal 1 UML sequence diagram aanwezig?  
  - [ ] Is dit sequence diagram een detaillering van het component in classes, modules, functies of andere bouwblokken?  
- [ ] Zijn de class diagrammen en sequence diagrammen grotendeels correct?  
  - [ ] Zijn ze correct voor de meeste ontwerpvragen?  
  - [ ] Is elk diagram volledig correct voor de bijbehorende ontwerpvraag?  
---
###  **Checklist: Prototypes**  
- [ ] Zijn prototypes aanwezig?  
- [ ] Hebben de prototypes een duidelijke relatie met design patterns of een ontwerpvraag?  
- [ ] Zijn de prototypes daadwerkelijk demonstreerbaar?  
- [ ] Komt de broncode overeen met de class- en sequence diagrammen?  
- [ ] Is voor de meerderheid van de ontwerpvragen een werkend prototype ontwikkeld voor een design pattern?  
- [ ] Kunnen de prototypes worden gedemonstreerd vanaf de main branch?  
  - [ ] Zijn de prototypes enkel op losse branches demonstreerbaar?  
  - [ ] Zijn de prototypes geïntegreerd en demonstreerbaar vanaf de main branch?  
- [ ] Komt de broncode vrijwel volledig overeen met de class- en sequence diagrammen?  
  - [ ] Komt de broncode volledig overeen met de diagrammen? 
---
###  **Checklist: Interne Samenhang**  
- [ ] Zijn de diagrammen voorzien van tekst?  
- [ ] Voegt de tekst iets toe aan het diagram?  
  - [ ] Is de tekst volledig overbodig of bevat deze grove tegenstrijdigheden?  
- [ ] Beschrijft de tekst bij de diagrammen keuzes, motivaties of aanvullende informatie die niet in het diagram staat?   