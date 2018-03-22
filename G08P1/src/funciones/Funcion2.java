//package funciones;
//
//import java.util.ArrayList;
//
//import base.Cromosoma;
//import base.Gen;
//
//public class Funcion2 extends Cromosoma {
//
//	private double[] fitnessF1;
//	public Funcion2(int longitud, double tolerancia, double min, double max) {
//
//		super(longitud, tolerancia, max, min);
//
//		this.fitnessF1 = new double[longitud];
//		calcularFitness();
//		setFitness(this.fitnessF1);
//	}
//
//	public void calcularFitness() {
//		double[] fenotipo = getFenotipo();
//		for (int i = 0; i < getLongitudPob(); i++) {
//			this.fitnessF1[i] = 0;
//		}
//		setFitness(this.fitnessF1);
//	}
//}
