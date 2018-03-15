package base;
import java.util.ArrayList;

import base.Gen;


public abstract class Poblacion {
	
	private ArrayList<Gen> poblacion; //cadena de bits (genotipo)
	private double[] fenotipo; //fenotipo
	int longitudPob; //longitud de poblaci�n 
	private double tolerancia; //tambien conocido como precision 
	double lgen;
	double max;
	double min;
	private Gen genMejor;
	private int genPeor;
	private double[] fitness;
    private int MAXABSOLUTO = Integer.MAX_VALUE;
    private int posGenMejor;
    
	public Poblacion(int longitud, double tolerancia, double max, double min) {
		super();
		
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
	
	public void calcularGenMejor() {
		double mejorFitness = 0.00000000000000000000;
		int pos = 0;
		for (int i = 0; i < this.fitness.length; i++) {
			if (mejorFitness < this.fitness[i]) {
				mejorFitness = this.fitness[i];
				genMejor = this.poblacion.get(i);
				pos = i;
			}	
		}
		
		this.posGenMejor = pos;	
		System.out.print("EL FITNESS MEJOR ES: " + mejorFitness +", Pos: "+ this.posGenMejor + ", Gen: ");
		boolean[] x = genMejor.getAlelos();
		for (int j = 0; j < x.length; j++) {
			if(x[j] == true) System.out.print(1);
			else System.out.print(0);
		}
		System.out.println();
	}

	public void calcularGenPeor() {
		double peorFitness = MAXABSOLUTO;
		for (int i = 0; i < this.fitness.length; i++) {
			if (peorFitness > this.fitness[i]) {
				peorFitness = this.fitness[i];
				genPeor = i;
			}
		}
		System.out.println("EL FITNESS PEOR ES: " + peorFitness +", Pos: " + this.genPeor);
	}
	public void setGenMejor(Gen gen) {
		calcularGenPeor();
		System.out.println("Vamos a sustituir en: " + this.genPeor);
		this.poblacion.set(genPeor, gen);
	}
	public void showFitness() {
		for (int i = 0; i < this.fitness.length; i++) {
			System.out.println(this.fitness[i]);
		}
	}
	public void showPoblacion() {
		System.out.println("La poblacion es:");
		boolean[] alelos;
		for (int i = 0; i < this.longitudPob; i++) {
			alelos = this.poblacion.get(i).getAlelos();
			for (int j = 0; j < this.lgen; j++) {
				if (alelos[j] == true) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}
	
	/*Getters and Setters*/
	public double[] getFenotipo() {
		return this.fenotipo;
	}
	public ArrayList<Gen> getPoblacion() {
		return this.poblacion;
	}
	public void setPoblacion(ArrayList<Gen> poblacion) {
		this.poblacion = new ArrayList<Gen>(poblacion);
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
	public Gen getGenMejor() {
		return genMejor;
	}
	public void setFitness(double[] fitness) {
		this.fitness = fitness;
	}
	public double[] getFitness() {
		return fitness;
	}
	public int getPosGenMejor() {
		return this.posGenMejor;
	}
}
