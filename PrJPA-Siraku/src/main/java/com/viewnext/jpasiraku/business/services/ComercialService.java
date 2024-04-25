package com.viewnext.jpasiraku.business.services;

import java.util.List;

import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.data.model.Comercial;

/**
 * Interfaz {@link ComercialService} que provee métodos para el manejo de
 * objetos {@link Comercial}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public interface ComercialService {

	/**
	 * Método que lee un {@link ComercialBo} de la base de datos dado su 'id.
	 * 
	 * @param id - {@link Long} con el 'id' del {@link ComercialBo}.
	 * @return {@link ComercialBo} obtenido de la base de datos.
	 * @throws NotFoundException - {@link NotFoundException} se lanza cuando no se
	 *                           ha encontrado un comercial con el 'id' introducido
	 *                           en la base de datos.
	 */
	ComercialBo getById(Long id) throws NotFoundException;

	/**
	 * Método que devuelve todos los {@link ComercialBo} que se encuentren en la
	 * base de datos.
	 * 
	 * @return {@link List} de {@link ComercialBo}.
	 */
	List<ComercialBo> getAll();

	/**
	 * Método que devuelve el número total de {@link ComercialBo} que existen en la
	 * base de datos.
	 * 
	 * @return
	 */
	Long getNumeroTotalComerciales();

	/**
	 * Método que persiste un comercial en la base de datos.
	 * 
	 * @param comercial - {@link ComercialBo} a persistir en la base de datos.
	 * @return {@link ComercialBo} resultante.
	 * @throws AlreadyExistsException - {@link AlreadyExistsException} se lanza
	 *                                cuando se ha encontrado un {@link Comercial}
	 *                                con el mismo 'id' en la base de datos.
	 */
	ComercialBo create(ComercialBo comercial) throws AlreadyExistsException;

	/**
	 * Metodo que elimina un {@link ComercialBo} de la base de datos dadao su 'id'.
	 * 
	 * @param id - {@link Long} con el 'id' del {@link ComercialBo}.
	 * @throws NotFoundException - {@link NotFoundException} se lanza cuando no se
	 *                           ha encontrado un {@link ComercialBo} con el 'id'
	 *                           introducido en la base de datos.
	 */
	void deleteById(Long id) throws NotFoundException;

	/**
	 * Método para actualizar un comercial en la base de datos.
	 * 
	 * @param comercialBo {@link ComercialBo} a actualizar en la base de datos.
	 * @return {@link ComercialBo} actualizado.
	 * @throws NotFoundException - {@link NotFoundException} se lanza cuando no se
	 *                           ha encontrado un {@link ComercialBo} con el 'id'
	 *                           introducido en la base de datos.
	 */
	ComercialBo update(ComercialBo comercialBo) throws NotFoundException;

}
