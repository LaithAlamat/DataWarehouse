# DataWarehouse Application

This is a Spring Boot application developed using JDK 17, Spring Boot, JUnit, Spring Data, and Docker containerization. It provides functionality for dealing with financial transactions or deals.

## Application Structure

The application consists of the following main components:

- **DealController**: This class defines REST endpoints for dealing with deals. It handles HTTP requests related to adding deals.

- **DealExceptionHandler**: This class is responsible for handling exceptions thrown during deal processing, specifically for handling `InvalidDealException`.

- **Deal**: An entity class representing a deal. It includes fields such as `dealUniqueId`, `fromCurrencyISOCode`, `toCurrencyISOCode`, `dealTimestamp`, and `dealAmount`.

- **DealRepository**: This interface extends JpaRepository and provides methods for CRUD operations on the Deal entity.

- **DealsAdapter**: This service class maps DealDTO objects to Deal entities.

- **DealService**: This service class provides methods for dealing with Deal entities, including saving deals and handling duplicate deals.

## Running the Application

To run the application, make sure you have Docker installed on your system.

1. Clone the repository.
2. Clean the project using the following:
    ```
   mvn clean package
   ```
3. Build the Docker image using the provided Dockerfile:
    ```
    docker build -t laithprog --build-arg JAR_FILE=./target/datawarehouse-1.0-SNAPSHOT.jar .
    ```
4. 
    ```
    docker compose up
    ```

The application will be accessible at `http://localhost:8080`.

## Testing

The application includes unit tests written using JUnit to ensure the correctness of its functionalities.


## Dependencies

The application relies on the following dependencies:

- **Spring Boot**: Provides a powerful framework for building Java applications.
- **JDK 17**: The Java Development Kit version 17.
- **Spring Data**: Simplifies the implementation of data access layers in Spring applications.
- **JUnit**: A widely used testing framework for Java applications.
