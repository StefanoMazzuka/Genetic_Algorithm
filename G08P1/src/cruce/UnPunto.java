package cruce;

import java.util.ArrayList;

import base.Gen;
import base.Poblacion;

public class UnPunto {

	private double pCruce;
	private int longitudGen;
	private ArrayList<Gen> poblacion;
	private ArrayList<Gen> poblacionACruzar;
	private ArrayList<Gen> poblacionCruzada;

	public UnPunto(double pCruce, Poblacion poblacion){
		longitudGen = (int) poblacion.getLgen();
		this.pCruce = pCruce;
		this.poblacion = poblacion.getPoblacion();
		this.poblacionACruzar = new ArrayList<Gen>();
		this.poblacionCruzada = new ArrayList<Gen>();
		cruzar(); // QUITAR!!!
	}

	public void cualCruza() {
		double pc = 0;
		for (int i = 0; i < this.poblacion.size(); i++) {

			pc = Math.random();
			if (pc < pCruce)
				this.poblacionACruzar.add(poblacion.get(i));
		}
		if (this.poblacionACruzar.size() % 2 != 0) 
			this.poblacionACruzar.remove(0);
	}

	public void cruzar() {
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

	public void showCruzados() {
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

