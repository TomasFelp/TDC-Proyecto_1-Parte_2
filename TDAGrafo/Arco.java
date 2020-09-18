package TDAGrafo;

public class Arco{

//Attributes
	private Vertice v1,v2;
	private TDALista.Position<Arco> posicionEnArcos;
	private TDALista.Position<Arco> posicionEnLV1,posicionEnLV2;
//Builder
	public Arco(Vertice v1,Vertice v2) {
		// TODO Auto-generated constructor stub
		this.v1=v1;
		this.v2=v2;
	}
	
//Methods	
	
	public void setVertice1(Vertice v) {
		v1=v;
	}
	
	public void setVertice2(Vertice v) {
		v2=v;
	}
	
	public void setPosicionEnArcos(TDALista.Position<Arco> ultimoDeArcos) {
		posicionEnArcos=ultimoDeArcos;
	}
	
	public void setPosicionEnListaV1(TDALista.Position<Arco> position) {
		posicionEnLV1=position;
	}
	
	public void setPosicionEnListaV2(TDALista.Position<Arco> position) {
		posicionEnLV2=position;
	}
	
	public Vertice getVertice1() {
		return v1;
	}
	
	public Vertice getVertice2() {
		return v2;
	}
	
	public TDALista.Position<Arco> getPosicionEnArcos() {
		return posicionEnArcos;
	}
	
	public TDALista.Position<Arco> getPosicionEnListaV1() {
		return posicionEnLV1;
	}
	
	public TDALista.Position<Arco> getPosicionEnListaV2() {
		return posicionEnLV2;
		
	}
}





















