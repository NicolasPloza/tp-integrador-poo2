package ar.edu.unq.poo2.integrador.moduloSearch;
import java.util.*;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public abstract class Search {

	
	public final List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		
		return inmuebles.stream()
						.filter((i) -> this.cumpleCondicion(i))
						.toList();
	}
	
	public abstract boolean cumpleCondicion(Inmueble inmueble);
	
}
