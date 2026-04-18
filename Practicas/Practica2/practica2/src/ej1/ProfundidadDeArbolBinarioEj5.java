package ej1;

public class ProfundidadDeArbolBinarioEj5 {
	private BinaryTree<Integer> arbol;
	
	public ProfundidadDeArbolBinarioEj5(BinaryTree<Integer>arbol) {
		this.arbol = arbol;
	}
	// devuelve la suma de todos los nodos del árbol que se encuentren a la profundidad pasada como argumento.
	public int sumaElementosProfundidad(int p) {
		BinaryTree<Integer> ab = null;
		Queue<BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>();
		cola.enqueue(this.arbol);
		cola.enqueue(null);
		int nivelActual = 0;
		int suma = 0;
		
		while (!cola.isEmpty()){	
			ab = cola.dequeue();

			if (ab != null) {
				if (nivelActual == p) {
	                suma += ab.getData();
	            }
				
				if (ab.hasLeftChild()) {
					cola.enqueue(ab.getLeftChild());
				}
				
				if (ab.hasRightChild()) {
					cola.enqueue(ab.getRightChild());
				}
				
			} else if (!cola.isEmpty()) {
				cola.enqueue(null);
				nivelActual ++;
			}
			
			if (nivelActual > p) {
                break;
            }
		}
		
		return suma;
		}
	
	public static void main(String[]args) {
		// Raiz
	    BinaryTree<Integer> ab = new BinaryTree<>(10);
	    
	    // Nivel 1
	    BinaryTree<Integer> hijoIzq = new BinaryTree<>(2);
	    BinaryTree<Integer> hijoDer = new BinaryTree<>(3);
	    ab.addLeftChild(hijoIzq);
	    ab.addRightChild(hijoDer);
	    
	    // Nivel 2
	    hijoIzq.addLeftChild(new BinaryTree<>(5));
	    hijoIzq.addRightChild(new BinaryTree<>(4));
	    hijoDer.addLeftChild(new BinaryTree<>(9));
	    hijoDer.addRightChild(new BinaryTree<>(8));
	    
	    ProfundidadDeArbolBinarioEj5 contador = new ProfundidadDeArbolBinarioEj5(ab);

	    // Pruebo con los 3 niveles
	    System.out.println("Suma nivel 0 (esperado 10): " + contador.sumaElementosProfundidad(0));
	    System.out.println("Suma nivel 1 (esperado 5): " + contador.sumaElementosProfundidad(1));
	    System.out.println("Suma nivel 2 (esperado 26): " + contador.sumaElementosProfundidad(2));
	}
}
