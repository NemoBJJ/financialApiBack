FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package
EXPOSE 8081
CMD ["java", "-jar", "target/*.jar"]