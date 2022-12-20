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

public class SceneForUserFromChannelFoundOnClickFoundButton
{
    private String userName=null;
    private Stage stageMain=null;
    private HBox hBoxPrimary=null;

    private ToggleButton toggleButtonLostInfo=null;
    private ToggleButton toggleButtonFoundInfo=null;
    private ToggleButton toggleButtonTakenInfo=null;
    private ToggleButton toggleButtonUserInfo=null;
    private VBox vBoxToggleGroup=null;

    private Label labelItemFoundName=null;
    private Label labelItemFoundTime=null;
    private Label labelItemFoundLocation=null;
    private Label labelItemFoundDescription=null;
    private Label labelItemFoundContact=null;

    private TextField textFieldItemFoundName=null;
    private DatePicker datePickerItemFoundTime=null;
    private TextField textFieldItemFoundLocation=null;
    private TextField textFieldItemFoundDescription=null;
    private TextField textFieldItemFoundContact=null;

    private Button buttonRegister=null;
    private VBox vBoxFunctionPart=null;
    private Text textInsertResult=null;
    private GridPane gridPaneFunctionPart=null;

    private Scene scene=null;

    public SceneForUserFromChannelFoundOnClickFoundButton(Stage stage,String userName)
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

        toggleButtonFoundInfo.setSelected(true);

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

        labelItemFoundName=new Label("物品名称");
        labelItemFoundTime=new Label("拾得时间");
        labelItemFoundLocation=new Label("拾得地点");
        labelItemFoundDescription=new Label("物品描述");
        labelItemFoundContact=new Label("联系方式");
        
        textFieldItemFoundName=new TextField();
        datePickerItemFoundTime=new DatePicker();
        datePickerItemFoundTime.setEditable(false);
        textFieldItemFoundLocation=new TextField();
        textFieldItemFoundDescription=new TextField();
        textFieldItemFoundContact=new TextField();

        gridPaneFunctionPart=new GridPane();
        gridPaneFunctionPart.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.8));
        gridPaneFunctionPart.setAlignment(Pos.CENTER);
        gridPaneFunctionPart.setHgap(10);
        gridPaneFunctionPart.setVgap(10);

        gridPaneFunctionPart.add(labelItemFoundName,0,0);
        gridPaneFunctionPart.add(textFieldItemFoundName,1,0);
        gridPaneFunctionPart.add(labelItemFoundTime,0,1);
        gridPaneFunctionPart.add(datePickerItemFoundTime,1,1);
        gridPaneFunctionPart.add(labelItemFoundLocation,0,2);
        gridPaneFunctionPart.add(textFieldItemFoundLocation,1,2);
        gridPaneFunctionPart.add(labelItemFoundDescription,0,3);
        gridPaneFunctionPart.add(textFieldItemFoundDescription,1,3);
        gridPaneFunctionPart.add(labelItemFoundContact,0,4);
        gridPaneFunctionPart.add(textFieldItemFoundContact,1,4);

        buttonRegister=new Button("添加拾得信息");
        textInsertResult=new Text();
        vBoxFunctionPart=new VBox();
        vBoxFunctionPart.setAlignment(Pos.CENTER);
        vBoxFunctionPart.setSpacing(10);
        vBoxFunctionPart.getChildren().addAll(gridPaneFunctionPart,buttonRegister,textInsertResult);

        hBoxPrimary.getChildren().addAll(vBoxToggleGroup,vBoxFunctionPart);
        scene=new Scene(hBoxPrimary);
    }

    private void setOnAction()
    {
        toggleButtonLostInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneForUserFromChannelFoundOnClickLostButton();
            }
        });

        toggleButtonFoundInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneForUserFromChannelFoundOnClickFoundButton();
            }
        });

        toggleButtonTakenInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneForUserFromChannelFoundOnClickTakenButton();
            }
        });

        toggleButtonUserInfo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToSceneForUserFromChannelFoundOnClickUserInfoButton();
            }
        });

        buttonRegister.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String name=textFieldItemFoundName.getText();
                LocalDate localDateTime=datePickerItemFoundTime.getValue();
                String location=textFieldItemFoundLocation.getText();
                String description=textFieldItemFoundDescription.getText();
                String contact=textFieldItemFoundContact.getText();

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
                        String sql = "insert into itemsfound(name,date,location,description,contact,username) values(?,?,?,?,?,?)";
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
                    textFieldItemFoundName.clear();
                    datePickerItemFoundTime.getEditor().clear();
                    textFieldItemFoundLocation.clear();
                    textFieldItemFoundDescription.clear();
                    textFieldItemFoundContact.clear();
                }
            }
        });
    }

    private void switchToSceneForUserFromChannelFoundOnClickLostButton()
    {
        SceneForUserFromChannelFoundOnClickLostButton sceneForUserFromChannelFoundOnClickLostButton=new SceneForUserFromChannelFoundOnClickLostButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelFoundOnClickLostButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelFoundOnClickFoundButton()
    {
        SceneForUserFromChannelFoundOnClickFoundButton sceneForUserFromChannelFoundOnClickFoundButton=new SceneForUserFromChannelFoundOnClickFoundButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelFoundOnClickFoundButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelFoundOnClickTakenButton()
    {
        SceneForUserFromChannelFoundOnClickTakenButton sceneForUserFromChannelFoundOnClickTakenButton=new SceneForUserFromChannelFoundOnClickTakenButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelFoundOnClickTakenButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelFoundOnClickUserInfoButton()
    {
        SceneForUserFromChannelFoundOnClickUserInfoButton sceneForUserFromChannelFoundOnClickUserInfoButton=new SceneForUserFromChannelFoundOnClickUserInfoButton(stageMain,userName);
        stageMain.setScene(sceneForUserFromChannelFoundOnClickUserInfoButton.getScene());
        stageMain.show();
    }
}
