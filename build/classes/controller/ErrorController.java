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

public class ErrorController extends Controller<String>{
    @FXML private Text errorTxt;
    
    @FXML private void initialize() { 
        errorTxt.setText(model);
    }
    
    @FXML private void quit (ActionEvent e){ stage.close();}
}
