package seleccion;

import java.util.ArrayList;
import java.util.Random;

import base.AlgoritmoGenetico;
import base.Cromosoma;

public class Ruleta extends Seleccion {	

	private double[] puntuacion;
	private double fitnessTotalPoblacion;
	
	@Override
	public void ejecutar(AlgoritmoGenetico ag) {
		// TODO Auto-generated method stub

		this.puntuacion = new double[ag.getlPoblacion()];
		this.fitnessTotalPoblacion = 0;
		
		ArrayList<Cromosoma> pob = ag.getPoblacion();
		ArrayList<Cromosoma> pobSeleccionada = new ArrayList<Cromosoma>();
		
		for (int i = 0; i < ag.getlPoblacion(); i++) {
			this.fitnessTotalPoblacion += pob.get(i).getFitnessTotalCromosoma();
		}
		
		for (int i = 0; i < ag.getlPoblacion(); i++) {
			this.puntuacion[i] = pob.get(i).getFitnessTotalCromosoma() / this.fitnessTotalPoblacion;
		}
		
		Random r = new Random();
		double probabilidad;
		double probAcumulada;
		for (int i = 0; i < ag.getlPoblacion(); i++) {
			probAcumulada = this.puntuacion[0];
			probabilidad = r.nextDouble() % 1;
			int j = 1;
			while (j < ag.getlPoblacion() && probabilidad > probAcumulada) {
				probAcumulada += this.puntuacion[j];
				j++;
			}
			pobSeleccionada.add(pob.get(j - 1).copy());
		}
	
		ag.setPoblacion(pobSeleccionada);
	}
	public void showPuntuacion() {
		System.out.println("La puntuacion son:");
		System.out.println("Fitnes total es: " + this.fitnessTotalPoblacion);
		for (int i = 0; i < this.puntuacion.length; i++) {
			System.out.println(this.puntuacion[i]);
		}
	}
	
	
}
