package ar.edu.unq.po2.App;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.Estacionamiento.EAplicacion;
import ar.edu.unq.po2.Exceptions.*;
import ar.edu.unq.po2.Modo.Automatico;
import ar.edu.unq.po2.Modo.Manual;
import ar.edu.unq.po2.Modo.Modo;
import ar.edu.unq.po2.MovementSensor.MovementSensor;
import ar.edu.unq.po2.Punto.Punto;
import ar.edu.unq.po2.SEM.SEM;
import ar.edu.unq.po2.ServicioNotificacion.ServicioNotificacion;

public class AppUser implements MovementSensor{
	
	private String patente;
	private SEM sistema;
	private double saldo;
	private Modo modo;
	private Estado estado;
	private ServicioNotificacion notificador;
	private Punto punto;
	
	
	public AppUser(String patente, SEM sistema, 
			ServicioNotificacion notificador, Punto punto) {
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
	
	protected void setModo(Modo modo) {
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
	
	protected Estado getEstado() {
		return this.estado;
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
	
	
	//Cambiar modo 
	public void cambiarAModoAutomatico() {
		this.setModo(new Automatico());
	}
	
	public void cambiarAModoManual() {
		this.setModo(new Manual());
	}
	
	
	
	//Indica si el estacionamiento hay un estacionamiento vigente
	public boolean consultarVigencia() {
		return this.sistema.verificarEstacionamientoConVigencia(this.patente);
	}

	
	//Si tiene saldo positivo y no hay un estacionamiento vigente, inicia un estacionamiento y notifica 
	//De lo contrario, notifica el problema
	public void iniciarEstacionamiento() {
		try {
			
			this.validarInicioEstacionamiento();
	        
	        EAplicacion estacionamiento = new EAplicacion(this.patente, this, this.punto);
	        sistema.addEstacionamiento(estacionamiento);
	        this.notificarInicioEstacionamiento(estacionamiento);
	        
	    } catch (RuntimeException e) {
	    	this.notificador.enviarNotificacion(e.getMessage());
	    }
	}

	
	private void notificarInicioEstacionamiento(EAplicacion estacionamiento) {
		LocalTime horaInicio = estacionamiento.getHoraInicio().truncatedTo(ChronoUnit.SECONDS);
		LocalTime horaActual = LocalTime.now();
	    LocalTime horaMaxima = this.calcularHoraMaxima(horaActual).truncatedTo(ChronoUnit.SECONDS);
	    String mensaje = " - Hora Inicio: " + horaInicio.format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
	                     " - Hora maxima: " + horaMaxima.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	    this.notificador.enviarNotificacion(mensaje); 	
	}


	private void validarInicioEstacionamiento() {
		 try {
	            this.verificarEstacionamientoVigente();
	            this.verificarSaldoPositivo();
	        } catch (SaldoInsuficienteException | EstacionamientoVigenteException e) {
	            throw e;
	        }		
	}


	private void verificarSaldoPositivo() {
		if (this.getSaldo() <= 0) {
            throw new SaldoInsuficienteException("No se puede iniciar estacionamiento, saldo insuficiente.");
        }		
	}


	private void verificarEstacionamientoVigente() {
		if (this.consultarVigencia()) {
            throw new EstacionamientoVigenteException("Ya hay un estacionamiento vigente.");
        }		
	}


	//Si hay un estacionamiento vigente, lo finaliza y notifica
	//De lo contrario, notifica una excepcion
	public void finalizarEstacionamiento() {
	        try {
	        	EAplicacion estacionamiento = obtenerEstacionamientoVigente();
	        	
	            estacionamiento.finalizar(LocalTime.now());
	            notificarFinEstacionamiento(estacionamiento);	
	        } catch (Exception e) {
	        	 this.notificador.enviarNotificacion(e.getMessage());
	        }
	}
	
	private void notificarFinEstacionamiento(EAplicacion estacionamiento) {
		 this.notificador.enviarNotificacion(
                 " - Hora Inicio: " + estacionamiento.getHoraInicio()
                 +" - Hora de Finalizacion: " + estacionamiento.getHoraFin() 
                 +" - Duracion total: " + estacionamiento.duracionTotal()
                 +" - Costo total: " + estacionamiento.costoTotal());		
	}


	private EAplicacion obtenerEstacionamientoVigente() {
        EAplicacion estacionamiento = (EAplicacion) this.sistema.estacionamientoConPatente(this.patente);
        if (estacionamiento == null) {
        	throw new NoExisteEstacionamientoVigenteException("No existe un estacionamiento vigente para esta patente.");
        }
        return estacionamiento;
    }
	
	
	//Calcula la hora maxima permitida a partir del saldo
	public LocalTime calcularHoraMaxima(LocalTime horaActual) {
	    double saldoActual = this.getSaldo();
	    double precioPorHora = sistema.getPrecioPorHora();
	    LocalTime horaFinSistema = sistema.getHoraFin();

	    double costoHasta20hs = precioPorHora * (horaFinSistema.getHour() - horaActual.getHour());
	    
	    //Calculo en base a si mi saldo me alcanza hasta las 20, sino calcular hasta donde alcance.
	    LocalTime horaMaxima = (saldoActual >= costoHasta20hs) ? horaFinSistema : horaActual.plusHours((int) (saldoActual / precioPorHora));

	    return horaMaxima.truncatedTo(ChronoUnit.SECONDS);
		}
	
	//Verifica si el punto actual es el mismo al del inicio del estacionamiento 
	public boolean esMismoPuntoDeInicio() {
		return this.punto == this.sistema.estacionamientoConPatente(this.patente).getPunto();
	}
}
