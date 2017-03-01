/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coldulator;

/**
 *
 * @author Admin
 */
public class Parete {
    public double coefficiente;
    public int tempInterna;
    public int tempEsterna;
    public int deltaT;
    public double superficie;
    public double spessore;
    
    public double superficieInfissi;
    public double spessoreInfissi;
    public double coeffInfissi;
    
    
    public Parete(double coefficiente, int tempInterna, int tempEsterna, double superficie, double spessore, double superficieInfissi, double spessoreInfissi, double coeffInfissi){
        this.superficieInfissi = superficieInfissi;
        this.spessoreInfissi = spessoreInfissi;
        this.coeffInfissi = coeffInfissi;
        
        this.coefficiente = coefficiente;
        this.tempInterna = tempInterna;
        this.tempEsterna = tempEsterna;
        this.deltaT = tempInterna - tempEsterna; 
        this.superficie = superficie - superficieInfissi;
        this.spessore = spessore;
    }
    
    public double calcDispersione(int t){
       return ((this.coefficiente * this.deltaT * this.superficie * t) / this.spessore) - ((this.coeffInfissi * this.deltaT * this.superficieInfissi * t) / this.spessoreInfissi) ;
    }
}
