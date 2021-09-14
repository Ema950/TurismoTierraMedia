package Pruebas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase AxB.
 * @author 4Elementos
 * @version 13/09/2021 - FINAL
 * @see https://github.com/Ema950/TurismoTierraMedia
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
	public void costoTest() {
		assertEquals(10, unaPromo.costo());
	}
	@Test
	public void duracionTest() {
		assertEquals(5, unaPromo.duracion(), 0.1);
	}
	@Test
	public void existeCupoTest() {
		assertTrue(unaPromo.existeCupo());
	}

}
