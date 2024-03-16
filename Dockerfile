FROM eclipse-temurin:8u392-b08-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]

# To pull a MS SQL image from DockerHub
# => " docker pull mcr.microsoft.com/mssql/server:2019-latest "

# To create a MS SQL container with an existed image
# => " docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=HotelBooking123@" -p 1400:1433 --name mssql-2019-container -d mcr.microsoft.com/mssql/server:2019-latest "

# To create an image
# => " docker build --tag hotel-booking-image . "

# To create a container with an existed image
# => " docker run -d -p 127.0.0.1:8081:8081 --name hotel-booking-container hotel-booking-image "