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

package coldulator.parete;

public class Parete {
    private double coefficiente;
    private double superficieTotale;
    private double spessore;

    private double superficieInfissi;
    private double spessoreInfissi;
    private double coeffInfissi;


    public Parete(double coefficiente, double superficieTotale, double spessore, double superficieInfissi, double spessoreInfissi, double coeffInfissi){
        this.superficieInfissi = superficieInfissi;
        this.spessoreInfissi = spessoreInfissi;
        this.coeffInfissi = coeffInfissi;

        this.coefficiente = coefficiente;
        this.superficieTotale = superficieTotale;
        this.spessore = spessore;
    }

    public Parete(double coefficiente, double superficieTotale, double spessore){
      this.superficieInfissi = 0;
      this.spessoreInfissi = 1;
      this.coeffInfissi = 0;

      this.coefficiente = coefficiente;
      this.superficieTotale = superficieTotale;
      this.spessore = spessore;
    }

    private double getSuperficie(){
      return this.superficieTotale - this.superficieInfissi;
    }

    public double getDispersione(int t, double deltaT){
      return ((this.coefficiente * deltaT * this.getSuperficie() * t) / this.spessore) +
             ((this.coeffInfissi * deltaT * this.superficieInfissi * t) / this.spessoreInfissi) ;
    }
}
