# soc-simulator

## Getting Started

### Prerequisite
* JDK >= 1.7
* Maven >= 3.2

### Running application
1. Pull from github

  To ensure you have the latest version, always pull from master.
  On your working directory:
  > git pull
2. Build using maven
  
  On your working directory:
  > mvn clean install
3. Run generated jar

  Upon the completion of maven install, it will generate *target* folder which contains executable jar and dependencies-jar. To make logging easier, log4j properties is detached outside executable jar. Hence, you may change logging configuration without recompile the jar.
  
  On your-working-directory/target:
   
  > java -jar -Dlog4j.configuration=file:/full_path/log4j.properties soc-simulator-0.0.1-SNAPSHOT.jar 
  
  Furthermore, you may override the application properties by define your own properties file.
  > java -jar -DappConfiguration=file:/full_path/your-defined.properties -Dlog4j.configuration=file:/full_path/log4j.properties soc-simulator-0.0.1-SNAPSHOT.jar 
    
