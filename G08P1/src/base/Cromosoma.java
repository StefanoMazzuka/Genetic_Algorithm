package base;

import funciones.Funcion1;

public abstract class Cromosoma {
	public Gen[] gen;
	public double precision;
	public double[] fenotipo;
	public double[] fitness;
	public double fitnessTotalCromosoma;
	public int lCromosoma;
		
	public Cromosoma() {}
	
	public Cromosoma(double precision) {
		this.precision = precision;
	}
	
	protected double bin_dec(Gen gen) {		
		double dec = 0;
		boolean[] alelos = gen.getAlelos();
		for(int i = 0; i < lCromosoma; i++) {
			if (alelos[i] == true) dec += Math.pow(2, i); 
		}
		
		return dec; 
	}
	
	public Funcion1 copy() {
		Gen[] gen = new Gen[1];
		gen[0] = this.gen[0].copy();
	
		double precision = this.precision;
		double[] fenotipo = this.fenotipo;
		double[] fitness = this.fitness;
		double fitnessTotalCromosoma = this.fitnessTotalCromosoma;
		int lCromosoma = this.lCromosoma;
		
		Funcion1 f = new Funcion1(this.precision);
		
		f.setGen(gen);
		f.setPrecision(precision);
		f.setFenotipo(fenotipo);
		f.setFitness(fitness);
		f.setFitnessTotalCromosoma(fitnessTotalCromosoma);
		f.setlCromosoma(lCromosoma);
		
		return f;
	}
	
	public void calcularFitnessTotal() {
		double fitnessTotalCromosoma = 0;
		for (int i = 0; i < this.fitness.length; i++) {
			fitnessTotalCromosoma += this.fitness[i];
		}
		this.fitnessTotalCromosoma = fitnessTotalCromosoma;
	}
	
	/*Getters y Setters*/
	public Gen[] getGen() {
		return gen;
	}

	public void setGen(Gen[] gen) {
		this.gen = gen;
	}

	public double[] getFitness() {
		return fitness;
	}

	public void setFitness(double[] fitness) {
		this.fitness = fitness;
	}

	public double[] getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(double[] fenotipo) {
		this.fenotipo = fenotipo;
	}

	public int getlCromosoma() {
		return lCromosoma;
	}

	public void setlCromosoma(int lCromosoma) {
		this.lCromosoma = lCromosoma;
	}

	public double getFitnessTotalCromosoma() {
		return fitnessTotalCromosoma;
	}

	public void setFitnessTotalCromosoma(double fitnessTotalCromosoma) {
		this.fitnessTotalCromosoma = fitnessTotalCromosoma;
	}
	
	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}
}
