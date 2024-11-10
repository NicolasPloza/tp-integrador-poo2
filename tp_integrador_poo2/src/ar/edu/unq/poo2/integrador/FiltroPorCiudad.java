package ar.edu.unq.poo2.integrador;

import java.util.List;

public class FiltroPorCiudad implements Search {

	private String ciudad;
	
	
	public FiltroPorCiudad(String ciudad) {
		this.ciudad = ciudad;	
	}
	
	public String getCiudad() {
		
		return this.ciudad;
	}
	
	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		
		return inmuebles.stream()
						.filter((i)-> i.getCiudad() == this.getCiudad())
						.toList();
	}
	
	
	
}
