package mutacion;

import java.util.ArrayList;

import base.Gen;
import base.Poblacion;

public class Mutacion {
	public Mutacion(double probMutacion, Poblacion poblacion) {
		boolean mutado;
		ArrayList<Gen> pob = poblacion.getPoblacion();
		for (int i = 0; i < poblacion.getLongitudPob(); i++) {
			mutado = false;
			boolean[] gen = pob.get(i).getAlelos();
			
			for (int j = 0; j < gen.length; j++) {
				double porbAuxiliar = Math.random();
				
				if (porbAuxiliar <= probMutacion) {
					if (gen[j]) gen[j] = false;
					else gen[j] = true;
					mutado = true;
				}
			}
			
			if (mutado) {
				System.out.println("El gen mutado es: " + (i + 1));
				pob.get(i).setAlelos(gen);
			}
		}
		poblacion.setPoblacion(pob);
	}
}
