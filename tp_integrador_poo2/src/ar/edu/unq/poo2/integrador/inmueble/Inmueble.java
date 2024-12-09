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
	private boolean alquilado;
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
			double precioDefault, TipoInmueble tipoDeInmueble, List<MedioDePago> mediosDePago,
			List<Servicio> servicios, Propietario propietario, List<Foto> fotos,
			PoliticaDeCancelacion politica, List<Periodo> periodos, GestionadorDeNotificaciones gestionador, Sistema sistema) {
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.capacidad = capacidad;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.calificaciones = new ArrayList<Calificacion>();
		this.alquilado = false;
		this.precioDefault = precioDefault;
		this.tipoDeInmueble = tipoDeInmueble;
		this.mediosDePago = mediosDePago;
		this.servicios = servicios;
		this.propietario = propietario;
		this.fotos = fotos;
		this.politicaDeCancelacion = politica;
		this.periodos = periodos;
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
	
	public boolean getAlquilado() {
		return alquilado;
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
	
	public List<MedioDePago> getMediosDePago() {
		return mediosDePago;
	}
	
	public List<Servicio> getServicio() {
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

	public void setServicios(List<Servicio> nuevosServicios) {
		this.servicios = nuevosServicios;
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
		List<Calificacion> categoriasIguales = this.getCalificaciones().stream().filter(calificacion1 -> calificacion1.getCategoria().getNombre() == categoria.getNombre()).toList();
		int cantidadDeCategoriasIguales = categoriasIguales.size();
		int puntajeTotalDeUnaMismaCategoria = categoriasIguales.stream().mapToInt(c->c.getPuntaje()).sum();
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
		
		long cantidadDePuntajes;
		Double totalPuntajes;
		
		totalPuntajes=calificaciones.stream()
									.mapToDouble( c -> c.getPuntaje() )
									.sum();
		
		cantidadDePuntajes =  calificaciones.stream()
											.count();
		
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
			this.sistema.registrar(reserva);
			this.agregarReserva(reserva); 
		}else {
			this.agregarReservaCondicional(reserva);
		}
	}

	public boolean tieneMedioDePago(MedioDePago medioDePago) {
		return this.mediosDePago.stream().anyMatch(mp-> mp.getNombre().equals(medioDePago.getNombre()));
	}
	
	public List<Reserva> getReservas(){
		return this.reservas;
	}
	
	public List<Reserva> getReservasAceptadas() {
		return 	reservas.stream()
						.filter( r -> r.esAceptada() )
						.toList() ;
	}
	
	public void agregarFoto(Foto foto) {
		if(this.fotos.size() < 5) {
			this.fotos.add(foto);
		}
	}

	public boolean estaDisponibleEn(LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		return  this.getReservasAceptadas()
					.stream()
					.allMatch(r -> r.getFechaInicio().isAfter(fechaSalida) || r.getFechaFin().isBefore(fechaEntrada));
	}

	public void agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);
		
	}

	public List<Reserva> getReservasCondicionales() {
		
		return reservasCondicionales;
	}

	public void procesarReservasCondicionalesPara(LocalDate fechaInicio, LocalDate fechaFin) {
	
		Reserva reserva = 
				this.getReservasCondicionales().stream()
					.filter(r -> r.estaDentroDeFechas(fechaInicio, fechaFin)).findFirst().orElse(null);
		
		if(reserva != null) {
			this.getReservasCondicionales().remove(reserva);
			this.propietario.agregarReserva(reserva);
			this.sistema.registrar(reserva);
			this.agregarReserva(reserva);
		}
		
	}

	public void agregarReservaCondicional(Reserva reservaCondicional) {
		
		this.reservasCondicionales.add(reservaCondicional);
	}

	public int cantidadDeAlquileres() {
		
		return this.getReservas().stream()
								 .filter(r -> r.estaFinalizada())
								 .toList().size();
	}


	
	
	
}
