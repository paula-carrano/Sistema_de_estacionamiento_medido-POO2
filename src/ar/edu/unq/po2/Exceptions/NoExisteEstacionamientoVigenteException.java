package ar.edu.unq.po2.Exceptions;

public class NoExisteEstacionamientoVigenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public NoExisteEstacionamientoVigenteException(String mensaje) {
        super(mensaje);
    }

}
