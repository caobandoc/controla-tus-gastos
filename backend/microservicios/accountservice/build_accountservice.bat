@echo off

echo Building accountservice...

docker rmi catalog-service:0.0.1
docker build -t catalog-service:0.0.1 .