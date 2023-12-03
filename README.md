# Example project for Crocobet Flink

## Local launch

```
docker run --name hazelcast -p 5701:5701 -d hazelcast/hazelcast
docker run --name postgres -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=crocobet -d postgres:16.0-alpine
docker run --name pulsar -p 6650:6650 -p 8080:8080 -d apachepulsar/pulsar:3.1.1 bin/pulsar standalone
docker exec -it pulsar /pulsar/bin/pulsar-admin topics create public/default/payment-topic
```

### Build and run local

```
gradle clean build -x test
java -jar build/lib/example-all.jar
```

### Run in Idea

```
run com.crocobet.example.Application
run com.crocobet.example.PaymentServiceTest.generatePaymentsTest in main Example app fro testing
```

## Docker launch

```
User docker compose command in main example project: docker-compose up 
```

## Flink dashboard

* http://localhost:8081

## Technologies

* `Apache Pulsar`
* `Apache Flink`
* `PostgreSQL`
* `Gradle`
* `Docker`

## Author

* Zura Chaganava

