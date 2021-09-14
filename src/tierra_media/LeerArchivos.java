package tierra_media;

import java.io.*;
import java.util.*;

/**
 * Clase que se utiliza para la lectura de los archivos, mediante el uso de
 * metodos estaticos.
 * 
 * @author 4Elementos
 * @version 13/09/2021 - FINAL
 * @see https://github.com/Ema950/TurismoTierraMedia
 */

public class LeerArchivos {

	/**
	 * Metodo que lee el archivo de Usuarios
	 * 
	 * @param archivo de usuarios
	 * @return Lista enlazada (LinkedList) de los usuarios leidos.
	 */
	public static LinkedList<Usuario> leerUsuarios(String archivo) {
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String datos[] = linea.split(", ");
				String nombre = datos[0];
				TipoAtraccion atraccionPreferida = TipoAtraccion.valueOf(datos[1].toUpperCase());
				int presupuesto = Integer.parseInt(datos[2]);
				Double tiempoDisponible = Double.parseDouble(datos[3]);
				Usuario u = new Usuario(nombre, atraccionPreferida, presupuesto, tiempoDisponible);
				usuarios.add(u);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return usuarios;
	}

	/**
	 * Metodo que lee el archivo de Atracciones
	 * 
	 * @param archivo de atracciones
	 * @return Lista enlazada (LinkedList) de las atracciones leidas.
	 */
	public static LinkedList<Atraccion> leerAtracciones(String archivo) {
		LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String datos[] = linea.split(", ");
				String nombre = datos[0];
				int costo = Integer.parseInt(datos[1]);
				Double tiempo = Double.parseDouble(datos[2]);
				int cupo = Integer.parseInt(datos[3]);
				TipoAtraccion tipo = TipoAtraccion.valueOf(datos[4].toUpperCase());
				Atraccion a = new Atraccion(nombre, costo, tiempo, cupo, tipo);
				atracciones.add(a);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return atracciones;
	}

	/**
	 * Metodo que lee el archivo de Promociones
	 * 
	 * @param archivo de promociones, lista con atracciones.
	 * @return Lista enlazada (LinkedList) de las promociones leidas.
	 */
	public static LinkedList<Promocion> leerPromociones(String archivo, List<Atraccion> atraccionesArchivo) {
		LinkedList<Promocion> promociones = new LinkedList<Promocion>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String datos[] = linea.split(", ");
				// aqui se almacena el nombre de la promocion
				String nombre = datos[0];
				// aqui se almacena el tipo de la promocion
				String tipo = datos[datos.length - 1];
				/*
				 * lista donde se va a almacenar los nombres de las atracciones de la promo,
				 * leidos del archivo
				 */
				ArrayList<String> nombreAtracciones = new ArrayList<String>();
				for (int i = 0; i < datos.length; i++) {
					nombreAtracciones.add(datos[i]);
				}
				// lista donde se va a almacenar las Atracciones de la promo.
				ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
				for (String a : nombreAtracciones) {
					for (Atraccion b : atraccionesArchivo) {
						if (a.equals(b.getNombre())) {
							atraccionesPromo.add(b);
						}
					}
				}
				/*
				 * dependiendo el valor que tenga la variable "tipo" se va a crear el tipo de
				 * promocion y se va a almacenar en la lista de promociones de retorno.
				 */
				try {
					int descuentoMonedas = Integer.parseInt(tipo);
					Promocion p = new Absoluta(nombre, atraccionesPromo, descuentoMonedas);
					promociones.add(p);
				} catch (NumberFormatException e1) {
					try {
						Double descuentoPorcentaje = Double.parseDouble(tipo);
						Promocion p = new Porcentual(nombre, atraccionesPromo, descuentoPorcentaje);
						promociones.add(p);
					} catch (NumberFormatException e2) {
						for (Atraccion a : atraccionesArchivo) {
							if (tipo.equals(a.getNombre())) {
								Promocion p = new AxB(nombre, atraccionesPromo, a);
								promociones.add(p);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return promociones;
	}
}
