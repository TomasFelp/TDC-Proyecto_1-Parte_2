package TDALista;

import java.util.Iterator;

/**
 * Lista implementada mediante nodos doblemente enlazados.
 * @author Tomás Felipe, lu 124441
 *
 * @param <E> Tipo de los elementos que almacenara la lista.
 */
public class DoubleLinkedList<E> implements PositionList<E>{

	
//Attributes 
	
	private DNodo<E> head;
	private DNodo<E> tail;
	private int tamanio;
	
//Builder
	/**
	 * Inicializa los atributos de la cola, referenciando los atributos correspondientes a los nodos centinelas.
	 */
	public DoubleLinkedList() {
		head=new DNodo<E>(null);
		tail=new DNodo<E>(null);
		head.setSiguiente(tail);
		tail.setPrev(head);
		tamanio=0;
	}
	
//Methods
  //Commands
	
	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub

		DNodo<E> nuevo=new DNodo<E>(element,head.getSiguiente(),head);
		head.getSiguiente().setPrev(nuevo);
		head.setSiguiente(nuevo);
		tamanio++;

	}
	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub
		DNodo<E> nuevo=new DNodo<E>(element,tail,tail.getPrev());
		tail.getPrev().setSiguiente(nuevo);
		tail.setPrev(nuevo);
		tamanio++;
	}
	
	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		DNodo<E> pos=checkPosition(p);
		DNodo<E> nuevo=new DNodo<E>(element,pos.getSiguiente(),pos);
		pos.setSiguiente(nuevo);
		nuevo.getSiguiente().setPrev(nuevo);
		tamanio++;
	}
	
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		DNodo<E> pos=checkPosition(p);
		DNodo<E> nuevo=new DNodo<E>(element,pos,pos.getPrev());
		pos.setPrev(nuevo);
		nuevo.getPrev().setSiguiente(nuevo);
		tamanio++;
	}
	
	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		DNodo<E> pos=checkPosition(p);
		E resp=pos.element();
		pos.setElemt(null);
		pos.getSiguiente().setPrev(pos.getPrev());
		pos.getPrev().setSiguiente(pos.getSiguiente());
		tamanio--;
		return resp;
	}
	
	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		DNodo<E> pos=checkPosition(p);
		E resp=pos.element();
		pos.setElemt(element);
		return resp;
	}
	
  //Queries
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tamanio;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tamanio==0 && head.getSiguiente()==tail;
	}
	@Override
	public Position<E> first() throws EmptyListException {
		// TODO Auto-generated method stub
		if(head.getSiguiente()==tail)
			throw new EmptyListException("La lista esta vacia");
		return head.getSiguiente();
	}
	@Override
	public Position<E> last() throws EmptyListException {
		// TODO Auto-generated method stub
		if(tamanio==0)
				throw new EmptyListException("Lista vacia, no se puede retornar la ultima posicion");
		return tail.getPrev();
	}
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		DNodo<E> resp=checkPosition(p);
		if(resp==tail.getPrev())
				throw new BoundaryViolationException("Se pidio next del ultimo");
		return resp.getSiguiente();
	}
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		DNodo<E> resp=checkPosition(p);
		if(resp.getPrev()==head) {
			throw new BoundaryViolationException("prev() error::se pidio el elemento anterior al primero");
		}
		if(isEmpty())
			throw new InvalidPositionException("La lista esta vacia");
		return resp.getPrev();
	}
	
	
	/**
	 * Determina si la posicion p es valida, esto es que no sea nula, tenga elemento y este en la lista.
	 * @param p posicion que se quiere validar.
	 * @return p cateado a nodo en caso de p ser una posicion valida de la lista.
	 * @throws InvalidPositionException si la posicion es nula, no contiene elementos o no pertenece a la lista.
	 */
	private DNodo<E> checkPosition( Position<E> p )throws InvalidPositionException {
	
	try {
		
		if( p == null ) 
			throw new InvalidPositionException("Posición nula");
		
		if (p.element() == null)
			throw new InvalidPositionException("p eliminada previamente");
		
		return (DNodo<E>) p;
		
	} catch( ClassCastException e ) {
		throw new InvalidPositionException( "p no es un nodo de lista" );
		}
	}
	
	
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new IteratorDoubleList<E>(this);
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		
		PositionList<Position<E>> iterable;
		iterable = new DoubleLinkedList<Position<E>>();
		DNodo<E> actual = head.getSiguiente();
		
		while(actual != tail){
			iterable.addLast(actual);
			actual = actual.getSiguiente();
		}
		return  iterable;
	}
	/*
	public void eliminar(PositionList<E> l){
	    DNodo<E> nodo;
	    for(E elem:l){
	      nodo=pertenece(elem);
	      if(nodo!=null)
	        remove(nodo);
	    }
	                          
	  }

	  private DNodo<E> pertenece(E elem){
	    DNodo<E> resp=null;
	    DNodo<E> actual;
	    boolean encontre=false;
	      if(!isEmpty()){
	        actual=head.getSiguiente();
	        while(actual!=tail && encontre==false){
	          if(actual.element().equals(elem)){
	            encontre=true;
	            resp=actual;
	          } 
	        }
	      } 

	    return resp;
	  }
	  */
	/*
	public void concatenar(PositionList<E> L){
		  Iterator<E> it=L.iterator();

		  
		    while(it.hasNext()){
		      addLast(it.next());
		    }
		 
		}	
	*/
	
