package com.viewnext.jpasiraku.business.services;

import java.util.Date;
import java.util.List;

import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Interfaz {@link ProductoService} que provee métodos para el manejo de objetos
 * {@link Producto}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public interface ProductoService {

	/**
	 * Método que lee un {@link Producto} de la base de datos dado su 'id'.
	 * 
	 * @param id- {@link Long} con el 'id' del {@link Producto}.
	 * @return {@link Producto} obtenido de la base de datos.
	 * @throws NotFoundException - {@link NotFoundException} se lanza cuando no se
	 *                           ha encontrado un {@link Producto} con el 'id'
	 *                           introducido en la base de datos.
	 */
	ProductoBo getById(Long id) throws NotFoundException;

	/**
	 * Método que devuelve todos los {@link Producto} que se encuentren el la base
	 * de datos.
	 * 
	 * @return {@link List} de {@link Producto}.
	 */
	List<ProductoBo> getAll();

	/**
	 * Método que devuelte todos los {@link Producto} de una determinada
	 * {@link Familia} que se encuentren en la base de datos.
	 * 
	 * @param familia - {@link Familia} de los {@link Producto} a buscar.
	 * @return {@link List} de {@link Producto}.
	 */
	List<ProductoBo> getByFamilia(Familia familia);

	/**
	 * Método que devuelve todos los {@link Producto} de un determinado rango de
	 * precios que se encuentren en la base de datos.
	 * 
	 * @param min - Precio mínimo del intervalo a buscar.
	 * @param max - Precio máximo del intervalo a buscar.
	 * @return {@link List} de {@link Producto}.
	 */
	List<ProductoBo> getBetweenPriceRange(double min, double max);

	/**
	 * Método que devuelve todos los {@link Producto} de un determinado rango de
	 * {@link Date} que se encuentren en la base de datos.
	 * 
	 * @param desde - {@link Date} mínima del intervalo a buscar.
	 * @param hasta - {@link Date} máxima del intervalo a buscar.
	 * @return {@link List} de {@link Producto}.
	 */
	List<ProductoBo> getBetweenDates(Date desde, Date hasta);

	/**
	 * Método que devuelve la cantidad total de productos existentes en la base de
	 * datos.
	 * 
	 * @return {@link Long} con el número total de {@link Producto} en la base de
	 *         datos.
	 */
	Long getTotal();

	/**
	 * Método que persiste un {@link Producto} en la base de datos.
	 * 
	 * @param producto - {@link Producto} a persistir en la base de datos.
	 * @return {@link Producto} resultante.
	 * @throws AlreadyExistsException - {@link AlreadyExistsException} se lanza
	 *                                cuando se ha encontrado un {@link Producto}
	 *                                con el mismo 'id' en la base de datos.
	 */
	ProductoBo create(ProductoBo producto) throws AlreadyExistsException;

}
