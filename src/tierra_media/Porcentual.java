package tierra_media;

import java.util.ArrayList;

/**
 * Clase que hereda de Promocion, implementa promociones donde se aplica un
 * porcentaje de descuento a su costo total.
 * 
 * @author Paiva, Víctor Emanuel
 * @version 08/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia
 */
public class Porcentual extends Promocion {

	// Atributo que representa el porcentaje de descuento de la Promocion
	private double porcentajeDescuento;

	public Porcentual(String nombre, ArrayList<Atraccion> atracciones, double porcentajeDescuento) {
		super(nombre, atracciones); // se llama al contructor de la clase madre.
		this.porcentajeDescuento = porcentajeDescuento;
	}

	/**
	 * Metodo que determina el costo de una Promocion, recorre la lista de
	 * atracciones que compone la promocion sumando los costos de cada atraccion y
	 * restando el porcentaje de descuento.
	 * 
	 * @return el costo de la promocion en monedas.
	 */
	@Override
	public int costo() {
		double costo = 0;
		for (Atraccion c : this.atracciones)
			costo += c.costo();
		costo -= (costo * this.getPorcentajeDescuento());
		return (int) Math.floor(costo); // el valor despues de aplicar el descuento se redondea hacia abajo.
	}

	@Override
	public String toString() {
		return super.toString() + " \nSu costo total es de: " + this.costo()
				+ " monedas, aplicando un descuento de un: " + this.getPorcentajeDescuento() * 100
				+ " %.  \nSu duracion es de: " + this.duracion() + " horas.\n";
	}

	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
}
