package tierra_media;

/**
 * Clase que permite crear un objeto de tipo Atraccion
 * 
 * @author 4Elementos
 * @version 13/09/2021 - FINAL
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

	public int costo() {
		return this.costo;
	}

	public double duracion() {
		return this.tiempo;
	}

	public boolean existeCupo() {
		return this.getCupo() > 0;
	}
	
	public void restarCupo() {
		this.setCupo(this.costo-1); 
		
	}

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
