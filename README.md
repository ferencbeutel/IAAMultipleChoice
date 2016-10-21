# IAAMultipleChoice

## Prerequirements
- Make sure that you have java8, ruby, rubygems and tomcat8 installed!
```shell
gem --version # should deliver a valid output
```
- Install CSS-Preprocessor (SASS)
```shell
gem install sass
```
- Update your H2-Driver which IntelliJ uses! There is a bug in it..
```shell
https://groups.google.com/forum/#!topic/h2-database/5LZlZ_jC4QI
```
First, download latest drivers, for example from here:
```shell
http://repo2.maven.org/maven2/com/h2database/h2/1.4.192/h2-1.4.192.jar
```
Then, in IntelliJ, open your Database view and click on Data Source Properties
Under Drivers, select H2
Remove the old jar-File and add the downloaded one
After that, drag and drop the database into the Database View

## Installation

- Fulfill Prerequirements
- Enable Annotation Processing
- Open IntelliJ, Navigate to File -> Settings -> Plugins
- Search and install File Watchers and Lombok Plugin
- Restart IntelliJ
- In IntelliJ, select New -> Project from Version Control -> Github
- Enter Github Credentials, paste Repository URL: 
```
https://github.com/ferencbeutel/IAAMultipleChoice.git
```
- Click through the Project Setup, make sure you add at least the web facet
- Right-Click pom.xml, select Maven -> Reimport
1- Open src/main/resources/spring.xml File
1- At the top of the Screen in the yellow frame Select 'Add Spring facet'
1- Copy src/main/resources/spring.properties_template to src/main/resources/spring.properties
1- Replace all placeholders in spring.properties with the correct ones for your setup
1- Navigate to File -> Project Structure -> Artifacts
1- If not already there add a Web Application: explored Artifact
1- Drag multiplechoice on \<output root\>, save and close dialog
1- Navigate to File -> Settings and under Tools click on File Watchers
1- Add a new Sass File Watcher
1- Change Scope to Project Files
2- If Program is not auto-detected select your installed sass-preprocessor here
2- Save and close Settings
2- Navigate to Run -> Edit Configurations
2- Add a new tomcat local server configuration
2- Name it Tomcat8 or something similar
2- Under Application Server, select your installed Tomcat Root Folder
2- Uncheck the After Launch Option
2- Under the deployment tab, select your Web: exploded facet
2- On the Server tab, Switch 'On Update' and 'On frame deactivation' to 'Update classes and resources'
2- Save and Close config, Start Tomcat
3- Wait until 'Artifact is deployed successfully' is seen in log output
3- navigate to localhost:8080/test to see if the application is working