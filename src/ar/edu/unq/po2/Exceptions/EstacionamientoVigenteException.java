package ar.edu.unq.po2.Exceptions;

public class EstacionamientoVigenteException extends RuntimeException{
	/* se utiliza para recordar versiones en clases serializables para verificar 
	que la clase cargada y el objeto sea compatible*/
	
	private static final long serialVersionUID = 1L;
	
	public EstacionamientoVigenteException(String mensaje) {
        super(mensaje);
    }

}
