package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SceneSignIn
{
    private String userName=null;
    Stage stageSignIn = null;
    Label labelUserName = null;
    Label labelUserPassword = null;
    TextField textFieldUserName = null;
    PasswordField passwordFieldUserPassWord = null;
    Button buttonSignIn = null;
    Button buttonSignUp = null;
    Text textSignInResult = null;
    Scene scene=null;

    public SceneSignIn(Stage stage)
    {
        this.stageSignIn=stage;
        this.stageSignIn.setTitle("用户登录");
        createLayout();
        setOnAction();
    }

    public Scene getScene()
    {
        return this.scene;
    }

    private void createLayout()
    {
        labelUserName = new Label("用户名");
        labelUserPassword = new Label("密码");
        textFieldUserName = new TextField();
        passwordFieldUserPassWord = new PasswordField();
        buttonSignIn = new Button("登录");
        buttonSignUp = new Button("注册");
        textSignInResult = new Text();

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(buttonSignIn, buttonSignUp);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(labelUserName, 0, 0);
        gridPane.add(textFieldUserName, 1, 0);
        gridPane.add(labelUserPassword, 0, 1);
        gridPane.add(passwordFieldUserPassWord, 1, 1);
        gridPane.add(hBox, 1, 2);
        gridPane.add(textSignInResult, 1, 3);

        scene=new Scene(gridPane, 400, 300);
    }

    private void setOnAction()
    {
        buttonSignIn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Connection connectionSignIn=null;
                try
                {
                    userName = textFieldUserName.getText();
                    String userPassword = passwordFieldUserPassWord.getText();
                    connectionSignIn = ConnectionController.getConnection();
                    QueryRunner queryRunnerSignIn = new QueryRunner();
                    String sqlSignIn = "select username,userpassword from users where username=?";
                    BeanHandler<UserSignIn> handlerSignIn = new BeanHandler<>(UserSignIn.class);
                    UserSignIn userSignIn = queryRunnerSignIn.query(connectionSignIn, sqlSignIn, handlerSignIn, userName);
                    if (userSignIn == null)
                    {
                        textSignInResult.setText("用户不存在！");
                        textFieldUserName.clear();
                        passwordFieldUserPassWord.clear();
                    }
                    else
                    {
                        if (!userPassword.equals(userSignIn.getUserPassword()))
                        {
                            textSignInResult.setText("密码错误！");
                            passwordFieldUserPassWord.clear();
                        }
                        else
                        {
                            textSignInResult.setText("欢迎，" + userName);
                            textFieldUserName.clear();
                            passwordFieldUserPassWord.clear();
                            switchToSceneWelcome();
                        }
                    }
                }
                catch (IOException e)
                {
                    Alert alertIOException=new Alert(Alert.AlertType.ERROR,"配置文件不存在或损坏");
                    alertIOException.setTitle("配置文件错误");
                    alertIOException.setHeaderText(null);
                    alertIOException.showAndWait();
                }
                catch (SQLException e)
                {
                    Alert alertSQLException=new Alert(Alert.AlertType.ERROR,"连接错误，请检查网络设置");
                    alertSQLException.setTitle("网络错误");
                    alertSQLException.setHeaderText(null);
                    alertSQLException.showAndWait();
                }
                catch (Exception e)
                {
                    Alert alertException=new Alert(Alert.AlertType.ERROR,"系统存在未知错误");
                    alertException.setTitle("未知错误");
                    alertException.setHeaderText(null);
                    alertException.showAndWait();
                }
            }
        });

        buttonSignUp.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneSignUp();
            }
        });
    }

    private void switchToSceneSignUp()
    {
        SceneSignUp sceneSignUp=new SceneSignUp(stageSignIn);
        stageSignIn.setScene(sceneSignUp.getScene());
        stageSignIn.show();
    }

    private void switchToSceneWelcome()
    {
        SceneWelcome sceneWelcome=new SceneWelcome(stageSignIn,userName);
        stageSignIn.setScene(sceneWelcome.getScene());
        stageSignIn.show();
    }
}
