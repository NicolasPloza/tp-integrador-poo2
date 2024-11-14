package ar.edu.unq.poo2.integrador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.Servicio;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class Sistema {
	List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private List<TipoInmueble> inmueblesAceptados = new ArrayList<TipoInmueble>();
	private List<Servicio> serviciosAceptados = new ArrayList<Servicio>();
	private List<Usuario> usuarios = new ArrayList<Usuario>(); 
	public void registrar(Reserva reserva) {
		reservas.add(reserva);
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void agregarCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void agregarTipoDeInmueble(TipoInmueble tipoInmueble) {
		this.inmueblesAceptados.add(tipoInmueble);
	}
	
	public void agregarServicio(Servicio servicio) {
		this.serviciosAceptados.add(servicio);
	}
	
	public List<Servicio> getServiciosAceptados() {
		return serviciosAceptados;
	}
	
	public void añadirUsuario(Usuario usuario) {
		this.usuarios .add(usuario);
	}

	public void cancelarReserva(Reserva reserva) {
		this.getReservas().stream().filter(r->r == reserva).findFirst().ifPresent(r->r.cancelar());
	}

	public long cantidadDeAlquileresDePropietario(Usuario propietario) {
		return this.getReservas().stream().
				filter(r->r.getPropietario() == propietario && r.estaFinalizada())
				.count();
	}
	
	public List<String> getNombresDeTiposPermitidos() {
		return this.inmueblesAceptados.stream().map(i->i.getNombre()).toList();
	}
	
	public List<String> getNombresDeServiciosPermitidos() {
		return this.serviciosAceptados.stream().map(s->s.getNombre()).toList();
	}

	public Long cantidadVecesAlquilado(Inmueble inmueble) {
		return this.getReservas().stream().
				filter(r->r.getInmueble() == inmueble)
				.count();
	}

	public List<Reserva> reservasDeInquilino(Inquilino x) {
		return this.getReservas().stream().filter(r->r.getPotencialInquilino() == x).toList();
	}

	public List<Reserva> reservasDeInquilinoFuturas(Inquilino inquilino) {
		return this.reservasDeInquilino(inquilino).stream()
				.filter(r->!r.estaFinalizada()).toList();
	}

	public List<Reserva> reservasDeInquilinoEnCiudad(Inquilino inquilino, String ciudad) {
		return this.reservasDeInquilino(inquilino).stream()
				.filter(r->r.getInmueble().getCiudad() == ciudad).toList();
	}

	public List<TipoInmueble> getTipoDeInmueblesAceptados() {
		return inmueblesAceptados;
	}
}
