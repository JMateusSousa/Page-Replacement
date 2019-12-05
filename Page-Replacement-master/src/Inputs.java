public class Inputs {

	private int valor;
	private char classe;
	private boolean bitR = true;
	private boolean bitM = false;

	public Inputs(int valor){
		this.valor = valor;
	}

	public int getValor(){
		return valor;
	}

	public boolean isBitR() {
		return bitR;
	}

	public void setBitR(boolean bitR) {
		this.bitR = bitR;
	}

	public boolean isBitM() {
		return bitM;
	}

	public void setBitM(boolean bitM) {
		this.bitM = bitM;
	}

	public char getClasse(){
		if(this.bitR == false && this.bitM == false){
			this.classe = 0;
		}
		else if(this.bitR == false && this.bitM == true){
			this.classe = 1;
		}
		else if(this.bitR == true && this.bitM == false){
			this.classe = 2;
		}
		else if(this.bitR == true && this.bitM == true){
			this.classe = 3;
		}
		return this.classe;
	}

	public boolean equals(Object o){
		return ((Inputs)o).getValor() == this.valor;
	}

	@Override
	public String toString() {
		return String.valueOf(valor);
	}


}
