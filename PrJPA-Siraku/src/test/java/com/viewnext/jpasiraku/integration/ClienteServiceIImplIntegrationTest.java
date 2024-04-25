package com.viewnext.jpasiraku.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.impl.ClienteServiceImpl;
import com.viewnext.jpasiraku.data.model.Cliente;
import com.viewnext.jpasiraku.util.DatosContacto;
import com.viewnext.jpasiraku.util.Direccion;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Test de integración para {@link Cliente}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "/h2/schema.sql", "/h2/data.sql" })
class ClienteServiceIImplIntegrationTest {

	@Autowired
	ClienteServiceImpl clienteServiceImpl;

	@DisplayName("Test .getAll")
	@Test
	@Order(1)
	public void givenNothing_whenGetAll_thenReturnClienteBoList() {
		List<ClienteBo> list = clienteServiceImpl.getAll();

		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@SuppressWarnings("deprecation")
	@DisplayName("Test .getById - Correct")
	@Test
	@Order(2)
	public void givenClienteId_whenGetById_thenReturnClienteBo() throws NotFoundException {
		Date date = new Date(124, 0, 01);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);
		ComercialBo comercialBo = new ComercialBo(1L, "Mario", "Sanchez", "Pilar");
		ClienteBo clienteBo = new ClienteBo("1", "NombreC", "Nombre", "Apellido1", "Apellido2",
				new Direccion("Direccion", "Poblacion", "CPostal", "Provincia", "Pais"),
				new DatosContacto("626053637", "626053638", "mario@email.com"), List.of(productoBo), comercialBo);

		ClienteBo clienteGet = clienteServiceImpl.getById(clienteBo.getIdentificadorFiscal());

		assertNotNull(clienteGet);
		assertThat(clienteGet).isEqualTo(clienteBo);
	}

	@DisplayName("Test .getById - Inorrect")
	@Test
	@Order(3)
	public void givenClienteId_whenGetById_thenThrowNotFoundException() {
		assertThrows(NotFoundException.class, () -> clienteServiceImpl.getById("3"));
	}

	@SuppressWarnings("deprecation")
	@DisplayName("Test .create - Correct")
	@Test
	@Order(4)
	public void givenClienteBo_whenCreate_thenReturnSavedClienteBo() throws AlreadyExistsException {
		int total = clienteServiceImpl.getAll().size();

		Date date = new Date(124, 0, 01);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);
		ComercialBo comercialBo = new ComercialBo(1L, "Mario", "Sanchez", "Pilar");
		ClienteBo clienteBo = new ClienteBo("2", "NombreC", "Nombre", "Apellido1", "Apellido2",
				new Direccion("Direccion", "Poblacion", "CPostal", "Provincia", "Pais"),
				new DatosContacto("626053637", "626053638", "mario@email.com"), List.of(productoBo), comercialBo);

		ClienteBo savedCliente = clienteServiceImpl.create(clienteBo);

		assertEquals(total + 1, clienteServiceImpl.getAll().size());
		assertEquals(clienteBo, savedCliente);
	}

	@SuppressWarnings("deprecation")
	@DisplayName("Test .create - Inorrect")
	@Test
	@Order(5)
	public void givenClienteBo_whenCreate_thenThrowAlreadyExistsException() {
		Date date = new Date(124, 0, 01);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);
		ComercialBo comercialBo = new ComercialBo(1L, "Mario", "Sanchez", "Pilar");
		ClienteBo clienteBo = new ClienteBo("1", "NombreC", "Nombre", "Apellido1", "Apellido2",
				new Direccion("Direccion", "Poblacion", "CPostal", "Provincia", "Pais"),
				new DatosContacto("626053637", "626053638", "mario@email.com"), List.of(productoBo), comercialBo);

		assertThrows(AlreadyExistsException.class, () -> clienteServiceImpl.create(clienteBo));
	}

	@DisplayName("Test .deleteById - Correct")
	@Test
	@Order(6)
	public void givenClienteId_whenDeleteById_thenRemoveCliente() throws NotFoundException {
		int total = clienteServiceImpl.getAll().size();

		clienteServiceImpl.deleteById("1");

		assertEquals(total - 1, clienteServiceImpl.getAll().size());
	}

	@DisplayName("Test .deleteById - Inorrect")
	@Test
	@Order(7)
	public void givenClienteId_whenDeleteById_thenThrowNotFoundException() {
		assertThrows(NotFoundException.class, () -> clienteServiceImpl.deleteById("21"));
	}

}
