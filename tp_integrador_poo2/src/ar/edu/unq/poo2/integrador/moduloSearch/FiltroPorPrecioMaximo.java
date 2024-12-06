package ar.edu.unq.poo2.integrador.moduloSearch;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class FiltroPorPrecioMaximo extends FiltroOpcional {

	private double precioMaximo;
	
	public FiltroPorPrecioMaximo(double precioMaximo) {
		this.precioMaximo=precioMaximo;
	}
	
	@Override
	public boolean cumpleCondicion(Inmueble inmueble) {
		return inmueble.precioDefaultMenorOIgualA(this.precioMaximo);
	}

}
