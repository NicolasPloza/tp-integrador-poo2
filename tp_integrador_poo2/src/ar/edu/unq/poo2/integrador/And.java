package ar.edu.unq.poo2.integrador;

import java.util.List;

public class And implements Search {
	
	private Search s1;
	private Search s2;
	
	public And(Search s1, Search s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		
		return  s2.filtrar(s1.filtrar(inmuebles)) ;
	}

}
