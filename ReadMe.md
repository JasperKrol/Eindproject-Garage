## Autogarage

Welkom bij mijn eindproject van Hogeschool Novi. Het project is een resultaat van de geleerde stof uit het "Full-Stack
Developer" curriculum. Binnen dit project heb ik de backend van een autogarage ontworpen. Hierin kan het proces van
keuringen en reparaties rondom auto's en klanten worden nagebootst. Het bevat al vooraf bepaalde data voor o.a. klanten,
auto's, keuringen, facturen en reparaties. Natuurlijk is het ook mogelijk om de applicatie van nieuwe date entries te
voorzien. Binnen deze applicatie is er ook authenticatie en autorisatie toegepast.

## Inhoudsopgave

* [Vooraf](#vooraf)
    + [Rollen en users](#Rollen-en-users)

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

> **_NOTE:_**
Dit dient gespecificeerd te worden bij gebruik. Voor de beoordeling staat nu open.

5. Authorization verplicht. (JWT token) zie sectie endpoints.

6. Voorbeelden van alle Postman collections zijn toegevoegd in de resource folder.

<br/>

#### Rollen en users

Deze rollen kunnen gebruikt worden voor de beoordeling en het testen van de applicatie. De verschillende rollen hebben
toegang tot hun eigen endpoints. Deze staan in de meest rechter kolom.

| Username                  | Password | Role                     | Endpoints authorization                            |
|---------------------------|----------|--------------------------|----------------------------------------------------|
| Henk                      | password | MONTEUR                  | Get.customers, Get.cars,inventory**, inspections** |
|                           |          |                          | repair_items**, invoices**                         |
| Peter                     | password | MONTEUR                  | Get.customers, Get.cars, inventory**, inspections**|
|                           |          |                          | repair_items**, invoices**                         |
| Jasper                    | password | ADMIN                    | Alles als admin                                    |
| Pien                      | password | OFFICE                   | invoices**, customers**, cars**, inspections**     |
|                           |          |                          | repairs**, registration_papers**, appointments**   |

<br>

## Installatie

1. Download en installeer PostgresSQL via onderstaande URL. De applicatie maakt gebruik van deze database.

> https://www.postgresql.org/download/

2. Installeer de IntelliJ IDE indien dit nog niet gedaan is.

3. Installeer Postman voor op de pc via onderstaande link.

> https://www.postman.com/downloads/

4. Clone het project in IntelliJ via onderstaande link van mijn repository of download het zipp-bestand en open het via
   File → new → project from existing source en klik op het pom bestand uit de zipp-file.

> https://github.com/JasperKrol/Eindproject-Garage

5. Verander de PostgreSQL credentials in resources > application-dev.properties naar jouw eigen local settings:<br/>
   server.port=8081</br>
   spring.datasource.url=jdbc:postgresql://localhost:5432/garage<br/>
   spring.datasource.username=postgres<br/>
   spring.datasource.password=Welkom123<br/>

   Wil je een andere configuratie gebruiken, dan pas je dit aan in het **application.properties** bestand.

6. Run het project via het groene pijltje in de rechterbovenhoek van IntelliJ.
   <br/></br>

### Endpoints

Hieronder vind u alle endpoints die de applicatie bevat. Deze kunnen benaderd worden via postman en werken via het
MVC-model. De authenticatie van de applicatie werkt met een Bearer token. Deze token moet mee gegeven worden aan elk
request. Dit doe je in Postman door onder Authorization, Bearer Token in te stellen en de token in te vullen.

De token verkrijg je door eerst het Authorization endpoint uit te voeren. De token is vervolgens 10 dagen geldig.

#### Autorisatie

* POST /api/v1/authenticate
  <br/><br/>
  In de body geef je de username en password mee. Hiervoor kan een user uit bovenstaande tabel gebruikt worden:

Voorbeeld:

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
Voor upload files worden alleen bestanden geaccepteerd die 5MB of kleiner zijn. Downloaden kan via een link of een request afgehandeld worden.

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



