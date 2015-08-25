
#### WUnderground

##### 1. Synopsis:

* WUnderground is a web application, to fetch the temperature details for the given US zip code.
        
* It uses the Weather Underground services to pull the weather informations.
        
* The app is delevoped with Spring MVC (3.2.14.RELEASE), it fetches the JSON response from the 
 Weather Underground services, and parse it using the google gson libraries (version 2.3.1) to parse
 the JSON responses.

##### 2. API References:

* [mockito](http://mockito.org)
* [Weather Underground services](http://www.wunderground.com)
* [GSON](https://sites.google.com/site/gson/gson-user-guide)
* [freemarker](http://freemarker.org/)

##### 3. Build Instructions:

* Install [Apache Maven](https://maven.apache.org/), using their instructions.
 * maven 2.2.1 or later is required.


* Clone the [source repository](https://github.com/amiticrdev/WUnderground) from Github. 
 * On the command line, enter:
```
git clone https://github.com/amiticrdev/WUnderground.git
``` 

* Changes to be made before running the app:

 * This app is default configured to be run on java 6, alternatively you can use a later java version also, by updating below plugin in pom.xml (WUnderground/pom.xml) (eg: set it to 1.7 in case you are using java 7) 
```
 <plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
       <source>1.6</source>
       <target>1.6</target>
    </configuration>
 </plugin>
```
* Open application.properties (WUnderground/src/main/resources/application.properties) file and specify your 'underground.weather.api.key' which is required to access the Underground services.


* Open a terminal/console/command prompt, change to the directory where you cloned Processing, and type:
```
mvn clean install tomcat7:run
```
* Above command will install and deploy your app on tomcat 7, you will not even need to start tomcat seterately, if you see logs similar to below, and no error message on the console, then you are good to go.

```
[INFO] Preparing tomcat7:run
[INFO] [resources:resources {execution: default-resources}]
[INFO] Copying 4 resources
[INFO] [compiler:compile {execution: default-compile}]
[INFO] Nothing to compile - all classes are up to date
[INFO] [tomcat7:run {execution: default-cli}]
[INFO] Running war on http://localhost:8080/WUnderground

```

* Your app is now deployed on : [http://localhost:8080/WUnderground](http://localhost:8080/WUnderground), hit this url and get started.


##### 4. Tests: 

#####Following are the test case details:
-----------------------------------------

* ZipcodeValidatorTest - This test verifies the various zip code related validations like empty value, character length limit, using integer only values etc.
        
* WUndergroundServiceTest - This is a test class for WUndergroundService, which fetches the Weather Data from the weather underground service, which uses mockito (a mocking framework for unit tests) for mocking the actual service and test it.
        
* WeatherControllerTest - This is a test class for WeatherController, which accepts the zip code from user, validates it and get the the Weather Data using the weather underground service, this test also uses mockito for mocking the actual service.
        
#####Commands to run the test cases:
------------------------------------

```
mvn test -Dtest=com.weather.validation.ZipcodeValidatorTest

mvn test -Dtest=com.weather.service.WUndergroundServiceTest

mvn test -Dtest=com.weather.controller.WeatherControllerTest

```
