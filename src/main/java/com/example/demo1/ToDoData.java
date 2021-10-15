package com.example.demo1;


import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;


public class ToDoData {

    private static ToDoData instance;
    private static String filename = "TodoListItens.txt";
    private List<ToDoItem> todoItens;
    private DateTimeFormatter formatter;

    public void loadToDoItens() throws IOException {
        todoItens = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try {
            while((input = br.readLine()) != null){
                String[] itemPiece = input.split("\t");
                String shortDescription = itemPiece[0];
                String details = itemPiece[1];
                String dateString = itemPiece[2];
                LocalDate date = LocalDate.parse(dateString, formatter);
                ToDoItem todoItem =  new ToDoItem(shortDescription, details, date);
                todoItens.add(todoItem);
            }

        } finally {
            if(br !=  null){
                br.close();
            }


        }
    }

    public void storeToDoItens() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<ToDoItem> iter = todoItens.iterator();
            while(iter.hasNext()){
                ToDoItem item = iter.next();
            }
        }finally {
            if(bw != null){
                bw.close();
            }
        }
    }


    public static ToDoData getInstance() {
        return instance;
    }

    private ToDoData() throws IOException {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<ToDoItem> getTodoItens() {
        return todoItens;
    }

    public void setTodoItens(List<ToDoItem> todoItens) {
        this.todoItens = todoItens;
    }
}
