# Santander Bootcamp 2023
Projeto criado durgitante o BootCamp Santander Backend Java 2023

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        - userId : Long
        - userName: String
        - userAccount: Account
        - features: Feature[]
        - card: Card
        - news: News[]
    }
    
    class Account {
        - accountId : Long
        - accountNumber: String
        - accountAgency: String
        - accountBalance: Number
        - adictionalLimit: Number
    }
    
    class Feature {
        - id : Long
        - Icon: String
        - Description: String
    }
    
    class Card {
        - cardId : Long
        - cardNumber: String
        - availableLimit: Number
    }
    
    class News {
        - id : Long
        - Icon: String
        - Description: String
    }

    User "1" *-- "1" Account
    User "1" *-- "N" Feature
    User "1" *-- "1" Card
    User "1" *-- "N" News
```
