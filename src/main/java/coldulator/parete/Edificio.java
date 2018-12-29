package coldulator.parete;

public class Edificio {
  private Parete[] pareti;

  public Edificio(double coefficienti[], double tempInterna, double tempEsterna, double[] altezze, double[] larghezze, double[] spessori){
    this.pareti = new Parete[6];
    for (int i = 0; i < 6; i++){
      this.pareti[i] = new Parete(coefficienti[i], tempInterna, tempEsterna, altezze[i]*larghezze[i], spessori[i]);
    }
  }

  public double getDispersione(int t){
    double dispersione = 0.00;
    for (int i = 0; i < 6; i++){
      dispersione += pareti[i].getDispersione(t);
    }
    return dispersione;
  }

  public boolean isDisperso(){
    return pareti[0].isDisperso();
  }

  public double getDeltaT(){
    return pareti[0].getDeltaT();
  }

}
