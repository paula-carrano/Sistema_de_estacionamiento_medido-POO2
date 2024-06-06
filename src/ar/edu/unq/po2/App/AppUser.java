package ar.edu.unq.po2.App;

import ar.edu.unq.po2.SEM.SEM;

public class AppUser {
	
	private String patente;
	private SEM sistema;
	private double saldo;
	
	//Constructor
	public AppUser(String p, SEM s) {
		this.setPatente(p);
		this.setSistema(s);
		this.saldo = 0;
	}
	
	
	//Setters
	private void setPatente(String p) {
		this.patente = p;
	}
	
	private void setSistema(SEM s) {
		this.sistema = s;
	}
	
	
	//Getters
	public double getSaldo() {
		return saldo;
	}
	
	
	//Suma el saldo recargado
	public void registrarSaldo(double monto) {
		this.saldo = saldo + monto;
	}
}
