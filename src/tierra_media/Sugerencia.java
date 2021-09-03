package tierra_media;

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

}
