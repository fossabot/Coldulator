package coldulator;

public class MiscUtils{
  public static boolean isNumeric(String str){
    try{
      Double.parseDouble(str);
    }
    catch(NumberFormatException e){
      return false;
    }
    return true;
  }
  public static boolean isInt(String str){
    try{
      Integer.parseInt(str);
    }
    catch(NumberFormatException e){
      return false;
    }
    return true;
  }
}
