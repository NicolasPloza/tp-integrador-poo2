package ar.edu.unq.poo2.integrador.moduloSearch;

import java.util.List;
import java.util.stream.Stream;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class Or implements Search {
	
	private Search searchIzquierdo;
	private Search searchDerecho;
	
	public Or(Search s1, Search s2) {
		this.searchIzquierdo = s1;
		this.searchDerecho = s2;
	}
	
	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		
		Stream<Inmueble> primerFiltrado = searchIzquierdo.filtrar(inmuebles).stream();
		Stream<Inmueble> segundoFiltrado = searchDerecho.filtrar(inmuebles).stream();
		
		return Stream.concat(primerFiltrado, segundoFiltrado)
					 .distinct().toList();
	}

}
