package ejercicios;

import java.util.List;

public class AnalizadorArbol {

	
	public double devolverMaximoPromedio (GeneralTree<AreaEmpresa>arbol) {
		if (arbol == null || arbol.isEmpty()) return 0;
		
		int cantNodos = 0;
		double suma = 0;
		double promedioMax = -1;
		
		Queue<GeneralTree<AreaEmpresa>> cola = new Queue<GeneralTree<AreaEmpresa>>();
		GeneralTree<AreaEmpresa> aux;
		cola.enqueue(arbol);
		cola.enqueue(null);
		
		while (!cola.isEmpty()) {
			aux = cola.dequeue();
			if (aux != null) {
				cantNodos++;
				AreaEmpresa a = aux.getData();
				suma += a.getTardanza();
				
				if (aux.hasChildren()) {
					List<GeneralTree<AreaEmpresa>> hijos = aux.getChildren();
					for(GeneralTree<AreaEmpresa> hijo: hijos) {
						cola.enqueue(hijo);
					}
				}
			} else {
				double promedioAct = suma / cantNodos;
				
				if (promedioAct > promedioMax) {
					promedioMax = promedioAct;
				}
				
				if (!cola.isEmpty()) {
					cola.enqueue(null);
					suma = 0;
					cantNodos = 0;
				}
			}
		}
		return promedioMax;
	}
	
	public static void main(String[]args) {
		// 1. Creamos el nodo raíz
		GeneralTree<AreaEmpresa> m = new GeneralTree<>(new AreaEmpresa("M", 14));

		// 2. Creamos los nodos del Nivel 1
		GeneralTree<AreaEmpresa> j = new GeneralTree<>(new AreaEmpresa("J", 13));
		GeneralTree<AreaEmpresa> k = new GeneralTree<>(new AreaEmpresa("K", 25));
		GeneralTree<AreaEmpresa> l = new GeneralTree<>(new AreaEmpresa("L", 10));

		// 3. Creamos los nodos del Nivel 2 (Hojas)
		// Hijos de J
		GeneralTree<AreaEmpresa> a = new GeneralTree<>(new AreaEmpresa("A", 4));
		GeneralTree<AreaEmpresa> b = new GeneralTree<>(new AreaEmpresa("B", 7));
		GeneralTree<AreaEmpresa> c = new GeneralTree<>(new AreaEmpresa("C", 5));

		// Hijos de K
		GeneralTree<AreaEmpresa> d = new GeneralTree<>(new AreaEmpresa("D", 6));
		GeneralTree<AreaEmpresa> e = new GeneralTree<>(new AreaEmpresa("E", 10));
		GeneralTree<AreaEmpresa> f = new GeneralTree<>(new AreaEmpresa("F", 18));

		// Hijos de L
		GeneralTree<AreaEmpresa> g = new GeneralTree<>(new AreaEmpresa("G", 9));
		GeneralTree<AreaEmpresa> h = new GeneralTree<>(new AreaEmpresa("H", 12));
		GeneralTree<AreaEmpresa> i = new GeneralTree<>(new AreaEmpresa("I", 19));

		// 4. Armamos la estructura vinculando los hijos (de izquierda a derecha)
		j.addChild(a);
		j.addChild(b);
		j.addChild(c);

		k.addChild(d);
		k.addChild(e);
		k.addChild(f);

		l.addChild(g);
		l.addChild(h);
		l.addChild(i);

		// 5. Vinculamos los hijos a la raíz M
		m.addChild(j);
		m.addChild(k);
		m.addChild(l);

		AnalizadorArbol analizador = new AnalizadorArbol();
		double maxPromedio = analizador.devolverMaximoPromedio(m);
		System.out.println("El máximo promedio es: " + maxPromedio);
	}
}
