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
	
	private void recorrerPreOrden(GeneralTree<Integer> arbol, Integer valor, List<Integer> lista) {
		Integer dato = arbol.getData();
		// Proceso raiz
		if (dato > valor && dato % 2 != 0) {
			lista.add(dato);
		}
		
		// Luego los hijos
		if (arbol.hasChildren()) {
			List<GeneralTree<Integer>> hijos = arbol.getChildren();
			for (GeneralTree<Integer> hijo: hijos) {
				recorrerPreOrden(hijo, valor, lista);
			}
		}
	}
	
	//----------------------------------------------------------------
	public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n){
		List<Integer> resultado = new LinkedList<Integer>();
		if (a != null && !a.isEmpty()) {
			recorrerInOrden(a, n, resultado);
		}
		return resultado;
	}
	
	private void recorrerInOrden(GeneralTree<Integer>arbol, Integer valor, List<Integer> lista){
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
	}
	
	//----------------------------------------------------------------
	public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n){
		List<Integer> resultado = new LinkedList<Integer>();
		if (a != null && !a.isEmpty()) {
			recorrerPostOrden(a, n, resultado);
		}
		return resultado;
	}
	
	private void recorrerPostOrden(GeneralTree<Integer> arbol, Integer valor, List<Integer> lista){
		// Proceso los hijos
		if (arbol.hasChildren()) {
			List<GeneralTree<Integer>> hijos = new LinkedList<GeneralTree<Integer>>();
			for (GeneralTree<Integer> hijo: hijos) {
				recorrerPostOrden(hijo, valor, lista);
			}
		}
		
		// Proceso la raiz
		Integer dato = arbol.getData();
		if (dato > valor && dato % 2 != 0) {
			lista.add(dato);
		}
	}

	//----------------------------------------------------------------
	public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n){
		List<Integer> resultado = new LinkedList<Integer>();
		if (a != null && !a.isEmpty()) {
			recorrerPorNiveles(a, n, resultado);
		}
		return resultado;
	}
	
	// nota: si me hubieran pedido contar cuantos valores que cumplen hay por nivel, tendria que haber puesto una marca "null" en la cola
	private void recorrerPorNiveles(GeneralTree<Integer>arbol, Integer valor, List<Integer> lista) {
		GeneralTree<Integer> arbolAux;
		Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
		
		cola.enqueue(arbol);
		while (!cola.isEmpty()) {
			arbolAux = cola.dequeue();
			Integer dato = arbolAux.getData();
			
			if (dato > valor && dato % 2 != 0) {
				lista.add(dato);
			}
			
			List<GeneralTree<Integer>> hijos = arbolAux.getChildren();
			for (GeneralTree<Integer> hijo: hijos) {
				cola.enqueue(hijo);
			}
		}
	}
}
