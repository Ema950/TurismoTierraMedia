package tierra_media;

import java.util.Comparator;

public class ComparadoresAtraccion {

	/**
	 * Comparator que permite ordenar una lista de atraccionese segun su costo, de
	 * Mayor a menor.
	 */
	public static Comparator<Atraccion> ordenarPorCosto = new Comparator<Atraccion>() {
		@Override
		public int compare(Atraccion o1, Atraccion o2) {
			return Integer.compare(o1.costo(), o2.costo());
		}
	};
	/**
	 * Comparator que permite ordenar una lista de atraccionese segun su duracion,
	 * de Mayor a menor.
	 */
	public static Comparator<Atraccion> ordenarPorTiempo = new Comparator<Atraccion>() {
		@Override
		public int compare(Atraccion o1, Atraccion o2) {
			return Double.compare(o1.duracion(), o2.duracion());
		}
	};

}
