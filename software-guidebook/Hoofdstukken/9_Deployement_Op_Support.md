## 9. Installatie, Werking en Ondersteuning

### 9.1 Installatie

Om de Triptop applicatie lokaal te installeren, volg je de onderstaande stappen:

1. **Benodigde software installeren:**
   - **Java JDK 17**: Zorg ervoor dat de juiste versie van de JDK is geïnstalleerd. Controleer dit met `java -version`.
   - **Maven**: Installeer Maven voor het bouwen van de applicatie. Controleer dit met `mvn -version`.
   - **Docker**: Installeer Docker voor het draaien van de database en andere services.
   - **Git**: Zorg ervoor dat Git is geïnstalleerd om de repository te clonen.

2. **Repository clonen:**
   ```bash
   git clone https://github.com/AIM-ENE-feb25/triptop-groep-3-pedro-ramon-mees.git
   cd triptop
   ```

3. **Database configureren:**
   - Start een PostgreSQL-container met Docker:
     ```bash
     docker run --name triptop-db -e POSTGRES_USER=triptop -e POSTGRES_PASSWORD=triptop -e POSTGRES_DB=triptop -p 5432:5432 -d postgres:latest
     ```
   - Controleer of de database draait:
     ```bash
     docker ps
     ```

4. **Applicatie configureren:**
   - Pas de `application.properties` of `application.yml` aan in de map `src/main/resources` om de databaseverbinding te configureren:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/triptop
     spring.datasource.username=triptop
     spring.datasource.password=triptop
     ```

5. **Dependencies installeren:**
   ```bash
   mvn clean install
   ```

### 9.2 Uitvoeren

Om de applicatie lokaal uit te voeren, gebruik je de volgende stappen:

1. **Applicatie starten:**
   ```bash
   mvn spring-boot:run
   ```

2. **Toegang tot de API:**
   - De API is standaard beschikbaar op `http://localhost:8080`.
   - Controleer de gezondheid van de applicatie via de `/test`-endpoint:
     ```bash
     curl http://localhost:8080/test
     ```

3. **Debuggen:**
   - Start de applicatie in debugmodus:
     ```bash
     mvn spring-boot:run -Dspring-boot.run.fork=false -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
     ```
   - Verbind een IDE zoals IntelliJ IDEA of VS Code met de debugger op poort `5005`.

### 9.3 Deployment

Voor productie-omgevingen raden we aan om de applicatie te containeriseren en te deployen met Docker of Kubernetes.

1. **Docker-image bouwen:**
   - Voeg een `Dockerfile` toe aan de root van het project:
     ```dockerfile
     FROM openjdk:17-jdk-slim
     ARG JAR_FILE=target/triptop-0.0.1-SNAPSHOT.jar
     COPY ${JAR_FILE} app.jar
     ENTRYPOINT ["java", "-jar", "/app.jar"]
     ```
   - Bouw de Docker-image:
     ```bash
     mvn clean package -DskipTests
     docker build -t triptop-api:latest .
     ```

2. **Docker-container starten:**
   ```bash
   docker run -d -p 8080:8080 --name triptop-api triptop-api:latest
   ```

