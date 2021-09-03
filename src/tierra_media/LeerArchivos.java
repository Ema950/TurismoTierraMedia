package tierra_media;

import java.io.*;
import java.util.*;

public class LeerArchivos {
	
	
	public static LinkedList<Usuario> leerUsuarios(String archivo){
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
	
	public static LinkedList<Atraccion> leerAtracciones(String archivo){
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
	
	
	public static LinkedList<Atraccion> leerPromociones(String archivo, List<Atraccion> atraccionesArchivo){
		LinkedList<Atraccion> promociones = new LinkedList<Atraccion>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String datos[] = linea.split(", ");
				//aqui se almacena el nombre de la promocion
				String nombre = datos[0];
				//aqui se almacena el tipo de la promocion
				String tipo = datos[datos.length-1];
				//arreglo donde se va a almacenar los nombres de las atracciones de la promo
				ArrayList<String> nombreAtracciones = new ArrayList<String>();
				for( int i= 1; i<datos.length-1; i++) {
					nombreAtracciones.add(datos[i]);
				}
				
				//arreglo donde se va a almacenar las Atracciones de la promo. 
				ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
				for (String a: nombreAtracciones) {
					for (Atraccion b: atraccionesArchivo) {
						if(a.equals(b.getNombre())) {
							atraccionesPromo.add(b);
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
