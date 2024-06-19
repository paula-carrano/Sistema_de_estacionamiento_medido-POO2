package ar.edu.unq.po2.Suscripcion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Compra.Compra;

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
	
	public void notificar(Object evento) {
		suscriptores.forEach(suscriptor-> suscriptor.actualizar(evento));
	}
	
	public List<Suscriptor> getSuscriptores(){
		return suscriptores;
	}
	
	
	}
