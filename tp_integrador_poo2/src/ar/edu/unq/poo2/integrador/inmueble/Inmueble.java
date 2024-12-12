package ar.edu.unq.poo2.integrador.inmueble;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.poo2.integrador.*;

public class Inmueble implements Rankeable{

	private String pais;
	private String ciudad;
	private double superficie;
	private int capacidad;
	private String checkIn;
	private String checkOut;
	private List<Calificacion> calificaciones;
	private double precioDefault;
	private TipoInmueble tipoDeInmueble;
	private Propietario propietario;
	private List<Foto> fotos;
	private List<MedioDePago> mediosDePago;
	private List<Servicio> servicios;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private List<Periodo> periodos;
	private GestionadorDeNotificaciones gestionadorDeNotificaciones;
	private List<String> comentarios;
	private Sistema sistema;
	private List<Reserva> reservas;
	private List<Reserva> reservasCondicionales;
	
	public Inmueble(double superficie, String pais, String ciudad, int capacidad, String checkIn, String checkOut,
			double precioDefault, TipoInmueble tipoDeInmueble, Propietario propietario,
			PoliticaDeCancelacion politica, GestionadorDeNotificaciones gestionador, Sistema sistema) {
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.capacidad = capacidad;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.calificaciones = new ArrayList<Calificacion>();
		this.precioDefault = precioDefault;
		this.tipoDeInmueble = tipoDeInmueble;
		this.mediosDePago = new ArrayList<MedioDePago>();
		this.servicios = new ArrayList<Servicio>();
		this.propietario = propietario;
		this.fotos = new ArrayList<Foto>();
		this.politicaDeCancelacion = politica;
		this.periodos = new ArrayList<Periodo>();
		this.gestionadorDeNotificaciones = gestionador;
		this.comentarios = new ArrayList<String>();
		this.sistema=sistema;
		this.reservas = new ArrayList<Reserva>();
		this.reservasCondicionales = new ArrayList<Reserva>();
	}
	
