# Dental Clinic - CRUD Project

This is a small project that implements a CRUD (Create, Read, Update, Delete) system for a dental clinic. The system manages two main entities: **Dentist** and **Patient**. Through this system, we can create, store, and manage these objects in an H2 database, performing CRUD operations using the **DAO** (Data Access Object) pattern along with other technologies such as **Maven**, **JUnit**, **JDBC**, and **Log4j**.

## Technologies Used

- **Java 11** or later
- **Maven**: For dependency management and building the project
- **H2 Database**: In-memory database to store the data of dentists and patients
- **JDBC**: Connection and access to the database
- **DAO Pattern**: Design pattern to separate data access logic
- **JUnit 5**: Unit testing framework
- **Log4j 2**: Logging framework

## Features

The project allows performing the following CRUD operations:

- **Create** a new dentist or patient.
- **Read** a dentist or patient by their ID.
- **Update** the details of a dentist or patient.
- **Delete** a dentist or patient.
- **List** all dentists and patients stored in the database.

## Setup

### Prerequisites

- **Java 11** or later.
- **Maven** installed on your system.
- **H2 Database** is embedded in the project, so no additional installation is required.

### Maven Dependencies

The `pom.xml` file includes the following dependencies:

```xml
<dependencies>
   <!-- Log4j for logging -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.24.1</version>
    </dependency>

    <!-- JDBC for H2 database connection -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.3.232</version>
    </dependency>

     <!-- JUnit for unit testing -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.11.3</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine<artifactId>
        <version>5.11.3</version>
    </dependency>
    </dependencies>
```

### Log4j Configuration

The `log4j2.xml` file should be located in the src/main/resources/ folder and contains the configuration for logging. Here's an example of a basic configuration for Log4j 2:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <!-- Appender config for terminal -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d [%t] %-5level: %msg%n" />
        </Console>

        <!-- Appender config for size file and rollover -->
        <RollingFile name="RollingFileAppender" fileName="logs/average-log.txt"
                     filePattern="logs/app-log-%i.txt">
            <PatternLayout pattern="%d [%t] %-5level: %msg%n" />
            <Policies>
                <!-- File size limit to 5MB -->
                <SizeBasedTriggeringPolicy size="5MB"/>
            </Policies>
            <DefaultRolloverStrategy max="10"/>
        </RollingFile>
    </Appenders>
    <Loggers>
        <!-- logger root config -->
        <Root level="debug">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="RollingFileAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

## Running the Project

To run the project, simply execute the `Main.java` file from your IDE or using Maven:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.'proyect-name'.Main"
```

## Contact

If you have any questions or feedback, feel free to reach out!

- Email: sebastianpallerodev@gmail.com
- GitHub: https://github.com/Sebaspallero