package ar.edu.unq.po2.SEM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import ar.edu.unq.po2.estacionamiento.*;

public class SEM {
	
	private List<Compra> compras;
	private List<Zona> zonas;
	
	
	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.zonas = new ArrayList<Zona>();
	}

	public List<Zona> getZonas() {
		return zonas;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}

	public void addCompra(Compra compra) {
		compras.add(compra);
	}
	
	public void addZona(Zona zona) {
		zonas.add(zona);
	}
	
	//Busca a la zona que le corresponde el estacionamiento y se lo envia 
	public void addEstacionamientoAZona(Estacionamiento estacionamiento, PuntoDeVenta puntoDeVenta) {
		Optional<Zona> zonaOptional = zonas.stream()
                .filter(z -> z.tieneAPuntoDeVenta(puntoDeVenta))
                .findFirst();
		
		Zona zona = zonaOptional.get();
        zona.addEstacionamiento(estacionamiento);
	}

}
