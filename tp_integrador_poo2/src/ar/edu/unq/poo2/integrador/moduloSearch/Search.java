package ar.edu.unq.poo2.integrador.moduloSearch;
import java.time.LocalDate;
import java.util.*;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public class Search {
	
	private String ciudad;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FiltroOpcional filtro;
	
	public Search(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, FiltroOpcional filtro) {
		this.ciudad=ciudad;
		this.fechaEntrada=fechaEntrada;
		this.fechaSalida=fechaSalida;
		this.filtro=filtro;
	}
	
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		List<Inmueble> filtradoDeInmuebles = inmuebles.stream()
				.filter(inmueble -> this.cumpleCondicion(inmueble))
				.toList();
		return filtradoDeInmuebles;
	}
	
	public boolean cumpleCondicion(Inmueble inmueble) {
		return inmueble.esDeCiudad(this.ciudad) && inmueble.estaDisponibleEn(this.fechaEntrada, this.fechaSalida) && this.filtro.cumpleCondicion(inmueble);
	}
	
}
