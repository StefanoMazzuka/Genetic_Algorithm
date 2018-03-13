package funciones;

import java.util.ArrayList;

import base.Poblacion;
import base.Gen;

public class Funcion1 extends Poblacion{

	private Gen genMejor;
	private int genPeor;
	private double[] fitness;

	public Funcion1(int longitud, double tolerancia, double min, double max) {

		super(longitud, tolerancia, max, min);

		this.fitness = new double[longitud];
		calcularFitness();
		setFitness(this.fitness);
	}

	public void calcularFitness() {
		double[] fenotipo = getFenotipo();
		ArrayList<Gen> pob = getPoblacion();
		for (int i = 0; i < getLongitudPob(); i++) {
			this.fitness[i] = 20 + Math.E - 20 * 
					Math.pow(Math.E, (-0.2 * Math.abs(fenotipo[i]))) - 
					Math.pow(Math.E, Math.cos(2 * Math.PI * fenotipo[i]));
		}
	}

	public void showFitness() {
		for (int i = 0; i < fitness.length; i++) {
			System.out.println(this.fitness[i]);
		}
	}
}
