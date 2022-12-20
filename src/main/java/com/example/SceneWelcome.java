package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneWelcome
{
    private String userName=null;
    private Stage stageWelcome;
    private Button buttonILostSomething=null;
    private Button buttonIFoundSomething=null;
    private Scene scene=null;

    public SceneWelcome(Stage stage,String userName)
    {
        this.userName=userName;
        this.stageWelcome=stage;
        stage.setTitle("欢迎");
        createLayout();
        setOnAction();
    }

    public Scene getScene()
    {
        return scene;
    }
    private void createLayout()
    {
        buttonILostSomething=new Button("我丢了东西");
        buttonIFoundSomething=new Button("我捡到了东西");
        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(buttonILostSomething,buttonIFoundSomething);
        scene=new Scene(vBox,400,300);
    }

    private void setOnAction()
    {
        buttonILostSomething.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneForUserFromChannelLostOnClickFoundButton();
            }
        });

        buttonIFoundSomething.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneForUserFromChannelFoundOnClickLostButton();
            }
        });
    }

    private void switchToSceneForUserFromChannelLostOnClickFoundButton()
    {
        SceneForUserFromChannelLostOnClickFoundButton sceneForUserFromChannelLostOnClickFoundButton=new SceneForUserFromChannelLostOnClickFoundButton(stageWelcome,userName);
        stageWelcome.setScene(sceneForUserFromChannelLostOnClickFoundButton.getScene());
        stageWelcome.show();
    }

    private void switchToSceneForUserFromChannelFoundOnClickLostButton()
    {
        SceneForUserFromChannelFoundOnClickLostButton sceneForUserFromChannelFoundOnClickLostButton=new SceneForUserFromChannelFoundOnClickLostButton(stageWelcome,userName);
        stageWelcome.setScene(sceneForUserFromChannelFoundOnClickLostButton.getScene());
        stageWelcome.show();
    }
}
