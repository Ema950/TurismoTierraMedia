package Pruebas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase Promocion
 * @author Barboza, Dario; Capandeguy, Agustín; Fernandez, Cecilia Inés; 
 * Martinez, Leandro; Noir, Sergio Javier; Paiva, Víctor Emanuel
 * @version 01/09/2021
 * @see https://github.com/Voidt-Team/TurismoTierraMedia
 */
public class PromocionTest {
	
	Atraccion unaAtraccion;
	Promocion unaPromo;
	ArrayList <Atraccion> atracciones = new ArrayList<Atraccion>();
	
	@Before 
	public void setUp() {
		unaAtraccion = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atracciones.add(unaAtraccion);
		unaPromo = new Absoluta("Pack Prueba", atracciones, 36);	
	}
	@After
	public void tearDown() {
		unaAtraccion = null;
		unaPromo = null; 
	}

	@Test
	public void duracionTest() {
		assertEquals(unaPromo.duracion(), 2, 0.1);
	}

}
