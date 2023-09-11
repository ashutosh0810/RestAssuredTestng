
# Restful booker api


This application is rest-based architecture and it leverages standard HTTP methods, aiming to provide interoperability between computer systems on the internet

## Installation

Steps to run the framework.
Unzip the code 
Navigate to the booking app folder if not by default
For Windows system: run runWindows.bat file   
```bat
runWindows.bat
```

For Linux system: runShell.sh file
```bash
./runShell.sh
```
 ## Folder structure

```
src/main/java/org/booking 

This structure contains the following class
Commons.java == It will contain reused constant value 
HttpsMethods.java= This will contain all the generic 
                   REST HTTPS method like GET PUT POST PATCH DELETE
Util.java= This class will contain the config file reading code 
```

```
BookingApp\src\main\resources\jsonbody

This will contain the static json body for the authentication 
log4j2.xml == configuration for the logger 
testConfig.properties == test config and test data 

```

```
BookingApp\src\test\java\org\bookingTest

This directory will contain all the test cases required for testing the API
```
```
BookingApp\TestReport

This will contain the test reports based on running time stamp like 
Booking Api Automation2023.09.11.13.09.34.html
```
```
BookingApp\logs 

This will contain all the logs with time stamp like app-2023-09-11-13-09-33.log 
```