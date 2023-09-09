@echo off
set currentDir=%CD%
echo Current Directory is : %CurrentDir%
mvn clean install 

echo Navigate to %CurrentDir%/TestReport to check the latest run result

pause;

