package ar.edu.unq.po2.App;

import ar.edu.unq.po2.SEM.SEM;

public class AppUser implements MovementSensor{
	
	// Atributos.
	private String patente;
	private SEM sistema;
	private double saldo;
	private Modo modo;
	
	//Constructor
	public AppUser(String patente, SEM sistema, Modo modo) {
		this.setPatente(patente);
		this.setSistema(sistema);
		this.setModo(modo);
		this.saldo = 0;
	}
	
	
	//Setters
	private void setPatente(String patente) {
		this.patente = patente;
	}
	
	private void setSistema(SEM sistema) {
		this.sistema = sistema;
	}
	
	public void setModo(Modo modo2) {
		this.modo = modo2;
	}
	
	//Getters
	public double getSaldo() {
		return saldo;
	}
	
	
	//Suma el saldo recargado
	public void registrarSaldo(double monto) {
		this.saldo = saldo + monto;
	}

	@Override
	public void driving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walking() {
		// TODO Auto-generated method stub
		
	}
}
