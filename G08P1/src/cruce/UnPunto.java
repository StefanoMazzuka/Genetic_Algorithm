package cruce;

import java.util.ArrayList;
import java.util.Random;

import base.AlgoritmoGenetico;
import base.Cromosoma;
import base.Gen;

public class UnPunto {

	private AlgoritmoGenetico agCopy;
	
	private double pCruce;
	private int lGen;
	private int lCromosoma;
	private ArrayList<Cromosoma> poblacion;
	private ArrayList<Cromosoma> poblacionACruzar;
	private Gen[] genCruzadoUno;
	private Gen[] genCruzadoDos;
	private int numElemACruzar;
	
	public UnPunto(double pCruce) {
		this.pCruce = pCruce;
	}
	public void cruzar(AlgoritmoGenetico ag) {

		this.agCopy = ag.copy();	
		this.poblacion = this.agCopy.getPoblacion();		
		this.lGen = this.poblacion.get(0).getlGen();
		this.lCromosoma = this.agCopy.getlCromosoma();
		this.poblacionACruzar = new ArrayList<Cromosoma>();
		this.genCruzadoUno = new Gen[lCromosoma];
		this.genCruzadoDos = new Gen[lCromosoma];
		
		cualCruza();
		
		int pos = 0;
		Random r = new Random();
		
		for (int i = 0; i < numElemACruzar; i +=2) {
			pos = r.nextInt(this.lGen);
			cruzarCromosomas(pos, poblacionACruzar.get(i), poblacionACruzar.get(i + 1));
		}
		
		poblacionFinal();
		agCopy.setPoblacion(this.poblacion);
		ag.setPoblacion(agCopy.getPoblacion());
	}
	public void cualCruza() {
		
		double pc = 0;
		for (int i = 0; i < this.agCopy.getlPoblacion(); i++) {
			pc = Math.random();
			if (pc < pCruce) {
				this.poblacionACruzar.add(this.poblacion.get(i).copy());
			}
		}
		
		this.numElemACruzar = this.poblacionACruzar.size();
		
		if (this.poblacionACruzar.size() % 2 != 0) {
			this.poblacionACruzar.remove(this.poblacion.get(0)); // Era esta linea... el error.
			this.numElemACruzar--;
		} 
	}
	public void cruzarCromosomas(int pos, Cromosoma padreUno, Cromosoma padreDos) {
		Gen[] padreUGen = padreUno.getGen();
		Gen[] padreDGen = padreDos.getGen();
		
		for (int i = 0; i < this.lCromosoma; i++) {
			cruzarGenes(i, pos, padreUGen[i].copy(), padreDGen[i].copy());
		}

		padreUno.setGen(this.genCruzadoUno);
		padreDos.setGen(this.genCruzadoDos);
		
		padreUno.calcularFenotipo();
		padreUno.calcularFitness();
		
		padreDos.calcularFenotipo();
		padreDos.calcularFitness();
		
		int i = 0;
		while (i < this.numElemACruzar && this.poblacionACruzar.get(i).getId() != padreUno.getId()) {
			i++;
		}
		
		this.poblacionACruzar.set(i, padreUno);
		
		while (i < this.numElemACruzar && this.poblacionACruzar.get(i).getId() != padreDos.getId()) {
			i++;
		}
		
		this.poblacionACruzar.set(i, padreDos);
	}
	public void cruzarGenes(int posGen, int pos, Gen padreUno, Gen padreDos) {	
		boolean[] hijoUno = new boolean[lGen];
		boolean[] hijoDos = new boolean[lGen];
		boolean[] padreU = padreUno.getAlelos();
		boolean[] padreD = padreDos.getAlelos();
		
		for (int i = 0; i < pos; i++) {
			hijoUno[i] = padreU[i];
			hijoDos[i] = padreD[i];
		}

		for (int j = pos; j < this.lGen; j++) {
			hijoUno[j] = padreD[j];
			hijoDos[j] = padreU[j];
		}		

		padreUno.setAlelos(hijoUno);		
		padreDos.setAlelos(hijoDos);
		
		this.genCruzadoUno[posGen] = padreUno;
		this.genCruzadoDos[posGen] = padreDos;
	}
	
	public void poblacionFinal() {

		for (int i = 0; i < this.numElemACruzar; i++) {
			this.poblacion.set(this.poblacionACruzar.get(i).getId(), this.poblacionACruzar.get(i));
		}
	}	
	public void showPoblacionACruzar() {
		System.out.println("Poblacion A Cruzar:");
		double[] fenotipo;
		double[] fitness;
		boolean[] alelos;
		for (int i = 0; i < this.numElemACruzar; i++) {
			fenotipo = this.poblacionACruzar.get(i).getFenotipo();
			fitness = this.poblacionACruzar.get(i).getFitness();
			System.out.println("Fenotipo: " + fenotipo[0] + " Fitness: " + fitness[0]);
			
			alelos = this.poblacionACruzar.get(i).gen[0].getAlelos();
			for (int j = 0; j < alelos.length; j++) {
				if (alelos[j]) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
	}
}

