package com.viewnext.jpasiraku.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
 * Test de los conversores de {@link BoToDto}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BoToDtoTest {

	@Autowired
	BoToDto boToDto;

	private ComercialBo comercialBo;
	private ComercialDto comercialDto;

	private ClienteBo clienteBo;
	private ClienteDto clienteDto;

	private ProductoBo productoBo;
	private ProductoDto productoDto;

	@BeforeEach
	public void setup() {
		comercialBo = ComercialBo.builder().codigo(1L).nombre("Mario").apellido1("Sanchez").apellido2("Pilar").build();
		comercialDto = ComercialDto.builder().codigo(1L).nombre("Mario").apellido1("Sanchez").apellido2("Pilar")
				.build();

		productoBo = ProductoBo.builder().codigo(1L).nombre("Nombre").precio(10).fechaAlta(new Date(1L))
				.familia(Familia.SOFTWARE).descatalogado(false).build();
		productoDto = ProductoDto.builder().codigo(1L).nombre("Nombre").precio(10).fechaAlta(new Date(1L))
				.familia(Familia.SOFTWARE).descatalogado(false).build();

		clienteBo = ClienteBo.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").productos(List.of(productoBo)).build();
		clienteDto = ClienteDto.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").productos(List.of(productoDto)).build();
	}

	@DisplayName("ComercialBo -> ComercialDto")
	@Test
	@Order(1)
	public void givenComercialBo_whenConvert_thenReturnComercialDto() {
		assertEquals(comercialDto, boToDto.boToComercialDto(comercialBo));
	}

	@DisplayName("ComercialBo -> ComercialDtoAux")
	@Test
	@Order(2)
	public void givenComercialBo_whenConvert_thenReturnComercialDtoAux() {
		ComercialDtoAux test = boToDto.boToComercialAux(comercialBo);

		String nombreCompleto = comercialBo.getNombre().charAt(0) + ". " + comercialBo.getApellido1() + " "
				+ comercialBo.getApellido2();

		assertEquals(comercialBo.getCodigo(), test.getCodigo());
		assertEquals(nombreCompleto, test.getNombreCompleto());
	}

	@DisplayName("ClienteBo -> ClienteDto")
	@Test
	@Order(3)
	public void givenClienteBo_whenConvert_thenReturnClienteDto() {
		assertEquals(clienteDto, boToDto.boToClienteDto(clienteBo));
	}

	@DisplayName("ClienteBo -> ClienteDtoAux")
	@Test
	@Order(4)
	public void givenClienteBo_whenConvert_thenReturnClienteDtoAux() {
		ClienteDtoAux test = boToDto.boToClienteAux(clienteBo);

		String nombreCompleto = clienteBo.getNombre().charAt(0) + ". " + clienteBo.getApellido1() + " "
				+ clienteBo.getApellido2();

		assertEquals(clienteBo.getIdentificadorFiscal(), test.getIdentificadorFiscal());
		assertEquals(nombreCompleto, test.getNombreCompleto());
		assertEquals(clienteBo.getDireccion(), test.getDireccion());
		assertEquals(clienteBo.getDatosContacto(), test.getDatosContacto());
		assertEquals(clienteDto.getProductos(), test.getProductos());
		assertEquals(clienteBo.getComercial(), test.getComercial());
		assertThat(test.getProductos()).isNotNull();
	}

	@DisplayName("ProductoBo -> ProductoDto")
	@Test
	@Order(5)
	public void givenProductoBo_whenConvert_thenReturnProductoDto() {
		assertEquals(productoDto, boToDto.boToProductoDto(productoBo));
	}

	@DisplayName("ProductoBo -> ProductoDtoAux")
	@Test
	@Order(6)
	public void givenProductoBo_whenConvert_thenReturnProductoDtoAux() {
		ProductoDtoAux test = boToDto.boToProductoAux(productoBo);

		String familiaNombre = productoBo.getNombre() + " - " + productoBo.getFamilia();

		assertEquals(productoBo.getCodigo(), test.getCodigo());
		assertEquals(familiaNombre, test.getFamiliaNombre());
		assertEquals(productoBo.getPrecio(), test.getPrecio(), 1);
		assertEquals(productoBo.getFechaAlta(), test.getFechaAlta());
		assertEquals(productoBo.isDescatalogado(), test.isDescatalogado());
	}
}
