package tierra_media;

import java.util.ArrayList;

/**
 * Clase necesaria para implementar los usuarios
 * 
 * @author Paiva, Víctor Emanuel
 * @version 08/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia
 */
public class Usuario {

	private String nombre;
	private TipoAtraccion atraccionPreferida;
	private int presupuesto;
	private double tiempoDisponible;
	private ArrayList<Atraccion> historialAtracciones = new ArrayList<Atraccion>();

	public Usuario(String nombre, TipoAtraccion atraccionPreferida, int presupuesto, double tiempoDisponible) {
		this.nombre = nombre;
		this.atraccionPreferida = atraccionPreferida;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;

	}

	/**
	 * Metodo que permite a un Usuario aceptar una sugerencia. Se modifica su
	 * estado, restanto su presupuesto y tiempo disponible; Se almacenan la
	 * atraccion en su Historial de atracciones;
	 * 
	 * @param unaAtraccion representa la atraccion aceptada por el usuario.
	 */
	public void aceptarSugerencia(Sugerible unaSugerencia) {
		int presupuestoActual = this.getPresupuesto();
		double tiempoActual = this.getTiempoDisponible();
		this.setPresupuesto(presupuestoActual - unaSugerencia.costo());
		this.setTiempoDisponible(tiempoActual - unaSugerencia.duracion());
		if (unaSugerencia instanceof Promocion) {
			for (Atraccion a : ((Promocion) unaSugerencia).getAtracciones())
				this.getHistorialAtracciones().add(a);
			if (unaSugerencia instanceof AxB) {
				this.getHistorialAtracciones().add(((AxB) unaSugerencia).getAtraccionGratis());
			}
		} else {
			this.getHistorialAtracciones().add((Atraccion) unaSugerencia);
		}

	}

	/**
	 * Metodo que devuelve el costo total de un Itinerio asignado a un Usuario.
	 * 
	 * @return costo total en monedas.
	 */
	public int costoItinerario() {
		int costo = 0;
		for (Atraccion c : this.getHistorialAtracciones())
			costo += c.costo();
		return costo;
	}

	/**
	 * Metodo que devuelve la duracion total de un Itinerio asignado a un Usuario.
	 * 
	 * @return duracion total.
	 */
	public double duracionItinerario() {
		double duracion = 0;
		for (Atraccion c : this.getHistorialAtracciones())
			duracion += c.duracion();
		return duracion;
	}

	/**
	 * Metodo que indica si un Usuario posee monedas;
	 * 
	 * @return true si tiene monedas disponibles.
	 */
	public boolean tieneMonedas() {
		return this.getPresupuesto() > 0;
	}

	/**
	 * Metodo que indica si un Usuario dispone de tiempo;
	 * 
	 * @return true si cuenta con tiempo.
	 */
	public boolean tieneTiempo() {
		return this.getTiempoDisponible() > 0;
	}

	/**
	 * Metodo que indica si un Usuario puede recorrer una Sugerencia
	 * 
	 * @param unaSugerencia es la sugerencia a controlar
	 * @return true si puede recorre la sugerencia.
	 */
	public boolean puedeRecorrer(Sugerible unaSugerencia) {
		return this.getPresupuesto() >= unaSugerencia.costo() && this.getTiempoDisponible() >= unaSugerencia.duracion()
				&& unaSugerencia.existeCupo();
	}

	/**
	 * Metodo que indica si las atracciones de una Sugerencia estan incluidas en el
	 * historial de atracciones de un Usuario
	 */
	public boolean poseeAtraccion(Sugerible unaSugerencia) {
		boolean retorno = false;
		if (unaSugerencia instanceof Promocion) {
			for (Atraccion a : ((Promocion) unaSugerencia).getAtracciones()) {
				if (this.historialAtracciones.contains(a)) {
					retorno = true;
				}
			}
		} else {
			this.historialAtracciones.contains(unaSugerencia);
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Metodo toString sobrescrito para Usuarios.
	 */
	@Override
	public String toString() {
		return nombre + ".  \nAtraccion Preferida: " + atraccionPreferida + ".  \nPresupuesto: " + presupuesto
				+ " monedas.  \nTiempo Disponible: " + tiempoDisponible + " horas.\n";
	}

	/**
	 * Metodo de comparacion sobrescrito
	 * 
	 * @param u representa el usuario con el cual se quiere comparar
	 * @return 0 si son iguales; -1 si "this" es menor; 1 si "this" es mayor
	 */
	public int compareTo(Usuario u) {
		return this.getNombre().compareTo(u.getNombre());
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(double tiempo) {
		this.tiempoDisponible = tiempo;
	}

	public ArrayList<Atraccion> getHistorialAtracciones() {
		return historialAtracciones;
	}

	public void setHistorialAtracciones(ArrayList<Atraccion> historialAtracciones) {
		this.historialAtracciones = historialAtracciones;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAtraccion getAtraccionPreferida() {
		return atraccionPreferida;
	}
}
