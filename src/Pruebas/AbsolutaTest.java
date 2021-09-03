package Pruebas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase Absoluta
 * @author Barboza, Dario; Capandeguy, Agust�n; Fernandez, Cecilia In�s; 
 * Martinez, Leandro; Noir, Sergio Javier; Paiva, V�ctor Emanuel
 * @version 01/09/2021
 * @see https://github.com/Voidt-Team/TurismoTierraMedia
 */
public class AbsolutaTest {
	Atraccion unaAtraccion;
	Absoluta unaPromo;
	ArrayList <Atraccion> atracciones = new ArrayList<Atraccion>();
	
	@Before 
	public void setUp() {
		unaAtraccion = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atracciones.add(unaAtraccion);
		unaPromo = new Absoluta("Pack Prueba", atracciones, 3);	
	}
	@After
	public void tearDown() {
		unaAtraccion = null;
		unaPromo = null; 
	}
	@Test
	public void costoTest() {
		assertEquals(unaPromo.costo(), 7);
	}

}
