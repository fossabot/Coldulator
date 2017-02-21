/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coldulator;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Utils {
    public static double material(String a){
        double b;
        switch (a) {
            case "Acciaio 30% Nickel":
                b = 105;
                break;
            case "Aria":
                b = 0.03;
                break;
            case "Cartone":
                b = 0.23;
                break;
            case "Ghiaccio":
                b = 2.50;
                break;
            case "Legno asciutto":
                b = 0.27;
                break;
            case "Piombo solido":
                b = 35;
                break;
            case "Cemento armato":
                b = 2.3;
                break;
            case "Muratura di Pietrame":
                b = 2.4;
                break;
            default:
                b = 0;
                break;
        }
        return b;
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
        double[] superfici = {dimensioni[0]*dimensioni[1], dimensioni[0]*dimensioni[2], dimensioni[0]*dimensioni[1], dimensioni[0]*dimensioni[2], dimensioni[1]*dimensioni[2], dimensioni[1]*dimensioni[2]};
        ArrayList<Parete> paretiFinale = new ArrayList();
        Parete pareteUtile;
        for(int i = 0; i < 6; i++){
            pareteUtile = new Parete(material(materiali[i]), tempInterna, tempEsterna[i], superfici[i], spessori[i]);
            paretiFinale.add(pareteUtile);
        }
        return calcolaStanza(paretiFinale, tempo);
    }
}
