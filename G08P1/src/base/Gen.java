package base;

public class Gen {
	boolean[] alelos;
	int lgen;

	public Gen(int lgen) {
		this.lgen = lgen;
		
		this.alelos = new boolean[lgen];
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

	public void setLgen(int lgen) {
		this.lgen = lgen;
	}
	
	public Gen copy()  {
		Gen g = new Gen(this.lgen);
		g.setAlelos(this.getAlelos());
		return g;
	}
}


