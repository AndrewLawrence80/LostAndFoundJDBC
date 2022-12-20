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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DialogArchiveItemFound
{
    private Stage stage = null;
    private Item item = null;
    private Label labelName = null;
    private Label labelDate = null;
    private Label labelLocation = null;
    private Label labelDescription = null;
    private Label labelContactLost = null;
    private Label labelContactFound = null;

    private Label labelNameValue = null;
    private Label labelDateValue = null;
    private Label labelLocationValue = null;
    private Label labelDescriptionValue = null;
    private TextField textFieldContactLost = null;
    private TextField textFieldContactFound = null;

    private GridPane gridPane = null;
    private Button buttonConfirm = null;
    private Text textInsertResult = null;
    private VBox vBox = null;

    private Scene scene = null;

    public DialogArchiveItemFound(Item item)
    {
        this.item = item;
        createLayout();
        setOnAction();
    }

    public void showAndWait()
    {
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void createLayout()
    {
        stage = new Stage();
        labelName = new Label("物品名称");
        labelDate = new Label("拾得时间");
        labelLocation = new Label("拾得地点");
        labelDescription = new Label("物品描述");
        labelContactLost = new Label("认领者联系方式");
        labelContactFound = new Label("拾得者联系方式");

        labelNameValue = new Label(item.getName());
        labelDateValue = new Label(item.getDate().toString());
        labelLocationValue = new Label(item.getLocation());
        labelDescriptionValue = new Label(item.getDescription());
        textFieldContactLost = new TextField();
        textFieldContactFound = new TextField();

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(labelName, 0, 0);
        gridPane.add(labelNameValue, 1, 0);
        gridPane.add(labelDate, 0, 1);
        gridPane.add(labelDateValue, 1, 1);
        gridPane.add(labelLocation, 0, 2);
        gridPane.add(labelLocationValue, 1, 2);
        gridPane.add(labelDescription, 0, 3);
        gridPane.add(labelDescriptionValue, 1, 3);
        gridPane.add(labelContactLost, 0, 4);
        gridPane.add(textFieldContactLost, 1, 4);
        gridPane.add(labelContactFound, 0, 5);
        gridPane.add(textFieldContactFound, 1, 5);


        buttonConfirm = new Button("确认");
        textInsertResult = new Text();

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(gridPane, buttonConfirm, textInsertResult);

        scene = new Scene(vBox,400,300);
    }

    private void setOnAction()
    {
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String contactLost = textFieldContactLost.getText();
                String contactFound = textFieldContactFound.getText();
                if (contactLost.equals("") || contactFound.equals(""))
                {
                    textInsertResult.setText("请完整填写信息！");
                }
                else
                {
                    try
                    {

                        Connection connection = ConnectionController.getConnection();
                        String sqlInsert = "insert into itemstaken(name,date,status,location,description,contactlost,contactfound) values(?,?,?,?,?,?,?)";
                        String sqlDelete = "delete from itemsfound where id=?";
                        QueryRunner queryRunner = new QueryRunner();
                        queryRunner.update(connection, sqlInsert, item.getName(),item.getDate(),"拾得", item.getLocation(), item.getDescription(), contactLost, contactFound);
                        queryRunner.update(connection, sqlDelete, item.getId());
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

                    textInsertResult.setText("完成！");
                    stage.close();
                }
            }
        });
    }
}
