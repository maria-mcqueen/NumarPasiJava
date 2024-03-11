package com.example.eu;

import Domain.Zi;
import Domain.ziFactory;
import Repository.DuplicateEntityException;
import Repository.FileRepository;
import Repository.IRepository;
import Repository.MemoryRepository;
import Service.ZiService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, DuplicateEntityException {

        TextField idTextField = new TextField();
        TextField modelTextField = new TextField();
        TextField nrpasiTextField = new TextField();
        TextField nrsomnTextField = new TextField();

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //IRepository<Zi> ziRepo = new MemoryRepository<>();
//        ZiService ziService = new ZiService(carRepo);

        IRepository<Zi> ziRepo = new FileRepository<>("activitati.txt",new ziFactory());
        ZiService ziService = new ZiService(ziRepo);

//        try {
////            ziService.add(1,20,4,"plimbare dimineata lmao");
////            ziService.add(2,15,7,"plimbare seara lmao");
////            ziService.add(3,40,9,"plimbare");
////            ziService.add(3,20,4,"plimbare dimineata lmao");
////            ziService.add(4,15,7,"plimbare seara lmao");
////            ziService.add(5,40,9,"plimbare");
//        } catch (DuplicateEntityException e) {
//            throw new RuntimeException(e);
//        }

        VBox mainVerticalBox = new VBox();
        mainVerticalBox.setPadding(new Insets(10));

        ObservableList<Zi> zis = FXCollections.observableArrayList(ziService.getAll());
        ListView<Zi> listView = new ListView<Zi>(zis);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Zi zi = listView.getSelectionModel().getSelectedItem();
                idTextField.setText(Integer.toString(zi.getData()));
                nrpasiTextField.setText(Integer.toString(zi.getNrPasi()));
                nrsomnTextField.setText(Integer.toString(zi.getNrSomn()));
                modelTextField.setText(zi.getLista());
            }
        });
        mainVerticalBox.getChildren().add(listView);



        GridPane zigridPane = new GridPane();
        Label idLabel = new Label();
        idLabel.setText("data: ");
        idLabel.setPadding(new Insets(10,5,10,0));

        Label nrPasiLabel = new Label();
        nrPasiLabel.setText("nrPasi: ");
        nrPasiLabel.setPadding(new Insets(10,5,10,0));

        Label nrSomnLabel = new Label();
        nrSomnLabel.setText("nr ore somn: ");
        nrSomnLabel.setPadding(new Insets(10,5,10,0));

        Label listaLabel = new Label();
        listaLabel.setText("lista de activitati: ");
        listaLabel.setPadding(new Insets(10,5,10,0));

        zigridPane.add(idLabel,0,0);
        zigridPane.add(idTextField,1,0);
        zigridPane.add(listaLabel,0,1);
        zigridPane.add(modelTextField,1,1);
        zigridPane.add(nrPasiLabel,2,0);
        zigridPane.add(nrpasiTextField,3,0);
        zigridPane.add(nrSomnLabel,2,1);
        zigridPane.add(nrsomnTextField,3,1);


        mainVerticalBox.getChildren().add(zigridPane);

        HBox buttonsHorizontalBox = new HBox();
        mainVerticalBox.getChildren().add(buttonsHorizontalBox);

        Button addButton = new Button("add zi ");
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    int nrPasi = Integer.parseInt(nrpasiTextField.getText());
                    int nrSomn = Integer.parseInt(nrsomnTextField.getText());
                    String lista = modelTextField.getText();
                    ziService.add(id,nrPasi,nrSomn,lista);
                    zis.setAll(ziService.getAll());
                } catch (Exception e) {
                    Alert popAlert = new Alert(Alert.AlertType.ERROR);
                    popAlert.setTitle("error");
                    popAlert.setContentText(e.getMessage());
                    popAlert.show();
                }
            }
        });

        buttonsHorizontalBox.getChildren().add(addButton);


        Button updateButton = new Button("update zi ");
        updateButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    int nrPasi = Integer.parseInt(nrpasiTextField.getText());
                    int nrSomn = Integer.parseInt(nrsomnTextField.getText());
                    String lista = modelTextField.getText();
                    ziService.update(id,nrPasi,nrSomn,lista);
                    zis.setAll(ziService.getAll());
                } catch (Exception e) {
                    Alert popAlert = new Alert(Alert.AlertType.ERROR);
                    popAlert.setTitle("error");
                    popAlert.setContentText(e.getMessage());
                    popAlert.show();
                }
            }
        });

        buttonsHorizontalBox.getChildren().add(updateButton);

        Button deleteButton = new Button("delete zi ");
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    ziService.remove(id);
                    zis.setAll(ziService.getAll());
                } catch (Exception e) {
                    Alert popAlert = new Alert(Alert.AlertType.ERROR);
                    popAlert.setTitle("error");
                    popAlert.setContentText(e.getMessage());
                    popAlert.show();
                }
            }
        });

        buttonsHorizontalBox.getChildren().add(deleteButton);

        Scene scene = new Scene(mainVerticalBox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}