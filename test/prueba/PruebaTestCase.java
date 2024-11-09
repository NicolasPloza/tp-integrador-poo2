package prueba;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PruebaTestCase {
	
	private Prueba prueba;

	@BeforeEach
	void setUp() {
		this.prueba=new Prueba(90);
	}

	@Test
	void test() {
		int numeroEsperado = 90;
		assertEquals(this.prueba.getNumero(), numeroEsperado);
	}

}
