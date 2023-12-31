@echo off

echo Building accountservice...

docker rmi account-service:0.0.1
docker build -t account-service:0.0.1 .