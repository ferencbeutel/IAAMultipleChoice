# IAAMultipleChoice

## Installation

1. Make sure that you have ruby, rubygems and tomcat8 installed!
```shell
gem --version # should deliver a valid output
```
2. Install CSS-Preprocessor (SASS)
```shell
gem install sass
```
3. Open IntelliJ, Navigate to File -> Settings -> Plugins
4. Search and install File Watchers Plugin
5. Restart IntelliJ
6. In IntelliJ, select New -> Project from Version Control -> Github
7. Enter Github Credentials, paste Repository URL: 
```
https://github.com/ferencbeutel/IAAMultipleChoice.git
```
8. Click through the Project Setup, make sure you add at least the web facet
9. Right-Click pom.xml, select Maven -> Reimport
10. Open src/main/resources/spring.xml File
11. At the top of the Screen in the yellow frame Select 'Add Spring facet'
12. Copy src/main/resources/spring.properties_template to src/main/resources/spring.properties
13. Replace all placeholders in spring.properties with the correct ones for your setup
14. Navigate to File -> Project Structure -> Artifacts
15. If not already there add a Web Application: explored Artifact
16. Drag multiplechoice on \<output root\>, save and close dialog
17. Navigate to File -> Settings and under Tools click on File Watchers
18. Add a new Sass File Watcher
19. Change Scope to Project Files
20. If Program is not auto-detected select your installed sass-preprocessor here
21. Save and close Settings
22. Navigate to Run -> Edit Configurations
23. Add a new tomcat local server configuration
24. Name it Tomcat8 or something similar
25. Under Application Server, select your installed Tomcat Root Folder
26. Uncheck the After Launch Option
27. Under the deployment tab, select your Web: exploded facet
28. On the Server tab, Switch 'On Update' and 'On frame deactivation' to 'Update classes and resources'
29. Save and Close config, Start Tomcat
30. Wait until 'Artifact is deployed successfully' is seen in log output
31. navigate to localhost:8080/test to see if the application is working