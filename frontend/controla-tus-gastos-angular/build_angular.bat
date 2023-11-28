@echo off

echo Building controla-tus-gastos-angular...

docker rmi controla-tus-gastos-angular:0.0.1
docker build -t controla-tus-gastos-angular:0.0.1 .
