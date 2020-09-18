package TDALista;

/**
 * Excepcion empleada cuando se intenta acceder a una comopenete de la lista invalida.
 * 	@author Tomás Felipe, lu 124441.
 */
public class InvalidPositionException extends Exception{
	

	private static final long serialVersionUID = 1L;

	public InvalidPositionException(String msg) {
		super(msg);
	}
}
