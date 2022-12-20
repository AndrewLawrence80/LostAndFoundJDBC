package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class DialogUpdateItemFoundInfo
{
    private Stage stage=null;
    private Item item=null;
    private Label labelName=null;
    private Label labelDate=null;
    private Label labelLocation=null;
    private Label labelDescription=null;
    private Label labelContact=null;
    private TextField textFieldName=null;
    private DatePicker datePicker=null;
    private TextField textFieldLocation=null;
    private TextField textFieldDescription=null;
    private TextField textFieldContact=null;
    private Button buttonConfirm=null;
    private Text textUpdateResult=null;
    private GridPane gridPane=null;
    private VBox vBox=null;
    private Scene scene=null;
    public DialogUpdateItemFoundInfo(Item item)
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
        stage=new Stage();
        stage.setTitle("修改物品信息");
        labelName=new Label("物品名称");
        labelDate=new Label("丢失时间");
        labelLocation=new Label("丢失地点");
        labelDescription=new Label("物品描述");
        labelContact=new Label("联系方式");
        textFieldName=new TextField();
        textFieldName.setText(item.getName());
        datePicker=new DatePicker();
        datePicker.getEditor().setEditable(false);
        datePicker.setValue(Date.valueOf(item.getDate().toString()).toLocalDate());
        textFieldLocation=new TextField();
        textFieldLocation.setText(item.getLocation());
        textFieldDescription=new TextField();
        textFieldDescription.setText(item.getDescription());
        textFieldContact=new TextField();
        textFieldContact.setText(item.getContact());
        buttonConfirm=new Button("确定");
        textUpdateResult=new Text();

        gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setHgap(10);
        gridPane.add(labelName,0,0);
        gridPane.add(textFieldName,1,0);
        gridPane.add(labelDate,0,1);
        gridPane.add(datePicker,1,1);
        gridPane.add(labelLocation,0,2);
        gridPane.add(textFieldLocation,1,2);
        gridPane.add(labelDescription,0,3);
        gridPane.add(textFieldDescription,1,3);
        gridPane.add(labelContact,0,4);
        gridPane.add(textFieldContact,1,4);
        vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(gridPane,buttonConfirm,textUpdateResult);

        scene=new Scene(vBox,400,300);

    }

    private void setOnAction()
    {
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String name=textFieldName.getText();
                LocalDate localDate=datePicker.getValue();
                String location=textFieldLocation.getText();
                String description=textFieldDescription.getText();
                String contact=textFieldContact.getText();

                if (name.equals("")||localDate==null||location.equals("")||description.equals("")||contact.equals(""))
                {
                    textUpdateResult.setText("请完整填写信息！");
                }
                else
                {
                    try
                    {
                        Date date = Date.valueOf(localDate);
                        Connection connection = ConnectionController.getConnection();
                        String sql = "update itemsfound set name=?,date=?,location=?,description=?,contact=? where id=?";
                        QueryRunner queryRunner = new QueryRunner();
                        queryRunner.update(connection, sql, name, date, location, description, contact, item.getId());
                        item.setName(name);
                        item.setDate(date);
                        item.setLocation(location);
                        item.setDescription(description);
                        item.setContact(contact);
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
                    textUpdateResult.setText("修改成功！");
                    stage.close();
                }
            }
        });
    }
}
