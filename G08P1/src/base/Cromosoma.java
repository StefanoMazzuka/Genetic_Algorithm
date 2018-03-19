package base;

public abstract class Cromosoma {
	private Gen[] genes;
	private double[] fitness;
	private double[] fenotipo;
	private double tolerancia;
	private int longGen;
	private double max;
	private double min;
	
	public Cromosoma(double tolerancia, int max, int min) {}
	
	public void CalcularlongGen() {
		this.longGen = (int) Math.ceil(Math.log((1 + ((this.max - this.min) / this.tolerancia))) / Math.log(2));
	}
}
