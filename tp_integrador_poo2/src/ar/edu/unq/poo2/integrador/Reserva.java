package ar.edu.unq.poo2.integrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.MedioDePago;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class Reserva {
	
	private Inquilino potencialInquilino;
	private Propietario propietario;
	private Inmueble inmueble;
	private Date fechaInicio;
	private Date fechaFin;
	private MedioDePago medioDePago;
	private List<Inquilino> inquilinosInteresados;
	private EstadoReserva estado;
	private GestionadorDeNotificaciones gestionador;
	
	public Reserva(Inquilino inquilino, Propietario propietario, Inmueble inmueble, Date inicio, Date fin, MedioDePago medioDePago, GestionadorDeNotificaciones gestionador) {
		this.potencialInquilino=inquilino;
		this.propietario=propietario;
		this.inmueble=inmueble;
		this.fechaInicio=inicio;
		this.fechaFin=fin;
		this.medioDePago=medioDePago;
		this.inquilinosInteresados=new ArrayList<Inquilino>();
		this.gestionador=gestionador;
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
	
	private String getEmailDelInquilino() {
		return this.potencialInquilino.getEmail();
	}
	
	public void notificarCancelacion() {
		this.gestionador.notificarCancelacion(this.getTipoDeInmueble(), this.getEmailDelPropietario());
	}
	
	private TipoInmueble getTipoDeInmueble() {
		return this.inmueble.getTipoDeInmueble();
	}
	
	private String getEmailDelPropietario() {
		return this.propietario.getEmail();
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
		return this.inmueble.getPrecioParaFecha(this.fechaInicio, this.fechaFin);
	}

}
