package tierra_media;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import dao.AtraccionDao;
import dao.PromocionDao;
import dao.UsuarioDao;

/**
 * Clase principal del sistema (main).
 * 
 * @author 4Elementos
 * @version 13/09/2021 - FINAL
 * @see https://github.com/Ema950/TurismoTierraMedia
 */

public class TurismoAplicacion {

	public static void main(String args[]) throws SQLException {
		
		UsuarioDao usuariosDao = new UsuarioDao();
		LinkedList<Usuario> usuarios = usuariosDao.findAll();
		AtraccionDao atraccionDao = new AtraccionDao();
		LinkedList<Atraccion> atracciones = atraccionDao.findAll();
		PromocionDao promoDao = new PromocionDao();
		LinkedList<Promocion> promociones = promoDao.findAll();
		/*
		 * Lista donde se va a alamacenar lo leido en atracciones y promociones luego se
		 * va a utilizar esta lista de sugerenica para crear una Tierra Media
		 */
		LinkedList<Sugerible> sugerencias = new LinkedList<Sugerible>();
		sugerencias.addAll(promociones);
		sugerencias.addAll(atracciones);
		// se crea una Tierra Media
		TierraMedia inicio = new TierraMedia(sugerencias);
		System.out.println("****************************************");
		System.out.println("* BIENVENIDO A TURISMO EN TIERRA MEDIA *");
		System.out.println("****************************************");
		// Variable que permite cortar el bucle While
		boolean salir = false;
		// Lista que va a almacenar los ususarios procesados
		LinkedList<Usuario> usuariosProcesados = new LinkedList<Usuario>();
		while (!salir) {
			menu1();
			switch (ingresoConsola()) {
			case 1:
				menu2(usuarios);
				int opcion = ingresoConsola();
				menu3(usuarios, opcion);
				/*
				 * aqui se almacena en una variable el usuario seleccionado por el operador
				 */
				Usuario usuarioSeleccionado = usuarios.get(opcion - 1);
				menu4(inicio.sugerenciasUsuario(usuarioSeleccionado), usuarioSeleccionado);
				menu5(atracciones, usuarioSeleccionado);
				menu6(usuarioSeleccionado);
				// se almacena el usuario procesado en la lista para tal fin
				usuariosProcesados.add(usuarioSeleccionado);
				/*
				 * se elimina el usuario procesado para que no vuelva a aparecer en las opciones
				 * de seleccion de usuario
				 */
				usuarios.remove(opcion - 1);
				/*
				 * si la lista de usuarios esta vacia quiere decir que se procesaron todos los
				 * usuarios
				 */
				if (usuarios.isEmpty()) {
					salir = true;
				}
				break;
			case 2:
				System.out.println("Gracias por utilizar el sistema, hasta pronto!!!");
				salir = true;
				break;
			default:
				System.out.println("Opcion Incorrecta. Vuelva a intentarlo por favor.\n");
			}
		}
		/*
		 * Se recorre la lista de usuarios procesados con el fin de escribir su
		 * itinerario en los archivos
		 */
		for (Usuario u : usuariosProcesados) {
			try {
				AlmacenarItinerario.crearArchivoItinerario(u);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("_________________________________________________________");
		System.out.println("\n!Se han Procesado todos los ususarios con exito!\n");
		System.out.println("\nEl itinerario de cada usuario fue almacenado en la carpeta ArchivosSalida\n");
		System.out.println("\nGracias por utilizar el sistema, hasta pronto!!!\n");
	}

	/**
	 * Metodo utilizado para el ingreso por consola de un operario.
	 * 
	 * @return la opcion seleccionada por el operario
	 */
	public static int ingresoConsola() {
		int opcion = 0;
		@SuppressWarnings("resource")
		Scanner ingreso = new Scanner(System.in);
		boolean ingresoCorrecto = false;
		while (!ingresoCorrecto) {
			try {
				opcion = Integer.parseInt(ingreso.next());
				ingresoCorrecto = true;
			} catch (NumberFormatException e) {
				System.out.println("Debe ingresar un numero! Vuelva a intentarlo por favor.");
			}
		}
		return opcion;
	}

	/**
	 * Menu con las primeras opciones cuando arranca el sistema
	 */
	public static void menu1() {
		System.out.println("Elija una opcion(numero): ");
		System.out.println("1. Procesar Usuarios");
		System.out.println("2. Salir");
	}

	/**
	 * Menu que muestra al operario los usuarios disponibles para procesar.
	 */
	public static void menu2(LinkedList<Usuario> usuarios) {
		System.out.println("Elija el usuario que desea procesar:\n");
		for (Usuario u : usuarios) {
			System.out.println((usuarios.indexOf(u) + 1) + "- " + u.getNombre());
		}
	}

	/**
	 * Menu que muestra al operario el usuario seleccionado.
	 */
	public static void menu3(LinkedList<Usuario> usuarios, int opcion) {
		System.out.println("Fue seleccionado el ususario:\n");
		System.out.println(usuarios.get(opcion - 1).toString());
	}

	/**
	 * Menu que muestra una sugerencia para un determinado usuario segun sus
	 * preferencias y permite al operario aceptar o rechazar la misma.
	 * 
	 * @param unaLista  lista con sugerencias
	 * @param unUsuario el usuario que se esta procesando
	 */
	public static void menu4(LinkedList<Sugerible> unaLista, Usuario unUsuario) {
		int i = 0;
		while (i < unaLista.size()) {
			if (unUsuario.puedeRecorrer(unaLista.get(i))) {
				System.out.println("_________________________________________________________");
				System.out.println("Segun sus preferencias se ofrece agregar a su Itinerario:\n");
				System.out.println(unaLista.get(i));
				System.out.println("Elija una opcion: \n1-Aceptar Sugerencia.\n2-Rechazar. ");
				int opcion = ingresoConsola();
				switch (opcion) {
				case 1:
					unUsuario.aceptarSugerencia(unaLista.get(i));
					unaLista.get(i).restarCupo();
					System.out.println("\nSugerencia agregada a su Itinerario\n");
					break;
				case 2:
					System.out.println("\nAtraccion rechazada\n");
					break;
				}
			}
			i++;
		}
		System.out.println("\nSegun sus gustos ya no hay opciones disponibles\n");
	}

	/**
	 * Menu que muestra una sugerencia para un determinado usuario sin tener en
	 * cuenta sus preferencias y permite al operario aceptar o rechazar la misma.
	 * 
	 * @param unaLista  lista con sugerencias
	 * @param unUsuario el usuario que se esta procesando
	 */
	public static void menu5(LinkedList<Atraccion> unaLista, Usuario unUsuario) {
		int i = 0;
		while (i < unaLista.size()) {
			if (unUsuario.puedeRecorrer(unaLista.get(i))) {
				System.out.println("Fuera de sus preferencias se ofrece agregar a su Itinerario:\n");
				System.out.println(unaLista.get(i));
				System.out.println("Elija una opcion: \n1-Aceptar Sugerencia.\n2-Rechazar. ");
				int opcion = ingresoConsola();
				switch (opcion) {
				case 1:
					unUsuario.aceptarSugerencia(unaLista.get(i));
					unaLista.get(i).restarCupo();
					System.out.println("Sugerencia agregada a su Itinerario\n");
					break;
				case 2:
					System.out.println("Atraccion rechazada\n");
					break;
				}
			}
			i++;
		}
	}

	/**
	 * Menu que muestra el itinerario de un ususario.
	 * 
	 * @param unUsuario
	 */
	public static void menu6(Usuario unUsuario) {
		System.out.println("El itinerario para " + unUsuario.getNombre() + " es:\n");
		for (Atraccion a : unUsuario.getHistorialAtracciones()) {
			System.out.println(a);
		}

	}
}
