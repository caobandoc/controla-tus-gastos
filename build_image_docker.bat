@echo off

echo Building backend projects...

cd backend/configserver
call build_configserver.bat

cd ../eurekaserver
call build_eurekaserver.bat

cd ../gatewayserver
call build_gatewayserver.bat

cd ../microservicios/authservice
call build_authservice.bat

cd ../authservice2
call build_authservice.bat

cd ../userservice
call build_userservice.bat

echo Build frontend projects...

cd ../../../frontend/controla-tus-gastos-angular
call build_angular.bat

echo Build completed for all projects.