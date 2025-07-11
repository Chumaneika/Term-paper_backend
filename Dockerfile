# 1) Базовый образ с JDK 17
FROM eclipse-temurin:17-jdk

# 2) Рабочая директория внутри контейнера
WORKDIR /app

# 3) Копируем всё из репозитория (включая mvnw и .mvn)
COPY . .

# Делаем mvnw исполняемым (на всякий случай)
RUN chmod +x mvnw

# 4) Собираем приложение без тестов
RUN ./mvnw package -DskipTests

# 5) Открываем порт, на котором будет слушать Spring Boot
EXPOSE 8080

# 6) Запускаем приложение
CMD ["java", "-jar", "target/Term-paper_backend-0.0.1-SNAPSHOT.jar"]
