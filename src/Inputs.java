public class Inputs {

	private int valor;
	private boolean bitR = true;
	private boolean mode;

	public boolean isBitR() {
		return bitR;
	}

	public void setBitR(boolean bitR) {
		this.bitR = bitR;
	}

	public boolean isMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

	public Inputs(int valor){

		this.valor = valor;
	}

	public void setValor(int valor){

		this.valor = valor;
	}
	
	public int getValor(){

		return valor;
	}

	public boolean equals(Object o){

		return ((Inputs)o).getValor() == this.valor;
	}

	@Override
	public String toString() {
		return String.valueOf(valor);
	}


}
