package ar.edu.unq.po2.App;

import ar.edu.unq.po2.SEM.SEM;

public class AppUser implements MovementSensor{
	
	private String patente;
	private SEM sistema;
	private double saldo;
	private Modo modo;
	private Estado estado;
	private ServicioNotificacion notificador;
	
	public AppUser(String patente, SEM sistema, Modo modo) {
		this.setPatente(patente);
		this.setSistema(sistema);
		this.setModo(modo);
		this.saldo = 0;
		this.estado = new Apagado();
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

	// Llega la alerta del Driving del sensor.
	@Override
	public void driving() {
		this.estado.manejando(this);
	}
	
	// Llega la alerta del Walking del sensor.
	@Override
	public void walking() {
		this.estado.caminando(this);
	}
	
	protected void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	protected Modo getModo() {
		return this.modo;
	}
	
	// Metodo para apagar el MovementSensor.
	public void apagarAlertas() {
		this.setEstado(new Apagado());
	}
	
	// Metodo para encender el MovementSensor.
	public void encenderAlertas() {
		this.setEstado(new Manejando());
	}
	
	public ServicioNotificacion getNotificador() {
		return this.notificador;
	}

	public boolean consultarVigencia() {
		return
		(this.sistema.verificarEstacionamientoConVigencia
				(this.patente));
	}

	public void iniciarEstacionamiento() {
		
	}

	public void finalizarEstacionamiento() {
		// TODO Auto-generated method stub
		
	}
	
}
