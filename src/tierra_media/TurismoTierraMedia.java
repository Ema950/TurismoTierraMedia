package tierra_media;

import java.util.LinkedList;

public class TurismoTierraMedia {
	public static void main (String args[]) {
		LinkedList <Atraccion> atraccionPrueba = new LinkedList <Atraccion>();
		atraccionPrueba = LeerArchivos.leerAtracciones("C:\\Users\\Emanuel\\Desktop\\Atracciones.in");
		for(Atraccion a: atraccionPrueba) {
			System.out.println(a.toString());
		}
	}
	
	
}
