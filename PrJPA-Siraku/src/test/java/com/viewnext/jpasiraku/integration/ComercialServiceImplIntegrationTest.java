package com.viewnext.jpasiraku.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.impl.ComercialServiceImpl;
import com.viewnext.jpasiraku.data.model.Comercial;

/**
 * Test de integración de {@link Comercial}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "/h2/schema.sql", "/h2/data.sql" })
class ComercialServiceImplIntegrationTest {

	@Autowired
	ComercialServiceImpl comercialServiceImpl;

	@DisplayName("Test .getAll")
	@Test
	@Order(1)
	void givenNothing_whenGetAll_thenReturnComercialBoList() {
		ComercialBo comercial1 = new ComercialBo(1L, "Mario", "Sanchez", "Pilar");
		ComercialBo comercial2 = new ComercialBo(2L, "Manuel", "Mateos", "de Torres");
		ComercialBo comercial3 = new ComercialBo(3L, "Francisco", "Balonero", "Olivera");

		List<ComercialBo> list = comercialServiceImpl.getAll();

		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(3);
		assertTrue(list.contains(comercial1));
		assertTrue(list.containsAll(List.of(comercial1, comercial2, comercial3)));
	}

	@DisplayName("Test .getById - Correct")
	@Test
	@Order(2)
	void givenComercialId_whenGetById_thenReturnComercialBo() throws NotFoundException {
		ComercialBo comercial1 = new ComercialBo(1L, "Mario", "Sanchez", "Pilar");

		ComercialBo comercialGet = comercialServiceImpl.getById(comercial1.getCodigo());

		assertNotNull(comercialGet);
		assertTrue(comercialGet.equals(comercial1));
	}

	@DisplayName("Test .getById - Incorrect")
	@Test
	@Order(3)
	void givenComercialId_whenGetById_thenThrowNotFoundException() throws NotFoundException {
		assertThrows(NotFoundException.class, () -> comercialServiceImpl.getById(5L));
	}

	@DisplayName("Test .getNumeroTotalComerciales")
	@Test
	@Order(4)
	void givenNothing_whenGetNumeroTotalComerciales_thenReturnNumeroComerciales() {
		assertEquals(3, comercialServiceImpl.getNumeroTotalComerciales().longValue());
	}

	@DisplayName("Test .create - Correct")
	@Test
	@Order(5)
	void givenComercialBo_whenCreate_thenReturnSavedComercialBo() throws AlreadyExistsException {
		Long total = comercialServiceImpl.getNumeroTotalComerciales();

		ComercialBo comercialBo = new ComercialBo(4L, "Mario", "Sanchez", "Pilar");
		ComercialBo savedComercial = comercialServiceImpl.create(comercialBo);

		assertEquals(total + 1, comercialServiceImpl.getNumeroTotalComerciales().longValue());
		assertEquals(4L, savedComercial.getCodigo().longValue());
		assertEquals("Mario", savedComercial.getNombre());
		assertEquals("Sanchez", savedComercial.getApellido1());
		assertEquals("Pilar", savedComercial.getApellido2());
	}

	@DisplayName("Test .create - Incorrect")
	@Test
	@Order(6)
	void givenComercialBo_whenCreate_thenThrowAlreadyExistException() {
		assertThrows(AlreadyExistsException.class,
				() -> comercialServiceImpl.create(new ComercialBo(1L, "Mario", "Sanchez", "Pilar")));
	}

	@DisplayName("Test .deleteById - Correct")
	@Test
	@Order(7)
	void givenComercialId_whenDeleteById_thenRemoveComercial() throws NotFoundException {
		Long total = comercialServiceImpl.getNumeroTotalComerciales();

		comercialServiceImpl.deleteById(3L);

		assertEquals(total - 1, comercialServiceImpl.getNumeroTotalComerciales().longValue());
	}

	@DisplayName("Test .deleteById - Incorrect")
	@Test
	@Order(8)
	void givenComercialId_whenDeleteById_thenThrowNotFoundException() {
		assertThrows(NotFoundException.class, () -> comercialServiceImpl.deleteById(5L));
	}

	@DisplayName("Test .update - Correct")
	@Test
	@Order(9)
	void givenComercialBo_whenUpdate_thenReturnUpdatedComercialBo() throws NotFoundException {
		ComercialBo comercialBo = new ComercialBo(1L, "Mario", "Pilar", "Sanchez");
		ComercialBo updatedComercial = comercialServiceImpl.update(comercialBo);

		assertEquals(comercialBo.getCodigo(), updatedComercial.getCodigo());
		assertEquals(comercialBo.getNombre(), updatedComercial.getNombre());
		assertEquals(comercialBo.getApellido1(), updatedComercial.getApellido1());
		assertEquals(comercialBo.getApellido2(), updatedComercial.getApellido2());
	}

	@DisplayName("Test .update - Incorrect")
	@Test
	@Order(10)
	void givenComercialBo_whenUpdate_thenThrowNotFoundException() {
		assertThrows(NotFoundException.class,
				() -> comercialServiceImpl.update(new ComercialBo(100L, "Mario", "Pilar", "Sanchez")));
	}

}
