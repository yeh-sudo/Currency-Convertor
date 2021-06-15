//reset data button


package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField input;
    @FXML
    private Label result;
    @FXML
    private ChoiceBox<String> origin;
    @FXML
    private ChoiceBox<String> target;

    private final String[] currentType = {"USD", "HKD", "GBP", "AUD", "CAD", "SGD", "CHF", "JPY", "SEK", "NZD", "THB", "PHP", "IDR", "EUR", "KRW", "VND", "MYR", "CNY", "TWD"};
    private ArrayList<Double> data = new ArrayList<>();

    public Controller() {}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        origin.getItems().addAll(currentType);
        target.getItems().addAll(currentType);
    }

    public void setData() {
        webCrawler webCrawler = new webCrawler();
        webCrawler.setResult();
        data = webCrawler.getResult();
        result.setText("");
    }

    public void exchangeButton(){
        if (origin.getValue() == null || target.getValue() == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please select currency type");
            alert.showAndWait();
            return;
        }
        int cntOri;
        int cntTar;
        for (cntOri = 0; cntOri < currentType.length; cntOri++){
            if (currentType[cntOri].equals(origin.getValue()))
                break;
        }
        for (cntTar = 0; cntTar < currentType.length; cntTar++){
            if (currentType[cntTar].equals(target.getValue()))
                break;
        }
        origin.setValue(currentType[cntTar]);
        target.setValue(currentType[cntOri]);
    }

    public void conv(){
        if (origin.getValue() == null || target.getValue() == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please select currency type");
            alert.showAndWait();
            return;
        }

        String str = input.getText();
        boolean isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
        if (!isNumeric) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error!! Please try again.");
            alert.showAndWait();
            return;
        }


        double tmp = Double.parseDouble(str);
        int cntOri;
        int cntTar;
        for (cntOri = 0; cntOri < currentType.length; cntOri++){
            if (currentType[cntOri].equals(origin.getValue()))
                break;
        }
        for (cntTar = 0; cntTar < currentType.length; cntTar++){
            if (currentType[cntTar].equals(target.getValue()))
                break;
        }


        if (cntTar == currentType.length - 1){
            if (cntOri == cntTar){
                result.setText(Double.toString(tmp));
            }
            else {
                result.setText(Double.toString(data.get(cntOri) * tmp));
            }
        }
        else if (cntOri == currentType.length - 1 && cntTar != currentType.length){
            result.setText(Double.toString((1/data.get(cntTar)) * tmp));
        }
        else {
            double ratio = data.get(cntOri) / data.get(cntTar);
            result.setText(Double.toString(ratio * tmp));
        }
    }
}
