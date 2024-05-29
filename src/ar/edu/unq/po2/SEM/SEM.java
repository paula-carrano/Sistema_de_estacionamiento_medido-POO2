package ar.edu.unq.po2.SEM;

import java.util.List;
import java.util.stream.Stream;

import ar.edu.unq.po2.estacionamiento.*;

public class SEM {
	
	private List<Compra> compras;
	private List<Zona> zonas;


	public void addCompra(Compra compra) {
		compras.add(compra);
	}
	
	public void addZona(Zona zona) {
		zonas.add(zona);
	}
	
	//Busca a la zona que le corresponde el estacionamiento y se lo envia 
	public void addEstacionamientoAZona(Estacionamiento estacionamiento) {
		Zona zona = (Zona) zonas.stream().filter(z -> z.getEstacionamientos().contains(estacionamiento));
		zona.addEstacionamiento(estacionamiento);
	}

}
