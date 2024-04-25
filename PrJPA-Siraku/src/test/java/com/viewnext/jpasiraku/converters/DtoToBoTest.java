package com.viewnext.jpasiraku.converters;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

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
import com.viewnext.jpasiraku.presentation.dto.ComercialDto;
import com.viewnext.jpasiraku.presentation.dto.ProductoDto;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Test de los conversores de {@link DtoToBo}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DtoToBoTest {

	@Autowired
	DtoToBo dtoToBo;

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

		clienteBo = ClienteBo.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").build();
		clienteDto = ClienteDto.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").build();

		productoBo = ProductoBo.builder().codigo(1L).nombre("Nombre").precio(10).fechaAlta(new Date(1L))
				.familia(Familia.SOFTWARE).descatalogado(false).build();
		productoDto = ProductoDto.builder().codigo(1L).nombre("Nombre").precio(10).fechaAlta(new Date(1L))
				.familia(Familia.SOFTWARE).descatalogado(false).build();
	}

	@DisplayName("ComercialDto -> ComercialBo")
	@Test
	@Order(1)
	public void givenComercialDto_whenConvert_thenReturnComercialBo() {
		assertEquals(comercialBo, dtoToBo.dtoToComercialBo(comercialDto));
	}

	@DisplayName("ClienteDto -> ClienteBo")
	@Test
	@Order(2)
	public void givenClienteDto_whenConvert_thenReturnClienteBo() {
		assertEquals(clienteBo, dtoToBo.dtoToClienteBo(clienteDto));
	}

	@DisplayName("ProductoDto -> ProductoBo")
	@Test
	@Order(3)
	public void givenProductoDto_whenConvert_thenReturnProductoBo() {
		assertEquals(productoBo, dtoToBo.dtoToProductoBo(productoDto));
	}

}
