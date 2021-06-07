#### Migrate Configs
1. Add lib to project
    ```
    File > Project Structure > Libraries > Add /path/to/lib
    ```
2. Add VM options 
    ```
    Run > Edit configurations > Add the following argument:
    --module-path "/Users//java/javafx-sdk-15.0.1/lib" 
    --add-modules javafx.controls,javafx.fxml [, etc]
    ``` 