@e@echo off

echo Building eurekaserver...

docker rmi eureka-server:0.0.1
docker build -t eureka-server:0.0.1 .