//Inner class
	
	/**
	 * Modela los nodos que conforman el estado interno de la lista.
	 * @author Tomás Felipe, lu 124441.
	 *
	 * @param <A> tipo de dato de los elementos que almacena cada nodo.
	 */
	private class DNodo<A> implements Position<A>{

	//Attributes 
		private A element;
		private DNodo<A> siguiente;
		private DNodo<A> anterior;
		
	//Builder
		
		/**
		 * Inicializa los valores de los atributos al momento de crear un nuevo objeto nodo.
		 * @param elem Elemento que contendra el nodo.
		 * @param sig Siguiente nodoal actual.
		 * @param ant Nodo anterior al actual.
		 */
		private DNodo(A elem,DNodo<A> sig,DNodo<A> ant){
			element=elem;
			siguiente=sig;
			anterior=ant;
		}
		
		/**
		 * Inicializa los valores de los atributos al momento de crear un nuevo objeto nodo,
		 * atributos no especificdos se inicializan en null.
		 * @param elem elemento que contendra el nodo.
		 */
		private DNodo(A elem){
			this(elem,null,null);
		}
		
	//Methods
	  //Commands
		/**
		 * Remplaza la referencia del atributo siguiente por la del parametro.
		 * @param sig Nueva referencia del nodo siguiente.
		 */
		private void setSiguiente(DNodo<A> sig) {
			siguiente=sig;
		}
		
		/**
		 * Remplaza la referencia del atributo anterior por la del parametro.
		 * @param prev Nueva referencia de del nodo anterior.
		 */
		private void setPrev(DNodo<A> prev) {
			anterior=prev;
		}
		
		/**
		 * Remplaza ele lemento actual que contiene el nodo por el del parametro.
		 * @param elem Nuevo valor del atributo elemento.
		 */
		private void setElemt(A elem) {
			element=elem;
		}
		
		
	  //Queries
		
		@Override
		public A element() {
			return element;
		}
		
		/**
		 * Devuelve el siguiente nodo al actual.
		 * @return el atributo seguiente.
		 */
		private DNodo<A> getSiguiente() {
			return siguiente;
		}
		
		/**
		 * Devuelve el nodo anterior al actual.
		 * @return el atributo anterior.
		 */
		private DNodo<A> getPrev() {
			return anterior;
		}

	}
	
}


























