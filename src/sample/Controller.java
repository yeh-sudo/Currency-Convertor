package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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
    private String[] currentType = {"TWD", "CNY", "HKD", "JPY", "KRW", "THB", "SGD", "GBP", "EUR", "DKK", "USD", "ETH", "BTC"};
    private double[] exchangeRate = {1.0, 0.23, 0.28, 3.89, 40.41, 1.13, 0.048, 0.025, 0.029, 0.22, 0.036};
    private double[] exchangeRateOfOtherCur = {97719.57, 1257502.55};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        origin.getItems().addAll(currentType);
        target.getItems().addAll(currentType);
    }

    public void exchangeButton(ActionEvent e){
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
