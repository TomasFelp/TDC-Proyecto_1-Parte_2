package TDAGrafo;

public class Tester {

	public static void main(String[]args) {
		Graph grafo=new Graph();
		
		grafo.addNode(1);
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addEdge(1,2);
		grafo.addEdge(1,8);
		grafo.addNode(3);
		grafo.addEdge(2,3);
		grafo.addNode(4);
		grafo.addEdge(3,4);
		grafo.addEdge(3,5);
		grafo.removeEdge(8,3);
		grafo.removeNode(5);		
		grafo.removeEdge(2,3);		
		grafo.removeEdge(1,2);		
		grafo.removeEdge(1,4);		
		
		
	}
}
