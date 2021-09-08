package tierra_media;

/**
 * Clase que permite crear un objeto de tipo Atraccion
 * 
 * @author Paiva, Víctor Emanuel
 * @version 08/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia
 */
public class Atraccion implements Sugerible {

	private String nombre;
	private int costo;
	private double tiempo;
	private int cupo;
	private TipoAtraccion tipo;

	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	@Override
	public int costo() {
		return this.costo;
	}

	@Override
	public double duracion() {
		return this.tiempo;
	}

	@Override
	public boolean existeCupo() {
		return this.getCupo() > 0;
	}

	@Override
	public boolean contieneTipo(TipoAtraccion unTipo) {
		return this.getTipoDeAtraccion().toString().equals(unTipo.toString());
	}

	@Override
	public String toString() {
		return "Atraccion: " + nombre + ".  \nTiene un costo de: " + costo + " monedas.  \nSu duracion es de: " + tiempo
				+ " horas. \nEs del tipo: " + tipo + " \n";
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAtraccion getTipoDeAtraccion() {
		return tipo;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

}
