package tierra_media;

import java.util.ArrayList;

/**
 * Clase necesaria para implementar los usuarios
 * @author  Paiva, V�ctor Emanuel
 * @version 02/08/2021
 * @see https://github.com/Ema950/TurismoTierraMedia.git
 */
public class Usuario {
	
	private String nombre;
	private TipoAtraccion atraccionPreferida;
	private int presupuesto;
	private double tiempoDisponible;
	private ArrayList<Atraccion> historialAtracciones= new ArrayList<Atraccion>();
	
	public Usuario(String nombre, TipoAtraccion atraccionPreferida, 
			        int presupuesto, double tiempoDisponible) {
		this.nombre = nombre;
		this.atraccionPreferida = atraccionPreferida;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;

	}
	/**
	 * Metodo que permite a un Usuario aceptar una sugerencia, en particular una Atraccion.
	 * Se modifica su estado, restanto su presupuesto y tiempo disponible; 
	 * Se almacenan la atraccion en su Historial de atracciones; 
	 * @param unaAtraccion representa la atraccion aceptada por el usuario. 
	 */
	public void aceptarSugerencia(Atraccion unaAtraccion) {
		int presupuestoActual = this.getPresupuesto();
		double tiempoActual = this.getTiempoDisponible();
		this.setPresupuesto(presupuestoActual-unaAtraccion.costo());
		this.setTiempoDisponible(tiempoActual-unaAtraccion.duracion());
		this.getHistorialAtracciones().add(unaAtraccion);	
	}
	/**
	 * Metodo que permite a un Usuario aceptar una sugerencia, en particular una Promocion.
	 * Se modiifica su estado, restanto su presupuesto y tiempo disponible; 
	 * Se almacenan la atraccion en su Historial de atracciones; 
	 * @param unaPromocion representa la promocion aceptada por el usuario. 
	 */
	public void aceptarSugerencia(Promocion unaPromocion) {
		int presupuestoActual = this.getPresupuesto();
		double tiempoActual = this.getTiempoDisponible();
		this.setPresupuesto(presupuestoActual-unaPromocion.costo());
		this.setTiempoDisponible(tiempoActual-unaPromocion.duracion());
		for(Atraccion a : unaPromocion.getAtracciones())
			this.getHistorialAtracciones().add(a);
		if(unaPromocion instanceof AxB) {
			this.getHistorialAtracciones().add(((AxB) unaPromocion).getAtraccionGratis());
		} 
	}
	/**
	 * Metodo que devuelve el costo total de un Itinerio asignado a un Usuario.
	 * @return costo total en monedas.
	 */
	public int costoItinerario() {
		int costo = 0; 
		for(Atraccion c : this.getHistorialAtracciones())
			costo+= c.costo();
		return costo;
	}
	/**
	 * Metodo que devuelve la duracion total de un Itinerio asignado a un Usuario.
	 * @return duracion total.
	 */
	public double duracionItinerario() {
		double duracion = 0; 
		for(Atraccion c : this.getHistorialAtracciones())
			duracion+= c.duracion();
		return duracion;
	}
	/**
	 * Metodo que indica si un Usuario posee monedas; 
	 * @return true si tiene monedas disponibles. 
	 */
	public boolean tieneMonedas() {
		return this.getPresupuesto()>0;
	}
	/**
	 * Metodo que indica si un Usuario dispone de tiempo; 
	 * @return true si cuenta con tiempo. 
	 */
	public boolean tieneTiempo() {
		return this.getTiempoDisponible()>0;
	}
	
	/**
	 * Metodo toString sobrescrito para Usuarios.
	 */
	@Override
	public String toString() {
		return  nombre + ", Atraccion Preferida: " + atraccionPreferida + ", Presupuesto: "
				+ presupuesto +" monedas, Tiempo Disponible: " + tiempoDisponible + " horas.";
	}
	/**
	 * Metodo de comparacion sobrescrito
	 * @param u representa el usuario con el cual se quiere comparar
	 * @return 0 si son iguales; -1 si "this" es menor; 1 si "this" es mayor
	 */
	public int compareTo(Usuario u) { 
		return this.getNombre().compareTo(u.getNombre());
	}	
	/**
	 * Metodo que devuelve el Presupuesto de un Usuario
	 * @return cantidad de monedas disponibles.
	 */
	public int getPresupuesto() {
		return presupuesto;
	}
	/**
	 * Metodo que modifica el presupuesto que dispone un Usuario
	 * @param presupuesto es el valor que se quiere modificar
	 */
	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}
	/**
	 * Metodo que devuelve el Tiempo que dispone un Usuario
	 * @return tiempo disponible.
	 */
	public double getTiempoDisponible() {
		return tiempoDisponible;
	}
	/**
	 * Metodo que modifica el tiempo que dispone un Usuario
	 * @param tiempo es el valor que se quiere modificar
	 */
	public void setTiempoDisponible(double tiempo) {
		this.tiempoDisponible = tiempo;
	}
	/**
	 * Metodo que devuelve las atracciones aceptadas por el usuario
	 * @return lista de atracciones aceptadas.
	 */
	public ArrayList<Atraccion> getHistorialAtracciones() {
		return historialAtracciones;
	}
	/**
	 * Metodo que modifica el historial de atracciones aceptadas por el Usuario
	 * @param historialAtracciones representa la modificacion a realizar.
	 */
	public void setHistorialAtracciones(ArrayList<Atraccion> historialAtracciones) {
		this.historialAtracciones = historialAtracciones;
	}
	/**
	 * Metodo que devuelve el nombre del Usuario
	 * @return nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo que devuelve el tipo de atraccion favorita.
	 * @return el tipo de atraccion favorita.
	 */
	public TipoAtraccion getAtraccionPreferida() {
		return atraccionPreferida;
	}
}
