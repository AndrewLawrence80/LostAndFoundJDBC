package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class SceneMyReleaseForUserFromChannelFound
{
    private String userName = null;
    private Stage stageMain = null;
    private HBox hBoxPrimary = null;

    private ToggleButton toggleButtonLostInfo = null;
    private ToggleButton toggleButtonFoundInfo = null;
    private ToggleButton toggleButtonTakenInfo = null;
    private ToggleButton toggleButtonUserInfo = null;
    private VBox vBoxToggleGroup = null;

    private Label labelItemLost = null;
    private Label labelItemFound = null;
    private TableView<Item> tableViewItemLost = null;
    private TableView<Item> tableViewItemFound = null;

    private Button buttonUpdateItemLost = null;
    private Button buttonArchiveItemLost = null;
    private Button buttonUpdateItemFound = null;
    private Button buttonArchiveItemFound = null;

    private HBox hBoxFunctionSectionLost = null;
    private HBox hBoxFunctionSectionFound = null;

    private VBox vBoxFunctionPart = null;

    private Scene scene = null;

    private ObservableList<Item> itemsLost = null;
    private ObservableList<Item> itemsFound = null;

    public SceneMyReleaseForUserFromChannelFound(Stage stage, String userName)
    {
        this.userName = userName;
        this.stageMain = stage;
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
        hBoxPrimary = new HBox();

        toggleButtonLostInfo = new ToggleButton("??????????????????");
        toggleButtonLostInfo.setFont(Font.font(18));
        toggleButtonFoundInfo = new ToggleButton("??????????????????");
        toggleButtonFoundInfo.setFont(Font.font(18));
        toggleButtonTakenInfo = new ToggleButton("??????????????????");
        toggleButtonTakenInfo.setFont(Font.font(18));
        toggleButtonUserInfo = new ToggleButton("??????????????????");
        toggleButtonUserInfo.setFont(Font.font(18));

        ToggleGroup toggleGroupNavigationGroup = new ToggleGroup();
        toggleGroupNavigationGroup.getToggles().addAll(toggleButtonLostInfo, toggleButtonFoundInfo, toggleButtonTakenInfo, toggleButtonUserInfo);

        toggleButtonUserInfo.setSelected(true);

        vBoxToggleGroup = new VBox();
        toggleButtonLostInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonFoundInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonTakenInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonUserInfo.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.2));
        toggleButtonLostInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        toggleButtonFoundInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        toggleButtonTakenInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        toggleButtonUserInfo.prefHeightProperty().bind(stageMain.heightProperty().multiply(0.25));
        vBoxToggleGroup.getChildren().addAll(toggleButtonLostInfo, toggleButtonFoundInfo, toggleButtonTakenInfo, toggleButtonUserInfo);

        labelItemLost = new Label("??????????????????");
        labelItemFound = new Label("??????????????????");
        tableViewItemLost = new TableView<Item>();
        tableViewItemLost.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.8));
        tableViewItemFound = new TableView<Item>();
        tableViewItemFound.prefWidthProperty().bind(stageMain.widthProperty().multiply(0.8));
        buttonUpdateItemLost = new Button("?????????????????????????????????");
        buttonArchiveItemLost = new Button("????????????????????????");
        buttonUpdateItemFound = new Button("?????????????????????????????????");
        buttonArchiveItemFound = new Button("????????????????????????");

        hBoxFunctionSectionLost = new HBox();
        hBoxFunctionSectionLost.setAlignment(Pos.CENTER);
        hBoxFunctionSectionLost.setSpacing(40);
        hBoxFunctionSectionLost.getChildren().addAll(labelItemLost, buttonUpdateItemLost, buttonArchiveItemLost);

        hBoxFunctionSectionFound = new HBox();
        hBoxFunctionSectionFound.setAlignment((Pos.CENTER));
        hBoxFunctionSectionFound.setSpacing(40);
        hBoxFunctionSectionFound.getChildren().addAll(labelItemFound, buttonUpdateItemFound, buttonArchiveItemFound);

        vBoxFunctionPart = new VBox();
        vBoxFunctionPart.setSpacing(10);
        vBoxFunctionPart.setAlignment(Pos.CENTER);
        vBoxFunctionPart.getChildren().addAll(hBoxFunctionSectionLost, tableViewItemLost, hBoxFunctionSectionFound, tableViewItemFound);

        hBoxPrimary.getChildren().addAll(vBoxToggleGroup, vBoxFunctionPart);
        scene = new Scene(hBoxPrimary);
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

        buttonUpdateItemLost.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Item itemSelected = tableViewItemLost.getSelectionModel().getSelectedItem();
                if (itemSelected == null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("???????????????");
                    alert.setContentText("?????????????????????????????????????????????");
                    alert.showAndWait();
                }
                else
                {
                    DialogUpdateItemLostInfo dialogUpdateItemLostInfo = new DialogUpdateItemLostInfo(itemSelected);
                    dialogUpdateItemLostInfo.showAndWait();
                    fillTableLost();
                }
            }
        });

        buttonArchiveItemLost.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Item itemSelected = tableViewItemLost.getSelectionModel().getSelectedItem();
                if (itemSelected == null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("???????????????");
                    alert.setContentText("?????????????????????????????????????????????");
                }
                else
                {
                    DialogArchiveItemLost dialogArchiveItemLost=new DialogArchiveItemLost(itemSelected);
                    dialogArchiveItemLost.showAndWait();
                    fillTableLost();
                }
            }
        });

        buttonUpdateItemFound.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Item itemSelected = tableViewItemFound.getSelectionModel().getSelectedItem();
                if (itemSelected == null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("???????????????");
                    alert.setContentText("?????????????????????????????????????????????");
                    alert.showAndWait();
                }
                else
                {
                    DialogUpdateItemFoundInfo dialogUpdateItemFoundInfo = new DialogUpdateItemFoundInfo(itemSelected);
                    dialogUpdateItemFoundInfo.showAndWait();
                    fillTableFound();
                }
            }
        });

        buttonArchiveItemFound.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Item itemSelected = tableViewItemFound.getSelectionModel().getSelectedItem();
                if (itemSelected == null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("???????????????");
                    alert.setContentText("?????????????????????????????????????????????");
                    alert.showAndWait();
                }
                else
                {
                    DialogArchiveItemFound dialogArchiveItemFound=new DialogArchiveItemFound(itemSelected);
                    dialogArchiveItemFound.showAndWait();
                    fillTableFound();
                }
            }
        });
    }

    private void switchToSceneForUserFromChannelLostOnClickLostButton()
    {
        SceneForUserFromChannelLostOnClickLostButton sceneForUserFromChannelLostOnClickLostButton = new SceneForUserFromChannelLostOnClickLostButton(stageMain, userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickLostButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelLostOnClickFoundButton()
    {
        SceneForUserFromChannelLostOnClickFoundButton sceneForUserFromChannelLostOnClickFoundButton = new SceneForUserFromChannelLostOnClickFoundButton(stageMain, userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickFoundButton.getScene());
        stageMain.show();

    }

    private void switchToSceneForUserFromChannelLostOnClickTakenButton()
    {
        SceneForUserFromChannelLostOnClickTakenButton sceneForUserFromChannelLostOnClickTakenButton = new SceneForUserFromChannelLostOnClickTakenButton(stageMain, userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickTakenButton.getScene());
        stageMain.show();
    }

    private void switchToSceneForUserFromChannelLostOnClickUserInfoButton()
    {
        SceneForUserFromChannelLostOnClickUserInfoButton sceneForUserFromChannelLostOnClickUserInfoButton = new SceneForUserFromChannelLostOnClickUserInfoButton(stageMain, userName);
        stageMain.setScene(sceneForUserFromChannelLostOnClickUserInfoButton.getScene());
        stageMain.show();
    }

    private void fillTable()
    {
        fillTableLost();
        fillTableFound();
    }

    private void fillTableLost()
    {
        try
        {
            Connection connection = ConnectionController.getConnection();
            String sql = "select id,name,date,location,description,contact from itemslost where username=?";
            BeanListHandler<Item> handler = new BeanListHandler<>(Item.class);
            QueryRunner queryRunner = new QueryRunner();
            List<Item> itemList = queryRunner.query(connection, sql, handler, userName);
            itemsLost = FXCollections.observableList(itemList);
            TableColumn<Item, Integer> idColumn = new TableColumn<>("??????Id");
            idColumn.setPrefWidth(tableViewItemLost.getPrefWidth() / 6);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Item, String> nameColumn = new TableColumn<>("????????????");
            nameColumn.setPrefWidth(tableViewItemLost.getPrefWidth() / 6);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
            TableColumn<Item, Date> dateColumn = new TableColumn<>("????????????");
            dateColumn.setPrefWidth(tableViewItemLost.getPrefWidth() / 6);
            dateColumn.setCellValueFactory(new PropertyValueFactory<Item, Date>("date"));
            TableColumn<Item, String> locationColumn = new TableColumn<>("????????????");
            locationColumn.setPrefWidth(tableViewItemLost.getPrefWidth() / 6);
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            TableColumn<Item, String> descriptionColumn = new TableColumn<>("????????????");
            descriptionColumn.setPrefWidth(tableViewItemLost.getPrefWidth() / 6);
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Item, String> contactColumn = new TableColumn<>("????????????");
            contactColumn.setPrefWidth(tableViewItemLost.getPrefWidth() / 6);
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tableViewItemLost.setItems(itemsLost);
            tableViewItemLost.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, contactColumn);
            tableViewItemLost.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
        catch (IOException e)
        {
            Alert alertIOException = new Alert(Alert.AlertType.ERROR, "??????????????????????????????");
            alertIOException.setTitle("??????????????????");
            alertIOException.setHeaderText(null);
            alertIOException.showAndWait();
        }
        catch (SQLException e)
        {
            Alert alertSQLException = new Alert(Alert.AlertType.ERROR, "????????????????????????????????????");
            alertSQLException.setTitle("????????????");
            alertSQLException.setHeaderText(null);
            alertSQLException.showAndWait();
        }
        catch (Exception e)
        {
            Alert alertException = new Alert(Alert.AlertType.ERROR, "????????????????????????");
            alertException.setTitle("????????????");
            alertException.setHeaderText(null);
            alertException.showAndWait();
        }
    }

    private void fillTableFound()
    {
        try
        {
            Connection connection = ConnectionController.getConnection();
            String sql = "select id,name,date,location,description,contact from itemsfound where username=?";
            BeanListHandler<Item> handler = new BeanListHandler<>(Item.class);
            QueryRunner queryRunner = new QueryRunner();
            List<Item> itemList = queryRunner.query(connection, sql, handler, userName);
            itemsFound = FXCollections.observableList(itemList);
            TableColumn<Item, Integer> idColumn = new TableColumn<>("??????Id");
            idColumn.setPrefWidth(tableViewItemFound.getPrefWidth() / 6);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Item, String> nameColumn = new TableColumn<>("????????????");
            nameColumn.setPrefWidth(tableViewItemFound.getPrefWidth() / 6);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
            TableColumn<Item, Date> dateColumn = new TableColumn<>("????????????");
            dateColumn.setPrefWidth(tableViewItemFound.getPrefWidth() / 6);
            dateColumn.setCellValueFactory(new PropertyValueFactory<Item, Date>("date"));
            TableColumn<Item, String> locationColumn = new TableColumn<>("????????????");
            locationColumn.setPrefWidth(tableViewItemFound.getPrefWidth() / 6);
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            TableColumn<Item, String> descriptionColumn = new TableColumn<>("????????????");
            descriptionColumn.setPrefWidth(tableViewItemFound.getPrefWidth() / 6);
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Item, String> contactColumn = new TableColumn<>("????????????");
            contactColumn.setPrefWidth(tableViewItemFound.getPrefWidth() / 6);
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tableViewItemFound.setItems(itemsFound);
            tableViewItemFound.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, contactColumn);
            tableViewItemFound.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
        catch (IOException e)
        {
            Alert alertIOException = new Alert(Alert.AlertType.ERROR, "??????????????????????????????");
            alertIOException.setTitle("??????????????????");
            alertIOException.setHeaderText(null);
            alertIOException.showAndWait();
        }
        catch (SQLException e)
        {
            Alert alertSQLException = new Alert(Alert.AlertType.ERROR, "????????????????????????????????????");
            alertSQLException.setTitle("????????????");
            alertSQLException.setHeaderText(null);
            alertSQLException.showAndWait();
        }
        catch (Exception e)
        {
            Alert alertException = new Alert(Alert.AlertType.ERROR, "????????????????????????");
            alertException.setTitle("????????????");
            alertException.setHeaderText(null);
            alertException.showAndWait();
        }
    }
}
