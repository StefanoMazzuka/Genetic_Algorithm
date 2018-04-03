package funciones;

import base.Cromosoma;
import base.Gen;

public class Funcion5 extends Cromosoma {

	private double min = 0;
	private double max = Math.PI;

	public Funcion5(double precision) {
		this.setPrecision(precision);
		this.gen = new Gen[2];
		this.lGen = new int[2];

		this.lGen[0] = (int) Math.ceil(Math.log((1 + ((this.max - this.min) / this.getPrecision()))) / Math.log(2));
		this.gen[0] = new Gen(this.lGen[0]);
		this.gen[0].crearAlelos();

		this.lGen[1] = (int) Math.ceil(Math.log((1 + ((this.max - this.min) / this.getPrecision()))) / Math.log(2));
		this.gen[1] = new Gen(this.lGen[1]);
		this.gen[1].crearAlelos();

		calcularFitness();
		this.calcularFitnessTotal();
	}

	public void calcularFenotipo() {
		double[] fenotipo = new double[2];

		for (int i = 0; i < 2; i++) {
			fenotipo[i] = this.min + (this.max - this.min) * 
					this.bin_dec(this.gen[i], 0) / (Math.pow(2, this.lGen[0]) - 1);
		}		

		this.setFenotipo(fenotipo);
	}

	public void calcularFitness() {
		calcularFenotipo();
		double[] fenotipo = this.getFenotipo();
		double[] fitness = new double[2];
		
		int sum0, sum1;
		for (int i = 0; i < 2; i++) {
			sum0 = 0;
			for (int j = 1; j < 5; j++) {
				sum0 += j * Math.cos((j + 1) * fenotipo[0] + j);
			}
			
			sum1 = 0;
			for (int j = 1; j < 5; j++) {
				sum1 += j * Math.cos((j + 1) * fenotipo[1] + j);
			}
			
			fitness[i] = sum0 * sum1;
		}
		
		this.setFitness(fitness);
		this.calcularFitnessTotal();
	}
}