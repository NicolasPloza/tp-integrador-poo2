package ar.edu.unq.poo2.integrador.moduloSearch;
import java.util.*;

import ar.edu.unq.poo2.integrador.inmueble.Inmueble;

public interface Search {
	public List<Inmueble> filtrar(List<Inmueble> inmuebles);
}
