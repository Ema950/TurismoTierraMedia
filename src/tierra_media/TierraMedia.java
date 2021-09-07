package tierra_media;

import java.util.*;

/**
 * Clase Principal (main) 
 * @author Paiva, Víctor Emanuel
 * @version 03/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia.git
 */
public class TierraMedia {
	
	private LinkedList<Sugerencia> sugerencias = new LinkedList<Sugerencia>();
	
	public TierraMedia(LinkedList<Sugerencia> sugerencias) {
		this.sugerencias = sugerencias;
	}




	public LinkedList <Sugerencia> sugerenciasUsuario (Usuario unUsuario){
		LinkedList<Sugerencia> retorno = new LinkedList<Sugerencia>();
		for (Sugerencia s : this.sugerencias) {
			if(unUsuario.puedeRecorrer(s)&& s.contieneTipo(unUsuario.getAtraccionPreferida())) {
				retorno.addLast(s);
			}	
		}
		return retorno;
	}




	public LinkedList<Sugerencia> getSugerencias() {
		return sugerencias;
	}
	
	
		
}
	


