package com.viewnext.jpasiraku.business.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.converters.BoToModel;
import com.viewnext.jpasiraku.converters.ModelToBo;
import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.data.repository.ProductoRepository;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Test para {@link ProductoServiceImpl}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class ProductoServiceTest {

	@Mock
	ProductoRepository productoRepository;

	@Mock
	BoToModel boToModel;

	@Mock
	ModelToBo modelToBo;

	@InjectMocks
	ProductoServiceImpl productoServiceImpl;

	private Producto producto;
	private ProductoBo productoBo;

	@BeforeEach
	public void setup() {
		producto = Producto.builder().codigo(1L).nombre("Producto").precio(10).familia(Familia.SOFTWARE)
				.descatalogado(false).build();
		productoBo = ProductoBo.builder().codigo(1L).nombre("Producto").precio(10).familia(Familia.SOFTWARE)
				.descatalogado(false).build();
	}

	@DisplayName("Test .getById - Correct")
	@Test
	@Order(1)
	public void givenProductoId_whenGetById_thenReturnProductoBo() throws NotFoundException {
		when(modelToBo.productoToBo(producto)).thenReturn(productoBo);
		when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
		
		ProductoBo productoGet = productoServiceImpl.getById(1L);
		
		assertThat(productoGet).isNotNull();
		assertEquals(1L, productoGet.getCodigo().longValue());
		assertEquals("Producto", productoGet.getNombre());
		assertEquals(Familia.SOFTWARE, productoGet.getFamilia());
		assertEquals(false, productoGet.isDescatalogado());
	}

	@DisplayName("Test .getById - Incorrect")
	@Test
	@Order(2)
	public void givenProductoId_whenGetById_thenThrowNotFoundException() {
		when(productoRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> productoServiceImpl.getById(1L));
	}

	@DisplayName("Test .getAll")
	@Test
	@Order(3)
	public void givenNothing_whenGetAll_thenReturnProductoBoList() {
		when(modelToBo.productoToBo(producto)).thenReturn(productoBo);
		when(productoRepository.findAll()).thenReturn(List.of(producto));
		
		List<ProductoBo> list = productoServiceImpl.getAll();
		
		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@DisplayName("Test .getByFamilia")
	@Test
	@Order(4)
	public void givenFamilia_whenGetByFamilia_thenReturnProductoBoList() {
		when(modelToBo.productoToBo(producto)).thenReturn(productoBo);
		when(productoRepository.findByFamilia(Familia.SOFTWARE)).thenReturn(List.of(producto));
		
		List<ProductoBo> list = productoServiceImpl.getByFamilia(Familia.SOFTWARE);
		
		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@DisplayName("Test .getBetweenPriceRange")
	@Test
	@Order(4)
	public void givenFamilia_whenGetBetweenPriceRange_thenReturnProductoBoList() {
		when(modelToBo.productoToBo(producto)).thenReturn(productoBo);
		when(productoRepository.findByPrecioBetween(5, 15)).thenReturn(List.of(producto));
		
		List<ProductoBo> list = productoServiceImpl.getBetweenPriceRange(5, 15);
		
		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@DisplayName("Test .getBetweenDates")
	@Test
	@Order(5)
	public void givenFamilia_whenGetBetweenDates_thenReturnProductoBoList() {
		when(modelToBo.productoToBo(producto)).thenReturn(productoBo);
		when(productoRepository.findByFechaAltaBetween(new Date(1L), new Date(2L))).thenReturn(List.of(producto));
		
		List<ProductoBo> list = productoServiceImpl.getBetweenDates(new Date(1L), new Date(2L));
		
		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@DisplayName("Test .getTotal")
	@Test
	@Order(6)
	public void givenNothing_whenGetTotal_thenReturnProductoCount() {
		when(productoRepository.count()).thenReturn(1L);
		
		Long total = productoServiceImpl.getTotal();
		
		assertEquals(1L, total.longValue());
	}

	@DisplayName("Test .create - Correct")
	@Test
	@Order(7)
	public void givenProductoBo_whenCreate_thenReturnSavedProductoBo() throws AlreadyExistsException {
		when(productoRepository.existsById(productoBo.getCodigo())).thenReturn(false);
		when(modelToBo.productoToBo(producto)).thenReturn(productoBo);
		when(boToModel.boToProducto(productoBo)).thenReturn(producto);
		when(productoRepository.save(producto)).thenReturn(producto);
		
		ProductoBo productoSave = productoServiceImpl.create(productoBo);
		
		assertThat(productoSave).isNotNull();
		assertEquals(productoSave, productoBo);
	}

	@DisplayName("Test .create - Incorrect")
	@Test
	@Order(8)
	public void givenProductoBo_whenCreate_thenThrowAlreadyExistsException() {
		when(productoRepository.existsById(productoBo.getCodigo())).thenReturn(true);
		
		assertThrows(AlreadyExistsException.class, () -> productoServiceImpl.create(productoBo));
	}
}
