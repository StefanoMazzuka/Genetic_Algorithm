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
	private double media;
	private double[] listaFitnessMejorAbsoluto;
	private double[] listaFitnessMejor;
	private double[] listaMedias;
	private int lCromosoma;
	private double porcentajeCruce;
	private double porcentajeMutacion;
	private int numeroGeneraciones;
	private boolean elitista;
	private double porcentajeEli;
	
	public AlgoritmoGenetico(int lPoblacion, double precision, double porcentajeCruce, 
			double porcentajeMutacion, int numeroGeneraciones, boolean elitista) {
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
		this.listaMedias = new double[this.numeroGeneraciones];
		Arrays.fill(this.listaFitnessMejorAbsoluto, 0.0);
		this.elitista = elitista;
	}

	public void ejecutarFuncion1() {
		crearPoblacionFuncion1();

		Ruleta r = new Ruleta();
		UnPunto p = new UnPunto(this.porcentajeCruce);
		Mutacion m = new Mutacion(this.porcentajeMutacion);
		
		for (int i = 0; i < this.numeroGeneraciones; i++) {
			r.ejecutarRuleta(this);
			p.cruzar(this);
			m.mutar(this);
			
			this.media = this.calcularMediaGeneracion();
			this.fitnessMejor = this.calcularFitnessMejor();
			this.listaMedias[i] = media;
			this.listaFitnessMejor[i] = this.fitnessMejor;
			this.listaFitnessMejorAbsoluto[i] = this.fitnessMejorAbsoluto;
			if (elitista) {
				ejecutarEli();
			}
		}
	}
	public void crearPoblacionFuncion1() {
		this.porcentajeEli = 1;
		Funcion1 f;
		for (int i = 0; i < lPoblacion; i++) {
			f = new Funcion1(this.precision);
			f.setId(i);
			this.poblacion.add(i, f);
		}
		this.lCromosoma = 1;
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
		double fitness;
		
		for (int i = 0; i < this.lPoblacion; i++) {
			fitness = this.poblacion.get(i).getFitnessTotalCromosoma();
			if (fitnessMejor < fitness)
				fitnessMejor = fitness;
		}
		
		if (fitnessMejorAbsoluto < fitnessMejor) 
			this.fitnessMejorAbsoluto = fitnessMejor;
		
		return fitnessMejor;
	}
	public double calcularMediaGeneracion() {
		double media = 0.00;
		double sumatorio = 0.00;
		double[] fitness;
		
		for (int i = 0; i < this.lPoblacion; i++) {
			fitness = this.poblacion.get(i).getFitness();
			sumatorio += fitness[0];
		}
		
		media = sumatorio / this.lPoblacion;
		
		return media;
	}
	
	public void ejecutarEli() {
		int numElegidos = (int) Math.round((this.porcentajeEli * this.lPoblacion));
		ordenar();
		
		Cromosoma c;
		for (int i = 0; i < numElegidos; i++) {
			c = this.poblacion.get(i).copy();
			this.poblacion.set((this.lPoblacion - 1) - i, c);
		}
		
		ordenar();
	}
	public void ordenar() {
		Cromosoma c1;
		Cromosoma c2;
		for (int i = 0; i < this.lPoblacion - 1; i++) {
			for (int j = i; j < this.lPoblacion; j++) {
				if (this.poblacion.get(i).getFitnessTotalCromosoma() <
						this.poblacion.get(j).getFitnessTotalCromosoma()) {
				c1 = this.poblacion.get(i).copy();
				c2 = this.poblacion.get(j).copy();
				
				this.poblacion.set(i, c2);
				this.poblacion.set(j, c1);
				}
			}
		}
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
		boolean elitista = this.elitista;

		AlgoritmoGenetico ag = new AlgoritmoGenetico(lPoblacion, precision, porcentajeCruce, 
				porcentajeMutacion, numeroGeneraciones, elitista);

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
	public double[] getListaMedias() {
		return listaMedias;
	}
	public void setListaMedias(double[] listaMedias) {
		this.listaMedias = listaMedias;
	}
	public boolean isElitista() {
		return elitista;
	}
	public void setElitista(boolean elitista) {
		this.elitista = elitista;
	}
}
