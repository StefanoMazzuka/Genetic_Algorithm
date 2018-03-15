package cruce;

import java.util.ArrayList;
import java.util.Arrays;

import base.Gen;
import base.Poblacion;

public class UnPunto {

	private double pCruce;
	private int longitudGen;
	private boolean[] individuosACruzar;
	private ArrayList<Gen> poblacion;
	private ArrayList<Gen> poblacionACruzar;
	private ArrayList<Gen> poblacionCruzada;

	public UnPunto(double pCruce) {
		this.pCruce = pCruce;
	}

	public void cruzar(Poblacion pob) {
		
		this.longitudGen = (int) pob.getLgen();	
		this.poblacion = pob.getPoblacion();
		this.individuosACruzar = new boolean[pob.getLongitudPob()];
		this.poblacionACruzar = new ArrayList<Gen>();
		this.poblacionCruzada = new ArrayList<Gen>();
		
		cualCruza();
		
		double intervalo = (1.0 / longitudGen);
		double interAcumulado = intervalo;
		double pe = 0;
		int pos = 0;

		for (int i = 0; i < this.poblacionACruzar.size(); i +=2) {
			pe =Math.random();
			for (int j = 0; j < (longitudGen) ; j++) {

				if (pe > interAcumulado) {
					pos++;
					interAcumulado += intervalo;
				}
			}
			cruzarGenes(pos, poblacionACruzar.get(i), poblacionACruzar.get(i + 1));
		}
		poblacionFinal();
		pob.setPoblacion(this.poblacion);
	}

	public void cualCruza() {
		double pc = 0;
		for (int i = 0; i < this.poblacion.size(); i++) {
			pc = Math.random();
			this.individuosACruzar[i] = false;
			if (pc < pCruce) {
				this.poblacionACruzar.add(poblacion.get(i));
				this.individuosACruzar[i] = true;
			}
		}
		if (this.poblacionACruzar.size() % 2 != 0) {
			this.poblacionACruzar.remove(0);
			this.individuosACruzar[0] = false;
		} 
	}
	
	public void cruzarGenes(int pos, Gen padreUno, Gen padreDos) {
		boolean[] hijoUno = new boolean[longitudGen];
		boolean[] hijoDos = new boolean[longitudGen];
		boolean[] padreU = padreUno.getAlelos();
		boolean[] padreD = padreDos.getAlelos();

		for (int i = 0; i < pos; i++) {
			hijoUno[i] = padreU[i];
			hijoDos[i] = padreD[i];
		}

		for (int j = pos; j < longitudGen; j++) {
			hijoUno[j] = padreD[j];
			hijoDos[j] = padreU[j];
		}		

		Gen hijoA = new Gen(longitudGen);
		hijoA.setAlelos(hijoUno);
		this.poblacionCruzada.add(hijoA);
		
		Gen hijoB = new Gen(longitudGen);
		hijoB.setAlelos(hijoDos);
		this.poblacionCruzada.add(hijoB);
	}

	public void poblacionFinal() {
		// Cogemos la posicion CREO QUE AQUI ESTA EL ERROR.
		int pos = 0;
		for (int i = 0; i < this.poblacion.size(); i++) {
			if (this.individuosACruzar[i] == true) {
				poblacion.set(i, this.poblacionCruzada.get(pos));
				pos++;
			}
		}
	}
	
	public void showCruzados() {
		System.out.println("Los que hay que cruzar son:");
		for (int i = 0; i < this.poblacionACruzar.size(); i++) {
			for (int j = 0; j < longitudGen; j++) {
				boolean[] gen = this.poblacionACruzar.get(i).getAlelos();
				if (gen[j]) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
		System.out.println("Los cruzados son:");
		for (int i = 0; i < this.poblacionCruzada.size(); i++) {
			for (int j = 0; j < longitudGen; j++) {
				boolean[] gen = this.poblacionCruzada.get(i).getAlelos();
				if (gen[j]) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}
}

