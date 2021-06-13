package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField input;
    @FXML
    private Label result;
    @FXML
    private Button exchange;
    @FXML
    private Button convert;
    @FXML
    private ChoiceBox<String> origin;
    @FXML
    private ChoiceBox<String> target;

    private double number;
    private String[] currentType = {"USD", "HKD", "GBP", "AUD", "CAD", "SGD", "CHF", "JPY", "SEK", "NZD", "THB", "PHP", "IDR", "EUR", "KRW", "VND", "MYR", "CNY"};
    private ArrayList<Double> data = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        origin.getItems().addAll(currentType);
        target.getItems().addAll(currentType);
    }

    public void exchangeButton(){
        if (origin.getValue() == null || target.getValue() == null)
            return;
        int cntOri;
        int cntTar;
        for (cntOri = 0; cntOri < currentType.length; cntOri++){
            if (currentType[cntOri] == origin.getValue())
                break;
        }
        for (cntTar = 0; cntTar < currentType.length; cntTar++){
            if (currentType[cntTar] == target.getValue())
                break;
        }
        origin.setValue(currentType[cntTar]);
        target.setValue(currentType[cntOri]);
    }

    public void conv(ActionEvent e){
        //TODO
    }
}
