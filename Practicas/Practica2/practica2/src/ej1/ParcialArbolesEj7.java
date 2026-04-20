package ej1;

public class ParcialArbolesEj7 {
	private BinaryTree<Integer> arbol;
	
	public ParcialArbolesEj7(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}
	
	public boolean isLeftTree (int num) {
		if (this.arbol.isEmpty() || this.arbol == null) {
			return false;
		}
		
		// 1. Busco el nodo con valor 'num'
        BinaryTree<Integer> nodoObjetivo = buscarNodo(this.arbol, num);

        // Si no está el nodo, devuelve false 
        if (nodoObjetivo == null) {
            return false;
        }
        
        int cantIzq = -1;
        int cantDer = -1;

        if (nodoObjetivo.hasLeftChild()) {
            cantIzq = contarNodos(nodoObjetivo.getLeftChild());
        }
        
        if (nodoObjetivo.hasRightChild()) {
            cantDer = contarNodos(nodoObjetivo.getRightChild());
        }

        // 3. Retorno true si la izquierda es mayor estricta que la derecha
        return cantIzq > cantDer;
    }
	
	private BinaryTree<Integer> buscarNodo(BinaryTree<Integer> nodoActual, int num){
		if (nodoActual == null) {
			return null;
		}
		
        if (nodoActual.getData() == num) {
        	return nodoActual;
        }
        
        BinaryTree<Integer> encontrado = null;

        // Busco por izquierda
        if (nodoActual.hasLeftChild()) {
            encontrado = buscarNodo(nodoActual.getLeftChild(), num);
        }

        // Si no lo encontramos, buscamos por derecha
        if (encontrado == null && nodoActual.hasRightChild()) {
            encontrado = buscarNodo(nodoActual.getRightChild(), num);
        }

        return encontrado;
	}
	
	
	private int contarNodos(BinaryTree<Integer> nodo) {
		if(nodo == null) {
			return 0;
		}
		
		int suma = 0;
		
		// Lógica de "único hijo": tiene uno pero NO el otro
        boolean tieneIzq = nodo.hasLeftChild();
        boolean tieneDer = nodo.hasRightChild();
		
        if ((tieneIzq && !tieneDer) || (!tieneIzq && tieneDer)) {
            suma = 1;
        }
        
        if (tieneIzq) {
            suma += contarNodos(nodo.getLeftChild());
        }
        
        if (tieneDer) {
            suma += contarNodos(nodo.getRightChild());
        }
        
		return suma;
	}
}
