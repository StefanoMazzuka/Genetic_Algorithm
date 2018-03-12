package funciones;

import java.util.ArrayList;

import base.Poblacion;
import base.Gen;

public class Funcion1 extends Poblacion{

	//private boolean maximize;
	private double[] fitness;

	public Funcion1(/*double[] fenotipo, double aptitud, double puntuacion,
			double punt_acum, */int longitud, double tolerancia,/* double lgen, 
			boolean maximize,*/ double min, double max) {
		
		super(/*fenotipo, puntuacion, punt_acum, */longitud, tolerancia, max, min);
		
		//this.maximize = maximize;
		this.fitness = new double[longitud];
		calcularFitness(getFenotipo());
	}
	
	public void calcularFitness(double[] fenotipo) {
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
	
	public double[] getFitness() {
		return this.fitness;
	}
}
