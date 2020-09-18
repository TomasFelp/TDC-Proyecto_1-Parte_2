package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que modela una lista de todos los ieradores de una DoubleLinkedList.
 * @author Tomás Felipe, lu 124441.
 *
 * @param <E> Tipo de elementos que almacena la lista que crea el objeto IteratorDoubleList.
 */
public class IteratorDoubleList<E> implements Iterator<E> {

	private PositionList<E> lista;
	private Position<E> cursor;
	
	/**
	 * Define la lista iterable, y la posicion del cursor al momento de crear un objetoIteratorDoubleList.
	 * @param l Lista de la cual devolvera los ieradores.
	 */
	public IteratorDoubleList(PositionList<E> l) {
		
			try {
				
				lista=l;
				if(lista!=null && !lista.isEmpty())
						cursor=l.first();
					else
						cursor=null;
				
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	/**
	 * Consulta si el lemento iterable tiene un siguiente
	 * @return true si hay siguiente, false de lo contrario.
	 */
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return cursor!=null && cursor.element()!=null;
	}
	
	/**
	 * Devuelve el siguiente elemento iterable de la lista.
	 * @return siguiente elemento iterable del actual.
	 */
	public E next() {
		// TODO Auto-generated method stub
		E resp=null;
		try {
			if(cursor==null)
					throw new NoSuchElementException("Error::no hay siguiente");
			 resp=cursor.element();
			cursor=(cursor==lista.last())?null: lista.next(cursor);
			
			
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}

}
