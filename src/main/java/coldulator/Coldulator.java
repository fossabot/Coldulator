package coldulator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Coldulator extends Application{
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Coldulator");

    // Creating the main grid
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    primaryStage.setTitle("JavaFX Welcome");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
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

    Label tempIntL = new Label("Temperatura Interna: ");
    TextField tempIntT = new TextField();

    grid.addRow(8, tempEstL, tempEstT);
    grid.addRow(9, tempIntL, tempIntT);

    // Legend
    Label lPar = new Label("Parete");
    Label lAlt = new Label("Altezza (m)");
    Label lLun = new Label("Larghezza (m)");
    Label lSpess = new Label("Spessore (m)");
    Label lMat = new Label("Materiale");
    Label lCof = new Label("Coefficiente");
    lPar.setId("title");
    lAlt.setId("title");
    lLun.setId("title");
    lSpess.setId("title");
    lMat.setId("title");
    lCof.setId("title");
    grid.addRow(1, lPar, lAlt, lLun, lSpess, lMat, lCof);
    Label pLar = new Label("Lunghezza (m)");
    Label pLun = new Label("Larghezza (m)");
    /* Label noLab = new Label(""); */
    pLar.setId("title");
    pLun.setId("title");
    grid.addRow(7, new Label(""), pLar, pLun, new Label(""), new Label(""), new Label(""));


    // Data at the right side of the image
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
      materiale[i-1] = new ComboBox();
      coefficiente[i-1] = new TextField();
      grid.addRow(i+1, paretiL[i-1], altezza[i-1], larghezza[i-1], spessore[i-1], materiale[i-1], coefficiente[i-1]);
    }
    paretiL[4] = new Label("Soffitto");
    paretiL[5] = new Label("Pavimento");
    for (int i = 5; i <= 6; i++){
      altezza[i-1] = new TextField();
      larghezza[i-1] = new TextField();
      spessore[i-1] = new TextField();
      materiale[i-1] = new ComboBox();
      coefficiente[i-1] = new TextField();
      grid.addRow(i+3, paretiL[i-1], altezza[i-1], larghezza[i-1], spessore[i-1], materiale[i-1], coefficiente[i-1]);
    }

    Scene scene = new Scene(grid, 640, 480);
    scene.getStylesheets().add("/style.css");

    Scene scene = new Scene(grid, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();


  }
}