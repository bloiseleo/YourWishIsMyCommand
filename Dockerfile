FROM amazoncorretto:17-alpine-jdk as builder
WORKDIR /build
COPY . .
RUN ./mvnw package -DskipTests
FROM amazoncorretto:17 as app
WORKDIR /app
COPY --from=builder /build/target/YourWishIsMyCommand-0.0.1.jar .
ENTRYPOINT ["java", "-jar", "/app/YourWishIsMyCommand-0.0.1.jar", "--spring.profiles.active=prod"]
