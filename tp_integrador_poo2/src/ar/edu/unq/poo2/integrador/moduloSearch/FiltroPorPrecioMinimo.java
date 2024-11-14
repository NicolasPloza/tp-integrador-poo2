package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorPrecioMinimo extends Search {
	
	private double precioMinimo;
	
	public FiltroPorPrecioMinimo(double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}
	
	public double getPrecioMinimo() {
		return this.precioMinimo;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		
		return inmueble.getPrecioDefault() >= this.getPrecioMinimo();
	}

}
