package seleccion;

import java.util.ArrayList;
import java.util.Arrays;

import base.Gen;
import base.Poblacion;

public class Ruleta {	
	
	private ArrayList<Gen> pobSeleccionada;
	private ArrayList<Gen> pobInicial;
	private double[] puntuacion;
	private double[] fitnessR;
	private double fitnessTotal;
	private double[] punt_acum;
	private int[] seleccionados;
	
	public Ruleta() {
		
	}
	
	public void ejecutarRuleta(Poblacion pob) {
		
		this.pobSeleccionada = new ArrayList<Gen>();
		this.pobInicial = pob.getPoblacion();
		this.puntuacion = new double[pob.getLongitudPob()];
		this.fitnessTotal = 0;
		this.punt_acum = new double[pob.getLongitudPob() + 1];
		Arrays.fill(this.punt_acum, 0);
		this.seleccionados = new int[pob.getLongitudPob()];
		Arrays.fill(this.seleccionados, 0);
		
		this.fitnessR = pob.getFitness();
		for (int i = 0; i < pob.getLongitudPob(); i++) {
			this.fitnessTotal += this.fitnessR[i];
		}
		
		calcularPuntuaciones();
		
		for (int i = 0; i < pob.getLongitudPob(); i++) {
			if (this.seleccionados[i] != 0) {
				for (int j = 0; j < this.seleccionados[i]; j++) {
					this.pobSeleccionada.add(pobInicial.get(i));
				}
			}
		}
		
		pob.setPoblacion(pobSeleccionada);
	}
	
	private void calcularPuntuaciones() {		
		for (int i = 0; i < this.fitnessR.length; i++) {
			this.puntuacion[i] = this.fitnessR[i] / this.fitnessTotal;		
		}
		 
		double cont = 0;
		for (int i = 0; i < this.puntuacion.length; i++) {
			cont += this.puntuacion[i];
			this.punt_acum[i + 1] = cont;
		}
		
		double rand;
		for (int i = 0; i < this.fitnessR.length; i++) {
			int j = 0;
			rand = Math.random();
			while (rand > this.punt_acum[j]) {
				j++;
			}
			this.seleccionados[j - 1]++;
		}
	}
	
	public void showSeleccionados() {
		for (int i = 0; i < this.fitnessR.length; i++) {
			System.out.println(this.seleccionados[i]);
		}
	}
}
