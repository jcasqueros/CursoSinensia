package com.viewnext.jpasiraku.business.exception;

/**
 * Excepción {@link NotFoundException} hereda de {@link Exception}.
 * <p>
 * Excepción que se lanza cuando al buscar un objeto en la base de datos no se
 * halla contenido.
 * 
 * @author Mario Sánchez Pilar
 *
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 3573365127807723791L;

	public NotFoundException(String message) {
		super(message);
	}

}
