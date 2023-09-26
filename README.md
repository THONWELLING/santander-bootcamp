# Santander Bootcamp 2023
Projeto criado durante o BootCamp Santander Backend Java 2023

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        - name: String
        - account: Account
        - features: Feature[]
        - card: Card
        - news: News[]
    }
    
    class Account {
        - accountNumber: String
        - accountAgency: String
        - accountBalance: Number
        - creditLimit: Number
    }
    
    class Feature {
        - featureIcon: String
        - featureDescription: String
    }
    
    class Card {
        - cardNumber: String
        - cardLimit: Number
    }
    
    class News {
        - newsIcon: String
        - newsDescription: String
    }

    User "1" *-- "1" Account
    User "1" *-- "N" Feature
    User "1" *-- "1" Card
    User "1" *-- "N" News
```
