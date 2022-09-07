# Concession Inventory API

## What is this project?
The Concession Inventory API is a REST API for managing a concession stand's inventory built using Java,  
Spring Boot, and PostgreSQL.  The creator wrote this application to streamline the management process for  
the concession inventory he is responsible for tracking as the Show Coordinator for UBC Improv.

### It allows you to:
- **Add** snacks to your concession stand database
- **Delete** snacks from your concession stand database by id
- **Update** a snack's price and/or stock in your concession stand database
- **Get** a list of all snacks from your concession stand database
- **Get** a filtered list of snacks based on allergens or snack category
- **Get** a sorted list of snacks by name, price, or stock (in ascending or descending order)

## How do I install and run this project?
**1) Clone the application**
```
https://github.com/willgao23/concession-inventory-api.git   
```
**2) Create a PostgreSQL database**
```
CREATE DATABASE snack
```
**3) Edit the username and password**
- Open `src/main/resources/application.properties`
- Edit `spring.datasource.username` and `spring.datasource.password` to match your PostgreSQL username  
and password

**4) Build and run the application with Maven**
```
mvn package
java -jar target/concession-inventory-0.0.1-SNAPSHOT.jar
```
The application will start running at `http://localhost:8080/concession-inventory-api/v1`

## How do I use this project?
Listed below are all HTTP requests supported and any associated path variables or request parameters.

```
(returns all snacks)
GET http://localhost:8080/concession-inventory-api/v1 

(returns all snacks sorted by name in ascending alphabetical order)
GET http://localhost:8080/concession-inventory-api/v1?sort=nameAsc  

(returns all snacks sorted by name in descending alphabetical order)
GET http://localhost:8080/concession-inventory-api/v1?sort=nameDesc  

(returns all snacks sorted by ascending price)
GET http://localhost:8080/concession-inventory-api/v1?sort=priceAsc

(returns all snacks sorted by descending price)
GET http://localhost:8080/concession-inventory-api/v1?sort=priceDesc

(returns all snacks sorted by ascending stock)
GET http://localhost:8080/concession-inventory-api/v1?sort=stockAsc

(returns all snacks sorted by descending stocK)
GET http://localhost:8080/concession-inventory-api/v1?sort=stockDesc

(returns only snacks that have nuts)
GET http://localhost:8080/concession-inventory-api/v1?filter=hasNuts  

(returns only snacks that have lactose)
GET http://localhost:8080/concession-inventory-api/v1?filter=hasLactose

(returns only snacks that have gluten)
GET http://localhost:8080/concession-inventory-api/v1?filter=hasGluten

(returns only snacks that have an allergen)
GET http://localhost:8080/concession-inventory-api/v1?filter=hasAllergens

(returns only snacks categorized as sweet)
GET http://localhost:8080/concession-inventory-api/v1?filter=isSweet

(returns only snacks categorized as salty)
GET http://localhost:8080/concession-inventory-api/v1?filter=isSalty

(returns only snacks categorized as drinks)
GET http://localhost:8080/concession-inventory-api/v1?filter=isDrink  
  
(adds the given snack to the database)
POST http://localhost:8080/concession-inventory-api/v1

(removes the snack with the given id from the database)
DELETE http://localhost:8080/concession-inventory-api/v1/{snackId}

(updates the price of the snack with the given id)
PUT http://localhost:8080/concession-inventory-api/v1/{snackId}?price={doubleNewPrice}

(updates the stock of the snack with the given id)  
PUT http://localhost:8080/concession-inventory-api/v1/{snackId}?stock={intNewStock}
```



