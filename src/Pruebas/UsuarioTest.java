package Pruebas;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import tierra_media.*;
/**
 * Pruebas Unitarias para la clase Usuario
 * @author Barboza, Dario; Capandeguy, Agust�n; Fernandez, Cecilia In�s; 
 * Martinez, Leandro; Noir, Sergio Javier; Paiva, V�ctor Emanuel
 * @version 01/09/2021
 * @see https://github.com/Voidt-Team/TurismoTierraMedia
 */
public class UsuarioTest {
	
	Usuario unUsuario;
	Atraccion unaAtraccion; 
	Promocion unaPromo;
	ArrayList <Atraccion> atracciones = new ArrayList<Atraccion>();
	
	@Before 
	public void setUp() {
		unUsuario = new Usuario ("Eowyn", TipoAtraccion.AVENTURA, 10, 8);
		unaAtraccion = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atracciones.add(unaAtraccion);
		unaPromo = new Absoluta("Pack Prueba", atracciones, 36);
	}
	@After
	public void tearDown() {
		unUsuario = null;
		unaAtraccion = null;
		unaPromo = null; 
	}

	@Test
	public void aceptarSugerenciaAtraccionTest() {
		unUsuario.aceptarSugerencia(unaAtraccion);
		assertNotNull(unUsuario.getHistorialAtracciones().get(0));
	}
	@Test
	public void aceptarSugerenciaPromoTest() {
		unUsuario.aceptarSugerencia(unaPromo);
		assertNotNull(unUsuario.getHistorialAtracciones().get(0));
	}
	/*
	 * Se agrega un test para el caso particular de las promociones AxB 
	 * cuando se llama al metodo aceptarSugerencia(), debido a que en el historial 
	 * de atracciones ademas de las atracciones de la promo, se debe agregar 
	 * una atraccion gratis. 
	 */
	@Test
	public void aceptarSugerenciaPromoAxBTest() {
		Atraccion atraccionGratis = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		Promocion promoAxB = new AxB("Pack AxB", atracciones, atraccionGratis);
		unUsuario.aceptarSugerencia(promoAxB);
		
		/*se verifica que en la ultima posicion no haya una referencia nula, eso
		 * nos asegura que se agrego correctamente la atraccion gratis.*/
		assertNotNull(unUsuario.getHistorialAtracciones().get(atracciones.size()));
		
		/*El tamanio del historial de atracciones de ser igual al tamanio de 
		 * las atracciones de la promo mas 1, debido a la atraccion gratis */
		assertEquals(unUsuario.getHistorialAtracciones().size(), atracciones.size()+1);	}
	
	@Test
	public void costoItinerarioTest() {
		unUsuario.aceptarSugerencia(unaAtraccion);
		assertEquals(unUsuario.costoItinerario(), 10);
	}
	@Test
	public void duracionItinerarioTest() {
		unUsuario.aceptarSugerencia(unaAtraccion);
		assertEquals(unUsuario.duracionItinerario(), 2, 0.1);
	}
	@Test
	public void tieneMonedasTest() {
		unUsuario.setPresupuesto(0);
		assertFalse(unUsuario.tieneMonedas());
	}
	@Test
	public void tieneTiempoTest() {
		unUsuario.setTiempoDisponible(0);
		assertFalse(unUsuario.tieneTiempo());
	}
	

}