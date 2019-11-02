/******************************************
*Universidade Federal da Paraíba - UFPB
*Centro de Informática - CI
*
*Sistemas Operacionais 1 - 2016.2
*
*Aluno: Matheus Maranhão Rêgo Praxedes
*Matrícula: 11403744
*
*Trabalho 2
*Data de entrega: 21/05/2017
*******************************************/

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
