package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class SceneFillUserInfo
{
    private String username=null;
    private Stage stageFillUserInfo = null;
    private Label labelFillUserInfo = null;
    private Label labelName = null;
    private Label labelAddress = null;
    private Label labelPhone = null;
    private Label labelWeChat = null;
    private Label labelQQ = null;
    private Label labelEmail = null;
    private Label labelChooseOneHint = null;
    private Line lineSeparator = null;
    private TextField textFieldName = null;
    private TextField textFieldAddress = null;
    private TextField textFieldPhone = null;
    private TextField textFieldWeChat = null;
    private TextField textFieldQQ = null;
    private TextField textFieldEmail = null;
    private Button buttonConfirm = null;

    private Scene scene = null;


    public SceneFillUserInfo(Stage stage)
    {
        this.stageFillUserInfo = stage;
        stageFillUserInfo.setTitle("完善信息");
        createLayout();
        setOnAction();
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Scene getScene()
    {
        return scene;
    }

    private void createLayout()
    {
        labelFillUserInfo = new Label("完善用户信息");
        labelName = new Label("姓名");
        labelAddress = new Label("联系地址");
        labelPhone = new Label("联系电话");
        labelWeChat = new Label("微信号");
        labelQQ = new Label("QQ号");
        labelEmail = new Label("Email");
        labelChooseOneHint = new Label("以下至少选填一项");
        lineSeparator = new Line();
        lineSeparator.setStartX(stageFillUserInfo.getWidth() / 2);
        lineSeparator.setEndX(stageFillUserInfo.getWidth() - 10);
        textFieldName = new TextField();
        textFieldAddress = new TextField();
        textFieldPhone = new TextField();
        textFieldWeChat = new TextField();
        textFieldQQ = new TextField();
        textFieldEmail = new TextField();
        buttonConfirm = new Button("确定");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(labelName, 0, 0);
        gridPane.add(textFieldName, 1, 0);
        gridPane.add(labelAddress, 0, 1);
        gridPane.add(textFieldAddress, 1, 1);
        gridPane.add(labelChooseOneHint, 0, 2);
        gridPane.add(lineSeparator, 1, 2);
        gridPane.add(labelPhone, 0, 3);
        gridPane.add(textFieldPhone, 1, 3);
        gridPane.add(labelWeChat, 0, 4);
        gridPane.add(textFieldWeChat, 1, 4);
        gridPane.add(labelQQ, 0, 5);
        gridPane.add(textFieldQQ, 1, 5);
        gridPane.add(labelEmail, 0, 6);
        gridPane.add(textFieldEmail, 1, 6);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(labelFillUserInfo, gridPane, buttonConfirm);

        scene = new Scene(vBox, 640, 480);
    }

    private void setOnAction()
    {
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String name=textFieldName.getText();
                String address=textFieldAddress.getText();
                String phone = textFieldPhone.getText();
                String weChat = textFieldWeChat.getText();
                String qq = textFieldQQ.getText();
                String email = textFieldEmail.getText();

                if (phone.equals("") && weChat.equals("") && qq.equals("") && email.equals(""))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "请至少填写一种联系方式");
                    alert.setHeaderText(null);
                    alert.setTitle("联系信息缺失");
                    alert.showAndWait();
                }
                else
                {
                    try
                    {
                        Connection connection = ConnectionController.getConnection();
                        String sqlFillUserInfo = "insert into userinfo(username,name,addr,phone,wechat,qq,email) values(?,?,?,?,?,?,?)";
                        QueryRunner queryRunner = new QueryRunner();
                        queryRunner.update(connection, sqlFillUserInfo, username, name, address, phone, weChat, qq, email);
                        switchToSceneSignIn();
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
    }

    private void switchToSceneSignIn()
    {
        SceneSignIn sceneSignIn = new SceneSignIn(stageFillUserInfo);
        stageFillUserInfo.setScene(sceneSignIn.getScene());
        stageFillUserInfo.show();
    }
}
