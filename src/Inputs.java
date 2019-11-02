public class Inputs {

	private int valor;

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
