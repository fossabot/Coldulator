// Copyright (c) 2018 Zekromaster
//
// GNU GENERAL PUBLIC LICENSE
//    Version 3, 29 June 2007
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package coldulator;

import coldulator.parete.Edificio;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coldulator extends Application{
  public static void main(String[] args) {
    launch(args);
  }

  private static void errorOut(String error){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Errore!");
    alert.setHeaderText("Errore!");
    alert.setContentText(error);
    alert.showAndWait();
  }

  private static void output(String str){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("");
    alert.setHeaderText("");
    alert.setContentText(str);
    alert.showAndWait();
  }

  private static void readJson(Stage primaryStage){
    // Apertura del file materials.json
    int n = 0;
    for(;;n++){
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Seleziona un file json");
      fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
      fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File", "*.json"));
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        Material.setJsonFile(file.getAbsolutePath());
        break;
      }else{
        if (n>=2){
          System.exit(2);
        }
      }
    }
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Coldulator");
    // Creating the main grid
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    // Creating the Image
    Image scheme = new Image("/dimensions.png", true);
    ImageView showScheme = new ImageView();
    showScheme.setImage(scheme);
    showScheme.setCache(true);
    grid.add(showScheme, 0, 1, 2, 7);

    // Temperatures under the Image
    Label tempEstL = new Label("Temperatura Esterna: ");
    TextField tempEstT = new TextField();
    tempEstT.setText("Gradi Celsius o Kelvin");

    Label tempIntL = new Label("Temperatura Interna: ");
    TextField tempIntT = new TextField();
    tempIntT.setText("Gradi Celsius o Kelvin");

    Label tempoL = new Label("Tempo (s): ");
    TextField tempoT = new TextField();

    grid.addRow(8, tempEstL, tempEstT);
    grid.addRow(9, tempIntL, tempIntT);
    grid.addRow(10, tempoL, tempoT);

    // Legend
    Label lPar = new Label("Parete");
    Label lAlt = new Label("Altezza (m)");
    Label lLun = new Label("Larghezza (m)");
    Label lSpess = new Label("Spessore (m)");
    Label lMat = new Label("Materiale");
    Label lCof = new Label("Coefficiente (W/m*K)");
    lPar.setId("title");
    lAlt.setId("title");
    lLun.setId("title");
    lSpess.setId("title");
    lMat.setId("title");
    lCof.setId("title");
    grid.addRow(1, lPar, lAlt, lLun, lSpess, lMat, lCof);
    Label pLar = new Label("Lunghezza (m)");
    Label pLun = new Label("Larghezza (m)");
    pLar.setId("title");
    pLun.setId("title");
    grid.addRow(7, new Label(""), pLar, pLun, new Label(""), new Label(""), new Label(""));

    // Data at the right side of the image
    readJson(primaryStage);
    Label[] paretiL = new Label[6];
    TextField[] altezza = new TextField[6];
    TextField[] larghezza = new TextField[6];
    TextField[] spessore = new TextField[6];
    ComboBox[] materiale = new ComboBox[6];
    TextField[] coefficiente = new TextField[6];
    for (int i = 1; i <= 4; i++){
      paretiL[i-1] = new Label("Parete " + i);
      altezza[i-1] = new TextField();
      larghezza[i-1] = new TextField();
      spessore[i-1] = new TextField();
      materiale[i-1] = new ComboBox<String>(Material.getListaNomiMateriali());
      coefficiente[i-1] = new TextField();
      grid.addRow(i+1, paretiL[i-1], altezza[i-1], larghezza[i-1], spessore[i-1], materiale[i-1], coefficiente[i-1]);
    }
    paretiL[4] = new Label("Soffitto");
    paretiL[5] = new Label("Pavimento");
    for (int i = 5; i <= 6; i++){
      altezza[i-1] = new TextField();
      larghezza[i-1] = new TextField();
      spessore[i-1] = new TextField();
      materiale[i-1] = new ComboBox<String>(Material.getListaNomiMateriali());
      coefficiente[i-1] = new TextField();
      grid.addRow(i+3, paretiL[i-1], altezza[i-1], larghezza[i-1], spessore[i-1], materiale[i-1], coefficiente[i-1]);
    }

    for (int i = 0; i < 6; i++){
      coefficiente[i].setDisable(true);
      int a = i;
      materiale[i].valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String vecchioValore, String nuovoValore) {
          coefficiente[a].setText(Material.getListaMateriali().get(nuovoValore).toString());
        }
      });
      materiale[i].getSelectionModel().selectFirst();
    }

    // Bottone per Calcolare
    Button calcola = new Button("Calcola");
    GridPane.setHalignment(calcola, HPos.CENTER);
    grid.add(calcola, 2, 10, 6, 1);
    calcola.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    calcola.setOnAction(new EventHandler<ActionEvent>(){

      @Override
      public void handle(ActionEvent event) {
        double[] h = new double[6];
        double[] l = new double[6];
        double[] s = new double[6];
        double[] c = new double[6];
        double tempInt, tempEst;
        int t;
        // Sanitizzazione degli input
        // Sanitizzazione delle altezze
        for (int i = 0; i < 6; i++){
          String str = altezza[i].getText();
          if (!MiscUtils.isNumeric(str)) {
            errorOut("Sicuro che " + str + " (Parete numero " + i + ") sia un numero?");
            return;
          }else{
            h[i] = Double.parseDouble(str);
          }
        }
        // Sanitizzazione delle lunghezze/larghezze
        for (int i = 0; i < 6; i++){
          String str = larghezza[i].getText();
          if (!MiscUtils.isNumeric(str)) {
            errorOut("Sicuro che " + str + " (Parete numero " + i + ") sia un numero?");
            return;
          }else{
            l[i] = Double.parseDouble(str);
          }
        }
        // Sanitizzazione dello Spessore
        for (int i = 0; i < 6; i++){
          String str = spessore[i].getText();
          if (!MiscUtils.isNumeric(str)) {
            errorOut("Sicuro che " + str + " (Parete numero " + i + ") sia un numero?");
            return;
          }else{
            s[i] = Double.parseDouble(str);
          }
        }
        // Sanitizzazione delle due temperature
        String tmp = tempIntT.getText();
        if (!MiscUtils.isNumeric(tmp)) {
          errorOut("Sicuro che " + tmp + " (Temperatura Interna) sia un numero?");
          return;
        }else{
          tempInt = Double.parseDouble(tmp);
        }
        tmp = tempEstT.getText();
        if (!MiscUtils.isNumeric(tmp)) {
          errorOut("Sicuro che " + tmp + " (Temperatura Esterna) sia un numero?");
          return;
        }else{
          tempEst = Double.parseDouble(tmp);
        }
        // Sanitizzazione del Tempo
        tmp = tempoT.getText();
        if (!MiscUtils.isInt(tmp)) {
          errorOut("Sicuro che " + tmp + " (Tempo) sia un numero?");
          return;
        }else{
          t = Integer.parseInt(tmp);
        }
        // Lettura dei coefficienti
        for (int i = 0; i < 6; i++){
          c[i] = Double.parseDouble(coefficiente[i].getText());
        }

        // Creazione dell'Edificio
        Edificio ed = new Edificio(c, tempInt, tempEst, h, l, s);

        // Output
        output("La dissipazione del calore in una stanza quale quella descritta è " + ed.getDispersione(t) + "J");
      }
    });

    // Creating the Scene
    Scene scene = new Scene(grid);
    scene.getStylesheets().add("/style.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
