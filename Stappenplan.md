# Opdrachtbeschrijving


## Stappenplan
_Let op_: het is uitdagender om jouw eigen stappenplan te maken. Mocht je niet zo goed weten waar je moet beginnen, kun je onderstaand stappenplan volgen:
1. Zet een nieuw project op via Spring Boot Initialzr genaamd garage
2. Maak een nieuwe map aan in je project genaamd Controllers
3. Maak een nieuwe map aan in je project genaamd Exceptions
4. Maak een nieuwe klasse aan in de map Controllers voor de televisionController
5. Voorzie de klasse van de juiste annotatie
6. Maak in de klasse de gevraagde requests met de juiste response entities
7. Maak een nieuwe klasse aan in de map Controllers voor de exceptionController
8. Voorzie de klasse van de juiste annotatie
9. Maak in de klasse een exceptionHandler met de juiste notatie en response entitiy
10. Maak een nieuwe klasse aan in de map Exceptions
11. Extend de klasse met de RunTimeException
12. Vul de klasse aan de hand van het voorbeeld van hoofdstuk 5.11 van de cursus Spring Boot
## Randvoorwaarden

- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `Controllers`;
- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `Exceptions`;
- Het project bevat een `CustomerController`;
- Het project bevat een `ExceptionController`;
- Het project bevat een `RecordNotFoundException`;
- De **CustomerController** bevat:
  x een GET-request voor alle klanten
  x een GET-request voor 1 klant
  x een POST-request voor 1 klant
  x een PUT-request voor 1 klant
  x een DELETE-request voor 1 klant
- De **ExceptionController** bevat:
  - een exceptionhandler
- De **RecordNotFoundException** bevat:
  - een default exception
  - een exception met message

