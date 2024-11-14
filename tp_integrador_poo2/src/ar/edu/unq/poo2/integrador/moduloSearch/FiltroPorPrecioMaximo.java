package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorPrecioMaximo extends Search {

private double precioMaximo;
	
	public FiltroPorPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	
	public double getPrecioMaximo() {
		return this.precioMaximo;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		
		return inmueble.getPrecioDefault() <= this.getPrecioMaximo();
	}

}
