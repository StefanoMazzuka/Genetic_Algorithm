package base;

import java.util.ArrayList;

import cruce.UnPunto;
import funciones.Funcion1;
import mutacion.Mutacion;
import seleccion.Ruleta;
import java.util.Arrays;

public class AlgoritmoGenetico {

	private ArrayList<Cromosoma> poblacion;
	private int lPoblacion;
	private double precision;
	private double fitnessMejorAbsoluto;
	private double fitnessMejor;
	private double[] listaFitnessMejorAbsoluto;
	private double[] listaFitnessMejor;
	private int lCromosoma;
	private double porcentajeCruce;
	private double porcentajeMutacion;
	private int numeroGeneraciones;

	public AlgoritmoGenetico(int lPoblacion, double precision, double porcentajeCruce, 
			double porcentajeMutacion, int numeroGeneraciones) {
		this.lPoblacion = lPoblacion;
		this.poblacion = new ArrayList<Cromosoma>(this.lPoblacion);
		this.precision = precision;
		this.porcentajeCruce = porcentajeCruce;
		this.porcentajeMutacion = porcentajeMutacion;
		this.numeroGeneraciones = numeroGeneraciones;
		this.listaFitnessMejorAbsoluto = new double[this.numeroGeneraciones];
		Arrays.fill(this.listaFitnessMejorAbsoluto, 0.0);
		this.listaFitnessMejor = new double[this.numeroGeneraciones];
		Arrays.fill(this.listaFitnessMejor, 0.0);
	}

	public void ejecutarFuncion1() {
		crearPoblacionFuncion1();

		Ruleta r = new Ruleta();
		UnPunto p = new UnPunto(this.porcentajeCruce);
		Mutacion m = new Mutacion(this.porcentajeMutacion);
		
		for (int i = 0; i < this.numeroGeneraciones; i++) {
//			this.showPoblacion();
//			System.out.println();
//			System.out.println("Fitnes Mejor: " + this.getFitnessMejor());
//			System.out.println("Fitnes Mejor Absoluto: " + this.getFitnessMejorAbsoluto());
//			System.out.println();
			r.ejecutarRuleta(this);
//			r.showPuntuacion();
//			System.out.println("Seleccionada:");
//			this.showPoblacion();
//			System.out.println();
			p.cruzar(this);
//			p.showPoblacionACruzar();
//			System.out.println();
//			System.out.println("Poblacion Cruzada:");
//			this.showPoblacion();
			m.mutar(this);
//			System.out.println();
//			System.out.println("Poblacion Mutada:");
//			this.showPoblacion();
			this.fitnessMejor = this.calcularFitnessMejor();
			this.listaFitnessMejor[i] = this.fitnessMejor;
			this.listaFitnessMejorAbsoluto[i] = this.fitnessMejorAbsoluto;
		}
	}

	public void crearPoblacionFuncion1() {
		Funcion1 f;
		for (int i = 0; i < lPoblacion; i++) {
			f = new Funcion1(this.precision);
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

	public double calcularFitnessMejor() {
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
		int lCromosoma = this.lCromosoma;
		double porcentajeCruce = this.porcentajeCruce;
		double porcentajeMutacion = this.porcentajeMutacion;
		int numeroGeneraciones = this.numeroGeneraciones;

		AlgoritmoGenetico ag = new AlgoritmoGenetico(lPoblacion, precision, porcentajeCruce, 
				porcentajeMutacion, numeroGeneraciones);

		ag.setPoblacion(poblacion);
		ag.setlPoblacion(lPoblacion);
		ag.setPrecision(precision);
		ag.setFitnessMejorAbsoluto(fitnessMejorAbsoluto);
		ag.setlCromosoma(lCromosoma);
		ag.setPorcentajeCruce(porcentajeCruce);
		ag.setPorcentajeMutacion(porcentajeMutacion);
		ag.setNumeroGeneraciones(numeroGeneraciones);

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

	public double getFitnessMejor() {
		return fitnessMejor;
	}

	public void setFitnessMejor(double fitnessMejor) {
		this.fitnessMejor = fitnessMejor;
	}

	public double[] getListaFitnessMejorAbsoluto() {
		return listaFitnessMejorAbsoluto;
	}

	public void setListaFitnessMejorAbsoluto(double[] listaFitnessMejorAbsoluto) {
		this.listaFitnessMejorAbsoluto = listaFitnessMejorAbsoluto;
	}

	public double[] getListaFitnessMejor() {
		return listaFitnessMejor;
	}

	public void setListaFitnessMejor(double[] listaFitnessMejor) {
		this.listaFitnessMejor = listaFitnessMejor;
	}
	
	public int getlCromosoma() {
		return lCromosoma;
	}

	public void setlCromosoma(int lCromosoma) {
		this.lCromosoma = lCromosoma;
	}

	public double getPorcentajeCruce() {
		return porcentajeCruce;
	}

	public void setPorcentajeCruce(double porcentajeCruce) {
		this.porcentajeCruce = porcentajeCruce;
	}

	public double getPorcentajeMutacion() {
		return porcentajeMutacion;
	}

	public void setPorcentajeMutacion(double porcentajeMutacion) {
		this.porcentajeMutacion = porcentajeMutacion;
	}
	
	public int getNumeroGeneraciones() {
		return numeroGeneraciones;
	}

	public void setNumeroGeneraciones(int numeroGeneraciones) {
		this.numeroGeneraciones = numeroGeneraciones;
	}
}
