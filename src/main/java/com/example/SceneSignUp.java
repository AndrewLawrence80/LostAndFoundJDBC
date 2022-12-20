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
import java.util.concurrent.TimeUnit;

public class SceneSignUp
{
    private Stage stageSignUp = null;
    private Label labelUserName = null;
    private Label labelUserPasswordFirst = null;
    private Label labelUserPasswordSecond = null;
    private TextField textFieldUserName = null;
    private PasswordField passwordFieldFirst = null;
    private PasswordField passwordFieldSecond = null;
    private Button buttonSignUp = null;
    private Button buttonCancel = null;
    private Text textSignUpResult = null;
    private Scene scene = null;

    public SceneSignUp(Stage stageSignUp)
    {
        this.stageSignUp = stageSignUp;
        this.stageSignUp.setTitle("用户注册");
        createLayout();
        setOnAction();
    }

    public Scene getScene()
    {
        return scene;
    }

    private void createLayout()
    {
        labelUserName = new Label("新用户用户名");
        labelUserPasswordFirst = new Label("新用户密码");
        labelUserPasswordSecond = new Label("确认用户密码");
        textFieldUserName = new TextField();
        passwordFieldFirst = new PasswordField();
        passwordFieldSecond = new PasswordField();
        buttonSignUp = new Button("注册");
        buttonCancel = new Button("取消");
        textSignUpResult = new Text();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(labelUserName, 0, 0);
        gridPane.add(textFieldUserName, 1, 0);
        gridPane.add(labelUserPasswordFirst, 0, 1);
        gridPane.add(passwordFieldFirst, 1, 1);
        gridPane.add(labelUserPasswordSecond, 0, 2);
        gridPane.add(passwordFieldSecond, 1, 2);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(buttonSignUp, buttonCancel);
        gridPane.add(hBox, 1, 3);
        gridPane.add(textSignUpResult, 1, 4);

        scene = new Scene(gridPane, 400, 300);
    }

    private void setOnAction()
    {
        buttonSignUp.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (textFieldUserName.getText().equals(""))
                {
                    textSignUpResult.setText("用户名不能为空！");
                }
                else if (!passwordFieldFirst.getText().equals(passwordFieldSecond.getText()))
                {
                    textSignUpResult.setText("两次输入密码不一致！");
                }
                else if (passwordFieldFirst.getText().equals(""))
                {
                    textSignUpResult.setText("密码不能为空！");
                }
                else
                {
                    String userName = textFieldUserName.getText();
                    String userPassword = passwordFieldFirst.getText();
                    Connection connection = null;
                    try
                    {
                        connection = ConnectionController.getConnection();
                        String sqlSignUp = "select username from users where username=?";
                        QueryRunner queryRunner = new QueryRunner();
                        BeanHandler<UserSignIn> handler=new BeanHandler<>(UserSignIn.class);
                        UserSignIn userExisted=queryRunner.query(connection,sqlSignUp,handler,userName);
                        if (userExisted != null)
                        {
                            textSignUpResult.setText("用户名已被注册！");
                            textFieldUserName.clear();
                            passwordFieldFirst.clear();
                            passwordFieldSecond.clear();
                        }
                        else
                        {
                            String sqlSignUpInsert = "insert into users(username,userpassword) values(?,?)";
                            queryRunner.update(connection,sqlSignUpInsert,userName,userPassword);
                            textSignUpResult.setText("注册成功！");
                            TimeUnit.SECONDS.sleep(1);
                            textFieldUserName.clear();
                            passwordFieldFirst.clear();
                            passwordFieldSecond.clear();
                            switchToSceneFillUserInfo(userName);
                        }
                    }
                    catch (IOException e)
                    {
                        Alert alertIOException = new Alert(Alert.AlertType.ERROR, "配置文件不存在或损坏");
                        alertIOException.setTitle("配置文件错误");
                        alertIOException.setHeaderText(null);
                        alertIOException.showAndWait();
                    }
                    catch (SQLException e)
                    {
                        Alert alertSQLException = new Alert(Alert.AlertType.ERROR, "连接错误，请检查网络设置");
                        alertSQLException.setTitle("网络错误");
                        alertSQLException.setHeaderText(null);
                        alertSQLException.showAndWait();
                    }
                    catch (Exception e)
                    {
                        Alert alertException = new Alert(Alert.AlertType.ERROR, "系统存在未知错误");
                        alertException.setTitle("未知错误");
                        alertException.setHeaderText(null);
                        alertException.showAndWait();
                    }
                }
            }
        });
        buttonCancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneSignIn();
            }
        });
    }

    private void switchToSceneFillUserInfo(String userName)
    {
        SceneFillUserInfo sceneFillUserInfo=new SceneFillUserInfo(stageSignUp);
        sceneFillUserInfo.setUsername(userName);
        stageSignUp.setScene(sceneFillUserInfo.getScene());
        stageSignUp.show();
    }

    private void switchToSceneSignIn()
    {
        SceneSignIn sceneSignIn=new SceneSignIn(stageSignUp);
        stageSignUp.setScene(sceneSignIn.getScene());
        stageSignUp.show();
    }
}
