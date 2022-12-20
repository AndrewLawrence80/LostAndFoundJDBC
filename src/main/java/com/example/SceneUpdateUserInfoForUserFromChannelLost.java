package com.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class SceneUpdateUserInfoForUserFromChannelLost
{
    private String userName=null;
    private Stage stageUpdateUserInfo=null;
    private Label labelPassword=null;
    private Label labelConfirmPassword=null;
    private Label labelName = null;
    private Label labelAddress = null;
    private Label labelPhone = null;
    private Label labelWeChat = null;
    private Label labelQQ = null;
    private Label labelEmail = null;
    private Label labelChooseOneHint = null;
    private Line lineSeparator = null;
    private PasswordField passwordFieldPassword=null;
    private PasswordField passwordFieldConfirmPassword=null;
    private TextField textFieldName = null;
    private TextField textFieldAddress = null;
    private TextField textFieldPhone = null;
    private TextField textFieldWeChat = null;
    private TextField textFieldQQ = null;
    private TextField textFieldEmail = null;
    private Button buttonConfirm = null;
    private Text textUpdateResult=null;

    private GridPane gridPane=null;
    private Scene scene=null;

    public SceneUpdateUserInfoForUserFromChannelLost(Stage stage,String userName)
    {
        this.stageUpdateUserInfo=stage;
        this.userName=userName;
        stageUpdateUserInfo.setTitle("修改用户信息");
        stageUpdateUserInfo.setWidth(640);
        stageUpdateUserInfo.setHeight(480);
        createLayout();
        setOnAction();
    }

    public Scene getScene()
    {
        return scene;
    }

    private void createLayout()
    {
        labelPassword=new Label("密码");
        labelConfirmPassword=new Label("确认密码");
        labelName=new Label("姓名");
        labelAddress=new Label("联系地址");
        labelPhone=new Label("联系电话");
        labelWeChat=new Label("微信号");
        labelQQ=new Label("QQ号");
        labelEmail=new Label("Email");
        labelChooseOneHint=new Label("以下至少选填一项");
        lineSeparator = new Line();
        lineSeparator.setStartX(stageUpdateUserInfo.getWidth() / 2);
        lineSeparator.setEndX(stageUpdateUserInfo.getWidth() - 10);
        buttonConfirm=new Button("确定");
        textUpdateResult=new Text();

        try
        {
            Connection connection = ConnectionController.getConnection();
            String sqlUsers = "select username,userpassword from users where username=?";
            String sqlUserInfo = "select username,name,addr address,phone,wechat,qq,email from userinfo where username=?";
            BeanHandler<UserSignIn> handlerUserSignIn = new BeanHandler<>(UserSignIn.class);
            BeanHandler<User> handlerUser = new BeanHandler<>(User.class);
            QueryRunner queryRunner = new QueryRunner();
            UserSignIn userSignIn = queryRunner.query(connection, sqlUsers, handlerUserSignIn, userName);
            User user = queryRunner.query(connection, sqlUserInfo, handlerUser, userName);
            passwordFieldPassword = new PasswordField();
            passwordFieldPassword.setText(userSignIn.getUserPassword());
            passwordFieldConfirmPassword = new PasswordField();
            passwordFieldConfirmPassword.setText(userSignIn.getUserPassword());
            textFieldName = new TextField();
            textFieldName.setText(user.getName());
            textFieldAddress = new TextField();
            textFieldAddress.setText(user.getAddress());
            textFieldPhone = new TextField();
            textFieldPhone.setText(user.getPhone());
            textFieldWeChat = new TextField();
            textFieldWeChat.setText(user.getWeChat());
            textFieldQQ = new TextField();
            textFieldQQ.setText(user.getQQ());
            textFieldEmail = new TextField();
            textFieldEmail.setText(user.getEmail());
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

        gridPane=new GridPane();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(labelPassword,0,0);
        gridPane.add(passwordFieldPassword,1,0);
        gridPane.add(labelConfirmPassword,0,1);
        gridPane.add(passwordFieldConfirmPassword,1,1);
        gridPane.add(labelName, 0, 2);
        gridPane.add(textFieldName, 1, 2);
        gridPane.add(labelAddress, 0, 3);
        gridPane.add(textFieldAddress, 1, 3);
        gridPane.add(labelChooseOneHint, 0, 4);
        gridPane.add(lineSeparator, 1, 4);
        gridPane.add(labelPhone, 0, 5);
        gridPane.add(textFieldPhone, 1, 5);
        gridPane.add(labelWeChat, 0, 6);
        gridPane.add(textFieldWeChat, 1, 6);
        gridPane.add(labelQQ, 0, 7);
        gridPane.add(textFieldQQ, 1, 7);
        gridPane.add(labelEmail, 0, 8);
        gridPane.add(textFieldEmail, 1, 8);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(gridPane, buttonConfirm,textUpdateResult);

        scene=new Scene(vBox);
    }

    private void setOnAction()
    {
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (!passwordFieldPassword.getText().equals(passwordFieldConfirmPassword.getText()))
                {
                    textUpdateResult.setText("两次输入密码不一致！");
                }
                else if (passwordFieldPassword.getText().equals(""))
                {
                    textUpdateResult.setText("密码不能为空！");
                }
                else
                {
                    String password=passwordFieldPassword.getText();
                    String name=textFieldName.getText();
                    String address=textFieldAddress.getText();
                    String phone = textFieldPhone.getText();
                    String weChat = textFieldWeChat.getText();
                    String qq = textFieldQQ.getText();
                    String email = textFieldEmail.getText();

                    if (phone.equals("") && weChat.equals("") && qq.equals("") && email.equals(""))
                    {
                        textUpdateResult.setText("请填写至少一种联系方式！");
                    }
                    else
                    {
                        try
                        {
                            Connection connection = ConnectionController.getConnection();
                            String sqlUsers = "update users set userpassword=? where username=?";
                            String sqlUserInfo = "update userinfo set name=?,addr=?,phone=?,wechat=?,qq=?,email=? where username=?";
                            QueryRunner queryRunner = new QueryRunner();
                            queryRunner.update(connection, sqlUsers, password,userName);
                            queryRunner.update(connection, sqlUserInfo, name, address, phone, weChat, qq, email, userName);
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
                        switchToSceneForUserFromChannelLostOnClickUserInfoButton();
                    }
                }
            }
        });
    }

    private void switchToSceneForUserFromChannelLostOnClickUserInfoButton()
    {
        SceneForUserFromChannelLostOnClickUserInfoButton sceneForUserFromChannelLostOnClickUserInfoButton=new SceneForUserFromChannelLostOnClickUserInfoButton(stageUpdateUserInfo,userName);
        stageUpdateUserInfo.setScene(sceneForUserFromChannelLostOnClickUserInfoButton.getScene());
        stageUpdateUserInfo.show();
    }

}
