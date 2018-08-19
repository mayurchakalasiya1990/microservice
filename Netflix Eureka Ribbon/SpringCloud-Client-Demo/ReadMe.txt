Part 2 - Config Client:

- Create a new, separate Spring Boot application. Use a version of Boot > 2.0.x. Name the project "SpringCloud-Client-Demo", and use this value for the Artifact. Add the web dependency. You can make this a JAR or WAR project, but the instructions here will assume JAR.

- Open the POM (or Gradle) file and add a “Dependency Management” section (after , before ) to identify the spring cloud parent pom. (You could simply change the parent entries, but most clients will probably be ordinary applications with their own parents):

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Finchley.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
- Add a dependency for group "org.springframework.cloud" and artifact "spring-cloud-starter-config”. You do not need to specify a version -- this is already defined in the parent pom in the dependency management section.

- Add a bootstrap.yml (or bootstrap.properties) file in the root of your classpath (src/main/resources recommended). Add the following key/values using the appropriate format:

spring.application.name=SpringCloud-Client-Demo
spring.cloud.config.uri=http://localhost:8001  
server.port=8002
(Note that this file must be "bootstrap" -- not "application" -- so that it is read early in the application startup process. The server.port could be specified in either file, but the URI to the config server affects the startup sequence.)_

- Add a REST controller to obtain and display the lucky word:

@RestController
public class LuckyWordController {

  @Value("${lucky-word}") String luckyWord;

  @GetMapping("/lucky-word")
  public String showLuckyWord() {
    return "The lucky word is: " + luckyWord;
  }
}
- Start your client. Open http://localhost:8002/lucky-word. You should see the lucky word message in your browser.

BONUS - Profiles:

- Create a separate file in your GitHub repository called "SpringCloud-Client-Demo-northamerica.yml” (or .properties). Populate it with the "lucky-word" key and a different value than used in the original file.

- Stop the client application. Modify the bootstrap file to contain a key of spring.profiles.active: northamerica. Save, and restart your client. Access the URL. Which lucky word is displayed? (You could also run the application with -Dspring.profiles.active=northamerica rather than changing the bootstrap file)

