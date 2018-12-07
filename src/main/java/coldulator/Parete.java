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

package coldulator;

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

	    public Parete(double coefficiente, int tempInterna, int tempEsterna, double superficie, double spessore){
        this.superficieInfissi = 0;
        this.spessoreInfissi = 1;
        this.coeffInfissi = 0;

        this.coefficiente = coefficiente;
        this.tempInterna = tempInterna;
        this.tempEsterna = tempEsterna;
        this.deltaT = tempInterna - tempEsterna;
        this.superficie = superficie - superficieInfissi;
        this.spessore = spessore;
    }

    public double getDispersione(int t){
       return ((this.coefficiente * this.deltaT * this.superficie * t) / this.spessore) + ((this.coeffInfissi * this.deltaT * this.superficieInfissi * t) / this.spessoreInfissi) ;
    }
}
