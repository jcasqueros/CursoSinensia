package com.viewnext.jpasiraku.converters;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.presentation.dto.ClienteDto;
import com.viewnext.jpasiraku.presentation.dto.ClienteDtoAux;
import com.viewnext.jpasiraku.presentation.dto.ComercialDto;
import com.viewnext.jpasiraku.presentation.dto.ComercialDtoAux;
import com.viewnext.jpasiraku.presentation.dto.ProductoDto;
import com.viewnext.jpasiraku.presentation.dto.ProductoDtoAux;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Converters de {@link ModelMapper} de objetos 'Bo' a objetos 'Dto'.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Component
public class BoToDto {

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Configuración adicional del {@link ModelMapper}. Se usa para añadir mapeos
	 * personalizados.
	 */
	private void configureMapper() {
		modelMapper.addConverter(new AbstractConverter<ComercialBo, ComercialDtoAux>() {
			@Override
			protected ComercialDtoAux convert(ComercialBo comercialBo) {
				String nombreCompleto = comercialBo.getNombre().charAt(0) + ". " + comercialBo.getApellido1() + " "
						+ comercialBo.getApellido2();
				return new ComercialDtoAux(comercialBo.getCodigo(), nombreCompleto);
			}
		});

		modelMapper.addConverter(new AbstractConverter<ClienteBo, ClienteDtoAux>() {
			@Override
			protected ClienteDtoAux convert(ClienteBo clienteBo) {
				String nombreCompleto = clienteBo.getNombre().charAt(0) + ". " + clienteBo.getApellido1() + " "
						+ clienteBo.getApellido2();
				List<ProductoDto> list = clienteBo.getProductos().stream().map(producto -> boToProductoDto(producto))
						.toList();
				return new ClienteDtoAux(clienteBo.getIdentificadorFiscal(), clienteBo.getNombreComercial(),
						nombreCompleto, clienteBo.getDireccion(), clienteBo.getDatosContacto(), list,
						clienteBo.getComercial());
			}
		});

		modelMapper.addConverter(new AbstractConverter<ProductoBo, ProductoDtoAux>() {
			@Override
			protected ProductoDtoAux convert(ProductoBo productoBo) {
				String nombreCompleto = productoBo.getNombre() + " - " + productoBo.getFamilia();
				return new ProductoDtoAux(productoBo.getCodigo(), nombreCompleto, productoBo.getPrecio(),
						productoBo.getFechaAlta(), productoBo.isDescatalogado());
			}
		});
	}

	/**
	 * Método para convertir {@link ClienteBo} a {@link ClienteDto}.
	 * 
	 * @param clienteBo
	 * @return
	 */
	public ClienteDto boToClienteDto(ClienteBo clienteBo) {
		return modelMapper.map(clienteBo, ClienteDto.class);
	}

	/**
	 * Método para convertir {@link ComercialBo} a {@link ComercialDto}.
	 * 
	 * @param comercialBo
	 * @return
	 */
	public ComercialDto boToComercialDto(ComercialBo comercialBo) {
		return modelMapper.map(comercialBo, ComercialDto.class);
	}

	/**
	 * Método para convertir {@link ProductoBo} a {@link ProductoDto}.
	 * 
	 * @param productoBo
	 * @return
	 */
	public ProductoDto boToProductoDto(ProductoBo productoBo) {
		return modelMapper.map(productoBo, ProductoDto.class);
	}

	/**
	 * Método para convertir {@link ComercialBo} a {@link ComercialDtoAux}.
	 * <p>
	 * {@link ComercialDtoAux} tiene el atributo 'nombreCompleto' que se mapea con
	 * la primera letra del nombre seguido de un punto y los dos apellidos.
	 * <p>
	 * Ejemplo: Mario Sanchez Pilar -> M. Sanchez Pilar
	 * 
	 * @param comercialBo
	 * @return
	 */
	public ComercialDtoAux boToComercialAux(ComercialBo comercialBo) {
		configureMapper();
		return modelMapper.map(comercialBo, ComercialDtoAux.class);
	}

	/**
	 * Método para convertir {@link ClienteBo} a {@link ClienteDtoAux}.
	 * <p>
	 * {@link ClienteDtoAux} tiene el atributo 'nombreCompleto' que se mapea con la
	 * primera letra del nombre seguido de un punto y los dos apellidos.
	 * <p>
	 * Ejemplo: Mario Sanchez Pilar -> M. Sanchez Pilar
	 * 
	 * @param clienteBo
	 * @return
	 */
	public ClienteDtoAux boToClienteAux(ClienteBo clienteBo) {
		configureMapper();
		return modelMapper.map(clienteBo, ClienteDtoAux.class);
	}

	/**
	 * Método para convertir {@link ProductoBo} a {@link ProductoDtoAux}.
	 * <p>
	 * {@link ProductoDtoAux} tiene el atributo 'familiaNombre' que se mapea con el
	 * nombre seguido de un guión y la {@link Familia}.
	 * <p>
	 * Ejemplo:
	 * <p>
	 * Nombre: Producto | Familia: Hardware -> Producto - Hardware
	 * 
	 * @param productoBo
	 * @return
	 */
	public ProductoDtoAux boToProductoAux(ProductoBo productoBo) {
		configureMapper();
		return modelMapper.map(productoBo, ProductoDtoAux.class);
	}

}
