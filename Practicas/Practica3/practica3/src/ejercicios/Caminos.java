package ejercicios;

import java.util.LinkedList;
import java.util.List;

public class Caminos {
	private GeneralTree<Integer> arbol;
	
	public Caminos(GeneralTree<Integer> arbol) {
		this.arbol = arbol;
	}
	
	
	// retorna el camino a la hoja más lejana - RECURSIÓN CON BACKTRAKING (PRE ORDEN)
	public List<Integer> caminoAHojaMasLejana (){
		LinkedList<Integer> resultado = new LinkedList<Integer>();
		if(arbol != null && !arbol.isEmpty()) {
			recorrer(arbol, resultado, new LinkedList<Integer>());
		}
		return resultado;
	}
	
	private void recorrer(GeneralTree<Integer> arbol, LinkedList<Integer> caminoMax, LinkedList<Integer> caminoAct) {
		caminoAct.add(arbol.getData());

		if (arbol.isLeaf()) {
			if(caminoAct.size() > caminoMax.size()) {
				caminoMax.clear();
				caminoMax.addAll(caminoAct);
			}
		} else {
			for(GeneralTree<Integer> hijo: arbol.getChildren()) {
				recorrer(hijo, caminoMax, caminoAct);			
			}
		}
		caminoAct.remove(caminoAct.size()-1);
	}
	
	public static void main(String[]args) {
		// --- Rama Izquierda (Nodo 17) ---
	    GeneralTree<Integer> n1 = new GeneralTree<>(1);
	    GeneralTree<Integer> n6 = new GeneralTree<>(6);
	    n6.addChild(n1); // El 1 es hijo del 6
	    
	    GeneralTree<Integer> n10 = new GeneralTree<>(10);
	    
	    GeneralTree<Integer> n17 = new GeneralTree<>(17);
	    n17.addChild(n10);
	    n17.addChild(n6);

	    // --- Rama Central (Nodo 9) ---
	    GeneralTree<Integer> n8 = new GeneralTree<>(8);
	    GeneralTree<Integer> n9 = new GeneralTree<>(9);
	    n9.addChild(n8);

	    // --- Rama Derecha (Nodo 15) ---
	    GeneralTree<Integer> n16 = new GeneralTree<>(16);
	    GeneralTree<Integer> n7 = new GeneralTree<>(7);
	    GeneralTree<Integer> n14 = new GeneralTree<>(14);
	    n14.addChild(n16);
	    n14.addChild(n7);
	    
	    GeneralTree<Integer> n18 = new GeneralTree<>(18);
	    
	    GeneralTree<Integer> n15 = new GeneralTree<>(15);
	    n15.addChild(n14);
	    n15.addChild(n18);

	    // --- Raíz (Nodo 12) ---
	    GeneralTree<Integer> raiz = new GeneralTree<>(12);
	    raiz.addChild(n17);
	    raiz.addChild(n9);
	    raiz.addChild(n15);

	    // --- Prueba del método ---
	    Caminos c = new Caminos(raiz); // Asumiendo que tenés un constructor o setter
	    List<Integer> resultado = c.caminoAHojaMasLejana();

	    System.out.println("Camino más largo: " + resultado);
	}
}
