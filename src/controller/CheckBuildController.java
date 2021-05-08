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

public class CheckBuildController extends Controller<Build>{

    @FXML private Text validTxt;
    
    private Build getBuild(){ return model; }
    
    @FXML public void initialize(){
        validTxt.setText(checkBuild());
        
    }

    private String checkBuild(){
         String ret = "";
        if(getBuild().isValid()){
            return "The build is functional.";
        } else{
            String[] check ={"CPU","MOTHERBOARD","MEMORY","CASE","STORAGE"};
            
            for(String type : check){
                if(!getBuild().hasPartOfType(type)){
                    ret += "The build is missing ";
                    switch(type){
                        case "MEMORY": 
                            ret+="RAM."; break;
                        case "STORAGE.":
                            ret+="storage."; break;
                        case "CPU":
                            ret+="a CPU."; break;
                        default:
                            ret +="a " + type.toLowerCase() + "."; break;
                    }
                    ret+="\n";
                }
            }
        }
        return ret;
    }
    
    @FXML private void quit(ActionEvent e){
        stage.close();
    }
}
