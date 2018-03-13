package funciones;

import java.util.ArrayList;

import base.Poblacion;
import base.Gen;

public class Funcion1 extends Poblacion{

	//private boolean maximize;
	private Gen genMejor;
	private Gen genPeor;

	private double[] fitness;

	public Funcion1(int longitud, double tolerancia, double min, double max) {

		super(longitud, tolerancia, max, min);

		this.fitness = new double[longitud];
		calcularFitness();
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

	public void calcularGenMejor() {
		double mejorFitness = 0;
		ArrayList<Gen> pob = getPoblacion();
		for (int i = 0; i < this.fitness.length; i++) {
			if (mejorFitness < this.fitness[i]) {
				mejorFitness = this.fitness[i];
				genMejor = pob.get(i);
			}	
		}
		System.out.println("EL FITNESS MEJOR ES:   " + mejorFitness);
	}

	public void calcularGenPeor() {
		double peorFitness = this.fitness[0];
		ArrayList<Gen> pob = getPoblacion();
		for (int i = 0; i < this.fitness.length; i++) {
			if (peorFitness > this.fitness[i]) {
				peorFitness = this.fitness[i];
				genPeor = pob.get(i);
			}
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
	public Gen getGenMejor() {
		return this.genMejor;
	}
	public Gen getGenPeor() {
		return this.genPeor;
	}
	public void setGenMejor(Gen gen) {
		calcularGenPeor();
		ArrayList<Gen> pob = getPoblacion();
		pob.remove(this.genPeor);
		pob.add(gen);
		setPoblacion(pob);
	}
}
