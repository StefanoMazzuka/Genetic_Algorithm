package base;
import java.util.ArrayList;

import base.Gen;


public abstract class Poblacion {
	
	//boolean poblacion;
	private ArrayList<Gen> poblacion; //cadena de bits (genotipo)
	private double[] fenotipo; //fenotipo
	//private double aptitud;//funci�n de evaluaci�n fitness adaptaci�n);
	//double puntuacion; //puntuaci�n relativa(aptitud/suma)
	//double punt_acum; //puntuaci�n acumulada para selecci�n
	int longitudPob; //longitud de poblaci�n 
	private double tolerancia; //tambien conocido como precision 
	double lgen;
	double max;
	double min;
	
	public Poblacion(/*double[] fenotipo, double puntuacion, double punt_acum, */
			int longitud, double tolerancia, double max, double min) {
		super();
		//this.fenotipo = fenotipo;
		//this.aptitud = aptitud;
		//this.puntuacion = puntuacion;
		//this.punt_acum = punt_acum;
		this.longitudPob = longitud;
		this.tolerancia = tolerancia;
		this.max = max;
		this.min = min;
		this.poblacion = new ArrayList<Gen>();
		
		crearPoblacion();
		calcularFenotipos();
	}

	public void longGen() {
		this.lgen = loga((1+((max-min) / this.tolerancia)), 2);
		this.lgen = Math.ceil(lgen);
	}
	static double loga(double x, double base) {
		return (Math.log(x) / Math.log(base));
	}
	public void calcularFenotipos() {
		this.fenotipo = new double[longitudPob];
		for (int i = 0; i < this.longitudPob; i++) {
			double valor = this.min + (this.max - this.min) * 
					bin_dec(this.poblacion.get(i)) / (Math.pow(2, this.lgen) - 1);
			this.fenotipo[i] = valor;
		}
		//setFenotipo(fenotipo);
	}
	private double bin_dec(Gen gen) {
		
		double dec = 0;
		boolean[] alelos = gen.getAlelos();
		for(int i = 0; i < lgen; i++) {
			if (alelos[i] == true) dec += Math.pow(2, i); 
		}
		
		return dec; 
	}
	public void crearPoblacion() {
		longGen();
		for (int i = 0; i < this.longitudPob; i++) {
			Gen gen = new Gen(lgen);
			this.poblacion.add(i, gen);
		}
	}
	
	/*Getters and Setters*/
	
	public double[] getFenotipo() {
		return this.fenotipo;
	}
	/*public void setFenotipo(double[] fenotipo) {
		this.fenotipo = fenotipo;
	}*/
	/*public double getAptitud() {
		return aptitud;
	}
	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}*/
	public ArrayList<Gen> getPoblacion() {
		return this.poblacion;
	}
	public void setPoblacion(ArrayList<Gen> poblacion) {
		this.poblacion = poblacion;
	}
	public double getTolerancia() {
		return tolerancia;
	}
	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}
	public int getLongitudPob() {
		return longitudPob;
	}
	public double getLgen() {
		return lgen;
	}
}
