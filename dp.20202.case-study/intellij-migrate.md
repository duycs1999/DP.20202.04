#### Migrate Configs
1. Add lib to project
    ```
    File > Project Structure > Libraries > Add /path/to/javafx/lib
    ```
2. Add VM options 
    ```
    Run > Edit configurations > Add the following argument:
    --module-path "/Users//java/javafx-sdk-15.0.1/lib" 
    --add-modules javafx.controls,javafx.fxml [, etc]
    ``` 

3. Add custom jar
mvn install:install-file \
–Dfile=/Users/thanhld/IdeaProjects/ISD.ICT.20201.20176873.LuuDucThanh/05 Programming/lib/distance-api-1.0-SNAPSHOT.jar \
-DgroupId=org.example \
-DartifactId=distance-api \
-Dversion=1.0-SNAPSHOT