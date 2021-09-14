package tierra_media;

import java.io.*;

/**
 * Clase utilizada para crear los archivos de salida, mediante el uso de metodos
 * estaticos.
 * 
 * @author 4Elementos
 * @version 13/09/2021 - FINAL
 * @see https://github.com/Ema950/TurismoTierraMedia
 */
public class EscribirArchivos {

	public static void crearArchivoItinerario(Usuario unUsuario) throws IOException {
		PrintWriter salida = new PrintWriter(new File("./ArchivosSalida/" + unUsuario.getNombre() + ".csv"));
		salida.println("El Itineario de " + unUsuario.getNombre() + " Esta compuesto por: ");
		for (Atraccion a : unUsuario.getHistorialAtracciones()) {
			salida.println(a);
		}
		salida.println("El costo total del Itinerario es de: " + unUsuario.costoItinerario() + " Monedas.");
		salida.println("La duracion total del recorrido es de: " + unUsuario.duracionItinerario() + " Horas.");
		salida.close();
	}

}
