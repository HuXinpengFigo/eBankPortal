

# README

## Project Structure

This project is a banking API uses `Spring Boot`, `Kafka` and `MongoDB`, which uses Kafka as message queue, MongoDB as data persistence. For fast deployment, a docker-compose file is provided to quickly setup environment and start required service.

```
    ++++++++++++          ++++++++++++  Add Data
    +  Message +--------->+   API    +------------
    ++++++++++++          ++++++++++++           |
                            Read Data            |
                               |                 V 
                               |           Message Bus
                               |           +++++++++++++
                               |           +   Kafka   +
                               |           +++++++++++++
                               |                 |
                               |                 |
                               V                 |
                            Database             |
                          ++++++++++++           |
                          +  MongoDB + <----------
                          ++++++++++++
```

Here is the application file structure and description of every file

```
ebankportal
    │   EBankPortalApplication.java : Main Application
    │
    ├───config
    │       JwtAuthenticationEntryPoint.java : For JWT setup
    │       JwtRequestFilter.java : For JWT setup
    │       JwtTokenUtil.java : For JWT setup
    │       KafkaTopicConfig.java : Config of Kafka
    │       WebSecurityConfig.java : Config of Spring Security
    │
    ├───controller
    │       JwtAuthenticationController.java : Auth related API
    │       TransactionController.java : Transaction related API
    │       UserController.java : User infomation related API
    │
    ├───entity
    │       Balance.java : User's balance and Credit Debit information
    │       BalanceAndExchangeRate.java : Balance and Exchange rate information
    │       ExchangeRate.java : Exchange rate of various currency based on HKD
    │       Transaction.java : Transaction information
    │       User.java : User's basic information
    │
    ├───model
    │       AddTransactionRequest.java : POJO of add transaction request
    │       ExchangeRateAPIResponse.java : POJO of call exchange rate API response
    │       JwtRequest.java : POJO of JWT request
    │       JwtResponse.java : POJO of JWT response
    │       TransactionResponse.java : POJO of transaction related operation
    │       UserResponse.java : POJO of user related operation
    │
    ├───repository
    │       TransactionRepository.java : Mongo repository of transaction
    │       UserRepository.java : Mongo repository of user
    │
    └───service
            JwtUserDetailsService.java : JWT service
            TransactionKafkaConsumer.java : Consume add transaction message
            TransactionKafkaProducer.java : Produce add transaction message
            TransactionService.java : Processes of query transactions
            UserKafkaConsumer.java : Consume add user message
            UserKafkaProducer.java : Produce add transaction message
            UserService.java : Processes of query users
```

## Requirements 

1. Java 17
2. Spring Boot 2.4.5
3. Maven
4. Kafka
5. Zookeeper
6. MongoDB
7. Docker

## Usage

### Setup environment through docker file

In root of the project you can find `docker-compose.yml`, then in that directory, run

```sh
docker-compose up
```

### Start project

With docker file's environment setup okay, use IDEA to load the project and run the main application. API works in `localhost:8080`

### Test API

Use postman to send request for testing APIs, the postman request list is given in the root directory.

### API list

JwtAuthenticationController:

| Path    | Method | Description                                    |
| ------- | ------ | ---------------------------------------------- |
| /signup | POST   | Add User to Database                           |
| /login  | GET    | Get JWT token through correct user information |

UserController:

| Path  | Method | Description                      |
| ----- | ------ | -------------------------------- |
| /user | GET    | Get user contents by email or id |

TransactionController:

| Path                             | Method | Description                            |
| -------------------------------- | ------ | -------------------------------------- |
| /trans                           | GET    | Get transaction contents by id         |
| /trans                           | POST   | Add a transaction                      |
| /trans/email                     | GET    | Get transaction contents by email      |
| /trans/period                    | GET    | Get transactions by time period        |
| /trans/currency                  | GET    | Get transactions by currency name      |
| /trans/balance_and_exchange_rate | GET    | Get balance and exchange rate by email |

### Running pipeline

```
         Get token                Using token
Signin --> Login --> Doing operation relatied to user or transaction
```

### CI/CD

For CI/CD, this project uses `Github Action` to  verify docker file and other small tasks in CI.