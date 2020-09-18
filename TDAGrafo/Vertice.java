package TDAGrafo;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Vertice{

//Attributes
	private int rotulo;
	private PositionList<Arco> adyacentes;
	private TDALista.Position<Vertice> posicionEnNodos;
	
//Builder
	public Vertice(int r) {
		// TODO Auto-generated constructor stub
		rotulo=r;
		adyacentes=new DoubleLinkedList<Arco>();
	}
	
//Methods
	
	public int element() {
		return rotulo;
	}
	
	public void setRotulo(int r) {
		rotulo=r;
	}
	
	public TDALista.PositionList<Arco> getAdyacentes(){
		return adyacentes;
	}
	
	public void setPosicionEnNodos(TDALista.Position<Vertice> p) {
		posicionEnNodos=p;
	}

	public TDALista.Position<Vertice> getPosicionEnNodos(){
		return posicionEnNodos;
	}
}














