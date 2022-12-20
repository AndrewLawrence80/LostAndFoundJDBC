package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SceneSignIn sceneSignIn = new SceneSignIn(primaryStage);
        primaryStage.setScene(sceneSignIn.getScene());
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
