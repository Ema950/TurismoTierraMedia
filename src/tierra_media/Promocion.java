package tierra_media;
import java.util.ArrayList;
/**
 * Clase abstracta que representa una Promocion, de esta heredan los tipos de promocion
 * @author Paiva, Víctor Emanuel
 * @version 02/08/2021
 * @see https://github.com/Ema950/TurismoTierraMedia.git
 */
public abstract class Promocion implements Sugerencia {
	 
	protected String nombre; 
	protected ArrayList <Atraccion> atracciones;
	
	public Promocion(String nombre, ArrayList<Atraccion> atracciones) {
		this.nombre = nombre; 
		this.atracciones = atracciones;
	}

	@Override
	public double duracion() {
		double duracion = 0;
		for(Atraccion c : this.atracciones)
			duracion+= c.duracion();
		return duracion;
	}
	
	@Override
	public boolean existeCupo() {
		boolean retorno= true; 
		for(Atraccion c : this.atracciones)
			retorno = retorno && c.existeCupo();
		return retorno; 	
	}
	

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}
}
