[![Java CI with Maven](https://github.com/ibobrov/test/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/ibobrov/test/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/ibobrov/test/graph/badge.svg?token=9fFCtDJINL)](https://codecov.io/gh/ibobrov/test)

# Тестовое задание для SML

### Тестовое задание (оригинальное)

Реализовать REST АПИ для клиентского приложения, которое должно отображать страницу со списком студентов с возможностями:
1. добавить нового студента в список;
2. удалить существующего студента;
3. отредактировать существующего студента;
4. внести изменения названий (поле text) в справочнике успеваемости. Исходные значения:
   [{id 2, text "неуд"}, {id 3, text "уд"}, {id 4, text "хор"}, {id 5, text "отл"}]

У студента есть поля:
- ФИО
- дата рождения
- успеваемость (опционально, значение из справочника)

Предлагаемый стек:
Java, Spring Framework, PostgreSQL, Spring-data, Liquibase/Flyway, Swagger, Docker

Сопутствующие требования:
1. инструкция по разворачиванию исходной версии СУБД;
2. автогенерация swagger-документации на основании исходного кода;
3. валидация

---

### Как запустить?  

// TODO

---

### Используемый мной стек:
* Java 17
* Maven
* Spring Boot, Data, Test, Validation
* PostgreSQL
* Liquibase
* JUnit 5
* Swagger
* Docker