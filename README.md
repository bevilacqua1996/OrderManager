# OrderManager

## Technologies Used

- Spring-Boot 2.7.16;
- Spring JPA;
- Postgresql;
- Log4J2;
- Maven.

## Docker image available on docker Hub

[orderManager Tags on Docker Hub](https://hub.docker.com/r/bevilacqua96/order-manager/tags)

## How to run locally

As the database uses local _postgresql server_, in order to run the application, you have to install postgresql on your local machine.
[Postgres Downloads Windows](https://www.postgresql.org/download/windows/)

Once you have installed, create the database **order_system**. It can be done using _PgAdmin_ interface.

Besides, the dependency management tool used is Maven. You should install maven on your local machine in order to compile and generate the executable _jar_ of application.
[Maven install and download](https://maven.apache.org/install.html)

Before compile, in your **application.properties** file (_/src/main/resources/application.properties_), define Username and password for your database and SMTP server email.
Example:

```
spring.datasource.username=postgres
spring.datasource.password=some_password

spring.mail.username=some_email@gmail.com
spring.mail.password=some_password
```

Once you have everything set, run the command below to compile:

```
mvn clean install
```

On _target_ folder you will have the executable _jar_ generated.
Run the command below in order to start the application.

```
java -jar order.manager-0.0.1-SNAPSHOT.jar
```

## Running with container

You can also _pull_ the docker image generated in order to test features and endpoints of application.

**Pre-requisite**: Install docker [Install docker](https://docs.docker.com/engine/install/)

```
docker pull bevilacqua96/order-manager:latest
docker run -p 127.0.0.1:32275:8080/tcp bevilacqua96/order-manager:latest
```

On that command above0 we have port 32275 exposed, so any of the application endpoints will be query through the port 32275.

Example:

```
http://localhost:32275/order-system/items
```

## Entities Diagram

![entity_diagram](https://github.com/bevilacqua1996/OrderManager/assets/18063196/ec960354-ec63-46fc-9a04-3681ed27d277)

## Application Context architecture

![application_overview (1)](https://github.com/bevilacqua1996/OrderManager/assets/18063196/10224bde-038d-4008-8e06-962c44502b17)

## Order Status Concepts

There are two main Order Status defined in the application:

- 1: OPEN
- 2: CLOSED

While an order is OPEN, Stock movement can vary between three status:

- 1: PACKING
- 2: IN TRANSIT
- 3: DELIVERED

Once the product is DELIVERED, the Order is CLOSED, and user is notified through an email that the order is closed.

## Endpoints documentation

For each of the entities the application will have GET, POST, PUT and DELETE operations. Besides, there are endpoints which are going to list all the objects saved on database for each of the entities.

**/order-system/item/{id}**
GET
PUT
DELETE

**/order-system/item**
POST

**/order-system/items**
GET

**/order-system/order/{id}**
GET
PUT
DELETE

**/order-system/order**
POST

**/order-system/orders**
GET

**/order-system/stock/movement/{id}**
GET
PUT
DELETE

**/order-system/stock/movement**
POST

**/order-system/stock/movements**
GET

**/order-system/user/{id}**
GET
PUT
DELETE

**/order-system/user**
POST

**/order-system/users**
GET

There are some others specific endpoints which will comply with specific requirements to register the Stock Movement evolution and changes in order Status.

**/order-system/stock/movement/order/{orderId}**
GET
- **description:** list all the stock movements for a specific open order

**/order-system/stock/movement/order/{orderId}/status/{statusId}**
POST
- **description:** creates new stock movements for a specific order registering the status as the order evolves. If order is closed, send an email at the end of operation to user who requested the order.

For more information, the API contract is on _/src/main/resources/**orderManager.yaml**_

## More about

You see more about this application and even how the CI process of this project was built in Substack article. Check on [Substack Article](https://open.substack.com/pub/bevilacqua96/p/building-your-own-docker-image-using?r=2je0lv&utm_campaign=post&utm_medium=web)
