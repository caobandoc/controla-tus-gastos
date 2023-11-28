@echo off

echo Building authservice2...

docker rmi auth-service:0.0.2
docker build -t auth-service:0.0.2 .