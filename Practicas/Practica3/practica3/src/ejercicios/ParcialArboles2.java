package ejercicios;

import java.util.LinkedList;
import java.util.List;

public class ParcialArboles2 {

	
	public static List<Integer> resolver(GeneralTree<Integer> arbol){
		List<Integer> resultado = new LinkedList<Integer>();
		if (arbol != null && !arbol.isEmpty()) {
			int[] sumaMax = new int[1]; 
	        sumaMax[0] = Integer.MIN_VALUE;
			recorrerYSumar(arbol, resultado, new LinkedList<Integer>(), 0, 0, sumaMax);
		}
		return resultado;
	}
	
	private static void recorrerYSumar(GeneralTree<Integer> nodo, List<Integer> resultado, List<Integer> caminoActual, int nivelActual, int sumaCamino, int[] sumaMax) {

		sumaCamino += nodo.getData() * nivelActual; 
		
		// proceso el nodo actual - solo agrego si es 1
		if(nodo.getData() == 1) {
			caminoActual.add(nodo.getData());			 
		}

		// caso base, si es hoja comparo sumaCamino con sumaMax
		if(nodo.isLeaf()) {
			if (sumaCamino > sumaMax[0]) {
				sumaMax[0] = sumaCamino;
				resultado.clear();
				resultado.addAll(caminoActual);
			}
		} else {
			// proceso sus hijos directos
			for(GeneralTree<Integer> hijo: nodo.getChildren()) {
				recorrerYSumar(hijo, resultado, caminoActual, nivelActual + 1, sumaCamino, sumaMax);
			}
		}
		
		// backtracking
		if(nodo.getData() == 1) {
			caminoActual.remove(caminoActual.size()-1);	
		}
	}

}

