package ar.edu.unq.po2.App;

import java.time.LocalTime;
import ar.edu.unq.po2.Estacionamiento.EAplicacion;
import ar.edu.unq.po2.Estacionamiento.Estacionamiento;
import ar.edu.unq.po2.SEM.SEM;

public class AppUser implements MovementSensor{
	
	private String patente;
	private SEM sistema;
	private double saldo;
	private Modo modo;
	private Estado estado;
	private ServicioNotificacion notificador;
	private Zonificacion zona;
	
	
	public AppUser(String patente, SEM sistema, Modo modo, ServicioNotificacion notificador) {
		this.setPatente(patente);
		this.setSistema(sistema);
		this.setModo(modo);
		this.notificador = notificador;
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
	
	public SEM getSistema() {
		return this.sistema;
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

	public void iniciarEstacionamiento(){
		if(this.getSaldo() < 0) {
			this.notificador.
			enviarNotificacion
			("No se puede iniciar estacionamiento, saldo insuficiente.");
		}
		EAplicacion estacionamiento = new EAplicacion(this.patente, this, null);
		this.sistema.addEstacionamiento(estacionamiento);
		this.notificador.
		enviarNotificacion("Hora Inicio: " 
		+ estacionamiento.getHoraInicio()
		+ " - Hora Fin: " + estacionamiento.calcularHoraFin());
	}

	public int calculoHoraMaxima() {
		return(int) 
				(this.saldo / this.sistema.getPrecioPorHora());
	}
	
	public void finalizarEstacionamiento() throws Exception {
		try{Estacionamiento estacionamiento =
			this.sistema.estacionamientosVigentes()
			.stream().filter(e -> this.patente.
				equals(e.getPatente())).findFirst()
				.get();
			estacionamiento.finalizar(LocalTime.now());
			this.notificador.enviarNotificacion(
				"Hora Inicio: " + estacionamiento.getHoraInicio()
				+" - Hora de Finalizacion: " + estacionamiento.getHoraFin() 
				+" - Duracion total: " + estacionamiento.duracionTotal()
				+"- Costo total: " + estacionamiento.costoTotal()
					);
		} catch (Exception e) {
			this.notificador.enviarNotificacion(
				"No existe un estacionamiento para esta patente.");
		}
	}
}
