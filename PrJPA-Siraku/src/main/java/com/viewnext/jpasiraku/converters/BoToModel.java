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
 * Converters de {@link ModelMapper} de objetos 'Bo' a objetos 'model'.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Component
public class BoToModel {

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Método para convertir {@link ClienteBo} a {@link Cliente}.
	 * 
	 * @param clienteBO
	 * @return
	 */
	public Cliente boToCliente(ClienteBo clienteBO) {
		return modelMapper.map(clienteBO, Cliente.class);
	}

	/**
	 * Método para convertir {@link ProductoBo} a {@link Producto}.
	 * 
	 * @param productoBO
	 * @return
	 */
	public Producto boToProducto(ProductoBo productoBO) {
		return modelMapper.map(productoBO, Producto.class);
	}

	/**
	 * Método para convertir {@link ComercialBo} a {@link Comercial}.
	 * 
	 * @param comercialBO
	 * @return
	 */
	public Comercial boToComercial(ComercialBo comercialBO) {
		return modelMapper.map(comercialBO, Comercial.class);
	}

}
