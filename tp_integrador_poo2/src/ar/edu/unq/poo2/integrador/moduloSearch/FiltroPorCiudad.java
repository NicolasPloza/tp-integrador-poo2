package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorCiudad extends Search {

	private String ciudad;
	
	public FiltroPorCiudad(String ciudad) {
		this.ciudad = ciudad;	
	}
	
	public String getCiudad() {
		
		return this.ciudad;
	}
	
	
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble){
		return inmueble.getCiudad() == this.getCiudad();
	}
	
	
}
