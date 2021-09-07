package tierra_media;
/**
 * Interface que brinda la firma de metodos usados en Atracciones y Promociones
 * @author Paiva, Víctor Emanuel
 * @version 03/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia.git
 */
public interface Sugerencia {
	
	/**
	 * Metodo que devuelve el costo de una sugerencia; 
	 * @return el coste en monedas. 
	 */
	public abstract int costo();
	/**
	 * Metodo que devuelve la duracion de una sugerencia.
	 * @return la duracion expresada en horas.
	 */
	public abstract double duracion();
	/**
	 * Metodo que determina si una sugerencia posee cupo. 
	 * @return true si existe cupo. 
	 */
	public abstract boolean existeCupo();
	/**
	 * Metodo que indica si una Sugerencia tiene un determinado tipo de atraccion. 
	 * @return true si posee dicho tipo.
	 */
	public abstract boolean contieneTipo(TipoAtraccion unTipo);

}
