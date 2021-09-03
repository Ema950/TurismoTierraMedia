package Pruebas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase AxB.
 * @author Barboza, Dario; Capandeguy, Agustín; Fernandez, Cecilia Inés; 
 * Martinez, Leandro; Noir, Sergio Javier; Paiva, Víctor Emanuel
 * @version 01/09/2021
 * @see https://github.com/Voidt-Team/TurismoTierraMedia
 */
public class AxBTest {
	
	Atraccion unaAtraccion;
	Atraccion atraccionGratis;
	AxB unaPromo;
	ArrayList <Atraccion> atracciones = new ArrayList<Atraccion>();
	
	
	@Before 
	public void setUp() {
		unaAtraccion = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atraccionGratis = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		atracciones.add(unaAtraccion);
		unaPromo = new AxB("Pack Prueba", atracciones, atraccionGratis);	
	}
	@After
	public void tearDown() {
		unaAtraccion = null;
		atraccionGratis= null;
		unaPromo = null; 
	}
	@Test
	public void test() {
		assertEquals(unaPromo.costo(), 10);
	}

}
