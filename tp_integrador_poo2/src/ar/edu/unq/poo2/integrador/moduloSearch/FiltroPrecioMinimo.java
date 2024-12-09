package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPrecioMinimo implements FiltroOpcional {
	
	private double precioMinimo;
	
	public FiltroPrecioMinimo(double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		return inmueble.precioDefaultMayorOIgualA(this.precioMinimo);
	}

}
