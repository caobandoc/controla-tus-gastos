@echo off

echo Building configserver...

docker rmi config-server:0.0.1
docker build -t config-server:0.0.1 .