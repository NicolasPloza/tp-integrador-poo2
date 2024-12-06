package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorPrecioMinimo extends FiltroOpcional {
	
	private double precioMinimo;
	
	public FiltroPorPrecioMinimo(double precioMinimo) {
		this.precioMinimo=precioMinimo;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		return inmueble.precioDefaultMayorOIgualA(this.precioMinimo);
	}

}
