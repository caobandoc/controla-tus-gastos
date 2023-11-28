@echo off

echo Building authservice...

docker rmi auth-service:0.0.1
docker build -t auth-service:0.0.1 .