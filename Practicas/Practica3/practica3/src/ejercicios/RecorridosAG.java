package ejercicios;

import java.util.LinkedList;
import java.util.List;

public class RecorridosAG {

	/* retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
	pasados como parámetros, recorrido en preorden. */
	public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n){
		List<Integer> resultado = new LinkedList<Integer>();
		if (a != null && !a.isEmpty()){
			recorrerPreOrden(a, n, resultado);
		}
		return resultado;
	}
	
	private List<Integer> recorrerPreOrden(GeneralTree<Integer> arbol, Integer valor, List<Integer> lista) {
		Integer dato = arbol.getData();
		if (dato > valor && dato % 2 != 0) {
			lista.add(dato);
		}
		
		if (arbol.hasChildren()) {
			List<GeneralTree<Integer>> hijos = arbol.getChildren();
			for (GeneralTree<Integer> hijo: hijos) {
				recorrerPreOrden(hijo, valor, lista);
			}
		}
		return lista;
	}
	
}
