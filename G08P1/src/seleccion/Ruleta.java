package seleccion;

import java.util.ArrayList;
import java.util.Arrays;

import base.Gen;
import base.Poblacion;

public class Ruleta {	
	
	private ArrayList<Gen> pobSeleccionada;
	private ArrayList<Gen> pobInicial;
	private double[] puntuacion;
	private double[] fitness;
	private double[] punt_acum;
	private int[] seleccionados;
	private double fitnessTotal;
	
	public Ruleta(Poblacion poblacion, double[] fitness) {
		this.pobInicial = poblacion.getPoblacion();
		this.pobSeleccionada = new ArrayList<Gen>();
		this.puntuacion = new double[poblacion.getLongitudPob()];
		this.fitness = fitness;
		this.fitnessTotal = 0;
		this.punt_acum = new double[poblacion.getLongitudPob() + 1];
		Arrays.fill(this.punt_acum, 0);
		this.seleccionados = new int[poblacion.getLongitudPob()];
		Arrays.fill(this.seleccionados, 0);
		
		for (int i = 0; i < poblacion.getLongitudPob(); i++) {
			this.fitnessTotal += fitness[i];
		}
		
		calcularPuntuaciones();
		
		for (int i = 0; i < poblacion.getLongitudPob(); i++) {
			if (this.seleccionados[i] != 0) {
				for (int j = 0; j < this.seleccionados[i]; j++) {
					this.pobSeleccionada.add(pobInicial.get(i));
				}
			}
		}
		
		poblacion.setPoblacion(pobSeleccionada);
	}
	
	private void calcularPuntuaciones() {		
		for (int i = 0; i < this.fitness.length; i++) {
			this.puntuacion[i] = this.fitness[i] / this.fitnessTotal;		
		}
		 
		double cont = 0;
		for (int i = 0; i < this.puntuacion.length; i++) {
			cont += this.puntuacion[i];
			this.punt_acum[i + 1] = cont;
		}
		
		double rand;
		for (int i = 0; i < this.fitness.length; i++) {
			int j = 0;
			rand = Math.random();
			while (rand > this.punt_acum[j]) {
				j++;
			}
			this.seleccionados[j - 1]++;
		}
	}
	
	public void showSeleccionados() {
		for (int i = 0; i < this.fitness.length; i++) {
			System.out.println(this.seleccionados[i]);
		}
	}
}
