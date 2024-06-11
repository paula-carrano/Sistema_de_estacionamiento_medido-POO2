package ar.edu.unq.po2.App;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.Estacionamiento.EAplicacion;
import ar.edu.unq.po2.SEM.SEM;

public class AppUser implements MovementSensor{
	
	private String patente;
	private SEM sistema;
	private double saldo;
	private Modo modo;
	private Estado estado;
	private ServicioNotificacion notificador;
	
	
	public AppUser(String patente, SEM sistema, Modo modo, ServicioNotificacion notificador) {
		this.setPatente(patente);
		this.setSistema(sistema);
		this.setModo(new Manual()); //Inicia en modo manual
		this.setNotificador(notificador);
		this.setSaldo(0); //Inicia con saldo 0
		this.setEstado(new Apagado()); //Inicia con el sensor apagado
	}
	
	
	//Setters
	private void setPatente(String patente) {
		this.patente = patente;
	}
	
	private void setSistema(SEM sistema) {
		this.sistema = sistema;
	}
	
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	private void setNotificador(ServicioNotificacion notificador) {
		this.notificador = notificador;
	}
	
	private void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	protected void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	//Getters
	public double getSaldo() {
		return saldo;
	}
	
	protected Modo getModo() {
		return this.modo;
	}
	
	public SEM getSistema() {
		return this.sistema;
	}
	
	public ServicioNotificacion getNotificador() {
		return this.notificador;
	}
	
	
	//Acreditar y desacreditar saldo
	public void registrarSaldo(double monto) {
		this.setSaldo(this.saldo + monto);
	}

	public void descontarSaldo(double monto) {
		this.setSaldo(this.saldo - monto);
	}
	
	
	//Alertas del movement sensor
	@Override
	public void driving() {
		this.estado.manejando(this);
	}
	
	@Override
	public void walking() {
		this.estado.caminando(this);
	}
	
	
	//Encendido y apagado e alertas del movement sensor
	public void apagarAlertas() {
		this.setEstado(new Apagado());
	}
	
	public void encenderAlertas() {
		this.setEstado(new Manejando());
	}
	
	
	//Indica si el estacionamiento hay un estacionamiento vigente
	public boolean consultarVigencia() {
		return this.sistema.verificarEstacionamientoConVigencia(this.patente);
	}

	
	//Si tiene saldo positivo, inicia un estacionamiento y notifica 
	//De lo contrario, indica notifica saldo insuficiente
	public void iniciarEstacionamiento(){
		if(this.getSaldo() > 0) {
			EAplicacion estacionamiento = new EAplicacion(this.patente, this, null);
			this.sistema.addEstacionamiento(estacionamiento);
			this.notificador.enviarNotificacion(
					" - Hora Inicio: " + estacionamiento.getHoraInicio() +
					" - Hora maxima: " + this.calcularHoraMaxima());
		} else {
			this.notificador.enviarNotificacion("No se puede iniciar estacionamiento, saldo insuficiente.");
		}
	}
	
	
	//Si hay un estacionamiento vigente, lo finaliza y notifica
	//De lo contrario, notifica una excepcion
	public void finalizarEstacionamiento() throws Exception {
		
		try{EAplicacion estacionamiento = (EAplicacion) this.sistema.estacionamientoConPatente(this.patente);
			estacionamiento.finalizar(LocalTime.now());
			this.notificador.enviarNotificacion(
				 " - Hora Inicio: " + estacionamiento.getHoraInicio()
				+" - Hora de Finalizacion: " + estacionamiento.getHoraFin() 
				+" - Duracion total: " + estacionamiento.duracionTotal()
				+" - Costo total: " + estacionamiento.costoTotal()
					);
		} catch (Exception e) {
			this.notificador.enviarNotificacion(
				"No existe un estacionamiento para esta patente.");
		}
	}
	
	
	//Calcula la hora maxima permitida a partir del saldo
	public LocalTime calcularHoraMaxima() {
	    int cantHorasMax = (int) (this.saldo / this.sistema.getPrecioPorHora());
	    LocalTime horaMaxima = LocalTime.now().plusHours(cantHorasMax).truncatedTo(ChronoUnit.SECONDS);
	    if (horaMaxima.isAfter(this.sistema.getHoraFin())) {
	        return this.sistema.getHoraFin();
	    } else {
	        return horaMaxima; 
	    }
	}
}
