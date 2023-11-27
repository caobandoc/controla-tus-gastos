@echo off

cd %~dp0\config\configserver
echo Building configserver...
docker rmi config-server:0.0.1
docker build -t config-server:0.0.1 .
cd ..

cd eurekaserver
echo Building eurekaserver...
docker rmi eureka-server:0.0.1
docker build -t eureka-server:0.0.1 .
cd ..

cd gatewayserver
echo Building gatewayserver...
docker rmi gateway-server:0.0.1
docker build -t gateway-server:0.0.1 .
cd ..

cd ..\microservicios\authservice
echo Building authservice...
docker rmi auth-service:0.0.1
docker build -t auth-service:0.0.1 .
cd ..

cd authservice2
echo Building authservice2...
docker rmi auth-service:0.0.2
docker build -t auth-service:0.0.2 .
cd ..

cd userservice
echo Building userservice...
docker rmi user-service:0.0.1
docker build -t user-service:0.0.1 .
cd ..

echo Build completed for all projects.
