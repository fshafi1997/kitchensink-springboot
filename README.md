# Kitchensink Spring Boot Migration

This is a migration of kitchensink from JBOSS EAP to Spring Boot

## Prerequisites

- Java 21
- Maven 3.6+
- Git (for version control)
- An IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Getting Started

### Cloning the Repository

```bash
gh repo clone fshafi1997/kitchensink-springboot
cd kitchensink-springboot
```

### Building the application
```
cd kitchensink-springboot
mvn clean package
```

### Running the Application
```
mvn spring-boot:run
```

### Running the tests
```
mvn test
```

### Access the application
Open your browser and navigate to:

- http://localhost:8080/index to access the main page.
- http://localhost:8080/h2-console to access the H2 database console (username: `sa`, password: `password`)

