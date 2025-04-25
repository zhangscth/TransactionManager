mvn clean package
docker build -t bank-transactions .
docker run -p 8080:8080 bank-transactions