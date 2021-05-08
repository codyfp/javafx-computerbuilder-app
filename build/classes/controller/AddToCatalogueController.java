package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.property.*;
import java.io.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class AddToCatalogueController extends Controller<Catalogue> {
    String type, name;
    double price;
    
    @FXML private TextField typeTf, nameTf, priceTf;
    
    @FXML private void initialize() { }
    
    @FXML private void handleAdd(ActionEvent event) throws Exception {
        try{
            model.addPart(getName(), getType(), getPrice());
            stage.close();
        }catch (NumberFormatException e){
            ViewLoader.showStage("Invalid price entered.", "/view/error.fxml", "Add New Part to Catalogue", new Stage());
        }
    }
    
    private String getType(){ return typeTf.getText(); }
    private String getName() { return nameTf.getText(); }
    private Double getPrice() throws NumberFormatException {
        return Double.parseDouble(priceTf.getText());
    }
}
