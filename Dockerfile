# 1) Берём официальный образ с JDK 21
FROM eclipse-temurin:21-jdk

# 2) Рабочая директория внутри контейнера
WORKDIR /app

# 3) Копируем весь проект (включая mvnw и .mvn)
COPY . .

# 4) Делаем mvnw исполняемым
RUN chmod +x mvnw

# 5) Собираем приложение без тестов
RUN ./mvnw package -DskipTests

# 6) Открываем порт, на котором слушает Spring Boot
EXPOSE 8080

# 7) Запускаем приложение
CMD ["sh", "-c", "java -jar target/*.jar"]
