package ar.edu.unq.poo2.integrador;

import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class AppMobile implements Interesado {
	
	private PopUpWindow window;
	private String color;
	private int frontSize;
	
	public AppMobile(PopUpWindow window, String color, int frontSize) {
		this.window=window;
		this.color=color;
		this.frontSize=frontSize;
	}

	@Override
	public void notificarCancelacion(TipoInmueble tipoDeInmueble) {
		this.window.popUp(this.mensaje(tipoDeInmueble.getNombre()), this.color, this.frontSize);
	}

	@Override
	public void notificarReserva() {
		
	}

	@Override
	public void notificarBajaDePrecio(TipoInmueble tipoDeInmueble, float precio) {
		
	}
	
	private String mensaje(String tipoDeInmueble) {
		return "El/la " + tipoDeInmueble + " que te interesa se ha liberado! Corre a reservarlo!";
	}

}
