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
	
	//----------------------------------------------------------------
	public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n){
		List<Integer> resultado = new LinkedList<Integer>();
		if (a != null && !a.isEmpty()) {
			recorrerInOrden(a, n, resultado);
		}
		return resultado;
	}
	
	private List<Integer> recorrerInOrden(GeneralTree<Integer>arbol, Integer valor, List<Integer> lista){
		List<GeneralTree<Integer>> hijos = arbol.getChildren();

	    // Proceso PRIMER HIJO (si existe)
	    if (arbol.hasChildren()) {
	        recorrerInOrden(hijos.get(0), valor, lista);
	    }

	    // Proceso la RAÍZ (después del primer hijo)
	    Integer dato = arbol.getData();
	    if (dato > valor && dato % 2 != 0) {
	        lista.add(dato);
	    }

	    // Proceso el RESTO DE LOS HIJOS (del segundo en adelante)
	    if (arbol.hasChildren() && hijos.size() > 1) {
	        for (int i = 1; i < hijos.size(); i++) {
	            recorrerInOrden(hijos.get(i), valor, lista);
	        }
	    }
	    return lista;
	}
	
}
