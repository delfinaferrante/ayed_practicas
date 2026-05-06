package ejercicios;

public class ParcialArboles3 {
	
	
	/* devuelve true si el arbol es creciente.
	 * Es creciente si para cada nivel, la cantidad de nodos que hay en ese nivel es igual a la cantidad de nodos
	 * del nivel anterior + 1. 
	 */
	public static boolean resolver(GeneralTree<Integer> arbol) {
		if(arbol != null && !arbol.isEmpty()) {
			return esCreciente(arbol);
		}
		return false;
	}
	
	private static boolean esCreciente(GeneralTree<Integer> nodo) {
		Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
		GeneralTree<Integer> aux;
		cola.enqueue(nodo);
		cola.enqueue(null);
		
		int nodosNivelAct = 0;
	    int nodosNivelAnt = 0; // El nivel -1 tendría 0 nodos
	    boolean cumple = true;

		while (!cola.isEmpty() && cumple) {
			aux = cola.dequeue();
			
			if (aux != null) {
				nodosNivelAct++;
				for(GeneralTree<Integer> hijo: aux.getChildren()) {
					cola.enqueue(hijo);
				}
				
			} else { // si es null termino el nivel		
				
				if(nodosNivelAct != (nodosNivelAnt+1)) {
					cumple = false;
				}
				
				nodosNivelAnt = nodosNivelAct;
				nodosNivelAct = 0;
				
				if(!cola.isEmpty()) {
					cola.enqueue(null);
				}
			}
		}
		
		return cumple;
	}
}
