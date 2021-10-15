package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public ToDoItem processResults (){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue= deadlinePicker.getValue();
        ToDoItem newItem = new ToDoItem(shortDescription,details,deadlineValue);
        ToDoData.getInstance().addTodoItem(newItem);
        return newItem;
    }
}
