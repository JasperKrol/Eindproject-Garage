## Autogarage

Welkom bij mijn eindproject van Hogeschool Novi. Het project is een resultaat van de geleerde stof uit het "Full-Stack
Developer" curriculum. Binnen dit project heb ik de backend van een autogarage ontworpen. Hierin kan het proces van
keuringen en reparaties rondom auto's en klanten worden nagebootst. Het bevat al vooraf bepaalde data voor o.a. klanten,
auto's, keuringen, facturen en reparaties. Natuurlijk is het ook mogelijk om de applicatie met nieuwe date entries te
voorzien. Binnen deze applicatie is er ook authenticatie en autorisatie toegepast.

## Inhoudsopgave

* [Vooraf](#vooraf)
    + [De volgende user worden in het project geladen om het te testen. Ook kun je in dit schema de verschillende rollen vinden en de daarbij behorende endpoints]

- [Installatie](#Installatie)

* [Endpoints](#endpoints)
    + [Authorization](#Autorisatie)
    + [Users](#users)
    + [Customers](#customers)
    + [Cars](#cars)
    + [Inspections](#inspections)
    + [Repairs](#repairs)
    + [Deficiencies](#deficiencies)
    + [Items](#items)

## Vooraf

1. Java version 11 jdk (of hoger).

2. Database PostgreSQL.

3. Cross-Origin is aangezet voor alle endpoints en alle origins.

4. Authorization verplicht. (JWT token) zie sectie endpoints.

5. Voorbeelden van alle Postman collections zijn toegevoegd in de resource folder.

<br/>

#### De volgende user worden in het project geladen om het te testen. Ook kun je in dit schema de verschillende rollen vinden en de daarbij behorende endpoints

| Username                  | Password | Role                     | Endpoints persmissons                              |
|---------------------------|----------|--------------------------|----------------------------------------------------|
| Henk                      | password | MONTEUR                  | Get.customers Get.cars  inventory** inspections**  |
|                           |          |                          | repair_items** invoices**                          |
| Peter                     | password | MONTEUR                  | Get.customers Get.cars  inventory** inspections**  |
|                           |          |                          | repair_items** invoices**                          |
| Jasper                    | password | ADMIN                    | Alles als admin                                    |
| Pien                      | password | OFFICE                   | invoices** customers** cars** inspections**        |
|                           |          |                          | repairs** registration_papers** appointments**     |

<br>

## Installatie

1. Download en installeer PostgresSQL via onderstaande URL. De applicatie maakt gebruik van deze database.

> https://www.postgresql.org/download/

2. Installeer de IntelliJ IDE indien dit nog niet gedaan is.

3. Installeer Postman voor op de pc via onderstaande link.

> https://www.postman.com/downloads/

<br/><br/>

3. Clone het project in IntelliJ via onderstaande link van mijn repository of download het zipp-bestand en open het via
   File → new → project from existing source en klik op het pom bestand uit de zipp-file.

> https://github.com/JasperKrol/Eindproject-Garage

<br/></br>

4. Verander de PostgreSQL credentials in resources > application-dev.properties naar jouw eigen local settings:<br/>
   server.port=8081</br>
   spring.datasource.url=jdbc:postgresql://localhost:5432/garage<br/>
   spring.datasource.username=postgres<br/>
   spring.datasource.password=Welkom123<br/>

   Wil je een andere configuratie gebruiken, dan pas je dit aan in het **application.properties** bestand.

5. Run het project via het groene pijltje in de rechterbovenhoek van IntelliJ
   <br/></br>

### Endpoints

De endpoints kunnen getest worden met een applicatie als Postman.<br/>

De authenticatie van de applicatie werkt met een Bearer token. Deze token moet mee gegeven worden aan elk request. Dit
doe je in Postman door onder Authorization, Bearer Token in te stellen en de token in te vullen.

De token verkrijg je door eerst het Authorization endpoint uit te voeren. De token is vervolgens 10 dagen geldig.

#### Autorisatie

* POST /api/v1/authenticate
  <br/><br/>
  In de body geef je de username en password mee. Hiervoor kan een user uit bovenstaande tabel gebruikt worden:
  <br/><br/>

  voorbeeld:

```json
 {
  "username": "jasper",
  "password": "password"
}
```

#### Users

* GET /api/v1/users
* POST /api/v1/users
* DELETE /api/v1/users/{username}
* GET /api/v1/users/{username}
* PUT /api/v1/users/{username}
* GET /api/v1/users/{username}/authorities
* POST /api/v1/users/{username}/authorities
* DELETE /api/v1/users/{username}/authorities/{authority}
* PATCH /api/v1/users/{username}/password

#### Customers

* GET /api/v1/customers
* POST /api/v1/customers
* DELETE /api/v1/customers/{id}
* GET /api/v1/customers?lastname={lastname}
* GET /api/v1/customers/{id}
* PUT /api/v1/customers/{id}
* PATCH /api/v1/customers/{id}
* GET /api/v1/customers/{id}/cars
* POST /api/v1/customers/{id}/cars

#### Cars

* GET /api/v1/cars
* POST /api/v1/cars
* DELETE /api/v1/cars/{id}
* GET /api/v1/cars/{id}
* PUT /api/v1/cars/{id}
* PATCH /api/v1/cars/{id}
* POST /api/v1/cars/{id}/licenseregistrationfile/upload<br/>

> **_NOTE:_**
Voor upload files worden alleen bestanden geaccepteerd die 5MB of kleiner zijn.
Downloaden kan via een link of een request afgehandeld worden.

* GET /api/v1/cars/{id}/licenseregistrationfile/download
* GET api/v1/cars/{id}/carInvoice
* > **_NOTE:_**
  Invoice wordt opgehaald voor Cars met repairs welke status REPAIR_COMPLETED hebben.<br/>
  Dummy data is ingevoerd voor een car met id 1, api/v1/car/1/carInvoice

#### Inspections

* GET /api/v1/inspections

> **_NOTE:_**
Optioneel parameter: 'date' <br/> voorbeeld: repairs?date=2022-01-06

* POST /api/v1/inspections
* DELETE /api/v1/inspections/{id}
* GET /api/v1/inspections/{id}
* PUT /api/v1/inspections/{id}
* PATCH /api/v1/inspections/{id}

#### Repairs

* GET /api/v1/repairs

> **_NOTE:_**
Optioneel parameter: 'date' <br/> voorbeeld: repairs?date=2022-01-06

* POST /api/v1/repairs
* DELETE /api/v1/repairs/{id}
* GET /api/v1/repairs/{id}
* PUT /api/v1/repairs/{id}
* PATCH /api/v1/repairs/{id}

#### Deficiencies

* GET /api/v1/deficiencies
* POST /api/v1/deficiencies
* DELETE /api/v1/deficiencies/{id}
* GET /api/v1/deficiencies/{id}
* PUT /api/v1/deficiencies/{id}
* PATCH /api/v1/deficiencies/{id}

#### Items

* GET /api/v1/items
* POST /api/v1/items
* DELETE /api/v1/items/{id}
* GET /api/v1/items/{id}
* PUT /api/v1/items/{id}
* PATCH /api/v1/items/{id}



