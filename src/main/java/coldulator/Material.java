package coldulator;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Material {
  private static HashMap<String, Double> materialList;
  private static boolean wasDefined = false;
  private static String jsonFile = "";

  private static void generaListaMateriali(){
    System.out.println(new File("").getAbsolutePath());
  	HashMap<String, Double> ritornata = new HashMap<>();
  	try{
  		JsonArray matList = new JsonParser().parse(new JsonReader(new FileReader(jsonFile))).getAsJsonArray();
      if (matList.get(0).getAsJsonObject().get("nome").isJsonNull()) System.exit(1);
  		for (JsonElement m: matList) {
  			JsonObject mat = m.getAsJsonObject();
        ritornata.put(mat.get("nome").getAsString(), mat.get("coefficiente").getAsDouble());
  		}
  	}catch(FileNotFoundException e) {
    }
    materialList = ritornata;
    wasDefined = true;
  }

  public static HashMap<String, Double> getListaMateriali(){
    if (!wasDefined) generaListaMateriali();
    return materialList;
  }

  public static ObservableList<String> getListaNomiMateriali(){
    return FXCollections.observableList(new ArrayList<>(getListaMateriali().keySet()));
  }

  public static void setJsonFile(String s){
    jsonFile = s;
  }

}
