package TDALista;

/**
* Excepcion utilizada cuando se viola el limite de la lista.
* @author Tomás Felipe, lu 124441.
*/

public class BoundaryViolationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoundaryViolationException(String msg) {
		super (msg);
	}

}
