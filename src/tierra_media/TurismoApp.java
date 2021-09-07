package tierra_media;

import java.util.*;
public class TurismoApp {

	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>(
			LeerArchivos.leerUsuarios("Archivos/Usuarios.in"));
	private static LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>(
			LeerArchivos.leerAtracciones("Archivos/Atracciones.in"));
	private static LinkedList<Promocion> promociones = new LinkedList<Promocion>(
			LeerArchivos.leerPromociones("Archivos/Promociones.in", atracciones));
	private static Scanner ingresoConsola = new Scanner(System.in);

	public static void main(String args[]) {
		LinkedList<Sugerencia> sugerencias = new LinkedList<Sugerencia>();
		sugerencias.addAll(promociones);
		sugerencias.addAll(atracciones);
		TierraMedia inicio = new TierraMedia(sugerencias);
		System.out.println("****************************************");
		System.out.println("* BIENVENIDO A TURISMO EN TIERRA MEDIA *");
		System.out.println("****************************************");
		boolean salir = false;
		while (!salir) {
			menu1();
			switch (ingresoConsola()) {
			case 1:
				menu2(usuarios);
				int opcion = ingresoConsola();
				menu3(usuarios, opcion);
				Usuario usuarioSeleccionado = usuarios.get(opcion - 1);
				menu4(inicio.sugerenciasUsuario(usuarioSeleccionado), usuarioSeleccionado);
				menu5(atracciones, usuarioSeleccionado);
				menu6(usuarioSeleccionado);
				usuarios.get(opcion-1).setHistorialAtracciones(usuarioSeleccionado.getHistorialAtracciones());
				
				break;
			case 2:
				System.out.println("Gracias por utilizar el sistema, hasta pronto!!!");
				salir = true;
				break;
			default:
				System.out.println("Opcion Incorrecta. Vuelva a intentarlo por favor.\n");

			}

		}
	}

	public static int ingresoConsola() {
		int opcion = 0;
		boolean ingresoCorrecto = false;
		while (!ingresoCorrecto) {
			try {
				opcion = Integer.parseInt(ingresoConsola.next());
				ingresoCorrecto = true;
			} catch (NumberFormatException e) {
				System.out.println("Debe ingresar un numero! Vuelva a intentarlo por favor.");
			}
		}
		return opcion;
	}

	public static void menu1() {
		System.out.println("Elija una opcion(numero): ");
		System.out.println("1. Procesar Usuarios");
		System.out.println("2. Salir");
	}

	public static void menu2(LinkedList<Usuario> usuarios) {
		System.out.println("Elija el usuario que desea procesar:\n");
		for (Usuario u : usuarios) {
			System.out.println((usuarios.indexOf(u) + 1) + "- " + u.getNombre());
		}
	}

	public static void menu3(LinkedList<Usuario> usuarios, int opcion) {
		System.out.println("Fue seleccionado el ususario:\n");
		System.out.println(usuarios.get(opcion - 1).toString());
	}

	public static void menu4(LinkedList<Sugerencia> unaLista, Usuario unUsuario) {
		int i = 0; 
		while (i < unaLista.size()) {
			if(unUsuario.puedeRecorrer(unaLista.get(i))) {
				System.out.println("_________________________________________________________");
				System.out.println("Segun sus preferencias se ofrece agregar a su Itinerario:\n");
				System.out.println(unaLista.get(i));
				System.out.println("Elija una opcion: \n1-Aceptar Sugerencia.\n2-Rechazar. ");
				int opcion = ingresoConsola();
				switch(opcion) {
				case 1: 
					unUsuario.aceptarSugerencia(unaLista.get(i));
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
	
	public static void menu5(LinkedList<Atraccion> unaLista, Usuario unUsuario) {
		int i = 0; 
		while (i < unaLista.size()) {
			if(unUsuario.puedeRecorrer(unaLista.get(i))) {
				System.out.println("Fuera de sus preferencias se ofrece agregar a su Itinerario:\n");
				System.out.println(unaLista.get(i));
				System.out.println("Elija una opcion: \n1-Aceptar Sugerencia.\n2-Rechazar. ");
				int opcion = ingresoConsola();
				switch(opcion) {
				case 1: 
					unUsuario.aceptarSugerencia(unaLista.get(i));
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
	public static void menu6(Usuario unUsuario) {
		System.out.println("El itinerario para "+ unUsuario.getNombre()+" es:\n");
		for (Atraccion a : unUsuario.getHistorialAtracciones()) {
			System.out.println(a);
		}
		
	}
}
