package tierra_media;
import java.util.ArrayList;
/**
 * Clase que hereda de Promocion, implementa promociones donde se aplica un
 * valor fijo en monedas a su costo total. 
 * @author Paiva, Víctor Emanuel
 * @version 02/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia.git
 */
public class Absoluta extends Promocion {
	
	//Atributo que representa el descuento fijo en monedas que brinda la promocion
	private int descuento; 
	
	public Absoluta(String nombre, ArrayList<Atraccion> atracciones, int descuento) {
		super(nombre, atracciones); //se llama al contructor de la clase madre.
		this.descuento = descuento; 
	}
	/**
	 * Metodo que determina el costo de una Promocion, recorre la lista de atracciones 
	 * que compone la promocion sumando los costos de cada atraccion y al total se le resta
	 * un costo fijo en monedas. 
	 * @return el costo de la promocion en monedas. 
	 */
	@Override
	public int costo() {
		int costo = 0;
		for(Atraccion c : this.atracciones)
			costo+= c.costo();
		costo -=this.getDescuento();
		return costo;	
	}
	
	/**
	 * Metodo que devuelve el descuento en monedas que ofrece la promocion. 
	 * @return el descuento en cantidad de monedas. 
	 */
	public int getDescuento() {
		return descuento;
	}
	
}
