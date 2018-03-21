package base;

import java.util.ArrayList;

import funciones.Funcion1;

public class AlgoritmoGenetico {
	
	private ArrayList<Cromosoma> poblacion;
	private int lPoblacion;
	private double precision;
	private double fitnessMejorAbsoluto;
	private int lCromosoma;

	public AlgoritmoGenetico(int lPoblacion, double precision) {
		this.lPoblacion = lPoblacion;
		this.poblacion = new ArrayList<Cromosoma>(this.lPoblacion);
		this.precision = precision;
		crearPoblacionFuncion1(precision);
	}
	
	public void crearPoblacionFuncion1(double precision) {
		Funcion1 f;
		for (int i = 0; i < lPoblacion; i++) {
			f = new Funcion1(precision);
			f.setId(i);
			this.poblacion.add(i, f);
		}
		this.lCromosoma = 1;
	}
	
	public void crearPoblacion2(double precision) {
		Funcion1 f;
		for (int i = 0; i < lPoblacion; i++) {
			f = new Funcion1(precision);
			this.poblacion.set(i, f);
		}
	}
	
	public void showPoblacion() {
		System.out.println("Poblacion:");
		double[] fenotipo;
		double[] fitness;
		boolean[] alelos;
		for (int i = 0; i < lPoblacion; i++) {
			fenotipo = this.poblacion.get(i).getFenotipo();
			fitness = this.poblacion.get(i).getFitness();
			System.out.println("Fenotipo: " + fenotipo[0] + " Fitness: " + fitness[0]);
			
			alelos = this.poblacion.get(i).gen[0].getAlelos();
			for (int j = 0; j < alelos.length; j++) {
				if (alelos[j]) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}

	public double getFitnessMejor() {
		double fitnessMejor = 0;
		double[] fitness;
		
		for (int i = 0; i < this.lPoblacion; i++) {
			fitness = this.poblacion.get(i).getFitness();
			if (fitnessMejor < fitness[0])
				fitnessMejor = fitness[0];
		}
		
		if (fitnessMejorAbsoluto < fitnessMejor) 
			this.fitnessMejorAbsoluto = fitnessMejor;
		
		return fitnessMejor;
	}

	public AlgoritmoGenetico copy() {
		ArrayList<Cromosoma> poblacion = this.poblacion;
		int lPoblacion = this.lPoblacion;
		double precision = this.precision;
		double fitnessMejorAbsoluto = this.fitnessMejorAbsoluto;
		
		AlgoritmoGenetico ag = new AlgoritmoGenetico(lPoblacion, precision);
		
		ag.setPoblacion(poblacion);
		ag.setlPoblacion(lPoblacion);
		ag.setPrecision(precision);
		ag.setFitnessMejorAbsoluto(fitnessMejorAbsoluto);
		
		return ag;
	}
	
	/*Getters and Setters*/
	public double getFitnessMejorAbsoluto() {
		return fitnessMejorAbsoluto;
	}
	
	public ArrayList<Cromosoma> getPoblacion() {
		return poblacion;
	}
	
	public void setPoblacion(ArrayList<Cromosoma> poblacion) {
		this.poblacion = poblacion;
	}

	public int getlPoblacion() {
		return lPoblacion;
	}

	public void setlPoblacion(int lPoblacion) {
		this.lPoblacion = lPoblacion;
	}
	
	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public void setFitnessMejorAbsoluto(double fitnessMejorAbsoluto) {
		this.fitnessMejorAbsoluto = fitnessMejorAbsoluto;
	}

	public int getlCromosoma() {
		return lCromosoma;
	}

	public void setlCromosoma(int lCromosoma) {
		this.lCromosoma = lCromosoma;
	}
}
