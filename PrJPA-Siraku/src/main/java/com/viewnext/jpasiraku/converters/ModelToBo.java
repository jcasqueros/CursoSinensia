package com.viewnext.jpasiraku.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.data.model.Cliente;
import com.viewnext.jpasiraku.data.model.Comercial;
import com.viewnext.jpasiraku.data.model.Producto;

/**
 * Converters de {@link ModelMapper} de objetos 'model' a objetos 'Bo'.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Component
public class ModelToBo {

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Método para convertir {@link Cliente} a {@link ClienteBo}.
	 * 
	 * @param cliente
	 * @return
	 */
	public ClienteBo clienteToBo(Cliente cliente) {
		return modelMapper.map(cliente, ClienteBo.class);
	}

	/**
	 * Método para convertir {@link Producto} a {@link ProductoBo}.
	 * 
	 * @param producto
	 * @return
	 */
	public ProductoBo productoToBo(Producto producto) {
		return modelMapper.map(producto, ProductoBo.class);
	}

	/**
	 * Método para convertir {@link Comercial} a {@link ComercialBo}.
	 * 
	 * @param comercial
	 * @return
	 */
	public ComercialBo comercialToBo(Comercial comercial) {
		return modelMapper.map(comercial, ComercialBo.class);
	}

}
