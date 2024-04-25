package com.viewnext.jpasiraku.presentation.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;

/**
 * Clase 'GlobalExceptionHandler'.
 * <p>
 * Esta clase tiene la etiqueta @RestControllerAdvise lo que nos ayudará al
 * menejo de las excepciones.
 * <p>
 * Añadimos dos handlers para las excepciones detectadas que rompían nuestro
 * servidor.
 * 
 * @author Mario Sánchez Pilar
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Excepcion que se lanza cuando el argumento pasado en una variable no es del
	 * tipo adecuado.
	 * 
	 * @param e {@link MethodArgumentTypeMismatchException}
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		ApiError apiError = new ApiError(
				"El parametro " + e.getName() + " debe ser tipo " + e.getRequiredType().getSimpleName() + ".");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepcion que se lanza cuando existe conflicto de clave primaria al insertar
	 * un objeto a la base de datos.
	 * 
	 * @param e {@link AlreadyExistsException}
	 * @return
	 */
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ApiError> handleAlreadyExistsException(AlreadyExistsException e) {
		ApiError apiError = new ApiError(e.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
	}

	/**
	 * Excepcion que se lanza cuando no se ha encontrado un objeto en la base de
	 * datos con el identificador introducido.
	 * 
	 * @param e {@link NotFoundException}
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
		ApiError apiError = new ApiError(e.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

}
