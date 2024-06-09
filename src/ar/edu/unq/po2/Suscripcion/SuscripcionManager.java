package ar.edu.unq.po2.Suscripcion;

import java.util.ArrayList;
import java.util.List;

public class SuscripcionManager {
	
	private List<Suscriptor> suscriptores;
	
	
	public SuscripcionManager() {
		this.suscriptores= new ArrayList<Suscriptor>();
	}
	
	
	public void agregarSuscriptor(Suscriptor suscriptor) {
		suscriptores.add(suscriptor);
	}
	
	public void removerSuscriptor(Suscriptor suscriptor) {
		suscriptores.remove(suscriptor);
	}
	
	public void notificar() {
		suscriptores.forEach(suscriptor-> suscriptor.actualizar());
	}
	
	public List<Suscriptor> getSuscriptores(){
		return suscriptores;
	}
}
