package base;

public class Gen {
	boolean[] alelos;
	double lgen;

	public Gen() {}

	public Gen(double lgen) {
		this.lgen = lgen;
		
		this.alelos = new boolean[(int) lgen];
		for (int i = 0; i < this.lgen; i++) {
			if (Math.round(Math.random()) == 1) this.alelos[i] = true;
			else this.alelos[i] = false;
		}
	}

	public boolean[] getAlelos() {
		return alelos;
	}

	public void setAlelos(boolean[] alelos) {
		this.alelos = alelos;
	}
	
	public double getLgen() {
		return lgen;
	}

	public void setLgen(double lgen) {
		this.lgen = lgen;
	}
}


