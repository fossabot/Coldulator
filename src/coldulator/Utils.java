/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coldulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.NumberFormatException;

/**
 *
 * @author Admin
 */
public class Utils {
    
  
  
    public static LinkedHashMap<String, Double> getMaterialMap(){
        LinkedHashMap<String, Double> map = new LinkedHashMap<String, Double>();
        map.put("Acciaio con 5% Ni", 29.00);
        map.put("Acciaio con 30% Ni", 105.00);
        map.put("Acqua (liquida in quiete a 20°C)", 0.63);
        map.put("Acqua pesante 10 - 100°C", 0.56);
        map.put("Alcool", 0.21);
        map.put("Alluminio", 210.00);
        map.put("Aria (in quiete a 20°C)", 0.03);
        map.put("Argentana", 27.00);
        map.put("Argento", 420.00);
        map.put("Asfalto", 0.64);
        map.put("Basalto", 1.27);
        map.put("Bronzo", 58.00);
        map.put("Carbone", 0.14);
        map.put("Carbone di storta", 4.00);
        map.put("Carbone in polvere", 0.12);
        map.put("Cartone", 0.14);
        map.put("Cartongesso in lastre", 0.21);
        map.put("Caucciù", 0.13);
        map.put("Celluloide", 0.35);
        map.put("Cellulosa compressa", 0.24);
        map.put("Cemento in polvere", 0.070);
        map.put("Cenere", 0.069);
        map.put("Creta", 0.90);
        map.put("Duralluminio", 160.00);
        map.put("Ferro elettrolitico", 87.00);
        map.put("Ferro ed acciaio", 46.5);
        map.put("Gesso", 0.4);
        map.put("Ghiaccio", 2.20);
        map.put("Ghisa", 50.00);
        map.put("Glicerina", 0.220);
        map.put("Grafite", 4.9);
        map.put("Granito", 3.18);
        map.put("Incrostazioni di caldaia", 1.16);
        map.put("Intonaco di calce e gesso", 0.70);
        map.put("Legno asciutto ortogonale alle fibre di abete e pino", 0.10);
        map.put("Legno asciutto ortogonale alle fibre di quercia", 0.18);
        map.put("Legno asciutto parallelamente alle fibre", 0.15);
        map.put("Linoleum", 0.18);
        map.put("Manganina", 23.00);
        map.put("Marmo", 2.1);
        map.put("Mercurio liquido a 0° C", 8.13);
        map.put("Mercurio liquido a 60° C", 9.64);
        map.put("Mercurio liquido a 120° C", 10.92);
        map.put("Mercurio liquido a 160° C", 11.6);
        map.put("Mercurio liquido a 222° C", 12.78);
        map.put("Mica", 0.39);
        map.put("Muratura di pietrame", 1.40);
        map.put("Muratura refrattaria (dinas. schamotte. silica) 200° C", 0.70);
        map.put("Muratura refrattaria (dinas. schamotte. silica) 1000° C", 1.2);
        map.put("Naftalina", 0.37);
        map.put("Neve (appena caduta e per strati fino a 3 cm)", 0.06);
        map.put("Neve (soffice. strati da 3 a 7 cm)", 0.12);
        map.put("Neve (moderatamente compatta. strati da 7 a 10 cm)", 0.23);
        map.put("Neve (compatta. strati da 20 a 40 cm)", 0.70);
        map.put("Nichel", 58.00);
        map.put("Oli e petroli", 0.12);
        map.put("Oro", 299.00);
        map.put("Ottone", 70.00);
        map.put("Pietra arenaria", 1.30);
        map.put("Pietra calcare compatta", 0.70);
        map.put("Pietra calcare granulosa", 0.95);
        map.put("Piombo solido", 35.00);
        map.put("Pb 44.5% + Bi 55.5% (lega liq.) 160 - 320° C", 9.2);
        map.put("Platino", 70.00);
        map.put("Porcellana", 0.80);
        map.put("Quarzo ortogonale all’asse", 6.60);
        map.put("Quarzo parallelo all’asse", 12.80);
        map.put("Quarzo oggetti fusi", 1.4);
        map.put("Rame (8300 Kg/m3)", 302.00);
        map.put("Rame (8900 Kg/m3)", 395.00);
        map.put("Sabbia asciutta", 0.35);
        map.put("Sabbia con 7% di umidità", 1.16);
        map.put("Sodio solido", 125.60);
        map.put("Sodio liquido 100 - 500° C", 86.00);
        map.put("Stagno", 64.00);
        map.put("Sughero (200 Kg/m3)", 0.052);
        map.put("Vetro", 0.5);
        map.put("Zinco", 110.00);
        map.put("Zolfo", 0.23);
        return map;
    }
    
    public static ArrayList<String> getMaterialList(){
        ArrayList<String> keys = new ArrayList<String>();
        for(Map.Entry<String, Double> entry : getMaterialMap().entrySet()) {
            String key = entry.getKey();
            keys.add(key);
        }
        return keys;
    }
    
    public static double material(String a){
        return getMaterialMap().get(a);
    }
    
    public static int max(int a, int b){
        if (a > b){
            return a;
        }else{
            return b;
        }
    }
    
    public static int min(int a, int b){
        if (a < b){
            return a;
        }else{
            return b;
        }
    }
    
    public static String calcolaStanza(ArrayList<Parete> Pareti, int tempo){
        String s;
        double total = 0;
        double totalInst = 0;
        for (Parete pareteNuova: Pareti){
            total = total + pareteNuova.calcDispersione(tempo);
            totalInst = totalInst + pareteNuova.calcDispersione(1);
        }
        s = "La dispersione totale in " + Integer.toString(tempo) + " secondi è uguale a " + Double.toString(total/1000) + " kJoule (" + Double.toString(totalInst/1000) + " kWatt su secondo)";
        return s;
    }
    
    public static String ottieniRisultato(double dimensioni[], int tempInterna, int tempo, String materiali[], double spessori[], int tempEsterna[]){
        double[] superfici = {dimensioni[0]*dimensioni[1], dimensioni[0]*dimensioni[1], dimensioni[0]*dimensioni[2], dimensioni[0]*dimensioni[2], dimensioni[1]*dimensioni[2], dimensioni[1]*dimensioni[2]};
        ArrayList<Parete> paretiFinale = new ArrayList();
        Parete pareteUtile;
        for(int i = 0; i < 6; i++){
            pareteUtile = new Parete(material(materiali[i]), tempInterna, tempEsterna[i], superfici[i], spessori[i]);
            paretiFinale.add(pareteUtile);
        }
        return calcolaStanza(paretiFinale, tempo);
    }
    
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static double parseAtAnyCost(String o){
        if (isDouble(o)){
            return Double.parseDouble(o);
        } else if (isInt(o)){
            return (double) Integer.parseInt(o);
        } else {
            throw new NumberFormatException();
        }
    }
}
