package mutacion;

import java.util.ArrayList;

import base.Gen;
import base.Poblacion;

public class Mutacion {
	private double probMutacion;
	
	public Mutacion(double probMutacion) {
		this.probMutacion = probMutacion;
	}
	
	
	public void mutar (Poblacion poblacion) {	
		
		boolean mutado;
		ArrayList<Gen> pob = new ArrayList<Gen>(poblacion.getPoblacion());
		boolean[] gen = new boolean[(int) poblacion.getLgen()];
		
		for (int i = 0; i < poblacion.getLongitudPob(); i++) {
			mutado = false;
			gen = pob.get(i).getAlelos();
			
			for (int j = 0; j < gen.length; j++) {
				double porbAuxiliar = Math.random();
				
				if (porbAuxiliar <= probMutacion) {
					if (gen[j] == true) gen[j] = false;
					else gen[j] = true;
					mutado = true;
				}
			}
			
			if (mutado) {
				System.out.println("Pos mutada: " + i);
				pob.get(i).setAlelos(gen);
			}
		}
		poblacion.setPoblacion(pob);
	}
}
