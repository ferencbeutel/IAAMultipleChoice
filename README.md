# IAAMultipleChoice

## Prerequirements
- Make sure that you have java8, ruby, rubygems and tomcat8 installed!
- Install CSS-Preprocessor (SASS)
```shell
gem install sass
```
- Update IntelliJ's H2-Driver! There is a bug in it..
```shell
https://groups.google.com/forum/#!topic/h-database/5LZlZ_jC4QI
```
- First, download latest drivers, for example from here:
```shell
http://repo2.maven.org/maven2/com/h2database/h2/1.4.192/h-1.4.192.jar
```
- Then, in IntelliJ, open your Database view and click on Data Source Properties
- Under Drivers, select H2
- Remove the old jar-File and add the downloaded one

## Installation
- Fulfill Prerequirements

##### IntelliJ Basic Config
- Enable Annotation Processing
- Search and install the Lombok Plugins for IntelliJ
- Restart IntelliJ

#### Setup Project
- In IntelliJ, select New -> Project from Version Control -> Github
- Enter Github Credentials, paste Repository URL: 
```
https://github.com/ferencbeutel/IAAMultipleChoice.git
```
- Click through the Project Setup, make sure you add at least the web facet
- Right-Click pom.xml, select Maven -> Reimport
- Navigate to File -> Project Structure -> Artifacts
- If not already there add a Web Application: explored Artifact
- Add Libraries into the Artifact

#### Configure Project
- Copy src/main/resources/spring.properties_template to src/main/resources/spring.properties
- Replace all placeholders in spring.properties with the correct ones for your setup
- example config, change datasource url at least:
    - datasource.url=/Users/ferencbeutel/IdeaProjects/IAAMultipleChoice/MultipleChoiceDB
    - smtp.host=smtp.gmail.com
    - smtp.port=587
    - smtp.startTls=true
    - smtp.auth=true
    - smtp.user=seminartests.autobot@gmail.com
    - smtp.password=maxnacken

#### Sass Compilation
- run the following code on the command line in the root project
```shell
sass --update web/static/:web/static/
```

#### Tomcat Setup
- Navigate to Run -> Edit Configurations
- Add a new tomcat local server configuration
- Under Application Server, select your installed Tomcat Root Folder
- Uncheck the After Launch Option
- under VM Options, include this if you have an IPv6-Address:
```
-Djava.net.preferIPv4Stack=true
```
- Under the deployment tab, select your Web: exploded facet