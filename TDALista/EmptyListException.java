package TDALista;

/**
 * Excepcion que se lanzara al trabajar con una lista vacia.
 * @author Tomás Felipe, lu 124441.
 */
public class EmptyListException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EmptyListException(String msg) {
		super(msg);
	}

}
