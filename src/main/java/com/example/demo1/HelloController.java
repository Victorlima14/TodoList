package com.example.demo1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class HelloController {

    private List<ToDoItem> toDoItens;

    @FXML
    private TextArea toDoDescription;

    @FXML
    private ListView toDoView;

    @FXML
    private Label deadLineLabel;

    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
 //       ToDoItem item1 = new ToDoItem("Develop app", "using Java", LocalDate.of(2021, Month.OCTOBER, 14));
 //       ToDoItem item2 = new ToDoItem("Study", "Java, Spring, Vue", LocalDate.of(2021, Month.OCTOBER, 15));
 //       ToDoItem item3 = new ToDoItem("Surf", "Practice", LocalDate.of(2021, Month.OCTOBER, 14));
 //       ToDoItem item4 = new ToDoItem("Exercise", "Running or bike", LocalDate.of(2021, Month.OCTOBER, 14));
 //       ToDoItem item5 = new ToDoItem("Travel", "Buy tickets", LocalDate.of(2021, Month.OCTOBER, 14));

 //       toDoItens = new ArrayList<ToDoItem>();

   //     toDoItens.add(item1);
 //       toDoItens.add(item2);
  //      toDoItens.add(item3);
  //      toDoItens.add(item4);
    //    toDoItens.add(item5);

 //       ToDoData.getInstance().setTodoItens(toDoItens);

        toDoView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
              @Override
               public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem oldValue, ToDoItem newValue) {
                    if(newValue != null){
                        ToDoItem item = (ToDoItem) toDoView.getSelectionModel().getSelectedItem();
                        toDoDescription.setText(item.getDetails());
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                        deadLineLabel.setText(df.format(item.getDeadline()));
                    }
               }
        });


        toDoView.getItems().setAll(ToDoData.getInstance().getTodoItens());
        toDoView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoView.getSelectionModel().selectFirst();

    }

    @FXML
    public void showNewItemDialog()  {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new Todo Item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e){
            System.out.println("Cant open dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = fxmlLoader.getController();
            ToDoItem newItem = controller.processResults();
            toDoView.getItems().setAll(ToDoData.getInstance().getTodoItens());
            toDoView.getSelectionModel().select(newItem);
            System.out.println("Button OK Pressed");
        }else{
            System.out.println("Button CANCEL Pressed");
        }

    }




}