package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.property.*;
import java.io.*;
import au.edu.uts.ap.javafx.*;
import javafx.util.Callback;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import model.*;

public class CatalogueController extends Controller<Catalogue>{
    @FXML private TableView<Part> partsTv;
    @FXML private Button addBuildBtn;
    @FXML private Button removeCatalogueBtn;
    @FXML private TableColumn priceCol;
    @FXML private TextField typeTf, minPriceTf, maxPriceTf;
    
    public final Catalogue getCatalogue() { return model; }
    private Part getPart() { return partsTv.getSelectionModel().getSelectedItem(); }
    
    @FXML private void initialize() {
        
        partsTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        addBuildBtn.setDisable(true);
        removeCatalogueBtn.setDisable(true);  
        //check if there is a selected item and sets buttons enabled/disabled as required
        partsTv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            removeCatalogueBtn.setDisable(partsTv.getSelectionModel().getSelectedCells().isEmpty());
            addBuildBtn.setDisable(partsTv.getSelectionModel().getSelectedCells().isEmpty());
        });
        //listeners for filter
        typeTf.textProperty().addListener((o, oldTxt, newTxt) ->{
            model.filterList(typeTf.getText().toUpperCase(), minPriceTf.getText(), maxPriceTf.getText());
        });
        minPriceTf.textProperty().addListener((o, oldTxt, newTxt) ->{
            model.filterList(typeTf.getText().toUpperCase(), minPriceTf.getText(), maxPriceTf.getText());
        });
        maxPriceTf.textProperty().addListener((o, oldTxt, newTxt) ->{
            model.filterList(typeTf.getText().toUpperCase(), minPriceTf.getText(), maxPriceTf.getText());
        });
        
        // formatting the price column to show $---.00
        priceCol.setCellFactory(new Callback<TableColumn,TableCell>(){
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Part, Double>() {
                    @Override
                    public void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
      
                    }
                    private String getString() {
                        DecimalFormat f = new DecimalFormat("###,##0.00");
                        return "$" + f.format(getItem());
                    }
                };
                return cell;
            }
        });        
    }
    
    
    @FXML private void handleNewPart(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/addtocatalogue.fxml", "Add New Part to Catalogue", new Stage());
    }
    
    
    @FXML private void handleRemove(ActionEvent event) {
        ObservableList<Part> selectedItems = partsTv.getSelectionModel().getSelectedItems();
        getCatalogue().removeItems(selectedItems);
    }
    
    @FXML private void handleAddToBuild(ActionEvent event) throws Exception {    
        ObservableList<Part> selectedItems = partsTv.getSelectionModel().getSelectedItems();
        getCatalogue().addToBuild(selectedItems);
    
    }
    
    @FXML public void quit(ActionEvent event){
        stage.close();
    }

    private void checkButtons(){
        addBuildBtn.setDisable(partsTv.getSelectionModel().getSelectedCells().isEmpty());
        removeCatalogueBtn.setDisable(partsTv.getSelectionModel().getSelectedCells().isEmpty());
    }


}

