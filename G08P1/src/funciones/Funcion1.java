package funciones;

import base.Cromosoma;
import base.Gen;

public class Funcion1 extends Cromosoma {

	private double min = 0;
	private double max = 32;

	public Funcion1(double precision) {
		this.setPrecision(precision);
		this.gen = new Gen[1];
		this.setlGen((int) Math.ceil(Math.log((1 + ((this.max - this.min) / this.getPrecision()))) / Math.log(2)));
		this.gen[0] = new Gen(this.getlGen());
		this.gen[0].crearAlelos();
		calcularFitness();
		this.calcularFitnessTotal();
	}

	public void calcularFenotipo() {
		double[] fenotipo = new double[1];
		fenotipo[0] = this.min + (this.max - this.min) * 
				this.bin_dec(this.gen[0]) / (Math.pow(2, this.getlGen()) - 1);
		this.setFenotipo(fenotipo);
	}

	public void calcularFitness() {
		calcularFenotipo();
		double[] fenotipo = this.getFenotipo();
		double[] fitness = new double[1];
		fitness[0] = 20 + Math.E - 20 * 
				Math.pow(Math.E, (-0.2 * Math.abs(fenotipo[0]))) - 
				Math.pow(Math.E, Math.cos(2 * Math.PI * fenotipo[0]));
		this.setFitness(fitness);
		this.calcularFitnessTotal();
	}
}
