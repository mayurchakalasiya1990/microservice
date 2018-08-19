Part 1 - Config Server:

- Create a new Spring Boot application. Name the project "SpringCloud-Server-Demo”, and use this value for the Artifact. Use Jar packaging and the latest versions of Java. Use a version of Boot > 2.0.x. No need to select any dependencies.

- Edit the POM (or Gradle) file. Add a “Dependency Management” section (after <properties>, before <dependencies>) to identify the spring cloud parent POM. "Finchley.RELEASE" is the most recent stable version at the time of this writing, but you can generally use the latest stable version available. Example:

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
    
- Add a dependency for group "org.springframework.cloud" and artifact "spring-cloud-config-server". You do not need to specify a version -- this is already defined in the spring-cloud-dependencies BOM.

- Edit the main Application class (probably named Lab3ServerApplication). Add the @EnableConfigServer to this class.

- Create a new repository on GitHub to hold your application configuration data. Call the repository "ConfigData" or a name of your choosing. Note the full URI of the repository, you will need this in a following step.

- Add a new file to your GitHub repository called "SpringCloud-Client-Demo.yml” (or SpringCloud-Client-Demo.properties). Add a key called "lucky-word" and a value of "Irish", "Rabbit's Foot", "Serendipity", or any other value of your choosing.

- Back in your project, create an application.yml (or application.properties) file in the root of your classpath (src/main/resources recommended). Add the key "spring.cloud.config.server.git.uri" and the value "https://github.com/"YOUR-GITHUB-ID"/ConfigData", substituting the value for Github ID and repository name as needed.

- Also within application.yml (or application.properties), set the “server.port” to 8001.

- Run the application. Open the URL http://localhost:8001/SpringCloud-Client-Demo/default/. You should see the JSON result that will actually be used by Spring. If the server is not working, review the prior steps to find the issue before moving on.
