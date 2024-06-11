package ar.edu.unq.po2.App;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import ar.edu.unq.po2.Estacionamiento.EAplicacion;
import ar.edu.unq.po2.Punto.Punto;
import ar.edu.unq.po2.SEM.SEM;

public class AppUser implements MovementSensor{
	
	private String patente;
	private SEM sistema;
	private double saldo;
	private Modo modo;
	private Estado estado;
	private ServicioNotificacion notificador;
	private Punto punto;
	
	
	public AppUser(String patente, SEM sistema, ServicioNotificacion notificador, Punto punto) {
		this.setPatente(patente);
		this.setSistema(sistema);
		this.setModo(new Manual()); //Inicia en modo manual
		this.setNotificador(notificador);
		this.setSaldo(0); //Inicia con saldo 0
		this.setEstado(new Apagado()); //Inicia con el sensor apagado
		this.setPunto(punto); 
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
	
	//El punto se actualiza automaticamente por el gps
	protected void setPunto(Punto punto) {
		this.punto = punto;
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

	
	//Si tiene saldo positivo y no hay un estacionamiento vigente, inicia un estacionamiento y notifica 
	//De lo contrario, notifica el problema
	public void iniciarEstacionamiento() {
		try {
	        if (this.consultarVigencia()) {
	            throw new RuntimeException("Ya hay un estacionamiento vigente.");
	        }
	        if (this.getSaldo() <= 0) {
	            throw new RuntimeException("No se puede iniciar estacionamiento, saldo insuficiente.");
	        }

	        EAplicacion estacionamiento = new EAplicacion(this.patente, this, this.punto);
	        sistema.addEstacionamiento(estacionamiento);
	        this.notificador.enviarNotificacion(
	                " - Hora Inicio: " + estacionamiento.getHoraInicio() +
	                " - Hora maxima: " + this.calcularHoraMaxima());
	    } catch (RuntimeException e) {
	        this.notificador.enviarNotificacion(e.getMessage());
	    }
	}

	
	//Si hay un estacionamiento vigente, lo finaliza y notifica
	//De lo contrario, notifica una excepcion
	public void finalizarEstacionamiento() {
	        try {
	        	EAplicacion estacionamiento = (EAplicacion) this.sistema.estacionamientoConPatente(this.patente);
	            estacionamiento.finalizar(LocalTime.now());
	            this.notificador.enviarNotificacion(
	                    " - Hora Inicio: " + estacionamiento.getHoraInicio()
	                    +" - Hora de Finalizacion: " + estacionamiento.getHoraFin() 
	                    +" - Duracion total: " + estacionamiento.duracionTotal()
	                    +" - Costo total: " + estacionamiento.costoTotal()
	            );
	        } catch (Exception e) {
	        	this.notificador.enviarNotificacion("No existe un estacionamiento vigente para esta patente.");
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
	
	
	//Verifica si el punto actual es el mismo al del inicio del estacionamiento 
	public boolean esMismoPuntoDeInicio() {
		return this.punto == this.sistema.estacionamientoConPatente(this.patente).getPunto();
	}
}
