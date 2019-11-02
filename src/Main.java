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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {


	public static void main(String[] args){


		int numero_quadros = 0;
		List<Inputs> sequencia_referencia = new ArrayList<>();
		List<Inputs> sequencia_referencia_otm = new ArrayList<>();

		try {
			
		    BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		    String valor;
		    numero_quadros = Integer.parseInt(entrada.readLine());
			
		    while(true) {
				valor = entrada.readLine();
				if (valor != null && valor.length() != 0)
					sequencia_referencia.add(new Inputs(Integer.parseInt(valor)));
				else {
					break;
				}
		    }

		} catch (IOException e) {
		    e.getMessage();
		      
		}
	
		for(int i = 0; i < sequencia_referencia.size() ; i++){

			sequencia_referencia_otm.add(sequencia_referencia.get(i));
		}

		Algorithms fifo = new Algorithms(numero_quadros);
		Algorithms mru = new Algorithms(numero_quadros);
	

		fifo.FIFO(sequencia_referencia);
		//otm.OTM(sequencia_referencia_otm);
		mru.MRU(sequencia_referencia);
	}
}


