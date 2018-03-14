package funciones;

import java.util.ArrayList;

import base.Poblacion;
import base.Gen;

public class Funcion4 extends Poblacion{

	private double[] fitnessF1;
	public Funcion4(int longitud, double tolerancia, double min, double max) {

		super(longitud, tolerancia, max, min);

		this.fitnessF1 = new double[longitud];
		calcularFitness();
		setFitness(this.fitnessF1);
	}

	public void calcularFitness() {
		double[] fenotipo = getFenotipo();
		for (int i = 0; i < getLongitudPob(); i++) {
			this.fitnessF1[i] = 20 + Math.E - 20 * 
					Math.pow(Math.E, (-0.2 * Math.abs(fenotipo[i]))) - 
					Math.pow(Math.E, Math.cos(2 * Math.PI * fenotipo[i]));
		}
		setFitness(this.fitnessF1);
	}
}
