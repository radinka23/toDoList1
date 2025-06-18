# ToDoList - Spring Boot & MySQL

ToDoList е уеб приложение за управление на задачи, изградено със Spring Boot и MySQL. Приложението е контейнеризирано с помощта на Docker и Docker Compose за лесна инсталация и стартиране.

---

##  Изграждане и стартиране на контейнерите

### Изисквания:
- Docker
- Docker Compose

### Команда за стартиране:
```bash
docker-compose up --build
```

Това ще:
- Изгради Spring Boot приложението като Docker image.
- Стартира два контейнера:
    - `todolist_app` (уеб приложението)
    - `todolist_mysql` (базата от данни)

Контейнерите се свързват автоматично чрез вътрешна Docker мрежа.

---

##  Структура на проекта

```
ToDoList/
│
├── Dockerfile                 # Dockerfile за Java приложението
├── docker-compose.yml         # Конфигурация за Compose
├── pom.xml                    # Maven конфигурация
├── README.md                  # Документация
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/toDoList/
│       │       ├── ToDoListApplication.java
│       │       ├── Controllers/
│       │       ├── Models/
│       │       ├── Repositories/
│       │       └── Services/
│       └── resources/
│           ├── application.properties
│           └── templates/     # Thymeleaf HTML файлове
```

---

##  Компоненти и тяхната роля

### 1. `todolist_app` (Spring Boot приложение)
- Управлява логиката за:
    - Регистрация и логин на потребители (Spring Security)
    - Създаване, редактиране, изтриване и визуализация на задачи
- Използва Thymeleaf за изграждане на уеб интерфейс
- Комуникира с MySQL чрез Spring Data JPA

### 2. `todolist_mysql` (MySQL база данни)
- Създава база данни `ToDoList`
- Достъпна през порт `3306`
- Данните се съхраняват в volume `todo-db-data` за устойчивост

---

##  Комуникация между услугите

Услугите общуват през вътрешната мрежа, дефинирана от Docker Compose.

В `application.properties` е конфигуриран достъп до базата с:

```properties
spring.datasource.url=jdbc:mysql://db:3306/ToDoList
spring.datasource.username=${DB_NAME}
spring.datasource.password=${DB_PASSWORD}
```

- `db` е името на услугата за MySQL в `docker-compose.yml`.
- Приложението използва JPA за автоматично създаване и актуализиране на таблици.

---

##  Заключение

Проектът използва добра архитектура с разделяне на логика, представяне и достъп до данни. Комуникацията между компонентите е сигурна и оптимизирана чрез Docker мрежа.