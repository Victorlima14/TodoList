package com.example.demo1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;


public class HelloController {

    private List<ToDoItem> toDoItens;

    @FXML
    private TextArea toDoDescription;

    @FXML
    private ListView toDoView;

    @FXML
    private Label deadLineLabel;

    public void initialize() {
        ToDoItem item1 = new ToDoItem("Develop app", "using Java", LocalDate.of(2021, Month.OCTOBER, 14));
        ToDoItem item2 = new ToDoItem("Study", "Java, Spring, Vue", LocalDate.of(2021, Month.OCTOBER, 15));
        ToDoItem item3 = new ToDoItem("Surf", "Practice", LocalDate.of(2021, Month.OCTOBER, 14));
        ToDoItem item4 = new ToDoItem("Exercise", "Running or bike", LocalDate.of(2021, Month.OCTOBER, 14));
        ToDoItem item5 = new ToDoItem("Travel", "Buy tickets", LocalDate.of(2021, Month.OCTOBER, 14));

        toDoItens = new ArrayList<ToDoItem>();

        toDoItens.add(item1);
        toDoItens.add(item2);
        toDoItens.add(item3);
        toDoItens.add(item4);
        toDoItens.add(item5);

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


        toDoView.getItems().setAll(toDoItens);
        toDoView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoView.getSelectionModel().selectFirst();

    }




}