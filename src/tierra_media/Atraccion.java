package tierra_media;

public class Atraccion implements Sugerencia {

	private String nombre;
	private int costo;
	private double tiempo;
	private int cupo;
	private TipoAtraccion tipo;

	public Atraccion(String nombre, int costo, double tiempo, 
			          int cupo, TipoAtraccion tipo) {
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
	public String toString() {
		return "Atraccion: " + nombre + ", tiene un costo de: " + costo + 
				" monedas, dura: " + tiempo + "hs, tiene un cupo de: " + 
				cupo + ", es del tipo: " + tipo;
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
