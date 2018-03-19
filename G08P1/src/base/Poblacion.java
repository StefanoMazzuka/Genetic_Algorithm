package base;
import java.util.ArrayList;

import base.Gen;


public abstract class Poblacion {
	
	private ArrayList<Gen> poblacion; //cadena de bits (genotipo)
	private double[] fenotipo; //fenotipo
	int longitudPob; //longitud de poblaci�n 
	private double tolerancia; //tambien conocido como precision 
	int lgen;
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
		this.lgen = (int) Math.ceil(loga((1 + ((max-min) / this.tolerancia)), 2));
	}
	static double loga(double x, int base) {
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
	public Gen calcularGenMejor() {
//		double mejorFitness = 0.00000000000000000000;
//		int pos = 0;
//		for (int i = 0; i < this.fitness.length; i++) {
//			if (mejorFitness < this.fitness[i]) {
//				mejorFitness = this.fitness[i];
//				genMejor = this.poblacion.get(i);
//				pos = i;
//			}	
//		}
		
		ordenar();
		System.out.print("EL FITNESS MEJOR ES: " + this.fitness[0]);
		return this.poblacion.get(0).copy();
		
//			
//		System.out.print("EL FITNESS MEJOR ES: " + mejorFitness +", Pos: "+ this.posGenMejor + ", Gen: ");
//		boolean[] x = genMejor.getAlelos();
//		for (int j = 0; j < x.length; j++) {
//			if(x[j] == true) System.out.print(1);
//			else System.out.print(0);
//		}
////		System.out.println();
//		return this.genMejor;
	}
	public void calcularGenMejorEli(double porcentageEli) {
		int numElegidos = (int)Math.round((porcentageEli * this.longitudPob));
		ordenar();
		
		for (int i = 0; i < numElegidos; i++) {
			this.poblacion.set((this.longitudPob - 1) - i, this.poblacion.get(i));
		}
		
		ordenar();
	}
/*	public void calcularGenPeor() {
		double peorFitness = MAXABSOLUTO;
		for (int i = 0; i < this.fitness.length; i++) {
			if (peorFitness > this.fitness[i]) {
				peorFitness = this.fitness[i];
				genPeor = i;
			}
		}
//		System.out.println("EL FITNESS PEOR ES: " + peorFitness +", Pos: " + this.genPeor);
	}
*/
	public void setGenMejor(Gen gen) {
//		System.out.println("Vamos a sustituir en: " + this.genPeor);
		ordenar();
		this.poblacion.set(this.longitudPob-1, gen);
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
	public void ordenar() {
		double fen;
		double fit;
		Gen pob;
		for (int i = 0; i < this.longitudPob - 1; i++) {
			for (int j = i; j < this.longitudPob; j++) {
				if (this.fitness[i] < this.fitness[j]) {
				fen = this.fenotipo[i];
				fit = this.fitness[i];
				pob = this.poblacion.get(i);
				
				this.fenotipo[i] = this.fenotipo[j];
				this.fitness[i] = this.fitness[j];
				this.poblacion.set(i, this.poblacion.get(j));
				
				this.fenotipo[j] = fen;
				this.fitness[j] = fit;
				this.poblacion.set(j, pob);
				}
			}
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
