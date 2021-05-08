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
import javafx.beans.binding.*;
import java.text.DecimalFormat;
import javafx.util.Callback;
import model.*;

public class BuildController extends Controller<Build>{
   @FXML private TableView<Part> buildPartsTv;
   @FXML private Button removeBtn;
   @FXML private Text totalTxt;
   @FXML private TableColumn priceCol;
   
    public final Build getBuild() { return model; }

    @FXML private void initialize() {
        buildPartsTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        removeBtn.setDisable(true);
        buildPartsTv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            removeBtn.setDisable(buildPartsTv.getSelectionModel().getSelectedCells().isEmpty());
        });
        
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

        totalTxt.textProperty().bind(getBuild().totalPriceProperty().asString("Total: $%.2f")); 
    }
    
    @FXML private void handleRemove(ActionEvent e){
        ObservableList<Part> selectedItems = buildPartsTv.getSelectionModel().getSelectedItems();
        getBuild().remove(selectedItems);
    }
    
    @FXML private void handleCheckBuild(ActionEvent e) throws Exception{
        ViewLoader.showStage(model, "/view/buildcheck.fxml", "Build Validity Status", new Stage());
    }
    
    @FXML public void quit(ActionEvent event){
        stage.close();
    }
}
