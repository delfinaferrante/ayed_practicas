package ej1;

public class ParcialArbolesEj9 {

	/*  recibe un árbol binario de enteros y devuelve un nuevo árbol que contenga en cada nodo dos tipos
	 * de información:
	 * 						- la suma de los números desde la raiz hasta el nodo actual
	 * 						- la diferencia entre el numero del nodo actual y el num del nodo padre
	 */
	public BinaryTree<Dato> sumAndDif(BinaryTree<Integer> arbol){
		if (arbol == null || arbol.isEmpty()) {
			return null;
		} else {
			int suma = 0;
			int num = 0;
			return recorrerYCopiar(arbol, suma, num);
		}	
	}
	
	// Recorrido pre orden
	private BinaryTree<Dato> recorrerYCopiar(BinaryTree<Integer> arbol, int sumaPadre, int numPadre) {
		int sumaActual = sumaPadre + arbol.getData();
	    int difActual = arbol.getData() - numPadre;
		
	    // Nuevo nodo con su objeto Dato ya cargado
	    BinaryTree<Dato> arbolNuevo = new BinaryTree<>(new Dato(sumaActual, difActual));

	    // 3. Recursión para los hijos
	    if (arbol.hasLeftChild()) {
	    	arbolNuevo.addLeftChild(recorrerYCopiar(arbol.getLeftChild(), sumaActual, arbol.getData()));
	    }
	    
	    if (arbol.hasRightChild()) {
	    	arbolNuevo.addRightChild(recorrerYCopiar(arbol.getRightChild(), sumaActual, arbol.getData()));
	    }

		return arbolNuevo;	
	}

}
