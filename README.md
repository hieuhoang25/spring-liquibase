# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#howto.data-initialization.migration-tool.liquibase)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
* [Liquibase](https://docs.liquibase.com/home.html)
### DEMO
We have three ways for configuring application : using .properties(yaml) file, using xml, using bean config. Beside, We 
have three types of mapping entity between backend and database: Mapping both Spring and DB, Mapping in Spring but not mapping in DB, Mapping DB but not mapping Spring
1. Let's dive into migration (Liquibase)
- Configuration Liquibase
```java
# application.properties
    server.port = 8081
        spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
        spring.datasource.username= postgres
        spring.datasource.password= 2510

        spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
        spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

        # Hibernate ddl auto (create, create-drop, validate, update)
        spring.jpa.hibernate.ddl-auto= none
        spring.jpa.show-sql = true

        spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml

        spring.liquibase.enabled = true
```
- db/changelog
-- db.changelog-master.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
        xmlns:pro="http://www.liquibase.org/xml/ns/pro"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd
        http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd
        http://www.liquibase.org/xml/ns/pro http://www.liquibase.org/xml/ns/pro/liquibase-pro-latest.xsd">
    <includeAll path="db/changelog/v1.0/"/>
</databaseChangeLog>
```
-- v1.0/
```yml
# v1_0_1_create_tbl_book.yaml
    databaseChangeLog:
    - preConditions:
        - onFail: CONTINUE
        - onError: CONTINUE
    - changeSet:
        id: v1_0
        author: hicode
        changes:
          - createTable:
              tableName: tbl_book
              columns:
                - column:
                    name: id
                    type: int
                    autoIncrement: true
                    constraints:
                      primaryKey: true
                      nullable: false
                - column:
                    name: name
                    type: varchar(50)
                - column:
                    name: price
                    type: double
                - column:
                    name: description
                    type: varchar(255)
                - column:
                    name: author_id
                    type: int
```
```yml
# v1_0_1_create_tbl_author.yaml
databaseChangeLog:
  - preConditions:
      - onFail: CONTINUE
      - onError: CONTINUE
  - changeSet:
      id: v_1_1
      author: hoangit
      failOnError: true
      changes:
        - sql: |
            CREATE TABLE tbl_author (
              id SERIAL PRIMARY KEY,
              name VARCHAR(255),
              email VARCHAR(255)
            )
```
```yml
# v1_0_1_add_foreignkey.yaml
databaseChangeLog:
  - preConditions:
      - onFail: CONTINUE
      - onError: CONTINUE
  - changeSet:
      id: v_0_3
      author: hicode
      failOnError: true
      changes:
        - sql: |
            ALTER TABLE tbl_book 
            ADD CONSTRAINT constraint_name
            FOREIGN KEY (author_id)
            REFERENCES tbl_author (id);

```
- Entity
```java
// Book
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="tbl_book")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    private String description;

    @Column(name = "author_id")
    private Integer authorId;
}

```

```java
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_author")
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String email;
}
```