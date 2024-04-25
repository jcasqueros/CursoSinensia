package com.viewnext.jpasiraku.business.services;

import java.util.List;

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.data.model.Cliente;

/**
 * Interfaz {@link ClienteService} que provee métodos para el manejo de objetos
 * {@link Cliente}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public interface ClienteService {

	/**
	 * Método que devuelve todos los {@link Cliente} que se encuentren el la base de
	 * datos.
	 * 
	 * @return {@link List} de {@link Cliente}.
	 */
	List<ClienteBo> getAll();

	/**
	 * Método que lee un {@link Cliente} de la base de datos dado su 'id'.
	 * 
	 * @param id - {@link String} con el 'id' del {@link Cliente}.
	 * @return {@link Cliente} obtenido de la base de datos.
	 * @throws NotFoundException - {@link NotFoundException} se lanza cuando no se
	 *                           ha encontrado un {@link Cliente} con el 'id'
	 *                           introducido en la base de datos.
	 */
	ClienteBo getById(String id) throws NotFoundException;

	/**
	 * Método que persiste un {@link Cliente} en la base de datos.
	 * 
	 * @param cliente - {@link Cliente} a persistir en la base de datos.
	 * @return {@link Cliente} resultante.
	 * @throws AlreadyExistsException - {@link AlreadyExistsException} se lanza
	 *                                cuando se ha encontrado un {@link Cliente} con
	 *                                el mismo 'id' en la base de datos.
	 */
	ClienteBo create(ClienteBo cliente) throws AlreadyExistsException;

	/**
	 * Método que elimina un {@link Cliente} de la base de datos dado su 'id'.
	 * 
	 * @param id - {@link String} con el 'id' del {@link Cliente}.
	 * @throws NotFoundException - {@link NotFoundException} se lanza cuando no se
	 *                           ha encontrado un {@link Cliente} con el 'id'
	 *                           introducido en la base de datos.
	 */
	void deleteById(String id) throws NotFoundException;

}
