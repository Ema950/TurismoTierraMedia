package Pruebas;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase Porcentual
 * @author Barboza, Dario; Capandeguy, Agustín; Fernandez, Cecilia Inés; 
 * Martinez, Leandro; Noir, Sergio Javier; Paiva, Víctor Emanuel
 * @version 01/09/2021
 * @see https://github.com/Voidt-Team/TurismoTierraMedia
 */
public class PorcentualTest {
	Atraccion unaAtraccion;
	Porcentual unaPromo;
	ArrayList <Atraccion> atracciones = new ArrayList<Atraccion>();
	
	@Before 
	public void setUp() {
		unaAtraccion = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atracciones.add(unaAtraccion);
		unaPromo = new Porcentual("Pack Prueba", atracciones, 0.12);	
	}
	@After
	public void tearDown() {
		unaAtraccion = null;
		unaPromo = null; 
	}
	@Test
	public void test() {
		assertEquals(unaPromo.costo(), 9);
	}

}
