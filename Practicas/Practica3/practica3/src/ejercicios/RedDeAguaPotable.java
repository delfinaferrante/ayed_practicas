package ejercicios;

import java.util.List;

public class RedDeAguaPotable {
	private GeneralTree<Character> estructura;
	
	public RedDeAguaPotable(GeneralTree<Character> estructura) {
		this.estructura = estructura;
	}
	
	// calcula el caudal de cada nodo y determina cuál es el caudal mínimo que recibe una casa.
	public double minimoCaudal(double caudal) {	
		if (estructura == null && !estructura.isEmpty()) {
			return 0;
		}
		
		// Recorrido preOrden (raiz, hijos)
		return recorrerYCalcularMin(caudal, estructura);
	}
	
	private double recorrerYCalcularMin(double caudalAct, GeneralTree<Character>nodo) {
		if (nodo.isLeaf()) {
			return caudalAct;
		}
		
		// Si tiene hijos, divido el caudal
	    double caudalHijo = caudalAct / nodo.getChildren().size();
	    double min = Double.MAX_VALUE;
		
		for(GeneralTree<Character> hijo: nodo.getChildren()) {
			double caudalRama = recorrerYCalcularMin(caudalHijo, hijo);
			
			if(caudalRama < min) {
				min = caudalRama;
			}
		}
		return min;
	}	
	
	
	public static void main(String[]args) {
		/*		    A
		 * 	  /     |    \	
		 *    B     C     D
		 *  / | \   |   / | \ \
		 *  E F  G  H  I J  K  L
		
		 *   Si A (caudal) = 1000
		 *   B, C y D reciben 333
		 *   E, F y G reciben 111
		 *   H recibe 333
		 *   I, J, K  y L reciben 83,33
		 */
		
		// Nodos del Nivel 2
	    // Hijos de B
	    GeneralTree<Character> e = new GeneralTree<>('E');
	    GeneralTree<Character> f = new GeneralTree<>('F');
	    GeneralTree<Character> g = new GeneralTree<>('G');
	    
	    // Hijo de C
	    GeneralTree<Character> h = new GeneralTree<>('H');
	    
	    // Hijos de D
	    GeneralTree<Character> i = new GeneralTree<>('I');
	    GeneralTree<Character> j = new GeneralTree<>('J');
	    GeneralTree<Character> k = new GeneralTree<>('K');
	    GeneralTree<Character> l = new GeneralTree<>('L');

	    // Nodos del Nivel 1
	    GeneralTree<Character> b = new GeneralTree<>('B');
	    GeneralTree<Character> c = new GeneralTree<>('C');
	    GeneralTree<Character> d = new GeneralTree<>('D');

	    // Agrego los hijos de B, C y D
	    b.addChild(e);
	    b.addChild(f);
	    b.addChild(g);

	    c.addChild(h);

	    d.addChild(i);
	    d.addChild(j);
	    d.addChild(k);
	    d.addChild(l);

	    // Creo la raíz y le agg sus hijos
	    GeneralTree<Character> a = new GeneralTree<>('A');
	    a.addChild(b);
	    a.addChild(c);
	    a.addChild(d);
	    
	    RedDeAguaPotable red = new RedDeAguaPotable(a);
	    double minimo = red.minimoCaudal(1000);
	    System.out.println("El caudal mínimo recibido por una casa es: " + minimo + " litros.");
	}
}
