@echo off

echo Building gatewayserver...

docker rmi gateway-server:0.0.1
docker build -t gateway-server:0.0.1 .