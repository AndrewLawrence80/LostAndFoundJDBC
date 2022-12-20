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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class SceneForUserFromChannelLostOnClickLostButton
{
    private String userName=null;
    private Stage stageMain=null;
    private HBox hBoxPrimary=null;

    private ToggleButton toggleButtonLostInfo=null;
    private ToggleButton toggleButtonFoundInfo=null;
    private ToggleButton toggleButtonTakenInfo=null;
    private ToggleButton toggleButtonUserInfo=null;
    private VBox vBoxToggleGroup=null;

    private Label labelItemLostName=null;
    private Label labelItemLostTime=null;
    private Label labelItemLostLocation=null;
    private Label labelItemLostDescription=null;
    private Label labelItemLostContact=null;

    private TextField textFieldItemLostName=null;
    private DatePicker datePickerItemLostTime=null;
    private TextField textFieldItemLostLocation=null;
    private TextField textFieldItemLostDescription=null;
    private TextField textFieldItemLostContact=null;

    private Button buttonRegister=null;
    private Text textInsertResult=null;
    private GridPane gridPaneFunctionPart=null;
    private VBox vBoxFunctionPart=null;

    private Scene scene=null;

    public SceneForUserFromChannelLostOnClickLostButton(Stage stage,String userName)
    {
        this.userName=userName;
        this.stageMain=stage;
        this.stageMain.setTitle("失物招领");
        this.stageMain.setWidth(stage.getWidth());
        this.stageMain.setHeight(stage.getHeight());
        createLayout();
        setOnAction();
    }

    public  Scene getScene()
    {
        return  scene;
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

        toggleButtonLostInfo.setSelected(true);

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

        labelItemLostName=new Label("物品名称");
        labelItemLostTime=new Label("丢失时间");
        labelItemLostLocation=new Label("丢失地点");
        labelItemLostDescription=new Label("物品描述");
        labelItemLostContact=new Label("联系方式");

        textFieldItemLostName=new TextField();
        datePickerItemLostTime=new DatePicker();
        datePickerItemLostTime.setEditable(false);
        textFieldItemLostLocation=new TextField();
        textFieldItemLostDescription=new TextField();
        textFieldItemLostContact=new TextField();



        gridPaneFunctionPart=new GridPane();
        gridPaneFunctionPart.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.8));
        gridPaneFunctionPart.setAlignment(Pos.CENTER);
        gridPaneFunctionPart.setHgap(10);
        gridPaneFunctionPart.setVgap(10);

        gridPaneFunctionPart.add(labelItemLostName,0,0);
        gridPaneFunctionPart.add(textFieldItemLostName,1,0);
        gridPaneFunctionPart.add(labelItemLostTime,0,1);
        gridPaneFunctionPart.add(datePickerItemLostTime,1,1);
        gridPaneFunctionPart.add(labelItemLostLocation,0,2);
        gridPaneFunctionPart.add(textFieldItemLostLocation,1,2);
        gridPaneFunctionPart.add(labelItemLostDescription,0,3);
        gridPaneFunctionPart.add(textFieldItemLostDescription,1,3);
        gridPaneFunctionPart.add(labelItemLostContact,0,4);
        gridPaneFunctionPart.add(textFieldItemLostContact,1,4);

        buttonRegister=new Button("添加丢失信息");
        textInsertResult=new Text();
        vBoxFunctionPart=new VBox();
        vBoxFunctionPart.setSpacing(10);
        vBoxFunctionPart.setAlignment(Pos.CENTER);
        vBoxFunctionPart.getChildren().addAll(gridPaneFunctionPart,buttonRegister,textInsertResult);

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

        buttonRegister.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String name=textFieldItemLostName.getText();
                LocalDate localDateTime=datePickerItemLostTime.getValue();
                String location=textFieldItemLostLocation.getText();
                String description=textFieldItemLostDescription.getText();
                String contact=textFieldItemLostContact.getText();

                if (name.equals("")||localDateTime==null||location.equals("")||description.equals("")||contact.equals(""))
                {
                    textInsertResult.setText("请完整填写信息！");
                }
                else
                {
                    try
                    {
                        Date dateTime = Date.valueOf(localDateTime);
                        Connection connection = ConnectionController.getConnection();
                        String sql = "insert into itemslost(name,date,location,description,contact,username) values(?,?,?,?,?,?)";
                        QueryRunner queryRunner = new QueryRunner();
                        queryRunner.update(connection, sql, name, dateTime, location, description, contact, userName);
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
                    textInsertResult.setText("添加成功！");
                    textFieldItemLostName.clear();
                    datePickerItemLostTime.getEditor().clear();
                    textFieldItemLostLocation.clear();
                    textFieldItemLostDescription.clear();
                    textFieldItemLostContact.clear();
                }
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
}
