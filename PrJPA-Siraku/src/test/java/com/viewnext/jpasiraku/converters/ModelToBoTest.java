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
import com.viewnext.jpasiraku.data.model.Cliente;
import com.viewnext.jpasiraku.data.model.Comercial;
import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Test para los conversores de {@link ModelToBo}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ModelToBoTest {

	@Autowired
	ModelToBo modelToBo;

	private Comercial comercial;
	private ComercialBo comercialBo;

	private Cliente cliente;
	private ClienteBo clienteBo;

	private Producto producto;
	private ProductoBo productoBo;

	@BeforeEach
	public void setup() {
		comercialBo = ComercialBo.builder().codigo(1L).nombre("Mario").apellido1("Sanchez").apellido2("Pilar").build();
		comercial = Comercial.builder().codigo(1L).nombre("Mario").apellido1("Sanchez").apellido2("Pilar").build();

		clienteBo = ClienteBo.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").build();
		cliente = Cliente.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").build();

		productoBo = ProductoBo.builder().codigo(1L).nombre("Nombre").precio(10).fechaAlta(new Date(1L))
				.familia(Familia.SOFTWARE).descatalogado(false).build();
		producto = Producto.builder().codigo(1L).nombre("Nombre").precio(10).fechaAlta(new Date(1L))
				.familia(Familia.SOFTWARE).descatalogado(false).build();
	}

	@DisplayName("Comercial -> ComercialBo")
	@Test
	@Order(1)
	public void givenComercial_whenConvert_thenReturnComercialBo() {
		assertEquals(comercialBo, modelToBo.comercialToBo(comercial));
	}

	@DisplayName("Cliente -> ClienteBo")
	@Test
	@Order(2)
	public void givenCliente_whenConvert_thenReturnClienteBo() {
		assertEquals(clienteBo, modelToBo.clienteToBo(cliente));
	}

	@DisplayName("Producto -> ProductoBo")
	@Test
	@Order(1)
	public void givenProducto_whenConvert_thenReturnProductoBo() {
		assertEquals(productoBo, modelToBo.productoToBo(producto));
	}

}
