package com.example;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class SceneForUserFromChannelLostOnClickFoundButton
{
    private String userName=null;
    private Stage stageMain=null;
    private HBox hBoxPrimary=null;

    private ToggleButton toggleButtonLostInfo=null;
    private ToggleButton toggleButtonFoundInfo=null;
    private ToggleButton toggleButtonTakenInfo=null;
    private ToggleButton toggleButtonUserInfo=null;
    private VBox vBoxToggleGroup=null;

    private Button buttonSearch=null;
    private TextField textFieldSearch=null;

    private TableView tableViewInfo=null;

    private HBox hBoxFunctionSection=null;
    private VBox vBoxFunctionPart=null;


    private Scene scene=null;

    public SceneForUserFromChannelLostOnClickFoundButton(Stage stage,String userName)
    {
        this.userName=userName;
        this.stageMain=stage;
        this.stageMain.setTitle("失物招领");
        this.stageMain.setWidth(800);
        this.stageMain.setHeight(600);
        createLayout();
        fillTable();
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

        vBoxFunctionPart=new VBox();
        vBoxFunctionPart.setSpacing(10);

        hBoxFunctionSection=new HBox();
        hBoxFunctionSection.setSpacing(10);
        hBoxFunctionSection.setAlignment(Pos.CENTER);
        hBoxFunctionSection.prefWidthProperty().bind(vBoxFunctionPart.widthProperty().multiply(1));
        textFieldSearch=new TextField();
        textFieldSearch.setPromptText("输入以搜索");
        textFieldSearch.prefWidthProperty().bind(hBoxFunctionSection.widthProperty().multiply(0.5));
        buttonSearch=new Button("搜索");
        hBoxFunctionSection.getChildren().addAll(textFieldSearch,buttonSearch);
        tableViewInfo=new TableView<Item>();
        tableViewInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.8));
        tableViewInfo.prefHeightProperty().bind(hBoxPrimary.heightProperty().subtract(hBoxFunctionSection.heightProperty().add(10)));
        vBoxFunctionPart.getChildren().addAll(hBoxFunctionSection,tableViewInfo);


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
        buttonSearch.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String stringSearch=textFieldSearch.getText();
                if (stringSearch.equals(""))
                {
                    fillTable();
                }
                else
                {
                    fillTableByName(stringSearch);
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

    private void fillTable()
    {
        try
        {
            Connection connection = ConnectionController.getConnection();
            String sql = "select id,name,date,location,description,contact from itemsfound";
            BeanListHandler<Item> handler = new BeanListHandler<>(Item.class);
            QueryRunner queryRunner = new QueryRunner();
            List<Item> itemList = queryRunner.query(connection, sql, handler);
            ObservableList<Item> items= FXCollections.observableList(itemList);
            TableColumn <Item,Integer> idColumn=new TableColumn<>("物品Id");
            idColumn.setPrefWidth(tableViewInfo.getPrefWidth()/6);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Item, String> nameColumn = new TableColumn<>("物品名称");
            nameColumn.setPrefWidth(tableViewInfo.getPrefWidth()/6);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
            TableColumn<Item, Date> dateColumn = new TableColumn<>("拾得日期");
            dateColumn.setPrefWidth(tableViewInfo.getPrefWidth()/6);
            dateColumn.setCellValueFactory(new PropertyValueFactory<Item, Date>("date"));
            TableColumn<Item, String> locationColumn = new TableColumn<>("拾得地点");
            locationColumn.setPrefWidth(tableViewInfo.getPrefWidth()/6);
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            TableColumn<Item, String> descriptionColumn = new TableColumn<>("物品描述");
            descriptionColumn.setPrefWidth(tableViewInfo.getPrefWidth()/6);
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Item, String> contactColumn = new TableColumn<>("联系方式");
            contactColumn.setPrefWidth(tableViewInfo.getPrefWidth()/6);
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tableViewInfo.setItems(items);
            tableViewInfo.getColumns().addAll(idColumn,nameColumn, dateColumn, locationColumn, descriptionColumn, contactColumn);
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

    private void fillTableByName(String stringSearch)
    {
        try
        {
            Connection connection = ConnectionController.getConnection();
            String sql = "select id,name,date,location,description,contact from itemsfound where name=?";
            BeanListHandler<Item> handler = new BeanListHandler<>(Item.class);
            QueryRunner queryRunner = new QueryRunner();
            List<Item> itemList = queryRunner.query(connection,sql,handler,stringSearch);
            ObservableList<Item> items = FXCollections.observableList(itemList);
            TableColumn<Item, Integer> idColumn = new TableColumn<>("物品Id");
            idColumn.setPrefWidth(tableViewInfo.getPrefWidth() / 6);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Item, String> nameColumn = new TableColumn<>("物品名称");
            nameColumn.setPrefWidth(tableViewInfo.getPrefWidth() / 6);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
            TableColumn<Item, Date> dateColumn = new TableColumn<>("拾得日期");
            dateColumn.setPrefWidth(tableViewInfo.getPrefWidth() / 6);
            dateColumn.setCellValueFactory(new PropertyValueFactory<Item, Date>("date"));
            TableColumn<Item, String> locationColumn = new TableColumn<>("拾得地点");
            locationColumn.setPrefWidth(tableViewInfo.getPrefWidth() / 6);
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            TableColumn<Item, String> descriptionColumn = new TableColumn<>("物品描述");
            descriptionColumn.setPrefWidth(tableViewInfo.getPrefWidth() / 6);
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Item, String> contactColumn = new TableColumn<>("联系方式");
            contactColumn.setPrefWidth(tableViewInfo.getPrefWidth() / 6);
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tableViewInfo.setItems(items);
            tableViewInfo.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, contactColumn);
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
