package ar.edu.unq.po2.Suscripcion;

import java.util.ArrayList;
import java.util.List;

public class SuscripcionManager {
	private List<Entidad> entidades;
	
	public SuscripcionManager() {
		this.entidades= new ArrayList<Entidad>();
	}
	
	public void suscribirEntidad(Entidad entidad) {
		entidades.add(entidad);
	}
	
	public void desuscribirEntidad(Entidad entidad) {
		entidades.remove(entidad);
	}
	
	public void notificar() {
		entidades.forEach(entidad-> entidad.actualizar());
	}
	
	public List<Entidad> getEntidades(){
		return entidades;
	}
}
