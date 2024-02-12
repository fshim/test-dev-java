# Тестовое задание
## Проверка:
1. Установить окружение (нужен docker)
``` 
docker-compose up -d  
```
2. Сборка
``` 
./mvnw clean package spring-boot:repackage -DskipTests
```
3. Запуск
``` 
java -jar target/test-dev-java-0.0.1-SNAPSHOT.jar
```

Отправить и посмотреть сообщения можно через KafkaUI http://localhost:8085

**transaction.event.topic** - топик, где ожидаются транзакции (одна транзакция - одно сообщение), например:
``` 
{
  "PID": 123,
  "PAMOUNT": 94.7,
  "PDATA": 20160101120000
}
```
http://localhost:8085/ui/clusters/local/all-topics/transaction.event.topic

**notification.event.topic** - результаты проверки
http://localhost:8085/ui/clusters/local/all-topics/notification.event.topic/messages?keySerde=String&valueSerde=String&limit=100