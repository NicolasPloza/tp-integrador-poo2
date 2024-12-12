package ar.edu.unq.poo2.integrador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;
import ar.edu.unq.poo2.integrador.inmueble.Servicio;
import ar.edu.unq.poo2.integrador.inmueble.TipoInmueble;

public class Sistema {
	
	private List<Reserva> reservas;
	private EnumMap<Calificable, List<Categoria>> categorias;
	private List<TipoInmueble> inmueblesAceptados;
	private List<Servicio> serviciosAceptados;
	private List<Usuario> usuarios;
	private List<Inmueble> inmuebles;
	
	public Sistema() {
		this.reservas = new ArrayList<Reserva>();
		this.categorias = new EnumMap<Calificable, List<Categoria>>(Calificable.class);
		this.inmueblesAceptados = new ArrayList<TipoInmueble>();
		this.serviciosAceptados = new ArrayList<Servicio>();
		this.usuarios = new ArrayList<Usuario>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.categorias.put(Calificable.INQUILINO, new ArrayList<Categoria>());
		this.categorias.put(Calificable.PROPIETARIO, new ArrayList<Categoria>());
		this.categorias.put(Calificable.INMUEBLE, new ArrayList<Categoria>());
	}
	
	public void registrarInmueble(Inmueble inmueble) {
		if(this.acepta(inmueble.getTipoDeInmueble(), inmueble.getServicios())) this.inmuebles.add(inmueble);
	}
	
	public List<Inmueble> getInmuebles() {
		
		return this.inmuebles;
	}
	
	public void registrar(Reserva reserva) {
		reservas.add(reserva);
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void agregarCategoria(Calificable calificable, Categoria categoria) {
		List<Categoria> listaDeCategorias = this.categorias.get(calificable);
		listaDeCategorias.add(categoria);
		this.categorias.put(calificable, listaDeCategorias);
	}
	
	public List<Categoria> getCategoriasPara(Calificable calificable) {
		return this.categorias.get(calificable);
	}
	
	public boolean tieneCategoriaPara(Calificable calificable, Categoria categoria) {
		List <Categoria> listaDeCategorias = this.categorias.get(calificable);
		return listaDeCategorias.contains(categoria);
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
		this.usuarios.add(usuario);
	}

	public List<String> getNombresDeTiposPermitidos() {
		return this.inmueblesAceptados.stream().map(i->i.getNombre()).toList();
	}
	
	public List<String> getNombresDeServiciosPermitidos() {
		return this.serviciosAceptados.stream().map(s->s.getNombre()).toList();
	}
	
	public List<TipoInmueble> getTipoDeInmueblesAceptados() {
		return inmueblesAceptados;
	}
	
	public boolean acepta(TipoInmueble tipoDeInmueble, List<Servicio> servicios) {
		return (this.inmueblesAceptados.contains(tipoDeInmueble) && this.serviciosAceptados.containsAll(servicios));
	}
	
	public List<Usuario> topTenDeInquilinos() {
        List<Usuario> inquilinos = this.usuarios.stream()
                .filter(usuario -> usuario.esInquilino())
                .sorted(Comparator.comparing(Usuario::getCantidadDeAlquileres).reversed())
                .limit(10)
                .toList();
        return inquilinos;
    }
	
	public boolean aceptaServicio(Servicio servicio) {
		return this.serviciosAceptados.contains(servicio);
	}

	public List<Inmueble> getInmueblesLibres() {
		
		return this.getInmuebles()
					.stream()
					.filter(inmueble -> !(inmueble.estaAlquilado()) ).toList() ;
	}

	public double getTasaDeOcupacion() {
		
		double inmueblesAlquilados =   this.inmuebles
										.stream()
										.filter(inmueble -> inmueble.estaAlquilado())
										.toList().size();	
		
		return  inmueblesAlquilados / this.inmuebles.size() ; 
	}
	


	

	
	
}
