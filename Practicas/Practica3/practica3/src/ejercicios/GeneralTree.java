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
	
}
