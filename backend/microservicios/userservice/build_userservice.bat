@echo off

echo Building userservice...

docker rmi user-service:0.0.1
docker build -t user-service:0.0.1 .