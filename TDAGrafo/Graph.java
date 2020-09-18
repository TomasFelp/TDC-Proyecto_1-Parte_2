package TDAGrafo;

import TDALista.*;
import  java.util.logging.*;
 
public class Graph{

//Attributes
	private PositionList<Vertice> vertices;
	private PositionList<Arco> arcos;
	private static Logger bitacora;
	
//Builder
	public Graph() {
		vertices=new DoubleLinkedList<Vertice>(); 
		arcos=new DoubleLinkedList<Arco>();
		
		if(bitacora==null) {
			bitacora=Logger.getLogger(Graph.class.getName());
			Handler manejador=new ConsoleHandler();
			manejador.setLevel(Level.FINE);
			bitacora.addHandler(manejador);
			
			bitacora.setLevel(Level.INFO);
			
			Logger rootLogger=bitacora.getParent();
			for(Handler h:rootLogger.getHandlers()) {
				h.setLevel(Level.OFF);
			}
		}
	}
	
//Methods
	
	public void addNode(int node) {
		TDALista.Position<Vertice> actual;
		TDALista.Position<Vertice> ultimo;
		boolean encontre=false;
		try {
			if(vertices.isEmpty()){
				vertices.addLast(new Vertice(node));
				bitacora.info("El nodo "+node+" se agrego al grafo.");
			}else{
				 
				actual= vertices.first();
				ultimo= vertices.last();
				
				while(actual!=ultimo && encontre==false) {
					if(actual.element().element()==node)
						encontre=true;
					 else
						actual=vertices.next(actual);
				}
				if(ultimo.element().element()==node)
					encontre=true;
				
				if(encontre==false) {
					vertices.addLast(new Vertice(node));
					bitacora.info("El nodo "+node+" se agrego al grafo.");
				}else
					bitacora.warning("No se puede agregar el nodo "+node+", porque ya se encuentra en el grafo.");
			 }
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	public void removeNode(int node){
		TDALista.Position<Vertice> actual;
		TDALista.Position<Vertice> ultimo;
		boolean encontre=false;
		try {
			if(!vertices.isEmpty()){
				 
				actual= vertices.first();
				ultimo= vertices.last();
				
				while(actual!=ultimo && encontre==false){
					if(actual.element().element()==node){
						encontre=true;
						removerArcosAdyacentes(actual.element());
						vertices.remove(actual);
					}else
						actual=vertices.next(actual);
				}
				if(ultimo.element().element()==node) {
					removerArcosAdyacentes(ultimo.element());
					vertices.remove(ultimo);
					encontre=true;
				}
			 }
			
			if(encontre==false)
				bitacora.warning("No se puede remover el nodo "+node+", porque no pertenece al grafo.");
			 else
				bitacora.info("El nodo "+node+" se removio del grafo.");
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	
	}
	
	/*
	 * Elimina del grafo todos los arcos que incluyen el nodo pasado por parametro.
	 */
	public void removerArcosAdyacentes(Vertice v) {
		try {
			for(Arco a:v.getAdyacentes()) {
				arcos.remove(a.getPosicionEnArcos());
			}
		}catch(InvalidPositionException e) {
			e.getStackTrace();
		}
	}
	
	public void addEdge(int node1,int node2) {
		Vertice v1=buscarNodo(node1);
		Vertice v2=buscarNodo(node2);
		Arco nuevo;
		PositionList<Arco> listaV1,listaV2;
		try {
			if(v1==null)
				bitacora.warning("No se puede agregar el arco ("+node1+","+node2+"), porque el nodo "+node1+" no pertence al grafo.");
			 else
				 if(v2==null)
					 bitacora.warning("No se puede agregar el arco ("+node1+","+node2+"), porque el nodo "+node2+" no pertence al grafo.");
				  else
					if(buscarArco(v1,v2)==null) {
						nuevo=new Arco(v1, v2);
						arcos.addLast(nuevo);
						nuevo.setPosicionEnArcos(arcos.last());
						
						listaV1=v1.getAdyacentes();
						listaV1.addLast(nuevo);
						nuevo.setPosicionEnListaV1(listaV1.last());
						
						listaV2=v2.getAdyacentes();
						listaV2.addLast(nuevo);
						nuevo.setPosicionEnListaV2(listaV2.last());
						bitacora.info("El arco ("+node1+","+node2+") fue agregado grafo.");
				
			}else {
				bitacora.warning("No se puede agregar el arco ("+node1+","+node2+"), porque ya pertence al grafo.");
			}
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
			
	
		
	}
	
	private Arco buscarArco(Vertice v1,Vertice v2) {
		PositionList<Arco> adyacentes=v1.getAdyacentes();
		TDALista.Position<Arco> actual;
		TDALista.Position<Arco> ultimo;
		Arco resp=null;
		boolean encontre=false;
		try {
			if(!adyacentes.isEmpty()){
				 
				actual= adyacentes.first();
				ultimo= adyacentes.last();
				
				while(actual!=ultimo && encontre==false){
					if(actual.element().getVertice2()==v2){
						resp=actual.element();
						encontre=true;
					}else
						actual=adyacentes.next(actual);
				}
				if(ultimo.element().getVertice2()==v2)
					resp=ultimo.element();
			 }
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
	private Vertice buscarNodo(int node) {
		Vertice resp=null;
		TDALista.Position<Vertice> actual;
		TDALista.Position<Vertice> ultimo;
		boolean encontre=false;
		Vertice verticeActual;
		try {
			if(!vertices.isEmpty()){
				 
				actual= vertices.first();
				ultimo= vertices.last();
				
				while(actual!=ultimo && encontre==false){
					verticeActual=actual.element();
					if(verticeActual.element()==node){
						encontre=true;
						resp=verticeActual;
					}else
						actual=vertices.next(actual);
				}
				if(ultimo.element().element()==node)
					resp=ultimo.element();
			 }
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
	public void removeEdge(int node1, int node2) {
		Vertice v1=buscarNodo(node1);
		Vertice v2=buscarNodo(node2);
		Arco arco;
		
		try {
			if(v1==null)
				bitacora.warning("No se puede remover el arco ("+node1+","+node2+"), porque el nodo "+node1+" no pertence al grafo.");
			 else
				 if(v2==null)
						bitacora.warning("No se puede remover el arco ("+node1+","+node2+"), porque el nodo "+node2+" no pertence al grafo.");
				  else {		  
					 arco=buscarArco(v1,v2);
					 if(arco!=null) {
					     arcos.remove(arco.getPosicionEnArcos());
					 	 v1.getAdyacentes().remove(arco.getPosicionEnListaV1());
					 	 v2.getAdyacentes().remove(arco.getPosicionEnListaV2());
					 	 bitacora.info("El arco ("+node1+","+node2+") se removio del grafo");
					 }else {
						 bitacora.warning("No se puede remover el arco ("+node1+","+node2+"), porque no pertence al grafo.");
					 }
				  }
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String resp="";
		resp+="nodos:";
		for(Vertice v:vertices) {
			resp+=v.element()+",";
		}
		resp+="\narcos: ";
		for(Arco a:arcos) {
			resp+="("+a.getVertice1().element()+","+a.getVertice2().element()+"),";
		}
		return resp;
	}
	
}



















