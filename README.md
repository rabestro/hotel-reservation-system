# Small Hotel Reservation System
## Story

Develop a small hotel reservation system. The application will use two types of users – hotel employees and customers.

Hotel administrators should be able to:

- Manage rooms – view / add / remove / edit
- See the schedule for each room
- See hotel availability statistics (how many rooms are free/busy) for a specified period

Customers should be able to:

- Check availability on the specified period
- Make a reservation

## Model UML diagram

```mermaid
classDiagram
    direction BT
    class AbstractAuditable {
        Date  createdDate
        Date  lastModifiedDate
    }
    class AbstractPersistable {
        PK  id
    }
    class Reservation {
        LocalDate  checkIn
        LocalDate  checkOut
    }
    class Room {
        String  number
    }
    class RoomType {
        String  description
        String  name
    }
    class User {
        String  email
        boolean  enabled
        String  name
        String  password
        Role  role
    }
    
    AbstractAuditable  --|>  AbstractPersistable 
    Reservation  --|>  AbstractAuditable 
    Room  --|>  AbstractAuditable 
    Room "0..1" <--> "0..*" Reservation 
    Room "0..*" <--> "0..1" RoomType 
    RoomType  --|>  AbstractAuditable 
    User  --|>  AbstractAuditable 
    User "0..1" <--> "0..*" Reservation 

```

## Acceptance tests

For functional testing, I used the [HTTP client](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html) built into the IntelliJ development environment. These tests can also be run from the command line and included in the CI process. The utility for running tests from the command line is located here.

https://github.com/restcli/restcli
