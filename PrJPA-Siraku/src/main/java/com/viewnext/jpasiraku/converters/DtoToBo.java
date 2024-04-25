package com.viewnext.jpasiraku.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.presentation.dto.ClienteDto;
import com.viewnext.jpasiraku.presentation.dto.ComercialDto;
import com.viewnext.jpasiraku.presentation.dto.ProductoDto;

/**
 * Converters de {@link ModelMapper} de objetos 'Dto' a objetos 'Bo'.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Component
public class DtoToBo {

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Método para convertir {@link ClienteDto} a {@link ClienteBo}.
	 * 
	 * @param clienteDto
	 * @return
	 */
	public ClienteBo dtoToClienteBo(ClienteDto clienteDto) {
		return modelMapper.map(clienteDto, ClienteBo.class);
	}

	/**
	 * Método para convertir {@link ComercialDto} a {@link ComercialBo}.
	 * 
	 * @param comercialDto
	 * @return
	 */
	public ComercialBo dtoToComercialBo(ComercialDto comercialDto) {
		return modelMapper.map(comercialDto, ComercialBo.class);
	}

	/**
	 * Método para convertir {@link ProductoDto} a {@link ProductoBo}.
	 * 
	 * @param productoDto
	 * @return
	 */
	public ProductoBo dtoToProductoBo(ProductoDto productoDto) {
		return modelMapper.map(productoDto, ProductoBo.class);
	}

}
