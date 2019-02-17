# Service Provider One | Workforce Manager Application
---
**Prerequisites**
* Java 1.8 or higher
---
**Usage**

**Start Application**

**Gradle**
* Run command "gradlew.bat bootRun" if Windows OS
* Run command "./gradlew bootRun" if Linux or Mac OS

**IDE**
1. Open as Gradle Java project in IntelliJ IDE.
2. Create Run configuration for a Java Application.
3. Use spo.workforcemanager.WorkforceManagerApplication as the Main Class
4. Use spo.Workforce_Manager as classpath of module.
5. Select JRE 1.8 or higher
6. Run the created Run configuration.
8. SpringBoot REST application will be started in an embedded tomcat server with port 8080

---
**REST Endpoints**

POST request to http://localhost:8080 with the input json.

***Example 1***

{ "rooms": [35, 21, 17], "senior": 10, "junior": 6 }
Gives a Status 200 and the below json response:
[{"senior":3,"junior":1},{"senior":1,"junior":2},{"senior":1,"junior":1}]

***Example 2***

{ "rooms": [24,28], "senior": 11, "junior": 6 }
Gives a Status 200 and the below json response:
[{"senior":2,"junior":1},{"senior":2,"junior":1}]

---
**Tools**
* Use a REST Client like Postman
* Use Java 11's new HTTP Client
* Use any thirdparty HTTP Client
---
**Stop Application**
* Stop tomcat service to stop the application
---
