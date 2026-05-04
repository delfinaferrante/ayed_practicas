package ejercicios;

import java.util.Iterator;
import java.util.List;

public class ParcialArboles {

	/* devuelve true si es de selección, false en caso contrario
	 * Es de selección si cada nodo tiene en su raíz el valor del menor de sus hijos.
	 */
	public static boolean esDeSeleccion (GeneralTree<Integer> arbol) {
		if (arbol != null && !arbol.isEmpty()) {
			return recorrerYVerificar(arbol);
		}
		return false;
	}
	
	private static boolean recorrerYVerificar(GeneralTree<Integer> nodo) {
		if(nodo.isLeaf()) {			// si es una hoja significa que ya recorri todo el arbol y es de seleccion
			return true;
		}
		
		int valorMin = Integer.MAX_VALUE;
		List<GeneralTree<Integer>> hijos = nodo.getChildren();
		for(GeneralTree<Integer> hijo:hijos) {		// cada nodo actual mira y busca el minimo en sus hijos
			if(hijo.getData() < valorMin) {
				valorMin = hijo.getData();
			}
		}
		
		if (!nodo.getData().equals(valorMin)) {
			return false;
		}
		
		Iterator<GeneralTree<Integer>> it = nodo.getChildren().iterator();
		boolean cumple = true;
		while(cumple && it.hasNext()) {			// si el nodo actual (padre) paso la prueba, entonces hay q verificar lo que tienen sus nodos hijos
			cumple = esDeSeleccion(it.next());
		}
		return cumple;
	}

	
	public static void main(String[]args) {
		// --- Rama Izquierda (debajo del 12 izquierdo) ---
	    // Sub-rama del 35
	    GeneralTree<Integer> n35_hoja = new GeneralTree<>(35);
	    GeneralTree<Integer> n35_padre = new GeneralTree<>(35);
	    n35_padre.addChild(n35_hoja);

	    // Sub-rama del 12 interno
	    GeneralTree<Integer> n14 = new GeneralTree<>(14);
	    GeneralTree<Integer> n12_hoja = new GeneralTree<>(12);
	    
	    // Sub-sub-rama del 33
	    GeneralTree<Integer> n35_33 = new GeneralTree<>(35);
	    GeneralTree<Integer> n83 = new GeneralTree<>(83);
	    GeneralTree<Integer> n90 = new GeneralTree<>(90);
	    GeneralTree<Integer> n33_hoja = new GeneralTree<>(33);
	    
	    GeneralTree<Integer> n33_padre = new GeneralTree<>(33);
	    n33_padre.addChild(n35_33);
	    n33_padre.addChild(n83);
	    n33_padre.addChild(n90);
	    n33_padre.addChild(n33_hoja);

	    GeneralTree<Integer> n12_interno = new GeneralTree<>(12);
	    n12_interno.addChild(n14);
	    n12_interno.addChild(n12_hoja);
	    n12_interno.addChild(n33_padre);

	    // Nodo 12 de la izquierda (Nivel 1)
	    GeneralTree<Integer> n12_izq = new GeneralTree<>(12);
	    n12_izq.addChild(n35_padre);
	    n12_izq.addChild(n12_interno);

	    // --- Rama Derecha (debajo del 25) ---
	    GeneralTree<Integer> n25_hoja = new GeneralTree<>(25);
	    GeneralTree<Integer> n25_padre = new GeneralTree<>(25);
	    n25_padre.addChild(n25_hoja);

	    // --- Raíz Principal ---
	    GeneralTree<Integer> raiz = new GeneralTree<>(12);
	    raiz.addChild(n12_izq);
	    raiz.addChild(n25_padre);

	    // --- Ejecución del Test ---
	    boolean resultado = ParcialArboles.esDeSeleccion(raiz);
	    
	    System.out.println("¿Es un árbol de selección?: " + resultado);
	    // Debería imprimir: true
	}

}
