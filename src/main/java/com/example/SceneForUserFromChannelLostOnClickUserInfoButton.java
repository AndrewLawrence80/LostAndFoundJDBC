package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SceneForUserFromChannelLostOnClickUserInfoButton
{
    private String userName;
    private Stage stageMain;

    private HBox hBoxPrimary = null;

    private ToggleButton toggleButtonLostInfo = null;
    private ToggleButton toggleButtonFoundInfo = null;
    private ToggleButton toggleButtonTakenInfo = null;
    private ToggleButton toggleButtonUserInfo = null;
    private VBox vBoxToggleGroup = null;

    private Label labelUserName = null;
    private Label labelUserNameValue = null;
    private Label labelUserPassword=null;
    private Label labelUserPasswordValue=null;
    private Label labelName = null;
    private Label labelNameValue = null;
    private Label labelAddress = null;
    private Label labelAddressValue = null;
    private Label labelPhone = null;
    private Label labelPhoneValue = null;
    private Label labelWeChat = null;
    private Label labelWeChatValue = null;
    private Label labelQQ = null;
    private Label labelQQValue = null;
    private Label labelEmail = null;
    private Label labelEmailValue = null;

    private Button buttonUpdateUserInfo = null;
    private Button buttonShowMyRelease = null;
    HBox hBoxFunctionSection=null;
    VBox vBoxFunctionPart=null;

    private Scene scene = null;

    public SceneForUserFromChannelLostOnClickUserInfoButton(Stage stage, String userName)
    {
        this.stageMain = stage;
        this.userName = userName;
        this.stageMain.setWidth(800);
        this.stageMain.setHeight(600);
        createLayout();
        setOnAction();
    }

    public Scene getScene()
    {
        return scene;
    }

    private void createLayout()
    {
        hBoxPrimary=new HBox();

        toggleButtonLostInfo=new ToggleButton("丢失物品信息");
        toggleButtonLostInfo.setFont(Font.font(18));
        toggleButtonFoundInfo=new ToggleButton("拾得物品信息");
        toggleButtonFoundInfo.setFont(Font.font(18));
        toggleButtonTakenInfo=new ToggleButton("完成处理信息");
        toggleButtonTakenInfo.setFont(Font.font(18));
        toggleButtonUserInfo=new ToggleButton("用户个人信息");
        toggleButtonUserInfo.setFont(Font.font(18));

        ToggleGroup toggleGroupNavigationGroup=new ToggleGroup();
        toggleGroupNavigationGroup.getToggles().addAll(toggleButtonLostInfo,toggleButtonFoundInfo,toggleButtonTakenInfo,toggleButtonUserInfo);

        toggleButtonUserInfo.setSelected(true);

        vBoxToggleGroup=new VBox();
        toggleButtonLostInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonFoundInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonTakenInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonUserInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonLostInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        toggleButtonFoundInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        toggleButtonTakenInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        toggleButtonUserInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        vBoxToggleGroup.getChildren().addAll(toggleButtonLostInfo,toggleButtonFoundInfo,toggleButtonTakenInfo,toggleButtonUserInfo);

        labelUserName=new Label("用户名");
        labelUserPassword=new Label("密码");
        labelName=new Label("姓名");
        labelAddress=new Label("联系地址");
        labelPhone=new Label("联系电话");
        labelWeChat=new Label("微信号");
        labelQQ=new Label("QQ号");
        labelEmail=new Label("Email");

        try
        {
            Connection connection = ConnectionController.getConnection();
            String sql = "select username,name,addr address,phone,wechat,qq,email from userinfo where username=?";
            QueryRunner queryRunner = new QueryRunner();
            BeanHandler<User> handler = new BeanHandler<>(User.class);
            User user = queryRunner.query(connection, sql, handler, userName);
            labelUserNameValue = new Label(userName);
            labelUserPasswordValue = new Label("********");
            labelNameValue = new Label(user.getName());
            labelAddressValue = new Label(user.getAddress());
            labelPhoneValue = new Label(user.getPhone());
            labelWeChatValue = new Label(user.getWeChat());
            labelQQValue = new Label(user.getQQ());
            labelEmailValue = new Label(user.getEmail());
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

        buttonUpdateUserInfo=new Button("修改个人信息");
        buttonShowMyRelease=new Button("查看我发布的");


        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.8));
        gridPane.add(labelUserName,0,0);
        gridPane.add(labelUserNameValue,1,0);
        gridPane.add(labelUserPassword,0,1);
        gridPane.add(labelUserPasswordValue,1,1);
        gridPane.add(labelName,0,2);
        gridPane.add(labelNameValue,1,2);
        gridPane.add(labelAddress,0,3);
        gridPane.add(labelAddressValue,1,3);
        gridPane.add(labelPhone,0,4);
        gridPane.add(labelPhoneValue,1,4);
        gridPane.add(labelWeChat,0,5);
        gridPane.add(labelWeChatValue,1,5);
        gridPane.add(labelQQ,0,6);
        gridPane.add(labelQQValue,1,6);
        gridPane.add(labelEmail,0,7);
        gridPane.add(labelEmailValue,1,7);

        hBoxFunctionSection=new HBox();
        hBoxFunctionSection.setSpacing(10);
        hBoxFunctionSection.setAlignment(Pos.CENTER);
        hBoxFunctionSection.getChildren().addAll(buttonUpdateUserInfo,buttonShowMyRelease);

        vBoxFunctionPart=new VBox();
        vBoxFunctionPart.setSpacing(20);
        vBoxFunctionPart.setAlignment(Pos.CENTER);
        vBoxFunctionPart.getChildren().addAll(gridPane,hBoxFunctionSection);
        hBoxPrimary.getChildren().addAll(vBoxToggleGroup,vBoxFunctionPart);
        scene=new Scene(hBoxPrimary);
    }

    private void setOnAction()
    {
        toggleButtonLostInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                switchToSceneForUserFromChannelLostOnClickLostButton();
            }
        });

        toggleButtonFoundInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                switchToSceneForUserFromChannelLostOnClickFoundButton();
            }
        });

        toggleButtonTakenInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                switchToSceneForUserFromChannelLostOnClickTakenButton();
            }
        });

        toggleButtonUserInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                switchToSceneForUserFromChannelLostOnClickUserInfoButton();
            }
        });

        buttonUpdateUserInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneUpdateUserInfoForUserFromChannelLost();
            }
        });

        buttonShowMyRelease.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneMyReleaseForUserFromChannelLost();
            }
        });
    }

    private void switchToSceneForUserFromChannelLostOnClickLostButton()
    {
        SceneForUserFromChannelLostOnClickLostButton sceneForUserFromChannelLostOnClickLostButton=new SceneForUserFromChannelLostOnClickLostButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickLostButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelLostOnClickFoundButton()
    {
        SceneForUserFromChannelLostOnClickFoundButton sceneForUserFromChannelLostOnClickFoundButton=new SceneForUserFromChannelLostOnClickFoundButton(stageMain, userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickFoundButton.getScene());
        stageMain.show();

    }

    private void switchToSceneForUserFromChannelLostOnClickTakenButton()
    {
        SceneForUserFromChannelLostOnClickTakenButton sceneForUserFromChannelLostOnClickTakenButton=new SceneForUserFromChannelLostOnClickTakenButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickTakenButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelLostOnClickUserInfoButton()
    {
        SceneForUserFromChannelLostOnClickUserInfoButton sceneForUserFromChannelLostOnClickUserInfoButton=new SceneForUserFromChannelLostOnClickUserInfoButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickUserInfoButton.getScene());
        stageMain.show();
    }

    private void switchToSceneUpdateUserInfoForUserFromChannelLost()
    {
        SceneUpdateUserInfoForUserFromChannelLost sceneUpdateUserInfoForUserFromChannelLost=new SceneUpdateUserInfoForUserFromChannelLost(stageMain,userName);
        stageMain.setScene(sceneUpdateUserInfoForUserFromChannelLost.getScene());
        stageMain.show();
    }

    private void switchToSceneMyReleaseForUserFromChannelLost()
    {
        SceneMyReleaseForUserFromChannelLost sceneMyReleaseForUserFromChannelLost=new SceneMyReleaseForUserFromChannelLost(stageMain,userName);
        stageMain.setScene(sceneMyReleaseForUserFromChannelLost.getScene());
        stageMain.show();
    }

}
