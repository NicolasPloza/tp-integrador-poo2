package ar.edu.unq.poo2.integrador.moduloSearch;

import java.util.Arrays;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class Or implements FiltroOpcional {
	
	private List<FiltroOpcional> filtros;
	
	public Or(FiltroOpcional filtroIzquierdo, FiltroOpcional filtroDerecho) {
		this.filtros = Arrays.asList(filtroIzquierdo, filtroDerecho);
	}
	
	public FiltroOpcional getFiltroIzquierdo() {
		return this.filtros.get(0);
	}
	
	public FiltroOpcional getFiltroDerecho() {
		return this.filtros.get(1);
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		return this.getFiltroIzquierdo().cumpleCondicion(inmueble) || this.getFiltroDerecho().cumpleCondicion(inmueble);
	}
	
}