	public String getPais() {
		return pais;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public double getSuperficie() {
		return superficie;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public String getCheckOut() {
		return checkOut;
	}
	
	public String getCheckIn() {
		return checkIn;
	}
	
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	
	public double getPrecioDefault() {
		return precioDefault;
	}
	
	public TipoInmueble getTipoDeInmueble() {
		return tipoDeInmueble;
	}
	
	public void agregarMedioDePago(MedioDePago medioDePago) {
		this.mediosDePago.add(medioDePago);
	}
	
	public List<MedioDePago> getMediosDePago() {
		return mediosDePago;
	}
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return politicaDeCancelacion;
	}
	
	public void agregarServicio(Servicio servicio) {
		if(this.sistema.aceptaServicio(servicio)) this.servicios.add(servicio);
	}
	
	public void setPrecioDefault(double nuevoPrecioDefault) {
		if(nuevoPrecioDefault < this.getPrecioDefault()) {
			this.notificarBajaDePrecio(nuevoPrecioDefault);
		} 
		this.precioDefault = nuevoPrecioDefault;
	}
	
	private void notificarBajaDePrecio(double precioANotificar) {
		this.getGestionadorDeNotificaciones().notificarBajaDePrecio(this.getTipoDeInmueble(), precioANotificar);
	}
	
	public GestionadorDeNotificaciones getGestionadorDeNotificaciones() {
		return gestionadorDeNotificaciones;
	}

	public double costoDeCancelacion(Reserva reserva) {
		return this.getPoliticaDeCancelacion().costo(reserva);
	}

	public void agregarCalificacion(Calificacion calificacion) {
		if(this.sistema.tieneCategoriaPara(Calificable.INMUEBLE, calificacion.getCategoria())) {
			this.calificaciones.addFirst(calificacion);
		}
	}

	public double getPromedio(Categoria categoria) {
		List<Calificacion> categoriasIguales = this.getCalificaciones().stream().filter(calificacion1 -> calificacion1.getCategoria().getNombre().equals(categoria.getNombre())).toList();
		int cantidadDeCategoriasIguales = categoriasIguales.size();
		int puntajeTotalDeUnaMismaCategoria = categoriasIguales.stream().mapToInt(calificacion->calificacion.getPuntaje()).sum();
		return puntajeTotalDeUnaMismaCategoria / cantidadDeCategoriasIguales;
	}

	public double getPrecioDePeriodo(LocalDate fechaDeInicio, LocalDate fechaDeFin) {
		double precioDePeriodo = 0.0;
		while(!fechaDeInicio.isAfter(fechaDeFin)) {
			if(this.perteneceAAlgunPeriodo(fechaDeInicio)) {
				precioDePeriodo += this.getPeriodo(fechaDeInicio).getPrecioPorDia();
			} else {
				precioDePeriodo += this.getPrecioDefault();
			}
			fechaDeInicio = fechaDeInicio.plusDays(1);
		} 
		return precioDePeriodo;
	}
	
	public Periodo getPeriodo(LocalDate fechaDeInicio) {
		return this.getPeriodos().stream().filter(p->p.esFechaDePeriodo(fechaDeInicio)).findFirst().orElse(null);
	}
	
	private boolean perteneceAAlgunPeriodo(LocalDate fechaDeInicio) {
		return this.getPeriodos().stream().anyMatch(p->p.esFechaDePeriodo(fechaDeInicio));
	}
	
	public void agregarPeriodo(Periodo periodo) {
		this.periodos.add(periodo);
	}
	
	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void agregarComentario(String comentario) {
		this.comentarios.add(comentario);
	}

	public List<String> getComentarios() {
		return comentarios;
	}
	
	public double getPromedioTotalDePuntajes() {
		long cantidadDePuntajes = calificaciones.stream()
				.count();
		Double totalPuntajes = calificaciones.stream()
				.mapToDouble(calificacion -> calificacion.getPuntaje())
				.sum();
		double promedio = totalPuntajes/cantidadDePuntajes;
		return Math.round(promedio * 100.0) / 100.0;
	}

	public boolean tieneCapacidadDe(int cantHuespedes) {
		return this.capacidad == cantHuespedes;
	}

	public boolean esDeCiudad(String ciudad) {
		return this.ciudad.equals(ciudad);
	}

	public boolean precioDefaultMenorOIgualA(double precioMaximo) {
		return this.precioDefault <= precioMaximo;
	}

	public boolean precioDefaultMayorOIgualA(double precioMinimo) {
		return this.precioDefault >= precioMinimo;
	}
	
	public void reservar(Inquilino inquilino, LocalDate fechaInicial, LocalDate fechaFin, MedioDePago medioDePago) {
		
		if(!this.tieneMedioDePago(medioDePago)) {
			throw new RuntimeException("El medio de pago ingresado no es valido");
		}
		
		
		Reserva reserva = new Reserva(inquilino, this, fechaInicial, fechaFin, medioDePago);
		
		if(this.estaDisponibleEn(fechaInicial, fechaFin) ) {						
			this.propietario.agregarReserva(reserva);
			inquilino.agregarReserva(reserva);
			this.sistema.registrar(reserva);
			this.reservas.add(reserva);
		}else {
			this.reservasCondicionales.add(reserva);
		}
	}

	public boolean tieneMedioDePago(MedioDePago medioDePago) {
		boolean tieneElMedioDePago = this.mediosDePago.stream()
				.anyMatch(pago-> pago.getNombre().equals(medioDePago.getNombre()));
		return tieneElMedioDePago;
	}
	
	public List<Reserva> getReservas(){
		return this.reservas;
	}
	
	public void agregarFoto(Foto foto) {
		if(this.fotos.size() < 5) this.fotos.add(foto);
	}

	public boolean estaDisponibleEn(LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		return  this.getReservas()
					.stream()
					.allMatch(reserva -> reserva.getFechaInicio().isAfter(fechaSalida) || reserva.getFechaFin().isBefore(fechaEntrada));
	}

	public List<Reserva> getReservasCondicionales() {
		
		return reservasCondicionales;
	}

	public void procesarReservasCondicionalesPara(LocalDate fechaInicio, LocalDate fechaFin) {
		Reserva reserva = this.getReservasCondicionales().stream()
					.filter(reservaCondicional -> reservaCondicional.estaDentroDeFechas(fechaInicio, fechaFin))
					.findFirst().orElse(null);
		if(reserva != null) {
			this.getReservasCondicionales().remove(reserva);
			this.propietario.agregarReserva(reserva);
			this.sistema.registrar(reserva);
			this.reservas.add(reserva);
			
		}
		
	}

	public int cantidadDeAlquileres() {
		int cantidad = this.getReservas().stream()
				.filter(reserva -> reserva.estaFinalizada())
				.toList()
				.size(); 
		return cantidad;
	}


	public boolean estaAlquilado() {
		boolean alquilado = this.reservas.stream()
				.anyMatch(reserva -> reserva.esFechaDeReservaAceptada(LocalDate.now()));
		return alquilado;
	}
	
}
