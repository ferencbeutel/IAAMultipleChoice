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
http://repo2.maven.org/maven2/com/h2database/h2/1.4.193/h2-1.4.193.jar
```
- Then, in IntelliJ, open your Database view and click on Data Source Properties
- Under Drivers, select H2
- Remove the old jar-File and add the downloaded one

## Installation
- Fulfill Prerequirements

#### IntelliJ Basic Config
- Enable Annotation Processing
- Search and install the Lombok Plugin for IntelliJ
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

## Basic usage
### data generation
- your first action after starting the application should be the generation of test data
- navigate to localhost:8080/application-administration
- fill in the form and submit the form

### Registration
- select register in the navigation menu
- fill in the form and follow the instructions in the mail sent to you

### Login
- you can login as a lecturer either with any of the Lecturers found in the User Database Table or with 
lecturer@nordakademie.de and PW genExamplePW
- you can login as a student with your registered mail, with any of the Students found in the User Database Table or 
with student@nordakademie.de and PW genExamplePW

### Lecturer Actions
#### Seminars
- you can add Seminars under the add Seminar navigation entry
- you can edit Seminars by clicking on the Seminar on your home page

#### Tests
- once you have created some Seminars, you can create tests for them by clicking on the appropriate button on the
home page
- you can edit them with in the same location after creation
- once you have created a test, you can start adding questions and answers to it at the edit test page

### Student Actions
#### Seminars
- you can view all seminars under the seminar list navigation entry
- to enroll in one, click on it and then hit the enroll button

#### Tests
- Once you have enrolled for a Seminar you will get an email at 01:00AM of the start day of the test
    - this is fully configurable in the SeminarScheduler by a cron expression
- When you got the mail, you can start the test on your home page by clicking on the appropriate button
- After that, enter your access token and follow the instructions on the screen
- Once you have finished a test, check your result on the home page

### Admin Actions
- under localhost:8080/application-administration, you can generate initial application data
- furthermore, you can send the test access token to everyone who is enrolled to a seminar which test starts today
