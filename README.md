# Example project for Crocobet Flink

## Local launch

```
docker run --name hazelcast -p 5701:5701 -d hazelcast/hazelcast
docker run --name postgres -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=crocobet -d postgres:16.0-alpine
docker run --name pulsar -p 6650:6650 -p 8080:8080 -d --mount source=pulsardata,target=/pulsar/data --mount source=pulsarconf,target=/pulsar/conf apachepulsar/pulsar:3.1.1 bin/pulsar standalone
```

### Build and run local

```
gradle clean build -x test
java -jar build/lib/example-all.jar profiles.active=dev
```

### Run in Idea

```
profiles.active=dev
```

## Technologies

* `Apache Pulsar`
* `Apache Flink`
* `PostgreSQL`
* `Gradle`
* `Docker`

## Author

* Zura Chaganava

