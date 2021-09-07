package tierra_media;
import java.util.ArrayList;
/**
 * Clase que hereda de Promocion, implementa promociones donde se ofrece una
 * atraccion gratis.
 * @author Paiva, Víctor Emanuel
 * @version 03/09/2021
 * @see https://github.com/Ema950/TurismoTierraMedia.git
 */
public class AxB extends Promocion {
	
	//Atributo que representa la atraccion gratis que brinda promocion.
	private Atraccion atraccionGratis;  
	
	public AxB(String nombre, ArrayList<Atraccion> atracciones, Atraccion atraccionGratis) {
		super(nombre, atracciones);//se llama al contructor de la clase madre.
		this.atraccionGratis = atraccionGratis; 
	}
	/**
	 * Metodo que determina el costo de una Promocion, recorre la lista de atracciones 
	 * que compone la promocion sumando los costos de cada atraccion.
	 * @return el costo de la promocion en monedas. 
	 */
	@Override
	public int costo() {
		int costo = 0; 
		for(Atraccion c : this.atracciones)
			costo+= c.costo();
		costo-= this.getAtraccionGratis().costo();
		return costo;
	}
	
	@Override
	public double duracion() {
		double duracion = 0;
		for(Atraccion c : this.atracciones)
			duracion+= c.duracion();
		duracion+= this.getAtraccionGratis().duracion();
		return duracion;
	}
	
	@Override
	public boolean existeCupo() {
		boolean retorno= true; 
		for(Atraccion c : this.atracciones)
			retorno = retorno && c.existeCupo();
		return retorno && this.getAtraccionGratis().existeCupo(); 	
	}
	
	@Override
	public String toString() {
		return super.toString()+" \nSu costo total es de: " + this.costo() 
		+ " monedas.  \nSu duracion es de: "+ this.duracion()+ 
		" horas.  \nSe ofrece como regalo la atraccion: "
		+ this.getAtraccionGratis().getNombre()+ " \n";
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}
}
