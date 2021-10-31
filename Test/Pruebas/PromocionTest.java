package Pruebas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase Promocion
 * @author 4Elementos
 * @version 13/09/2021 - FINAL
 * @see https://github.com/Ema950/TurismoTierraMedia
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
		assertEquals(2, unaPromo.duracion(), 0.1);
	}
	
	@Test
	public void existeCupoTest() {
		assertTrue(unaPromo.existeCupo());
		unaAtraccion.setCupo(0);
		assertFalse(unaPromo.existeCupo());
	}
	
	@Test
	public void contieneAtraccionTest() {
		assertTrue(unaPromo.contieneAtraccion(unaAtraccion));
		Atraccion otraAtraccion = new Atraccion("PRUEBA", 2, 4, 6, TipoAtraccion.DEGUSTACION);
		assertFalse(unaPromo.contieneAtraccion(otraAtraccion));
	}
	
	@Test
	public void contieneTipoTest() {
		assertTrue(unaPromo.contieneTipo(TipoAtraccion.AVENTURA));
		assertFalse(unaPromo.contieneTipo(TipoAtraccion.DEGUSTACION));
	}
}
