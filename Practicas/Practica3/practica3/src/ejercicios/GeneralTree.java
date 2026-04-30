package ejercicios;
import java.util.LinkedList;
import java.util.List;

public class GeneralTree <T>{
		private T data;
		private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>(); 

		public GeneralTree() {
		}
		
		public GeneralTree(T data) {
			this.data = data;
		}

		public GeneralTree(T data, List<GeneralTree<T>> children) {
			this(data);
			this.children = children;
		}	
		
		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public List<GeneralTree<T>> getChildren() {
			return this.children;
		}
		
		public void setChildren(List<GeneralTree<T>> children) {
			if (children != null)
				this.children = children;
		}
		
		public void addChild(GeneralTree<T> child) {
			this.getChildren().add(child);
		}

		public boolean isLeaf() {
			return !this.hasChildren();
		}
		
		public boolean hasChildren() {
			return !this.children.isEmpty();
		}
		
		public boolean isEmpty() {
			return this.data == null && !this.hasChildren();
		}

		public void removeChild(GeneralTree<T> child) {
			if (this.hasChildren())
				children.remove(child);
		}
		
		//longitud del camino más largo desde el nodo raíz hasta una hoja
		public int altura() {	 
			if (this.isLeaf()) {
				return 0;
			}
			
			int alturaMax = -1;
			
			List<GeneralTree<T>> hijos = this.getChildren();
			for (GeneralTree<T> hijo: hijos) {
				alturaMax = Math.max(alturaMax, hijo.altura()); //Math max compara la altura maxima y la altura del nodo actual
			}
			return alturaMax + 1;
		}
		
		// devuelve la profundidad o nivel del dato en el árbol. El nivel de un nodo 
		//es la longitud del único camino de la raíz al nodo.
		public int nivel(T dato){
			if (this.getData() == dato) {
				return 0; //la distancia a mi mismo es 0
			}
			
			if (this.hasChildren()) {
				List<GeneralTree<T>> hijos = this.getChildren();
				for (GeneralTree<T> hijo: hijos) {
					int nivelHijo = hijo.nivel(dato);
					
					//si nivelHijo >= 0 significa que el dato se encontro
					if (nivelHijo != -1) {
						return nivelHijo + 1; //devuelvo el nivel del nodo actual + 1
					}
				}
			}
			return -1;
		}

		// el ancho de un árbol se define como la cantidad de nodos que
		//se encuentran en el nivel que posee la mayor cantidad de nodos.
		public int ancho(){
			Queue<GeneralTree<T>> cola = new Queue<GeneralTree<T>>();
			GeneralTree<T> aux;
			cola.enqueue(this);
			cola.enqueue(null);
			int cantNodos = 0;
			int max = -1;
			
			while (!cola.isEmpty()) {
				aux = cola.dequeue();
				if (aux != null) {
					cantNodos++;
				
					if (aux.hasChildren()) {
						List<GeneralTree<T>> hijos = aux.getChildren();
						for (GeneralTree<T> hijo: hijos) {
							cola.enqueue(hijo);
						}
					}
				} else {
		            // Terminó un nivel: actualizo el máximo
		            if (cantNodos > max) {
		                max = cantNodos;
		            }
		            
		            // Si la cola no está vacía, hay más niveles por procesar
		            if (!cola.isEmpty()) {
		                cantNodos = 0; // REINICIO 
		                cola.enqueue(null); 
		            }
				}
			}
			return max;
		}
		
		
		//------------ ejercicio 5 ---------------
		// recorro desde la raiz hasta encontrar a, y luego busco b
		public boolean esAncestro(T a, T b) {
			// Busco el nodo A
		    GeneralTree<T> nodoA = buscarNodo(this, a);
		    
		    // Si encuentro el nodo A y no es una hoja, busco a 'b' en sus hijos
		    if (nodoA != null && !nodoA.isLeaf()) {
		        return buscarB(nodoA, b);
		    }
		    return false;
		}
			
		private GeneralTree<T> buscarNodo(GeneralTree<T> arbol, T a){
			if(arbol.getData().equals(a)) {
				return arbol;
			}
			
			if (arbol.hasChildren()) {
				for (GeneralTree<T> hijo : arbol.getChildren()) {
					GeneralTree<T> aux = buscarNodo(hijo, a);
					if (aux != null) {		// encontre A
						return aux;
					}
				}
			}
			return null;	// no encontre A
		}
		
		private boolean buscarB(GeneralTree<T> arbolA, T b) {
			// Recorro los hijos de A
			for(GeneralTree<T> hijo: arbolA.getChildren()) {
				if (hijo.getData().equals(b)) {
					return true;
				}
				
				if (buscarB(hijo, b)) {
					return true;
				}
			}
			return false;
		}
		
		public static void main(String[]args) {
			GeneralTree<Integer> a = new GeneralTree<Integer>(14);
			GeneralTree<Integer> aI = new GeneralTree<Integer>(13);
			GeneralTree<Integer> aM = new GeneralTree<Integer>(25);
			GeneralTree<Integer> aD = new GeneralTree<Integer>(10);

			a.addChild(aI);
			a.addChild(aM);
			a.addChild(aD);
			
			System.out.println(a.esAncestro(14, 25));
		}
	
}
