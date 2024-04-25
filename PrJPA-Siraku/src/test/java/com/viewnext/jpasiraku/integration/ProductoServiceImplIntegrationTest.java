package com.viewnext.jpasiraku.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.impl.ProductoServiceImpl;
import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Test de integración para los {@link Producto}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@SuppressWarnings("deprecation")
class ProductoServiceImplIntegrationTest {

	@Autowired
	ProductoServiceImpl productoServiceImpl;

	@DisplayName("Test .getAll")
	@Test
	@Order(1)
	public void givenNothing_whenGetAll_thenReturnProductoBoList() {
		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);

		List<ProductoBo> list = productoServiceImpl.getAll();

		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
		assertTrue(list.contains(productoBo));
	}

	@DisplayName("Test .getById - Correct")
	@Test
	@Order(2)
	public void givenProductoId_whenGetById_thenReturnProductoBo() throws NotFoundException {
		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);

		ProductoBo productoGet = productoServiceImpl.getById(1L);

		assertNotNull(productoGet);
		assertThat(productoGet).isEqualTo(productoBo);
	}

	@DisplayName("Test .getById - Incorrect")
	@Test
	@Order(3)
	public void givenProductoId_whenGetById_thenThrowNotFoundException() {
		assertThrows(NotFoundException.class, () -> productoServiceImpl.getById(2L));
	}

	@DisplayName("Test .getByFamilia")
	@Test
	@Order(4)
	public void givenFamilia_whenGetByFamilia_thenReturnProductoBoList() {
		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);

		List<ProductoBo> list = productoServiceImpl.getByFamilia(Familia.SOFTWARE);

		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
		assertTrue(list.contains(productoBo));
	}

	@DisplayName("Test .getBetweenPriceRange")
	@Test
	@Order(5)
	public void givenPriceRange_whenGetByPriceRange_thenReturnProductoBoList() {
		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);

		List<ProductoBo> list = productoServiceImpl.getBetweenPriceRange(0, 2);

		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
		assertTrue(list.contains(productoBo));
	}

	@DisplayName("Test .getBetweenDates")
	@Test
	@Order(6)
	public void givenDateRange_whenGetBetweenDates_thenReturnProductoBoList() {
		Date desde = new Date(123, 0, 1);
		Date hasta = new Date(125, 0, 1);

		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);

		List<ProductoBo> list = productoServiceImpl.getBetweenDates(desde, hasta);

		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
		assertTrue(list.contains(productoBo));
	}

	@DisplayName("Test .getTotal")
	@Test
	@Order(7)
	public void givenNothing_whenGetTotal_thenReturnTotalProductoBo() {
		assertEquals(1L, productoServiceImpl.getTotal().longValue());
	}

	@DisplayName("Test .create - Correct")
	@Test
	@Order(8)
	public void givenProductoBo_whenCreate_thenReturnSavedProductoBo() throws AlreadyExistsException {
		Long total = productoServiceImpl.getTotal();

		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(2L, "Producto", 1, date, Familia.SOFTWARE, false);

		ProductoBo savedProducto = productoServiceImpl.create(productoBo);

		assertEquals(total + 1, productoServiceImpl.getTotal().longValue());
		assertEquals(productoBo, savedProducto);
	}

	@DisplayName("Test .create - Incorrect")
	@Test
	@Order(9)
	public void givenProductoBo_whenCreate_throwAlreadyExistsException() {
		Date date = new Date(124, 0, 1);
		ProductoBo productoBo = new ProductoBo(1L, "Producto", 1, date, Familia.SOFTWARE, false);

		assertThrows(AlreadyExistsException.class, () -> productoServiceImpl.create(productoBo));
	}

}
