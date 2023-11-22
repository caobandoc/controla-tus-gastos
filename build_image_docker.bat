@echo off

cd %~dp0\config\configserver
echo Building configserver...
docker build -t config-server:0.0.1 .
cd ..

cd eurekaserver
echo Building eurekaserver...
docker build -t eureka-server:0.0.1 .
cd ..

cd gatewayserver
echo Building gatewayserver...
docker build -t gateway-server:0.0.1 .
cd ..

cd ..\microservicios\authservice
echo Building authservice...
docker build -t auth-service:0.0.1 .
cd ..

cd authservice2
echo Building authservice2...
docker build -t auth-service:0.0.2 .
cd ..

cd userservice
echo Building userservice...
docker build -t user-service:0.0.1 .
cd ..

echo Build completed for all projects.
