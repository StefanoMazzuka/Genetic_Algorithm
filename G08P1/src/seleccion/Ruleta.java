package seleccion;

import java.util.Arrays;

public class Ruleta {	
	
	private double[] puntuacion;
	private double[] fitness;
	private double[] punt_acum;
	private int[] seleccionados;
	private double fitnessTotal;
	
	public Ruleta(double[] fitness) {
		this.puntuacion = new double[fitness.length];
		this.fitness = fitness;
		this.fitnessTotal = 0;
		this.punt_acum = new double[puntuacion.length + 1];
		Arrays.fill(this.punt_acum, 0);
		this.seleccionados = new int[fitness.length];
		Arrays.fill(this.seleccionados, 0);
		
		for (int i = 0; i < fitness.length; i++) {
			this.fitnessTotal += fitness[i];
		}
		calcularPuntuaciones();
	}
	
	private void calcularPuntuaciones() {		
		for (int i = 0; i < fitness.length; i++) {
			this.puntuacion[i] = fitness[i] / this.fitnessTotal;		
		}
		 
		double cont = 0;
		for (int i = 0; i < puntuacion.length; i++) {
			cont += puntuacion[i];
			this.punt_acum[i + 1] = cont;
		}
		
		double rand;
		for (int i = 0; i < fitness.length; i++) {
			int j = 0;
			rand = Math.random();
			while (rand > punt_acum[j]) {
				j++;
			}
			this.seleccionados[j - 1]++;
		}
	}
	
	public void showSeleccionados() {
		for (int i = 0; i < fitness.length; i++) {
			System.out.println(this.seleccionados[i]);
		}
	}
}
