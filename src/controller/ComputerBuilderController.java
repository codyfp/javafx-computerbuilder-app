package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.beans.property.*;
import java.io.*;
import au.edu.uts.ap.javafx.*;
import javafx.application.Platform;

import model.*;

public class ComputerBuilderController extends Controller<ComputerBuilder>{
    protected Build build;
    private Catalogue catalogue;
    
    public final ComputerBuilder getComputerBuilder() { return model; }
    
    @FXML private void initialize() {
        build = new Build();
        catalogue = new Catalogue(build);
    }
    public Build getBuild(){ return build; }
    
    @FXML private void handleCatalogueOpen(ActionEvent event) throws Exception {
        ViewLoader.showStage(catalogue, "/view/catalogue.fxml", "Catalogue", new Stage());
        
    }
    @FXML private void handleBuildOpen(ActionEvent event) throws Exception {
        ViewLoader.showStage(build, "/view/build.fxml", "Build", new Stage());
    }
    
    @FXML private void quit(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
}