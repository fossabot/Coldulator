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
    
    public Parete(double a, int b, int c, double d, double e){
        this.coefficiente = a;
        this.tempInterna = b;
        this.tempEsterna = c;
        this.deltaT = c- b;
        this.superficie = d;
        this.spessore = e;
    }
    
    public double calcDispersione(int t){
        double dispersione;
        dispersione = (this.coefficiente * this.deltaT * this.superficie*t)/this.spessore;
        return dispersione;
    }
}
