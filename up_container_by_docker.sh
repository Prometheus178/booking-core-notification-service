./gradlew clean bootJar
docker image build -t booking-core-notification-service .
docker run -d --hostname notification-service --name notification-service -p 8081:8081 booking-core-notification-service