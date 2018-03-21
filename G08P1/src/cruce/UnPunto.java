package cruce;

import java.util.ArrayList;

import base.AlgoritmoGenetico;
import base.Cromosoma;
import base.Gen;
import base.Poblacion;

public class UnPunto {

	private AlgoritmoGenetico agCopy;
	
	private double pCruce;
	private int longitudGen;
	private boolean[] individuosACruzar;
	private ArrayList<Cromosoma> poblacion;
	private ArrayList<Cromosoma> poblacionACruzar;
	private ArrayList<Cromosoma> poblacionCruzada;

	public UnPunto(double pCruce) {
		this.pCruce = pCruce;
	}
	public void cruzar(AlgoritmoGenetico ag) {
		
		this.agCopy = ag.copy();	
		this.poblacion = this.agCopy.getPoblacion();		
		this.longitudGen = this.poblacion.get(0).getlCromosoma();
		this.individuosACruzar = new boolean[this.agCopy.getlPoblacion()];
		this.poblacionACruzar = new ArrayList<Cromosoma>();
		this.poblacionCruzada = new ArrayList<Cromosoma>();
		
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
		agCopy.setPoblacion(this.poblacion);
		ag.setPoblacion(agCopy.getPoblacion());
	}
	public void cualCruza() {
		double pc = 0;
		int pos = 0;
		for (int i = 0; i < this.agCopy.getlPoblacion(); i++) {
			pc = Math.random();
			this.individuosACruzar[i] = false;
			if (pc < pCruce) {
				this.poblacionACruzar.add(this.poblacion.get(i).copy());
				this.individuosACruzar[i] = true;
				pos = i;
			}
		}
		
		if (this.poblacionACruzar.size() % 2 != 0) {
			this.poblacionACruzar.remove(this.poblacion.get(pos)); // Era esta linea... el error.
			this.individuosACruzar[pos] = false;
		} 
	}
	public void cruzarGenes(int pos, Cromosoma padreUno, Cromosoma padreDos) {
		boolean[] hijoUno = new boolean[longitudGen];
		boolean[] hijoDos = new boolean[longitudGen];
		Gen[] padreUGen = padreUno.getGen();
		Gen[] padreDGen = padreDos.getGen();
		boolean[] padreU = padreUGen[0].getAlelos();
		boolean[] padreD = padreDGen[0].getAlelos();
		
		for (int i = 0; i < pos; i++) {
			hijoUno[i] = padreU[i];
			hijoDos[i] = padreD[i];
		}

		for (int j = pos; j < longitudGen; j++) {
			hijoUno[j] = padreD[j];
			hijoDos[j] = padreU[j];
		}		

		Gen[] hijoA = new Gen[1];
		hijoA[0].setAlelos(hijoUno);
		padreUno.setGen(hijoA);
		this.poblacionCruzada.add(padreUno);
		
		Gen[] hijoB = new Gen[1];
		hijoB[0].setAlelos(hijoDos);
		padreDos.setGen(hijoB);
		this.poblacionCruzada.add(padreDos);
	}
	public void poblacionFinal() {
		// Cogemos la posicion CREO QUE AQUI ESTA EL ERROR.
		int pos = 0;
		for (int i = 0; i < this.poblacion.size(); i++) {
			if (this.individuosACruzar[i] == true) {
				this.poblacion.set(i, this.poblacionCruzada.get(pos));
				pos++;
			}
		}
	}
	public void showCruzados() {
		System.out.println("Los que hay que cruzar son:");
		for (int i = 0; i < this.poblacionACruzar.size(); i++) {
			for (int j = 0; j < longitudGen; j++) {
				boolean[] gen = this.poblacionACruzar.get(i).getGen()[0].getAlelos();
				if (gen[j]) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
		System.out.println("Los cruzados son:");
		for (int i = 0; i < this.poblacionCruzada.size(); i++) {
			for (int j = 0; j < longitudGen; j++) {
				boolean[] gen = this.poblacionCruzada.get(i).getGen()[0].getAlelos();
				if (gen[j]) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}
}

