# Java JDBC CRUD
## My first time using JDBC to build CRUD applications

## Technologies
- **Java 21**
- **MySQL**
- **JDBC Connector**

## Features
- **Create**: You can add products with Name, Price and Category.
- **Read**: List added products.
- **Update**: Update item information based on its ID.
- **Delete**: Remote an item based on its Id.

## Setup and Pre-requisits
- You need a MySQL Server running. Use the script below to create the tables.
```sql
CREATE TABLE category (
  Id int NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE product (
  Id int NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Price double NOT NULL,
  CategoryId int NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (CategoryId) REFERENCES category (Id)
);
```
### Connection Configuration
- In your IDE, create an file named **db.properties** in the project root with the following format:
```bash
user=user
password=password
dburl=jdbc:mysql://localhost:3306/db_name
useSSL=false
```
