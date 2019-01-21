package coldulator.parete;

public class Edificio {
  private Parete[] pareti;
  private double tempInterna;
  private double tempEsterna;

  public Edificio(double coefficienti[], double tempInterna, double tempEsterna, double[] altezze, double[] larghezze, double[] spessori){
    this.pareti = new Parete[6];
    this.tempInterna = tempInterna;
    this.tempEsterna = tempEsterna;
    for (int i = 0; i < 6; i++){
      this.pareti[i] = new Parete(coefficienti[i], altezze[i]*larghezze[i], spessori[i]);
    }
  }

  public double getDispersione(int t){
    double dispersione = 0.00;
    for (int i = 0; i < 6; i++){
      dispersione += pareti[i].getDispersione(t, this.getDeltaT());
    }
    return dispersione;
  }

  public boolean isDisperso(){
    return this.tempInterna > this.tempEsterna;
  }

  public double getDeltaT(){
    return this.tempInterna - this.tempEsterna;
  }

}
