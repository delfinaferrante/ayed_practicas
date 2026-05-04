package ejercicios;

import java.util.Iterator;
import java.util.List;

public class Navidad {
	private GeneralTree<Integer> abeto;
	
	public Navidad(GeneralTree<Integer> abeto) {
		this.abeto = abeto;
	}
	
	// Un arbol es abeto si: cada nodo padre tiene como minimo 3 hojas. 
	public String esAbetoNavidenio() {
        if (abeto == null || abeto.isEmpty()) {
            return "El arbol no es abeto";
        }
        
        if (verSiEsAbeto(abeto)) {
            return "El arbol es abeto";
        } else {
            return "El arbol no es abeto";
        }
    }
	
	private boolean verSiEsAbeto(GeneralTree<Integer>nodo) {	
		if(nodo.isLeaf()) {
			return true;
		}
		
		int cantHijosHoja = 0;
		List<GeneralTree<Integer>> hijos = nodo.getChildren();
		// Cuento cuantos de sus hijos directos son hojas
		for (GeneralTree<Integer> hijo: hijos) {
			if(hijo.isLeaf()) {
				cantHijosHoja++;
			}
		}
		
		if(cantHijosHoja < 3) {
			return false;
		}
		
		// si la cantidad de hijos hoja es 3 o más, voy a recorrer todos sus otros nodos que tienen hijos
		for (GeneralTree<Integer> hijo: hijos) {
			if (!hijo.isLeaf()) {
				if (!verSiEsAbeto(hijo)) {
					return false;
				}
			}
		}
		return true;
	}
}
