# 1) Базовый образ с JDK 17
FROM eclipse-temurin:17-jdk

# 2) Рабочая директория внутри контейнера
WORKDIR /app

# 3) Копируем pom.xml и сами исходники
COPY pom.xml .
COPY src ./src

# 4) Собираем приложение без тестов (ускорение сборки)
RUN ./mvnw package -DskipTests

# 5) Открываем порт, на котором будет слушать Spring Boot
EXPOSE 8080

# 6) Команда запуска — укажи точное имя JAR-файла, который собирается
CMD ["java", "-jar", "target/Term-paper_backend-0.0.1-SNAPSHOT.jar"]
