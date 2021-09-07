package tierra_media;

import java.util.Comparator;

public class ComparadoresAtraccion {
	
	public static Comparator<Atraccion> ordenarPorCosto = new Comparator<Atraccion>(){
		@Override
		public int compare(Atraccion o1, Atraccion o2) {
			return Integer.compare(o1.costo(), o2.costo());
		}		
	}; 
	
	public static Comparator<Atraccion> ordenarPorTiempo = new Comparator<Atraccion>(){
		@Override
		public int compare(Atraccion o1, Atraccion o2) {
			return Double.compare(o1.duracion(), o2.duracion());
		}		
	}; 

}
