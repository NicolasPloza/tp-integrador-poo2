package ar.edu.unq.poo2.integrador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.MedioDePago;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class Reserva {
	
	private Inquilino potencialInquilino;
	private Inmueble inmueble;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private MedioDePago medioDePago;
	private List<Inquilino> inquilinosInteresados;
	private EstadoReserva estado;
	private GestionadorDeNotificaciones gestionador;
	
	public Reserva(Inquilino inquilino, Inmueble inmueble, LocalDate inicio, LocalDate fin, MedioDePago medioDePago) {
		this.potencialInquilino=inquilino;
		this.inmueble=inmueble;
		this.fechaInicio=inicio;
		this.fechaFin=fin;
		this.medioDePago=medioDePago;
		this.inquilinosInteresados=new ArrayList<Inquilino>();
		this.gestionador=new GestionadorDeNotificaciones();
		this.estado=Pendiente.getInstance();
	}
	
	public void concretar() {
		this.estado.concretar(this);
	}
	
	public void cancelar() {
		this.estado.cancelar(this);
	}
	
	public void setPotenciaInquilino(Inquilino inquilino) {
		this.potencialInquilino=inquilino;
	}
	
	public void añadirALaCola(Inquilino inquilino) {
		this.inquilinosInteresados.add(inquilino);
	}
	
	public void notificarReserva() {
		this.gestionador.notificarReserva(this.getEmailDelInquilino());
	}
	
	public String getEmailDelInquilino() {
		return this.potencialInquilino.getEmail();
	}
	
	public void notificarCancelacion() {
		this.gestionador.notificarCancelacion(this.getTipoDeInmueble(), this.getEmailDelPropietario());
	}
	
	public TipoInmueble getTipoDeInmueble() {
		return this.inmueble.getTipoDeInmueble();
	}
	
	public String getEmailDelPropietario() {
		return this.inmueble.getPropietario().getEmail();
	}
	
	public void setEstado(EstadoReserva estado) {
		this.estado=estado;
	}
	
	public boolean tieneInquilinosEncolados() {
		return !this.inquilinosInteresados.isEmpty();
	}
	
	public void procesarCola() {
		this.setPotenciaInquilino(this.inquilinosInteresados.get(0));
		this.inquilinosInteresados.remove(0);
	}
	
	public double precioParaFechaElegida() {
		return this.inmueble.getPrecioDePeriodo(this.fechaInicio, this.fechaFin);
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	public boolean estaFinalizada() {
		return this.fechaFin.isBefore(LocalDate.now()) && (this.estado.estaAceptada());
	}
	
	public Inquilino getPotencialInquilino() {
		return this.potencialInquilino;
	}
	
	public Propietario getPropietario() {
		return this.inmueble.getPropietario();
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	public void setGestionador(GestionadorDeNotificaciones gestionador) {
		this.gestionador = gestionador;
	}

}
