package seleccion;

import java.util.ArrayList;
import java.util.Random;

import base.AlgoritmoGenetico;
import base.Cromosoma;

public class Estocastico extends Seleccion {

	private double[] puntuacion;
	private double fitnessTotalPoblacion;
	
	
	
	@Override
	public void ejecutar(AlgoritmoGenetico ag) {
		// TODO Auto-generated method stub
		this.puntuacion = new double[ag.getlPoblacion()];
		this.fitnessTotalPoblacion = 0;
		
		ArrayList<Cromosoma> pob = ag.getPoblacion();
		ArrayList<Cromosoma> pobSeleccionada = new ArrayList<Cromosoma>();
		
		double N = pob.size();
		double distMarcas = 1 / N;
		double primeraMarca = 0.0;
		
		Random r = new Random();
		
		/*
		 * Calculamos el fitnes total
		 */
		for (int i = 0; i < ag.getlPoblacion(); i++) {
			this.fitnessTotalPoblacion += pob.get(i).getFitness();
		}
		
		/*
		 * Calculamos el fitness de cada idividuo entre el fitness total
		 */
		for (int i = 0; i < ag.getlPoblacion(); i++) {
			this.puntuacion[i] = pob.get(i).getFitness() / this.fitnessTotalPoblacion;
		}
		
		/*
		 * Calculamos el empiece de la primera marca
		 */
		primeraMarca = r.nextDouble() % distMarcas;
		
		/*
		 * Calculamos el resto de elementos que pertenecen a la poblacion
		 */
		
	}

}
