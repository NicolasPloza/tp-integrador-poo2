package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class And extends Search {
	
	private Search searchIzquierdo;
	private Search searchDerecho;
	
	public And(Search s1, Search s2) {
		this.searchIzquierdo = s1;
		this.searchDerecho = s2;
	}
	
	/*public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		
		return inmuebles.stream()
				.filter((i)-> cumpleCondicion(i))
				.toList();
	}*/
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		return 	searchIzquierdo.cumpleCondicion(inmueble) && 
				searchDerecho.cumpleCondicion(inmueble);
	}

}
