package com.viewnext.jpasiraku.business.exception;

/**
 * Excepción {@link AlreadyExistsException} hereda de {@link Exception}.
 * <p>
 * Excepción que se lanza cuando en encuentra un conflicto al intentar persistir
 * un objeto en la base de datos debido a que ya existe uno presente con algún
 * atributo no repetible.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public class AlreadyExistsException extends Exception {

	private static final long serialVersionUID = 8596592008398563458L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}
